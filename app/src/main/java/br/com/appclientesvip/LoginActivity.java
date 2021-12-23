package br.com.appclientesvip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView txtRecuperarSenha, txtLerPolitica;
    EditText editEmail, editSenha;
    CheckBox chLembrar;
    Button btnAcessar, btnSejaVip;

    boolean isFormularioOK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initFormulario();

        txtRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Carregando tela de recuperação de senha ...",
                        Toast.LENGTH_LONG).show();
            }
        });

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


    }
}