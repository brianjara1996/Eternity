package com.Mayor.Backend.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuario")
@ToString
@EqualsAndHashCode
public class Carta {
	
	@Getter @Setter @Column(name= "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
	private Long id;
	
	@Getter @Setter @Column(name= "numero")
	private int numero;
	
	
	@Getter @Setter @Column(name= "palo")
	private String palo;


	public Carta(int Numero, String Palo) {
		this.numero = Numero;
		this.palo = Palo;
	}


	public Long getId() {
		return id;
	}


	public int getNumero() {
		return numero;
	}


	public String getPalo() {
		return palo;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public void setPalo(String palo) {
		this.palo = palo;
	}
	
	
	


}