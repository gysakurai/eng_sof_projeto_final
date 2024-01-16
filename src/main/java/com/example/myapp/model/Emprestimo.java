package com.example.myapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Emprestimo {
    
	Item item;
	String dataEmprestimo;
	Aluno aluno;

	public Emprestimo(Item item, Aluno aluno) {
		super();
		this.item = item;
		this.dataEmprestimo = calculaDataEmprestimo();
		this.aluno = aluno;
	}

	public Item getItem() {
		return item;
	}

	public Aluno getAluno(){
		return aluno;
	}

	public String getDataEmprestimo(){
		return dataEmprestimo;
	}

	private String calculaDataEmprestimo(){
		LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayAsString = today.format(formatter);

		return todayAsString;
	}

}
