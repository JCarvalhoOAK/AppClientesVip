package br.com.appclientesvip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import br.com.appclientesvip.R;

public class ClientePessoaJuridica extends AppCompatActivity {

    CheckBox chSimples, chMei;
    boolean isSimplesNacional, isMei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_pessoa_juridica);

        chSimples = findViewById(R.id.chSimplesNacional);
        chMei   =   findViewById(R.id.chMEI);

    }

    public void simplesNacional(View view){

        isSimplesNacional = chSimples.isChecked();

    }

    public void mei(View view){

        isMei = chMei.isChecked();

    }
}