package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Pesquisar extends AppCompatActivity {

    private final Calendar dataCalendario = Calendar.getInstance();
    private boolean isDateSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        isDateSelected = false;

        setContentView(R.layout.activity_cadastro_operacoes); // setContentView precisa estar acima da findViewByID, para não existir problemas ao encontrar o elemento de interface.
        carregarDatePickerIni();
        carregarDatePickerFin();

    }

    public void pesquisar (View view){
        //pegar aqui a data inical, data final e o radiobotton
        //fazer as validações de campos vazios
        ////enviar para finAppDao
    }



    private void updateLabel(EditText editText) {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(sdf.format(dataCalendario.getTime()));
    }

    // Cuida de listeners relacionados a abertura da janela de DatePickerDialog, e salvamento da data no calendário / edit de data.
    private void carregarDatePickerIni() {
        final EditText dataIniEdit = findViewById(R.id.inicialEdit);
        dataIniEdit.setInputType(InputType.TYPE_NULL);

        // Ao definir a data no datepicker, atualiza no objeto do calendário, e também atualiza a label.
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month,
                                  int day) {
                // TODO Auto-generated method stub
                dataCalendario.set(Calendar.YEAR, year);
                dataCalendario.set(Calendar.MONTH, month);
                dataCalendario.set(Calendar.DAY_OF_MONTH, day);
                isDateSelected = true;
                updateLabel(dataIniEdit);
            }
        };

        // Ao clicar no editText, abre o datepicker
        dataIniEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Pesquisar.this, date, dataCalendario
                        .get(Calendar.YEAR), dataCalendario.get(Calendar.MONTH),
                        dataCalendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void carregarDatePickerFin() {
        final EditText dataFinEdit = findViewById(R.id.DataFinEdit);
        dataFinEdit.setInputType(InputType.TYPE_NULL);

        // Ao definir a data no datepicker, atualiza no objeto do calendário, e também atualiza a label.
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month,
                                  int day) {
                // TODO Auto-generated method stub
                dataCalendario.set(Calendar.YEAR, year);
                dataCalendario.set(Calendar.MONTH, month);
                dataCalendario.set(Calendar.DAY_OF_MONTH, day);
                isDateSelected = true;
                updateLabel(dataFinEdit);
            }
        };

        // Ao clicar no editText, abre o datepicker
        dataFinEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Pesquisar.this, date, dataCalendario
                        .get(Calendar.YEAR), dataCalendario.get(Calendar.MONTH),
                        dataCalendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
}