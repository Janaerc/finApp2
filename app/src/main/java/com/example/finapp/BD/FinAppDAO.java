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
        ContentValues values = new ContentValues();
        values.put("operacao", financa.getOperacao());
        values.put("classificacao", financa.getClassificacao());
        values.put("data", financa.getData());
        values.put("valor", financa.getValor());
        try {
            write.insert(DBHelper.TABLE1_NAME, null, values);
            Log.i("INFO", "Operação salva com sucesso. ");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvar operação. " + e.getMessage());
            return false;
        }
        return true;
    }

    public List<Classificacao> getClassificacao() {
        SQLiteDatabase db = this.write;
        String query;
        query = "SELECT " +
                "       operacao," +
                "       classificacao," +
                "       SUM(valor) " +
                "FROM financa " +
                "GROUP BY classificacao " +
                "ORDER BY operacao DESC ";

        Cursor c = db.rawQuery(query, null);
        List<Classificacao> list = new ArrayList<>();
        while (c.moveToNext()) {
            String operacaoString = String.valueOf(c.getColumnIndex("operacao"));
            //String operacaoString = c.getString(c.getColumnIndex("operacao"));
            String classificacaoString = String.valueOf((c.getColumnIndex("classificacao")));
            float valorString = c.getColumnIndex("valor");

            Classificacao categoria = new Classificacao(operacaoString, classificacaoString, valorString);
            list.add(categoria);
        }
        return list;
    }
/*
    public float getSaldo() {
        SQLiteDatabase db = this.write;
        String query;
        query = "SELECT " +
                "    ( " +
                "        SELECT sum(valor) " +
                "        FROM operacao o " +
                "        INNER JOIN tipo t ON o.id_tipo = t.id_tipo " +
                "        WHERE t.nome = 'Crédito' " +
                "    ) AS credito, " +
                "    ( " +
                "        SELECT sum(valor) " +
                "        FROM operacao o " +
                "        INNER JOIN tipo t ON o.id_tipo = t.id_tipo " +
                "        WHERE t.nome = 'Débito' " +
                "    ) AS debito";
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() == 0 || !c.moveToFirst()) // Se não retornar resultado, então o saldo é 0.
            return 0;
        c.moveToFirst();
        // c.moveToNext(); // Utilizar apenas uma vez, como possuimos só um valor
        float credito = c.getFloat(c.getColumnIndex("credito"));
        float debito = c.getFloat(c.getColumnIndex("debito"));
        return credito-debito;
    }


}*/


   /* public List<Financa> getAllFinanca() {
        List<Financa> financaList = new ArrayList<>();
        Cursor cursor = read.query(DBHelper.TABLE1_NAME, new String[]{"id", " nome", },
                null, null, null, null, null );

        while(cursor.moveToNext()) {
            ToDo toDo = new ToDo();
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String toDoName = cursor.getString(cursor.getColumnIndex("name"));
            toDo.setId(id);
            toDo.setToDoName(toDoName);
            toDoList.add(toDo);
        }
        return toDoList;
    }*/







}
