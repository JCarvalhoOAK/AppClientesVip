package br.com.appclientesvip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroUsuarioActivity extends AppCompatActivity {
    
    
    Button btnCadastro;
    EditText editNome;
    EditText editEmail;
    EditText editSenhaA;
    EditText editSenhaB;
    
    CheckBox ckTermo;
    
    boolean isFormularioOK;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        
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

                    if(isFormularioOK){
                        if(!validarSenha()){

                            editSenhaA.setError("*");
                            editSenhaB.setError("b");
                            editSenhaA.requestFocus();

                            Toast.makeText(getApplicationContext(),
                                    "As senhas digitadas não conferem ...",
                                    Toast.LENGTH_LONG).show();
                        } else {

                        }


                    }


            }
        });
        
    }

    private boolean validarSenha() {
        return true;
    }


    private void initFormulario() {
    }
}