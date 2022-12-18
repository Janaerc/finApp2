package com.example.finapp.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.finapp.Model.Classificacao;
import com.example.finapp.Model.Financa;

import java.util.ArrayList;
import java.util.List;

public class FinAppDAO{

    private SQLiteDatabase write; //Para escrever nas tabelas
    private SQLiteDatabase read; //Para ler nas tabelas


    public FinAppDAO(Context context) {
        DBHelper db = new DBHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    public boolean insereFinanca(Financa financa) {
        Log.i("INFO", "entrou no insere financa da dao. ");
        ContentValues values = new ContentValues();
        values.put("operacao", financa.getOperacao());
        values.put("classificacao", financa.getClassificacao());
        values.put("data", financa.getData());
        values.put("valor", financa.getValor());
        try {
            write.insert(DBHelper.TABLE1_NAME, null, values);
            Log.i("INFO", "rafa " + financa.getOperacao() + "" + financa.getValor());
            Log.i("INFO", "rafa " + financa.getClassificacao() + "" + financa.getData());
        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvar operação. " + e.getMessage());
            return false;
        }
        return true;
    }

    public List<Classificacao> getClassificacao() {
        SQLiteDatabase db = this.read;
        String query;
        query = "SELECT operacao, classificacao, SUM(valor) FROM financa GROUP BY classificacao ORDER BY operacao DESC";

        Cursor c = read.rawQuery(query, null);
        List<Classificacao> list = new ArrayList<>();
        while (c.moveToNext()) {
            float valor = c.getFloat(2);
            String operacaoString = c.getString(0);
            String classificacaoString = c.getString(1);
            Classificacao categoria = new Classificacao(operacaoString, classificacaoString, valor);
            list.add(categoria);
        }
        return list;
    }

    public List<Financa> pesquisar(String dataInicialString, String dataFinalString, String tipoOperacaoString) {
        SQLiteDatabase db = this.read;
        String query;
        List<Financa> list = new ArrayList<>();

        if ("Débito".equals(tipoOperacaoString)){
            query = "SELECT classificacao, data, valor FROM financa WHERE operacao LIKE 'Débito' AND data BETWEEN'" + dataInicialString + "' AND '" + dataFinalString + "'";

            Cursor c = read.rawQuery(query, null);
            while (c.moveToNext()) {
                String classificacaoString = c.getString(0);
                String dataString = c.getString(1);
                float valor = c.getFloat(2);
                Financa financa = new Financa(null, classificacaoString, dataString,valor);
                list.add(financa);
            }
            return list;
        }
        if ("Crédito".equals(tipoOperacaoString)) {
            query = "SELECT classificacao, data, valor FROM financa WHERE operacao LIKE 'Crédito' AND data BETWEEN'" + dataInicialString + "' AND '" + dataFinalString + "'";

            Cursor c = read.rawQuery(query, null);
            while (c.moveToNext()) {
                String classificacaoString = c.getString(0);
                String dataString = c.getString(1);
                float valor = c.getFloat(2);
                Financa financa = new Financa(null, classificacaoString, dataString,valor);
                list.add(financa);
            }
            return list;
        }
        else {
            query = "SELECT classificacao, data, valor FROM financa WHERE data BETWEEN'" + dataInicialString + "' AND '" + dataFinalString + "'";

            Cursor c = read.rawQuery(query, null);
            while (c.moveToNext()) {
                String classificacaoString = c.getString(0);
                String dataString = c.getString(1);
                float valor = c.getFloat(2);
                Financa financa = new Financa(null, classificacaoString, dataString,valor);
                list.add(financa);
            }
        }
        return list;
    }

    public List<Financa> extrato() {
        SQLiteDatabase db = this.read;
        String query;
        List<Financa> list = new ArrayList<>();
        query = "SELECT classificacao, data, valor FROM financa ORDER BY data limit 15";

        Cursor c = read.rawQuery(query, null);
        while (c.moveToNext()) {
            String classificacaoString = c.getString(0);
            String dataString = c.getString(1);
            float valor = c.getFloat(2);
            Financa financa = new Financa(null, classificacaoString, dataString,valor);
            list.add(financa);
        }
        return list;
    }

    public float saldo() {
        SQLiteDatabase db = this.read;
        String query, query1;
        float credito = 0, debito= 0;
        query = "SELECT SUM(valor) from financa WHERE operacao LIKE 'débito'";
        Cursor c = read.rawQuery(query, null);
        while (c.moveToNext()) {
            debito = c.getFloat(0);
        }
        query1 = "SELECT SUM(valor) from financa WHERE operacao LIKE 'crédito'";
        c = read.rawQuery(query1, null);
        while (c.moveToNext()) {
             credito = c.getFloat(0);
        }
        Log.i("INFO", "rafa " +  debito);
        Log.i("INFO", "rafa " + credito);
        return (credito - debito);
    }









}
