package com.hotel.desafio_hotel_back.model;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "reservas")
public class Reservas  {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


	@ManyToOne
	@JoinColumn(name = "hospedes")
	private  Hospedes hospede;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrada;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSaida;
	
	private Boolean adicionalVeiculo;
	
	@Column(name = "valor", nullable = false, columnDefinition = "double default 0.0")
	private double Valor;


}
