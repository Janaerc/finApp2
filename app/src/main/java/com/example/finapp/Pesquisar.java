package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finapp.BD.FinAppDAO;
import com.example.finapp.Model.Financa;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Pesquisar extends AppCompatActivity {

    private final Calendar dataCalendario = Calendar.getInstance();
    private boolean isDateSelectedIni;
    private boolean isDateSelectedFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        isDateSelectedIni = false;
        isDateSelectedFin = false;

        setContentView(R.layout.activity_pesquisar); // setContentView precisa estar acima da findViewByID, para não existir problemas ao encontrar o elemento de interface.
        carregarDatePickerIni();
        carregarDatePickerFin();

    }

    public void pesquisar (View view){
        //pegar aqui a data inical, data final e o radiobotton
        //fazer as validações de campos vazios
        ////enviar para finAppDao
        RadioGroup radioGroup = findViewById(R.id.radioGroupOperacao);
        EditText dataInicial = findViewById(R.id.inicialEdit);
        String dataInicialString = dataInicial.getText().toString();
        EditText dataFinal = findViewById(R.id.DataFinEdit);
        String dataFinalString = dataFinal.getText().toString();
        int radioSelectedID = radioGroup.getCheckedRadioButtonId();

        // radio button
        if (radioSelectedID == -1) {
            Toast toast = Toast.makeText(getApplicationContext(), "Selecione um tipo de operação", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (!isDateSelectedIni) {
            Toast toast = Toast.makeText(getApplicationContext(), "Selecione uma data inicial", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (!isDateSelectedFin) {
            Toast toast = Toast.makeText(getApplicationContext(), "Selecione uma data final", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        RadioButton tipoOperacaoRadio = (RadioButton) (findViewById(radioSelectedID));
        String tipoOperacaoString = (String)tipoOperacaoRadio.getText();

        FinAppDAO db = new FinAppDAO(this);
        List<Financa> lista = db.pesquisar(dataInicialString, dataFinalString, tipoOperacaoString);





        LinearLayout listaLinearLayout = findViewById(R.id.listaPesquisarLayout);
        listaLinearLayout.removeAllViewsInLayout();


        for (Financa c: lista){
            TextView tv = new TextView(this);
            tv.setText("Classificação:" + c.getClassificacao() + "\nData:" + c.getData() + "\nValor: " + c.getValor());
            tv.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            tv.setHeight(350);
            tv.setTextSize(20f);
            listaLinearLayout.addView(tv);
        }
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
                isDateSelectedIni = true;
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
                isDateSelectedFin = true;
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