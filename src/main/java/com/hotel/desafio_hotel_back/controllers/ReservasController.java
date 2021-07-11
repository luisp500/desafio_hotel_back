package com.hotel.desafio_hotel_back.controllers;


import com.hotel.desafio_hotel_back.model.Hospedes;
import com.hotel.desafio_hotel_back.model.Reservas;
import com.hotel.desafio_hotel_back.repository.HospedeRepository;
import com.hotel.desafio_hotel_back.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;
import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
public class ReservasController {

	@Autowired
	private HospedeRepository hospedesRepository;
	
	@Autowired
	private ReservaRepository reservasRepository;

	private static Double DIARIA_DURANTE_SEMANA = 120D;
	private static Double DIARIA_FINAL_SEMANA = 150D;
	private static Double CARRO_DURANTE_SEMANA = 15D;
	private static Double CARRO_FINAL_SEMANA = 20D;
	
	@PostMapping("/reservas/checkin")
	public Reservas createReserva(@RequestBody Reservas reserva) {
		Hospedes hospede = hospedesRepository.findOneByNomeIgnoreCaseAndDocumentoAndTelefone(reserva.getHospede().getNome(), reserva.getHospede().getDocumento(), reserva.getHospede().getTelefone());
		if(hospede == null) {
			hospede = hospedesRepository.save(reserva.getHospede());
		}
		reserva.setHospede(hospede);
		if(reserva.getDataSaida() != null) {
			reserva.setValor(this.calcularValor(reserva.getDataEntrada(), reserva.getDataSaida(), reserva.getAdicionalVeiculo() == null ? false : reserva.getAdicionalVeiculo()));
		}
		reserva = reservasRepository.save(reserva);
		return reserva;
	}
	
	@GetMapping("/reservas/abertas")
	public Page<Reservas> reservasAbertas(Pageable pageable){
		return reservasRepository.findByDataSaidaAfter(new Date(), pageable);
	}
	
	@GetMapping("/reservas/fechadas")
	public Page<Reservas> reservasFechadas(Pageable pageable){
		return  reservasRepository.findByDataSaidaBefore(new Date(), pageable);
	}
	
	private double calcularValor(Date dataEntrada, Date dataSaida, boolean adicionalVeiculo) {
		Double valor = 0D;
		Date current = dataEntrada;
		while(current.before(dataSaida)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(current);
			
			if(calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
				// final de semana
				valor += DIARIA_FINAL_SEMANA;
				if(adicionalVeiculo) {
					valor += CARRO_FINAL_SEMANA;
				}
			} else {
				// dia de semana
				valor += DIARIA_DURANTE_SEMANA;
				if(adicionalVeiculo) {
					valor += CARRO_DURANTE_SEMANA;
				}
			}
			calendar.add(Calendar.DATE, 1);
			current = calendar.getTime();
		}
		return valor;
	}
	
}
