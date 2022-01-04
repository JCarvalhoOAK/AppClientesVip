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
import android.widget.EditText;
import android.widget.Toast;

import br.com.appclientesvip.Controller.ClientePFController;
import br.com.appclientesvip.R;
import br.com.appclientesvip.api.AppUtil;
import br.com.appclientesvip.model.Cliente;
import br.com.appclientesvip.model.ClientePF;

public class ClientePessoaFisicaActivity extends AppCompatActivity {

    Cliente novoVip;
    ClientePF novoClientePF;
    ClientePFController controller;

    private SharedPreferences preferences;

    EditText editCPF, editNomeCompleto;
    Button btnSalvarContinuar, btnVoltar, btnCancelar;

    boolean isFormularioOK, isPessoaFisica;

    int ultimoIDClientePF;
    int clienteID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_pessoa_fisica);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {

//                    novoClientePF.setClienteID(ultimoIDClientePF); //here my add
                    novoClientePF.setCpf(editCPF.getText().toString());
                    novoClientePF.setNomeCompleto(editNomeCompleto.getText().toString());
                    novoClientePF.setClienteID(clienteID);

                    controller.incluir(novoClientePF);
                    ultimoIDClientePF = controller.getUltimoID();

                    salvarSharedPreferences();

                    Intent intent;

                    if (isPessoaFisica)
                        intent = new Intent(ClientePessoaFisicaActivity.this,
                                CredencialDeAcessoActivity.class);
                    else
                        intent = new Intent(ClientePessoaFisicaActivity.this,
                                ClientePessoaJuridicaActivity.class);
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

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder lerPoliticaDeUso = new AlertDialog.Builder(
                        ClientePessoaFisicaActivity.this);
                lerPoliticaDeUso.setIcon(R.drawable.especializada);
                lerPoliticaDeUso.setTitle(R.string.chamadaCancelar);
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

    private void initFormulario() {

        editCPF = findViewById(R.id.editCPF);
        editNomeCompleto = findViewById(R.id.editNomeCompleto);

        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCancelar = findViewById(R.id.btnCancelar);

        novoClientePF = new ClientePF();
        novoVip = new Cliente();

        controller = new ClientePFController(this);

        restaurarSharedPreferences();
    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editCPF.getText().toString())) {
            editCPF.setError("*");
            editCPF.requestFocus();
            retorno = false;
        }
        if(!AppUtil.isCPF(editCPF.getText().toString())){

            editCPF.setError("*");
            editCPF.requestFocus();
            retorno = false;

            Toast.makeText(this, R.string.cpfInvalido, Toast.LENGTH_LONG).show();

        }else{

            editCPF.setText(AppUtil.mascaraCPF(editCPF.getText().toString()));

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

        dados.putString("cpf", editCPF.getText().toString());
        dados.putString("nomeCompleto", editNomeCompleto.getText().toString());
        dados.putInt("ultimoIDClientePF", ultimoIDClientePF);

        dados.apply();
    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
        clienteID = preferences.getInt("clienteID", -1);

        novoVip.setId(clienteID); //My Add

    }
}