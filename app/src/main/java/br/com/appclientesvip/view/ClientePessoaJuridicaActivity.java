package br.com.appclientesvip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.appclientesvip.R;
import br.com.appclientesvip.api.AppUtil;
import br.com.appclientesvip.model.Cliente;
import br.com.appclientesvip.model.ClientePJ;

public class ClientePessoaJuridicaActivity extends AppCompatActivity {

    Cliente novoVip;
    ClientePJ novoClientePJ;

    private SharedPreferences preferences;

    EditText editCNPJ, editRazaoSocial, editDataAberturaPJ;
    CheckBox chSimples, chMEI;

    Button btnSalvarContinuar, btnVoltar, btnCancelar;
    boolean isFormularioOK, isSimplesNacional, isMEI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_pessoa_juridica);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {

                    novoClientePJ.setCnpj(editCNPJ.getText().toString());
                    novoClientePJ.setRazaoSocial(editRazaoSocial.getText().toString());
                    novoClientePJ.setDataAbertura(editDataAberturaPJ.getText().toString());

                    novoClientePJ.setSimplesNacional(isSimplesNacional);
                    novoClientePJ.setMei(isMEI);

                    salvarSharedPreferences();

                    Intent intent = new Intent( ClientePessoaJuridicaActivity.this, LoginActivity.class );
                    startActivity(intent);
                }

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientePessoaJuridicaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void simplesNacional(View view){

        isSimplesNacional = chSimples.isChecked();

    }

    public void mei(View view){

        isMEI = chMEI.isChecked();

    }


    private void initFormulario() {

        chSimples = findViewById(R.id.chSimplesNacional);
        chMEI   =   findViewById(R.id.chMEI);

        editCNPJ = findViewById(R.id.editCNPJ);
        editRazaoSocial = findViewById(R.id.editRazaoSocial);
        editDataAberturaPJ = findViewById(R.id.editDataAberturaPJ);

        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCancelar = findViewById(R.id.btnCancelar);
//        isFormularioOK = false;

        novoClientePJ = new ClientePJ();
        novoVip = new Cliente();

        restaurarSharedPreferences();

    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editCNPJ.getText().toString())) {
            editCNPJ.setError("*");
            editCNPJ.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editRazaoSocial.getText().toString())) {
            editRazaoSocial.setError("*");
            editRazaoSocial.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editDataAberturaPJ.getText().toString())) {
            editDataAberturaPJ.setError("*");
            editDataAberturaPJ.requestFocus();
            retorno = false;
        }

        return retorno;

    }

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
//        isLembrarSenha = preferences.getBoolean("loginAutomatico", false);

    }
}