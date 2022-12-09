package com.example.finapp.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.finapp.Model.Financa;

public class FinAppDAO {

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
        values.put("valor", financa.getValor());
        values.put("data", String.valueOf(financa.getData()));
        try {
            write.insert(DBHelper.TABLE1_NAME, null, values);
            Log.i("INFO", "Operação salva com sucesso. ");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvar operação. " + e.getMessage());
            return false;
        }
        return true;
    }
}
