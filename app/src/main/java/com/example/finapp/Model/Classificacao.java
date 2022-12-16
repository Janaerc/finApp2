package com.example.finapp.Model;

public class Classificacao {
    private String operacao;
    private String classificacao;
    private Float valor;


    public Classificacao(String operacao, String classificacao, Float valor) {
        this.operacao = operacao;
        this.classificacao = classificacao;
        this.valor = valor;
    }

    public Classificacao() {
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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

}
