package br.com.appclientesvip.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.appclientesvip.R;
import br.com.appclientesvip.api.AppUtil;
import br.com.appclientesvip.controller.ClienteController;
import br.com.appclientesvip.controller.ClientePFController;
import br.com.appclientesvip.controller.ClientePJController;
import br.com.appclientesvip.model.Cliente;

public class MeusDadosActivity extends AppCompatActivity {

    // Card Cliente
    EditText editPrimeiroNome, editSobreNome;
    CheckBox chPessoaFisica;

    // Card ClientePF
    EditText editCPF, editNomeCompleto;

    // Card ClientePJ
    EditText editCNPJ,editRazaoSocial, editDataAberturaPJ;
    CheckBox chSimplesNacional, chMEI ;

    // Card Credenciais
    EditText editEmail,editSenhaA;

    Button btnVoltar;

    Cliente cliente;

    ClienteController controller;
    ClientePFController controllerPF;
    ClientePJController controllerPJ;

    SharedPreferences preferences;

    int clienteID;
    boolean isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);


        restaurarSharedPreferences();

        initFormulario();

        polularFormulario();

    }
    private void polularFormulario() {

        if(clienteID>=1){

            cliente = controller.getClienteByID(cliente);
            cliente.setClientePF(controllerPF.getClientePFByFK(cliente.getId()));

            if(!cliente.isPessoaFisica())
                cliente.setClientePJ(controllerPJ.getClientePJByFK(cliente.getClientePF().getId()));


            // Dados Obj Cliente
            editPrimeiroNome.setText(cliente.getPrimeiroNome());
            editSobreNome.setText(cliente.getSobreNome());
            editEmail.setText(cliente.getEmail());
            editSenhaA.setText(cliente.getSenha());
            chPessoaFisica.setChecked(cliente.isPessoaFisica());

            // Dados Pessoa Física Obj Cliente
            editCPF.setText(cliente.getClientePF().getCpf());
            editNomeCompleto.setText(cliente.getClientePF().getNomeCompleto());

            // Dados Pessoa Júridica Obj Cliente
            if(!cliente.isPessoaFisica()){


                editCNPJ.setText(cliente.getClientePJ().getCnpj());
                editRazaoSocial.setText(cliente.getClientePJ().getRazaoSocial());
                editDataAberturaPJ.setText(cliente.getClientePJ().getDataAbertura());
                chSimplesNacional.setChecked(cliente.getClientePJ().isSimplesNacional());
                chMEI.setChecked(cliente.getClientePJ().isMei());

            }


        }else{

//            new FancyAlertDialog.Builder(MeusDadosActivity.this)
//                    .setTitle("ATENÇÃO")
//                    .setBackgroundColor(Color.parseColor("#303F9F"))
//                    .setMessage("Não foi possível, recuperar os dados do cliente?")
//                    .setNegativeBtnText("RETORNAR")
//                    .setNegativeBtnBackground(Color.parseColor("#FF4081"))
//                    .isCancellable(true)
//                    .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
//                    .OnNegativeClicked(new FancyAlertDialogListener() {
//                        @Override
//                        public void OnClick() {
//
//                            Intent intent = new Intent(MeusDadosActivity.this, MainActivity.class);
//                            startActivity(intent);
//
//                        }
//                    })
//                    .build();
            //-------------------------------------
            AlertDialog.Builder meuAlert = new AlertDialog.Builder(
                    MeusDadosActivity.this);
            meuAlert.setIcon(R.drawable.especializada);
            meuAlert.setTitle(R.string.atencao);
            meuAlert.setMessage(R.string.naoRecuperou);
            meuAlert.setCancelable(true);
//            meuAlert.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
//                String user = cliente.getPrimeiroNome();
//                String r = getString(R.string.volteSempre);
//                String res = user + ", " + r;
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(getApplicationContext(),
//                            res,
//                            Toast.LENGTH_LONG).show();
//                    finish();
//                    return;
//                }
//            });
            meuAlert.setNegativeButton(R.string.retornar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            meuAlert.create().show();

        }

    }

    private void initFormulario() {


        // Card Cliente
        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobreNome = findViewById(R.id.editSobreNome);
        chPessoaFisica = findViewById(R.id.chPessoaFisica);

        // Card ClientePF
        editCPF = findViewById(R.id.editCPF);
        editNomeCompleto = findViewById(R.id.editNomeCompleto);

        // Card ClientePJ
        editCNPJ = findViewById(R.id.editCNPJ);
        editRazaoSocial = findViewById(R.id.editRazaoSocial);
        editDataAberturaPJ = findViewById(R.id.editDataAberturaPJ);

        chSimplesNacional = findViewById(R.id.chSimplesNacional);
        chMEI = findViewById(R.id.chMEI);

        // Card Credenciais
        editEmail = findViewById(R.id.editEmail);
        editSenhaA = findViewById(R.id.editSenhaA);

        cliente = new Cliente();
        cliente.setId(clienteID);

        controller = new ClienteController(this);
        controllerPF = new ClientePFController(this);
        controllerPJ = new ClientePJController(this);

        if(!cliente.isPessoaFisica()){
            // busco os dados
        }



    }

    public void vontar(View view) {

        Intent intent = new Intent(MeusDadosActivity.this, MainActivity.class);
        startActivity(intent);

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
        clienteID = preferences.getInt("clienteID", -1);

    }
}