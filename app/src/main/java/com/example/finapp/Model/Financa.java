package com.example.finapp.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Financa implements Serializable {
    private int id;
    private String operacao;
    private String classificacao;
    private float valor;
    private String data;

    public Financa(String operacao, String classificacao, String data, float valor) {
        this.operacao = operacao;
        this.classificacao = classificacao;
        this.valor = valor;
        this.data = data;
    }

    public Financa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
