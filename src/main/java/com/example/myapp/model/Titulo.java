package com.example.myapp.model;

public class Titulo {
    int prazo;
    int codigo;
    public Titulo(int codigo)
    {
        this.codigo = codigo;
        this.prazo = codigo + 1;
    }

    public int getPrazo() {
        return prazo;
    }
    public int getCodigo() {
        return codigo;
    }
}
