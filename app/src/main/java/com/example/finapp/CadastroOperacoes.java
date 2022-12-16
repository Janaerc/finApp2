package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finapp.BD.FinAppDAO;
import com.example.finapp.Model.Financa;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CadastroOperacoes extends AppCompatActivity {

    private ArrayAdapter<CharSequence> creditoAdapter;
    private ArrayAdapter<CharSequence> debitoAdapter;

    //Calendario
    private final Calendar dataCalendario = Calendar.getInstance();
    private boolean isDateSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        isDateSelected = false; // Inicializa variável de controle do calendário.



        // Carregar dados que serão preenchidos no spinner de categoria de operação.
        // Credito
        this.creditoAdapter = ArrayAdapter.createFromResource(this, R.array.categoria_credito, android.R.layout.simple_spinner_item); // Cria um adaptador para preencher as listas de dropdown com as informações das categorias de crédito.
        this.creditoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Indicar que o layout será usado numa lista de dropdown.

        // Debito
        this.debitoAdapter = ArrayAdapter.createFromResource(this, R.array.categoria_debito, android.R.layout.simple_spinner_item); // Cria um adaptador para preencher as listas de dropdown com as informações das categorias de débito.
        this.debitoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Indicar que o layout será usado numa lista de dropdown.

        setContentView(R.layout.activity_cadastro_operacoes); // setContentView precisa estar acima da findViewByID, para não existir problemas ao encontrar o elemento de interface.
        carregarDatePicker(); // Carrega informações do datepicker do cadastro da operação.
    }

    // Chamado ao selecionar débito no RadioButton de Cadastro de Operação
    public void selecionarDebito(View view) {
        Spinner categoriaSpinner = (Spinner) findViewById(R.id.spinnerClassificacoes);
        categoriaSpinner.setAdapter(this.debitoAdapter);
    }

    // Chamado ao selecionar crédito no RadioButton de Cadastro de Operação
    public void selecionarCredito(View view) {
        Spinner categoriaSpinner = (Spinner) findViewById(R.id.spinnerClassificacoes);
        categoriaSpinner.setAdapter(this.creditoAdapter);
    }



    public void salvarOperacao(View view){
        RadioGroup radioGroup = findViewById(R.id.tipoOperacaoRadioGroup);
        Spinner categoriaOperacaoSpinner = findViewById(R.id.spinnerClassificacoes);
        EditText editTextValor = findViewById(R.id.editTextValor);
        EditText data = findViewById(R.id.dataOperacaoEdit);
        String dataString = data.getText().toString();
        String valorOperacaoString = editTextValor.getText().toString();
        int radioSelectedID = radioGroup.getCheckedRadioButtonId();


        //Validação
        // radio button
        if (radioSelectedID == -1) {
            Toast toast = Toast.makeText(getApplicationContext(), "Selecione um tipo de operação", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // data
        if (!isDateSelected) {
            Toast toast = Toast.makeText(getApplicationContext(), "Selecione uma data", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // valor
        if (valorOperacaoString.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Insira um valor", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        // Finalmente, os dados são recebidos em seus formatos apropriados.
        RadioButton tipoOperacaoRadio = (RadioButton)(findViewById(radioSelectedID));
        String tipoOperacaoString = (String) tipoOperacaoRadio.getText();
        String categoriaOperacaoString = (String) categoriaOperacaoSpinner.getSelectedItem().toString();
        Float valorOperacaoFloat = Float.valueOf(valorOperacaoString);



        Financa financa = new Financa(tipoOperacaoString, categoriaOperacaoString, dataString, valorOperacaoFloat);
            FinAppDAO db = new FinAppDAO(this);
            db.insereFinanca(financa);

            // Operacao criada com sucesso.
            Toast toast = Toast.makeText(getApplicationContext(), "Operação criada com sucesso!.", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }

    //CALENDARIO  ______________________________
    private void updateLabel(EditText editText) {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(sdf.format(dataCalendario.getTime()));
    }

    // Cuida de listeners relacionados a abertura da janela de DatePickerDialog, e salvamento da data no calendário / edit de data.
    private void carregarDatePicker() {
        final EditText dataOperacaoEdit = findViewById(R.id.dataOperacaoEdit);
        dataOperacaoEdit.setInputType(InputType.TYPE_NULL);

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
                updateLabel(dataOperacaoEdit);
            }
        };

        // Ao clicar no editText, abre o datepicker
        dataOperacaoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CadastroOperacoes.this, date, dataCalendario
                        .get(Calendar.YEAR), dataCalendario.get(Calendar.MONTH),
                        dataCalendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

}


