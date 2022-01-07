package br.com.appclientesvip.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.appclientesvip.controller.ClienteController;
import br.com.appclientesvip.R;
import br.com.appclientesvip.api.AppUtil;
import br.com.appclientesvip.model.Cliente;

public class CredencialDeAcessoActivity extends AppCompatActivity {

    Button btnCadastro;
    EditText editNome;
    EditText editEmail;
    EditText editSenhaA;
    EditText editSenhaB;

    CheckBox ckTermo;

    boolean isFormularioOK, isPessoaFisica;

    private SharedPreferences preferences;

    Cliente cliente;
    ClienteController controller;

    int clienteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credencial_de_acesso);

        initFormulario();

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), CadastreseActivity.class);
//                startActivity(intent);
                isFormularioOK = true;

                if (TextUtils.isEmpty(editNome.getText().toString())){
                    editNome.setError("*");
                    editNome.requestFocus();
                    isFormularioOK = false;
                }

                if (TextUtils.isEmpty(editEmail.getText().toString())){
                    editEmail.setError("*");
                    editEmail.requestFocus();
                    isFormularioOK = false;
                }

                if (TextUtils.isEmpty(editSenhaA.getText().toString())){
                    editSenhaA.setError("*");
                    editSenhaA.requestFocus();
                    isFormularioOK = false;
                }

                if (TextUtils.isEmpty(editSenhaB.getText().toString())){
                    editSenhaB.setError("*");
                    editSenhaB.requestFocus();
                    isFormularioOK = false;
                }
                if(!ckTermo.isChecked()){
                    isFormularioOK = false;
                }

                if(isFormularioOK){
                    if(!validarSenha()){

                        editSenhaA.setError("*");
                        editSenhaB.setError("b");
                        editSenhaA.requestFocus();

//                        Toast.makeText(getApplicationContext(),
//                                R.string.senhasDiferentes,
//                                Toast.LENGTH_LONG).show();
                            AlertDialog.Builder lerPoliticaDeUso = new AlertDialog.Builder(
                                    CredencialDeAcessoActivity.this);
                            lerPoliticaDeUso.setIcon(R.drawable.especializada);
                            lerPoliticaDeUso.setTitle(R.string.atencao);
                            lerPoliticaDeUso.setMessage(R.string.senhasDiferentes);
                            lerPoliticaDeUso.setCancelable(true);
                            lerPoliticaDeUso.setPositiveButton(R.string.continuar, null);

                            lerPoliticaDeUso.create().show();

                    } else {

                        cliente.setEmail(editEmail.getText().toString());
                        cliente.setSenha(editSenhaA.getText().toString());

                        controller.alterar(cliente);

                        salvarSharedPreferences();

                        Intent iMenuPrincipal = new Intent(CredencialDeAcessoActivity.this, LoginActivity.class);
                        startActivity(iMenuPrincipal);
                        finish();
                        return;
                    }
                }
            }
        });

    }

    private void initFormulario() {

        btnCadastro = findViewById(R.id.btnCadastroUser);

        editNome = findViewById(R.id.editNomeUser);
        editEmail = findViewById(R.id.editEmail);
        editSenhaA = findViewById(R.id.editSenhaA);
        editSenhaB = findViewById(R.id.editSenhaB);

        ckTermo = findViewById(R.id.ckTermo);

        isFormularioOK = false;

        cliente = new Cliente();
        controller= new ClienteController(this);

        restaurarSharedPreferences();

    }

    public void validarTermo(View view){

        if(!ckTermo.isChecked()){

            Toast.makeText(getApplicationContext(),
                    R.string.termoNecessario,
                    Toast.LENGTH_LONG).show();

        }

    }

    public boolean validarSenha() {

        boolean retorno = false;

        int senhaA, senhaB;

        senhaA = Integer.parseInt(editSenhaA.getText().toString());
        senhaB = Integer.parseInt(editSenhaB.getText().toString());

        retorno=(senhaA == senhaB);

        return retorno;
    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("email", editEmail.getText().toString());
        dados.putString("senha",AppUtil.gerarMD5Hash(editSenhaA.getText().toString()));
        dados.apply();
    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

        clienteID = preferences.getInt("clienteID", -1);
        String primeiroNome = preferences.getString("primeiroNome", "");
        String sobreNome = preferences.getString("sobreNome", "");
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);

        cliente.setId(clienteID);
        cliente.setPrimeiroNome(primeiroNome);
        cliente.setSobreNome(sobreNome);
        cliente.setPessoaFisica(isPessoaFisica);

        if (isPessoaFisica)
            editNome.setText(preferences.getString("nomeCompleto", "Verifique os dados"));
        else
            editNome.setText(preferences.getString("razaoSocial", "Verifique os dados"));
    }
}