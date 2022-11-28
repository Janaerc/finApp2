package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickExtrato(View view){
        Intent it = new Intent(this, Extrato.class);
        startActivity(it);

    }

    public void onClickOperacoes(View view){
        Intent it = new Intent(this, CadastroOperacoes.class);
        startActivity(it);

    }
    public void onClickLista(View view){
        Intent it = new Intent(this, ListaClassificada.class);
        startActivity(it);

    }
    public void onClickPesquisar(View view){
        Intent it = new Intent(this, Pesquisar.class);
        startActivity(it);

    }





}