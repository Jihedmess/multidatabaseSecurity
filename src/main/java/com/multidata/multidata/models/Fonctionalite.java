package com.multidata.multidata.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fonctionalite")
public class Fonctionalite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column
	private String name;
	@Column
	private String desscription;
	@Column
	private String fileActivation;
	@Column
	private String filaDesactivation;
	@Column
	private String fileChek;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesscription() {
		return desscription;
	}
	public void setDesscription(String desscription) {
		this.desscription = desscription;
	}
	public String getFileActivation() {
		return fileActivation;
	}
	public void setFileActivation(String fileActivation) {
		this.fileActivation = fileActivation;
	}
	public String getFilaDesactivation() {
		return filaDesactivation;
	}
	public void setFilaDesactivation(String filaDesactivation) {
		this.filaDesactivation = filaDesactivation;
	}
	public String getFileChek() {
		return fileChek;
	}
	public void setFileChek(String fileChek) {
		this.fileChek = fileChek;
	}
	
	
	

}
