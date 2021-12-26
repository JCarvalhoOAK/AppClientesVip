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


public class LoginActivity extends AppCompatActivity {

    private android.app.AlertDialog dialog;

    Cliente clienteFake;

    private SharedPreferences preferences;

    TextView txtRecuperarSenha, txtLerPolitica;
    EditText editEmail, editSenha;
    CheckBox chLembrar;
    Button btnAcessar, btnSejaVip;

    boolean isFormularioOK, isLembrarSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initFormulario();
        
        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {

                    salvarSharedPreferences();

                    if (validarDadosDoUsuario()) {
                        Intent intent = new Intent(
                                LoginActivity.this, MainActivity.class
                        );
                        startActivity(intent);
                        finish();
                        return;
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Verifique os dados ...",
                                Toast.LENGTH_LONG).show();
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
                  lerPoliticaDeUso.setTitle("TERMO DE USO");
//                  lerPoliticaDeUso.setPositiveButtonIcon(R.drawable.)
                  lerPoliticaDeUso.setMessage("Termos e condições de uso é o documento pelo qual um site ou um aplicativo define as regras da utilização de seu serviço pelos usuários. Ou seja, o dono da aplicação determina as condições de uso do site, seja ele gratuito ou pago. Os usuários são informados sobre regras como idade mínima, cadastro, pagamento, direitos autorais e responsabilidades.\n" +
                          "\n" +
                          "Nesse sentido, esse instrumento estabelece a relação contratual entre o dono da aplicação (ex. você, sua empresa) e o usuário final. Assim, define-se exatamente qual serviço está sendo oferecido. Os termos de uso para aplicativo de entrega de comida, por exemplo, devem deixar bem claro que a empresa apenas presta serviços de tecnologia e entrega, enquanto que os restaurantes são responsáveis pela comida. Assim, o instrumento serve para delimitar as responsabilidades do seu site ou aplicativo. Ainda, limita-se a responsabilidade da plataforma em relação a serviços ou bens fornecidos por terceiros.\n" +
                          "\n" +
                          "Por fim, os termos devem sempre transmitir com clareza o que está sendo aceito pelo usuário. Inclusive dando aceite à política de uso de dados pessoais na plataforma, representada por outro documento, a Política de Privacidade. Os Termos de uso e a Política de privacidade são, em conjunto, documentos importantes para um site ou aplicativo definir bem as regras de uso de seu serviço e assim evitar problemas futuros.");
                  lerPoliticaDeUso.setCancelable(false);
                  lerPoliticaDeUso.setPositiveButton("Aceitar", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                                          Toast.makeText(getApplicationContext(),
                        "Obrigado por aceitar, continue com seu cadastro!",
                        Toast.LENGTH_LONG).show();

                      }
                  });
//                  lerPoliticaDeUso.setNegativeButton("Recusar", null);
                  lerPoliticaDeUso.setNegativeButton("Recusar", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          Toast.makeText(getApplicationContext(),
                                  "Lamentamos, mas é necessario aceitar para prosseguir!",
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


        isFormularioOK = false;

        clienteFake = ClienteController.getClienteFake();

        restaurarSharedPreferences();


    }

    public void lembrarSenha(View view) {

        isLembrarSenha =chLembrar.isChecked();

    }

    private boolean validarDadosDoUsuario() {

        return ClienteController.validarDadosDoCliente(clienteFake,
                editEmail.getText().toString(),
                editSenha.getText().toString());

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
        isLembrarSenha = preferences.getBoolean("loginAutomatico", false);

    }
}