package br.com.appclientesvip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.appclientesvip.R;
import br.com.appclientesvip.api.AppUtil;
import br.com.appclientesvip.model.Cliente;
import br.com.appclientesvip.model.ClientePF;

public class ClientePessoaFisicaActivity extends AppCompatActivity {

    Cliente novoVip;
    ClientePF novoClientePF;

    private SharedPreferences preferences;

    EditText editCPF, editNomeCompleto;
    Button btnSalvarContinuar, btnVoltar, btnCancelar;

    boolean isFormularioOK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_pessoa_fisica);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {

                    novoClientePF.setCpf(editCPF.getText().toString());
                    novoClientePF.setNomeCompleto(editNomeCompleto.getText().toString());

                    salvarSharedPreferences();

                    Intent intent = new Intent( ClientePessoaFisicaActivity.this, LoginActivity.class );
                    startActivity(intent);
                }

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientePessoaFisicaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }

    private void initFormulario() {

        editCPF = findViewById(R.id.editCPF);
        editNomeCompleto = findViewById(R.id.editNomeCompleto);

        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCancelar = findViewById(R.id.btnCancelar);

        novoClientePF = new ClientePF();
        novoVip = new Cliente();

        restaurarSharedPreferences();

    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editCPF.getText().toString())) {
            editCPF.setError("*");
            editCPF.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editNomeCompleto.getText().toString())) {
            editNomeCompleto.setError("*");
            editNomeCompleto.requestFocus();
            retorno = false;
        }

        return retorno;

    }

//    public void pessoaFisica(View view) {
//
//        isPessoaFisica = chPessoaFisica.isChecked();
//
//    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

//        dados.putString("primeiroNome", novoVip.getPrimeiroNome());
//        dados.putString("sobrenome", novoVip.getSobreNome());
//        dados.putBoolean("chPessoaFisica", novoVip.isPessoaFisica());
//        dados.apply();
    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

    }
}