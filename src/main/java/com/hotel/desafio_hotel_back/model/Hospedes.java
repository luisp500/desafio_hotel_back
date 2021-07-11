package com.hotel.desafio_hotel_back.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "hospedes")
public class Hospedes  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String nome;
	private String documento;
	private String telefone;

	
	
}
