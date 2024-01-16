package com.example.myapp.model;

public class Aluno {
String nome;
String RA;

public Aluno(String nome, String RA) {
	super();
	this.RA = RA;
    this.nome = nome;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getRA() {
	return RA;
}

public void setRA(String RA) {
	this.RA = RA;
}

public boolean verficaAluno()
{   //Se o RA é null é retorna erro - método aleatório
	if(this.RA.equals("10"))
	 return false;
else
	return true;
}

}
