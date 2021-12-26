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
import android.widget.TextView;
import android.widget.Toast;

import br.com.appclientesvip.Controller.ClienteController;
import br.com.appclientesvip.R;
import br.com.appclientesvip.api.AppUtil;
import br.com.appclientesvip.model.Cliente;

public class ClienteVipActivity extends AppCompatActivity {

    Cliente novoVip;
    private SharedPreferences preferences;

    EditText editPrimeiroNome, editSobreNome;
    CheckBox chPessoaFisica;
    Button btnSalvarContinuar, btnVoltar, btnCancelar;

    boolean isFormularioOK, isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_vip);
        
        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {

                    novoVip.setPrimeiroNome(editPrimeiroNome.getText().toString());
                    novoVip.setSobreNome(editSobreNome.getText().toString());
                    novoVip.setPessoaFisica(isPessoaFisica);

                    salvarSharedPreferences();

                    if(isPessoaFisica){
//                        Tela pessoa Fisica
                        Intent intent = new Intent( ClienteVipActivity.this, ClientePessoaFisicaActivity.class );
                        startActivity(intent);

                    } else{
//                        Tela CNPJ
                        Intent intent = new Intent( ClienteVipActivity.this, ClientePessoaJuridicaActivity.class );
                        startActivity(intent);

                    }
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder lerPoliticaDeUso = new AlertDialog.Builder(
                        ClienteVipActivity.this);
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
//                lerPoliticaDeUso.setNegativeButton("Recusar", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(),
//                                "Lamentamos, mas é necessario aceitar para prosseguir!",
//                                Toast.LENGTH_LONG).show();
//                        finish();
//                        return;
//                    }
//                });

                lerPoliticaDeUso.create().show();

            }
        });


        
    }


    private void initFormulario() {

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobreNome = findViewById(R.id.editSobreNome);
        chPessoaFisica   = findViewById(R.id.chPessoaFisica);

        btnSalvarContinuar  = findViewById(R.id.btnSalvarContinuar);
        btnVoltar  = findViewById(R.id.btnVoltar);
        btnCancelar = findViewById(R.id.btnCancelar);


        isFormularioOK = false;

        novoVip = new Cliente();

        restaurarSharedPreferences();

    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editPrimeiroNome.getText().toString())){
            editPrimeiroNome.setError("*");
            editPrimeiroNome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editSobreNome.getText().toString())){
            editSobreNome.setError("*");
            editSobreNome.requestFocus();
            retorno = false;
        }

        return retorno;

    }

    public void pessoaFisica(View view){

        isPessoaFisica = chPessoaFisica.isChecked();

    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("primeiroNome", novoVip.getPrimeiroNome());
        dados.putString("sobrenome", novoVip.getSobreNome());
        dados.putBoolean("chPessoaFisica", novoVip.isPessoaFisica());
        dados.apply();
    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
//        isLembrarSenha = preferences.getBoolean("loginAutomatico", false);

    }
}