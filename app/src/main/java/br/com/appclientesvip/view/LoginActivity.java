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

import br.com.appclientesvip.controller.ClienteController;
import br.com.appclientesvip.R;
import br.com.appclientesvip.api.AppUtil;
import br.com.appclientesvip.model.Cliente;


public class LoginActivity extends AppCompatActivity {

//    private android.app.AlertDialog dialog;

    Cliente cliente;

    private SharedPreferences preferences;

    TextView txtRecuperarSenha, txtLerPolitica, btnSejaVipNovo;
    EditText editEmail, editSenha;
    CheckBox chLembrar;
    Button btnAcessar, btnSejaVip;

//    ImageView imgBackGround, imgLogo;

    boolean isFormularioOK, isLembrarSenha;

    ClienteController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initFormulario();
        
//        loadImages();
        
//        btnAcessar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isFormularioOK = validarFormulario()) {
//
//                    salvarSharedPreferences();
//
//                    if (validarDadosDoUsuario()) {
//                        Intent intent = new Intent(
//                                LoginActivity.this, MainActivity.class
//                        );
//                        startActivity(intent);
//                        finish();
//                        return;
//                    } else {
//                        Toast.makeText(getApplicationContext(),
//                                "Verifique os dados ...",
//                                Toast.LENGTH_LONG).show();
//                    }
//                }
//
//            }
//        });

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormularioOK = validarFormulario()) {

                    if (validarDadosDoUsuario()) {


                        salvarSharedPreferences();

                        Intent intent =
                                new Intent(LoginActivity.this,
                                        MainActivity.class);

                        startActivity(intent);
                        finish();
                        return;

                    } else {
                        Toast.makeText(getApplicationContext(),
                                R.string.verifiqueDados, Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        btnSejaVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        LoginActivity.this, ClienteVipActivity.class
                );
                startActivity(intent);
                finish();
                return;

            }
        });

        txtRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Carregando tela de recuperação de senha ...",
                        Toast.LENGTH_LONG).show();
            }
        });



        txtLerPolitica.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {

                  AlertDialog.Builder lerPoliticaDeUso = new AlertDialog.Builder(
                          LoginActivity.this);
                  lerPoliticaDeUso.setIcon(R.drawable.especializada);
                  lerPoliticaDeUso.setTitle(R.string.txtTermoUso);
//                  lerPoliticaDeUso.setPositiveButtonIcon(R.drawable.)
                  lerPoliticaDeUso.setMessage(R.string.politicaPrivacidadeUso);
                  lerPoliticaDeUso.setCancelable(false);
                  lerPoliticaDeUso.setPositiveButton(R.string.aceitar, new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                                          Toast.makeText(getApplicationContext(),
                        R.string.chamadaAceitar,
                        Toast.LENGTH_LONG).show();

                      }
                  });
//                  lerPoliticaDeUso.setNegativeButton("Recusar", null);
                  lerPoliticaDeUso.setNegativeButton(R.string.recusar, new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          Toast.makeText(getApplicationContext(),
                                  R.string.chamadaLamentamos,
                                  Toast.LENGTH_LONG).show();
                          finish();
                          return;
                      }
                  });

                  lerPoliticaDeUso.create().show();
            }
        });


    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editEmail.getText().toString())){
            editEmail.setError("*");
            editEmail.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editSenha.getText().toString())){
            editSenha.setError("*");
            editSenha.requestFocus();
            retorno = false;
        }

        return retorno;

    }

    private void initFormulario() {

        txtRecuperarSenha = findViewById(R.id.txtRecurarSenha);
        txtLerPolitica = findViewById(R.id.txtLerPolitica);
        editEmail   = findViewById(R.id.editEmail);
        editSenha   = findViewById(R.id.editSenhaA);
        chLembrar   = findViewById(R.id.ckLembrar);
        btnAcessar  = findViewById(R.id.btnAcessar);
        btnSejaVip  = findViewById(R.id.btnSejaVip);
//        btnSejaVipNovo  = findViewById(R.id.btnSejaVipNovo);
//        imgBackGround = findViewById(R.id.imgBackground);
//        imgLogo =   findViewById(R.id.imgLogo);


        isFormularioOK = false;

        controller = new ClienteController(getApplicationContext());

//        clienteFake = ClienteController.getClienteFake();
        cliente= new Cliente();

//---------------------------------------------
//       for(int i =0; i< 30; i++) {
//           cliente.setPrimeiroNome("Novo " + i);
//           cliente.setSobreNome("Sobrenome" + i);
//           cliente.setEmail(i + "@teste.com");
//           cliente.setSenha(i + "12345");
//           cliente.setPessoaFisica(false);
//
//           controller.incluir(cliente);
//       }

//---------------------------------------------
//        cliente.setPrimeiroNome("Novo "+i);
//        cliente.setSobreNome("Sobrenome"+i);
//        cliente.setEmail(i+"@teste.com");
//        cliente.setSenha(i+"12345");
//        cliente.setPessoaFisica(false);
//        controller.incluir(cliente);

//---------------------------------------------
//        cliente.setId(1);
//        cliente.setPrimeiroNome("Alterado");
//        cliente.setSobreNome("DINUovo");
//        cliente.setEmail("teste@teste.com");
//        cliente.setSenha("12345");
//        cliente.setPessoaFisica(true);
//        controller.alterar(cliente);

//---------------------------------------------
//        cliente.setId(20);
//        controller.deletar(cliente);

//---------------------------------------------
//        cliente.setId(1);
//        cliente.setPrimeiroNome("Alterado");
//        cliente.setSobreNome("DINUovo");
//        cliente.setEmail("teste@teste.com");
//        cliente.setSenha("12345");
//        cliente.setPessoaFisica(false);
//        List<Cliente> clientes =controller.listar();

//---------------------------------------------
//        controller.incluir(cliente);
//        controller.alterar(cliente);
//        controller.deletar(cliente);
//        List<Cliente> clientes =controller.listar();

//---------------------------------------------
        restaurarSharedPreferences();


    }


//    private void loadImages(){
//
//
//        Picasso.with(this)
//                .load(AppUtil.URL_IMG_BACKGROUD)
//                .placeholder(R.drawable.carregando_animacao)
//                .into(imgBackground);
//
//
//
//        Picasso.with(this).load(AppUtil.URL_IMG_LOGO).placeholder(R.drawable.carregando_animacao).into(imgLogo);
//
//    }


    public void lembrarSenha(View view) {

        isLembrarSenha =chLembrar.isChecked();

    }

    // TODO: Implementar Validação senha MD5
    public boolean validarDadosDoUsuario() {

        boolean retorno = false;

        // comparar as senhas

        String senhaDigitadaPura = editSenha.getText().toString();
        String senhaMD5 = cliente.getSenha();

        if(senhaMD5.equals(AppUtil.gerarMD5Hash(senhaDigitadaPura))){
            retorno = true;
        }


        return retorno;

    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putBoolean("loginAutomatico", isLembrarSenha);
        dados.putString("emailCliente", editEmail.getText().toString());
        dados.apply();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

        cliente.setEmail(preferences.getString("email", "teste@teste.com"));
        cliente.setSenha(preferences.getString("senha", "12345"));
        cliente.setPrimeiroNome(preferences.getString("primeiroNome", "User"));
        cliente.setSobreNome(preferences.getString("sobreNome", "One"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica", true));

        isLembrarSenha = preferences.getBoolean("loginAutomatico", false);

    }
}