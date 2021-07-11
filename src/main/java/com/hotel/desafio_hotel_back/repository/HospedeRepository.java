package com.hotel.desafio_hotel_back.repository;


import com.hotel.desafio_hotel_back.model.Hospedes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedeRepository extends JpaRepository<Hospedes, Long> {
	Page<Hospedes> findByNomeContainingIgnoreCaseOrDocumentoContainingIgnoreCaseOrTelefoneContainingIgnoreCase(String nome, String documento, String telefone, Pageable pageable);
	Hospedes findOneByNomeIgnoreCaseAndDocumentoAndTelefone(String nome, String documento, String telefone);
	Hospedes findOneByNomeContainingIgnoreCaseOrDocumentoContainingIgnoreCaseOrTelefoneContainingIgnoreCase(String nome, String documento, String telefone);
}
