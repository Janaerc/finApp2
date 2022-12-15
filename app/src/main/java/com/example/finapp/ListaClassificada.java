package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finapp.BD.FinAppDAO;
import com.example.finapp.Model.Classificacao;


import java.util.List;

public class ListaClassificada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_classificada);
        this.getSupportActionBar().hide();


        LinearLayout listaLinearLayout = findViewById(R.id.listaLinearLayout);
        FinAppDAO db = new FinAppDAO(this);
        List<Classificacao> lista = db.getClassificacao();
        for (Classificacao c : lista) {
            // Se o valor for positivo, colocar um '+' na frente
            String valorString;
            if (c.getOperacao().equals("Crédito"))
                valorString = "+" + String.valueOf(c.getValor());
            else
                valorString = "-" + String.valueOf(c.getValor());

            TextView tv = new TextView(this);
            tv.setText("" + c.getClassificacao() + "\n" + c.getOperacao() + "\nTotal : " + valorString);

            // Define o tamanho fixo de layout (altura)
            // RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tv.getLayoutParams();
            tv.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            tv.setHeight(350);
            tv.setTextSize(20f);
            // tv.setLayoutParams(params);

            listaLinearLayout.addView(tv);
        }
    }
}