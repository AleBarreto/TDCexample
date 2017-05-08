package com.oceanbrasil.tdcexample.model;

/**
 * Created by ocean on 03/05/17.
 */

public class Carro {

    String ano;
    String marca;
    String cor;

    public Carro() {
    }

    public Carro(String ano, String marca, String cor) {
        this.ano = ano;
        this.marca = marca;
        this.cor = cor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
