package com.hotel.desafio_hotel_back.repository;


import com.hotel.desafio_hotel_back.model.Reservas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ReservaRepository extends JpaRepository<Reservas, Long>{
	Page<Reservas> findByDataSaidaAfter(Date date, Pageable pageable);
	Page<Reservas> findByDataSaidaBefore(Date date, Pageable pageable);
}
