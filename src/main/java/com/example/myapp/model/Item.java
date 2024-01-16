package com.example.myapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item {
    /*Cada Item é associado a um livro*/
	Livro livro;
    String dataDevolucao;
 
    /*Constructor, quando uma instancia do item é criada
     * já é associada a ela o livro*/
    public Item(Livro livro) {
		super();
		this.livro = livro;
		this.dataDevolucao = CalculaDataDevolucao();
	}
    
    /*Método getDataDevolucao*/
	public String getDataDevolucao() {
		return dataDevolucao;
	}

	/*Método setDataDevolucao*/
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	/*Método getLivro*/
	public Livro getLivro() {
		return livro;
	}
	
	/*Método setLivro*/
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	private String CalculaDataDevolucao(){
		LocalDate today = LocalDate.now();
        LocalDate sevenDaysLater = today.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sevenDaysLaterAsString = sevenDaysLater.format(formatter);

		return sevenDaysLaterAsString;
	}
}
