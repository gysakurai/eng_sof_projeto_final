package com.example.myapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Livro {
    Titulo titulo;
	String dataInserido;

	public Livro(int codigo) {
		//instância um titulo e o associa ao livro
		titulo = new Titulo(codigo);
		LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayAsString = today.format(formatter);

        this.dataInserido = todayAsString;
	}
	public int verPrazo() {
		return titulo.getPrazo();
	}
	public int verCodigo(){
		return titulo.getCodigo();
	}
	public String getdataInserido(){
		return dataInserido;
	}

}
