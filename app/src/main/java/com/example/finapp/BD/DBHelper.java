package com.example.finapp.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static int DB_VERSION = 1;
    public static String DB_NAME = "FINANCA.DB";
    public static String TABLE1_NAME = "financa";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_SQL= "CREATE TABLE IF NOT EXISTS "  + TABLE1_NAME + "("
                + "idFinanca INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "operacao Text,"
                + "classificacao Text,"
                + "data TEXT,"
                + "valor REAL"
                + ")";

        /*
        String CREATE_SQL= "CREATE TABLE IF NOT EXISTS "  + TABLE1_NAME + "("
                + "idFinanca INTEGER PRIMARY KEY,"
                + "idOperacao INTEGER,"
                + "idClassificacao INTEGER,"
                + "data TEXT,"
                + "valor REAL,"
                + "FOREIGN KEY (idOperacao) REFERENCES operacao(idOperacao),"
                + "FOREIGN KEY (idClassificacao) REFERENCES classificao(idClassificacao)"
                + ")";

         */


        try {
            sqLiteDatabase.execSQL(CREATE_SQL);
            Log.i("INFO DB", "Tabela criada1.");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar tabela." + e.getMessage());
        }


        // Create table operacao
        CREATE_SQL = "CREATE TABLE IF NOT EXISTS operacao ("
                + "idOperacao INTEGER PRIMARY KEY,"
                + "nome TEXT"
                + ")";

        try {
            sqLiteDatabase.execSQL(CREATE_SQL);
            Log.i("INFO DB", "Tabela criada2.");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar tabela." + e.getMessage());
        }


        // Create table classificacao
        CREATE_SQL = "CREATE TABLE IF NOT EXISTS  classificacao ("
                + "idClassificacao INTEGER PRIMARY KEY,"
                + "nome TEXT,"
                + "idOperacao,"
                + "FOREIGN KEY (idOperacao) REFERENCES operacao(idOperacao)"
                + ")";

        try {
            sqLiteDatabase.execSQL(CREATE_SQL);
            Log.i("INFO DB", "Tabela criada3.");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar tabela." + e.getMessage());
        }

        // Insert into table operacao
        String INSERT_SQL = "INSERT INTO operacao (nome) VALUES ('Débito')";
        sqLiteDatabase.execSQL(INSERT_SQL);
        INSERT_SQL = "INSERT INTO operacao (nome) VALUES ('Crédito')";
        sqLiteDatabase.execSQL(INSERT_SQL);

        // Insert into table classificacao
        INSERT_SQL = "INSERT INTO classificacao (nome, idOperacao) VALUES ('Moradia','1')";
        sqLiteDatabase.execSQL(INSERT_SQL);
        INSERT_SQL = "INSERT INTO classificacao (nome, idOperacao) VALUES ('Saúde','1')";
        sqLiteDatabase.execSQL(INSERT_SQL);
        INSERT_SQL = "INSERT INTO classificacao (nome, idOperacao) VALUES ('Outros','1')";
        sqLiteDatabase.execSQL(INSERT_SQL);
        INSERT_SQL = "INSERT INTO classificacao (nome, idOperacao) VALUES ('Salário','2')";
        sqLiteDatabase.execSQL(INSERT_SQL);
        INSERT_SQL = "INSERT INTO classificacao (nome, idOperacao) VALUES ('Outros','2')";
        sqLiteDatabase.execSQL(INSERT_SQL);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Código para mudança do BD
        //Método chamado apenas quando a versão do DB mudar
        //Código exemplo para alterar
        //String ALTER_SQL = "ALTER TABLE " + TABLE1_NAME
        //        + " ADD COLUMN status VARCHAR(3) ";


        try {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS operacao;");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS classificacao;");
            onCreate(sqLiteDatabase);
            Log.i("INFO DB", "Tabela atualizada.");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao atualizar tabela." + e.getMessage());
        }

    }


}



