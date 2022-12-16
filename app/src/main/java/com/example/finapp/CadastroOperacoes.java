package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finapp.BD.FinAppDAO;
import com.example.finapp.Model.Financa;

public class CadastroOperacoes extends AppCompatActivity {

    private ArrayAdapter<CharSequence> creditoAdapter;
    private ArrayAdapter<CharSequence> debitoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        // Carregar dados que serão preenchidos no spinner de categoria de operação.
        // Credito
        this.creditoAdapter = ArrayAdapter.createFromResource(this, R.array.categoria_credito, android.R.layout.simple_spinner_item); // Cria um adaptador para preencher as listas de dropdown com as informações das categorias de crédito.
        this.creditoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Indicar que o layout será usado numa lista de dropdown.

        // Debito
        this.debitoAdapter = ArrayAdapter.createFromResource(this, R.array.categoria_debito, android.R.layout.simple_spinner_item); // Cria um adaptador para preencher as listas de dropdown com as informações das categorias de débito.
        this.debitoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Indicar que o layout será usado numa lista de dropdown.

        setContentView(R.layout.activity_cadastro_operacoes); // setContentView precisa estar acima da findViewByID, para não existir problemas ao encontrar o elemento de interface.

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
    }


