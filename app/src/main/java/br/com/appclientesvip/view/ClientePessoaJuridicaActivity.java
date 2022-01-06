package br.com.appclientesvip.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.appclientesvip.Controller.ClientePFController;
import br.com.appclientesvip.Controller.ClientePJController;
import br.com.appclientesvip.R;
import br.com.appclientesvip.api.AppUtil;
import br.com.appclientesvip.model.Cliente;
import br.com.appclientesvip.model.ClientePJ;

public class ClientePessoaJuridicaActivity extends AppCompatActivity {

    Cliente novoVip;
    ClientePJ novoClientePJ;
    ClientePJController controller;

    private SharedPreferences preferences;

    EditText editCNPJ, editRazaoSocial, editDataAberturaPJ;
    CheckBox chSimples, chMEI;

    Button btnSalvarContinuar, btnVoltar, btnCancelar;
    boolean isFormularioOK, isSimplesNacional, isMEI;

    int  ultimoIDClientePF;
//    int ultimoIDClientePJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_pessoa_juridica);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {

                    //TODO: SETAR o valor do ultimo cliente incluido
                    novoClientePJ.setClientePFID(ultimoIDClientePF);
                    novoClientePJ.setCnpj(editCNPJ.getText().toString());
                    novoClientePJ.setRazaoSocial(editRazaoSocial.getText().toString());
                    novoClientePJ.setDataAbertura(editDataAberturaPJ.getText().toString());

                    novoClientePJ.setSimplesNacional(isSimplesNacional);
                    novoClientePJ.setMei(isMEI);

                    controller.incluir(novoClientePJ);

                    salvarSharedPreferences();

                    Intent intent = new Intent( ClientePessoaJuridicaActivity.this, CredencialDeAcessoActivity.class );
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

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder lerPoliticaDeUso = new AlertDialog.Builder(
                        ClientePessoaJuridicaActivity.this);
                lerPoliticaDeUso.setIcon(R.drawable.especializada);
                lerPoliticaDeUso.setTitle(R.string.chamadaCancelar);
//                  lerPoliticaDeUso.setPositiveButtonIcon(R.drawable.)
                lerPoliticaDeUso.setMessage(R.string.cancelar);
                lerPoliticaDeUso.setCancelable(false);
                lerPoliticaDeUso.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                R.string.cancelado,
                                Toast.LENGTH_LONG).show();

                    }
                });
                lerPoliticaDeUso.setNegativeButton(R.string.nao, null);
                lerPoliticaDeUso.create().show();

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

        editCNPJ    = findViewById(R.id.editCNPJ);
        editRazaoSocial = findViewById(R.id.editRazaoSocial);
        editDataAberturaPJ = findViewById(R.id.editDataAberturaPJ);

        chSimples = findViewById(R.id.chSimplesNacional);
        chMEI   =   findViewById(R.id.chMEI);

        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCancelar = findViewById(R.id.btnCancelar);
//        isFormularioOK = false;

        novoClientePJ = new ClientePJ();
        novoVip = new Cliente();
        controller = new ClientePJController(this);

        restaurarSharedPreferences();

    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editCNPJ.getText().toString())) {
            editCNPJ.setError("*");
            editCNPJ.requestFocus();
            retorno = false;
        }
        if(!AppUtil.isCNPJ(editCNPJ.getText().toString())){

            editCNPJ.setError("*");
            editCNPJ.requestFocus();
            retorno = false;

            Toast.makeText(this, R.string.cnpjInvalido, Toast.LENGTH_LONG).show();

        }else{

            editCNPJ.setText(AppUtil.mascaraCNPJ(editCNPJ.getText().toString()));

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

        dados.putString("cnpj", editCNPJ.getText().toString());
        dados.putString("razaoSocial", editRazaoSocial.getText().toString());
        dados.putString("dataAbertura", editDataAberturaPJ.getText().toString());

        dados.putBoolean("simplesNacional", isSimplesNacional);
        dados.putBoolean("mei", isMEI);
        dados.putInt("ultimoIDClientePF", ultimoIDClientePF);

        dados.apply();
    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        ultimoIDClientePF = preferences.getInt("ultimoIDClientePF", -1);


    }
}