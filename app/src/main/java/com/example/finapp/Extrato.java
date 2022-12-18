package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finapp.BD.FinAppDAO;
import com.example.finapp.Model.Financa;

import java.util.List;

public class Extrato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        LinearLayout listaLinearLayout = findViewById(R.id.listaPesquisarLayout);
        FinAppDAO db = new FinAppDAO(this);
        List<Financa> lista = db.extrato();
        for (Financa c: lista){
            TextView tv = new TextView(this);
            tv.setText("Classificação:" + c.getClassificacao() + "\nData:" + c.getData() + "\nValor: " + c.getValor());
            tv.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            tv.setHeight(350);
            tv.setTextSize(20f);
            listaLinearLayout.addView(tv);
        }
        float saldo = db.saldo();
        TextView saldoTx = findViewById(R.id.saldoText);
        String saldoString = Float.toString(saldo);
        saldoTx.setText(saldoString);










    }
}