package br.com.appclientesvip.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.appclientesvip.R;
import br.com.appclientesvip.api.AppUtil;
import br.com.appclientesvip.controller.ClienteController;
import br.com.appclientesvip.controller.ClientePFController;
import br.com.appclientesvip.controller.ClientePJController;
import br.com.appclientesvip.model.Cliente;
import br.com.appclientesvip.model.ClientePF;
import br.com.appclientesvip.model.ClientePJ;

public class MainActivity extends AppCompatActivity {

    Cliente cliente;
//    ClientePF clientePF;
//    ClientePJ clientePJ;
    ClienteController controller;
    ClientePFController controllerPF;
    ClientePJController controllerPJ;

   TextView txtTitulo, txtDataAtual, txtHoraAtual;

    private SharedPreferences preferences;

    List<Cliente> clientes;
    List<String> cidades;

    Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDataAtual = findViewById(R.id.txtDataAtual);
        txtHoraAtual = findViewById(R.id.txtHoraAtual);

        txtDataAtual.setText(AppUtil.getDataAtual());
        txtHoraAtual.setText(AppUtil.getHoraAtual());

        initFormulario();

//        buscarListaDeClientes();



    }

//    private void buscarListaDeClientes() {
//
//        clientes = new ArrayList<>();
////        clientes.add(cliente);
//
////        Cliente novoCliente01 = new Cliente();
////        novoCliente01.setPrimeiroNome("Novo cliente 01");
////        clientes.add(novoCliente01);
////
////        Cliente novoCliente02 = new Cliente();
////        novoCliente02.setPrimeiroNome("Novo cliente 02");
////        clientes.add(novoCliente02);
//
//        for (int i = 0; i < 10; i++) {
//
//            cliente = new Cliente();
//            cliente.setPrimeiroNome("Cliente nº" +i);
//
//            clientes.add(cliente);
//        }
//
//
//        for( Cliente obj: clientes){
//
//            Log.i(AppUtil.LOG_APP, "Obj: "+ obj.getPrimeiroNome() );
//
//        }
//
//    }

    private void initFormulario() {

        cliente = new Cliente();
//        clientePF = new ClientePF();
//        clientePJ = new ClientePJ();
        controller = new ClienteController(this);
        controllerPF = new ClientePFController(this);
        controllerPJ = new ClientePJController(this);

        txtTitulo = findViewById(R.id.txtWlcmFull);

        restaurarSharedPreferences();

        txtTitulo.setText(cliente.getPrimeiroNome() + ", " + getString(R.string.welcomeFulano));
    }

    public void meusDados(View view) {

        Intent intent = new Intent(MainActivity.this, MeusDadosActivity.class);
        startActivity(intent);


    }
//    public void meusDados(View view) {
//
//        /**
//         *    <boolean name="pessoaFisica" value="false" />
//         *     <string name="emailCliente">teste@teste.com</string>
//         *     <string name="primeiroNome">Arthur</string>
//         *     <boolean name="simplesNacional" value="true" />
//         *     <boolean name="loginAutomatico" value="true" />
//         *     <string name="cnpj">99888666000135</string>
//         *     <string name="sobreNome">Doylle</string>
//         *     <string name="dataAbertura">21/10/2001</string>
//         *     <boolean name="mei" value="true" />
//         *     <string name="senha">12345</string>
//         *     <string name="cpf">44433322211</string>
//         *     <string name="razaoSocial">SHERLOCK ME</string>
//         *     <string name="email">teste@teste.com</string>
//         *     <string name="nomeCompleto">Arthur Connan Doylle</string>
//         */
//
//        Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE ***");
//        Log.i(AppUtil.LOG_APP, "ID: " + cliente.getId());
//        Log.i(AppUtil.LOG_APP, "Primeiro Nome: " + cliente.getPrimeiroNome());
//        Log.i(AppUtil.LOG_APP, "Sobreome: " + cliente.getSobreNome());
//        Log.i(AppUtil.LOG_APP, "Email: " + cliente.getEmail());
//        Log.i(AppUtil.LOG_APP, "Senha: " + cliente.getSenha());
//        Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE  PF***");
//        Log.i(AppUtil.LOG_APP, "Nome Completo: " + clientePF.getNomeCompleto());
//        Log.i(AppUtil.LOG_APP, "CPF: " + clientePF.getCpf());
//
//        if (!cliente.isPessoaFisica()) {
//            Log.i(AppUtil.LOG_APP, "***********************");
//            Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE PJ***");
//            Log.i(AppUtil.LOG_APP, "CNPJ: " + clientePJ.getCnpj());
//            Log.i(AppUtil.LOG_APP, "Razão Social" + clientePJ.getRazaoSocial());
//            Log.i(AppUtil.LOG_APP, "Data Abertura: " + clientePJ.getDataAbertura());
//            Log.i(AppUtil.LOG_APP, "Simples Nacional: " + clientePJ.isSimplesNacional());
//            Log.i(AppUtil.LOG_APP, "MEI: " + clientePJ.isMei());
//        }
//
//    }

    public void atualizarMeusDados(View view) {


        Intent intent = new Intent(MainActivity.this, AtualizarMeusActivity.class);
        startActivity(intent);

    }

//    public void atualizarMeusDados(View view) {
//
//        if(cliente.isPessoaFisica()){
//
//            cliente.setPrimeiroNome("User");
//            cliente.setSobreNome("Default");
//
//            clientePF.setNomeCompleto("Default User da Silva");
//
//           salvarSharedPreferences();
//
//            Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE ***");
//            Log.i(AppUtil.LOG_APP, "Primeiro Nome: " + cliente.getPrimeiroNome());
//            Log.i(AppUtil.LOG_APP, "Sobreome: " + cliente.getSobreNome());
//            Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE PF ***");
//            Log.i(AppUtil.LOG_APP, "Nome Completo: " + clientePF.getNomeCompleto());
//
//        }else{
//
//            clientePJ.setRazaoSocial("COMPANNY ME");
//
//            Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE PJ ***");
//            Log.i(AppUtil.LOG_APP, "Razão Social: " + clientePJ.getRazaoSocial());
//
//        }
//    }


    public void sairApp(View view) {

        AlertDialog.Builder meuAlert = new AlertDialog.Builder(
                MainActivity.this);
        meuAlert.setIcon(R.drawable.especializada);
        meuAlert.setTitle(R.string.btnSairApp);
        meuAlert.setMessage(R.string.sairCertesa);
        meuAlert.setCancelable(false);
        meuAlert.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            String user = cliente.getPrimeiroNome();
            String r = getString(R.string.volteSempre);
            String res = user + ", " + r;

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        res,
                        Toast.LENGTH_LONG).show();
                finish();
                return;
            }
        });
        meuAlert.setNegativeButton(R.string.nao, null);
        meuAlert.create().show();

    }

//    public void excluirMinhaConta(View view) {
//
//        new FancyAlertDialog.Builder(MainActivity.this)
//                .setTitle("EXCLUIR SUA CONTA")
//                .setBackgroundColor(Color.parseColor("#303F9F"))
//                .setMessage("Confirma EXCLUSÃO definitiva da sua conta do aplicativo?")
//                .setNegativeBtnText("RETORNAR")
//                .setNegativeBtnBackground(Color.parseColor("#FF4081"))
//                .setPositiveBtnText("SIM")
//                .setPositiveBtnBackground(Color.parseColor("#4ECA25"))
//                .isCancellable(true)
//                .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
//                .OnPositiveClicked(new FancyAlertDialogListener() {
//                    @Override
//                    public void OnClick() {
//
//                        cliente.setClientePF(controllerPF.getClientePFByFK(cliente.getId()));
//
//                        if(!cliente.isPessoaFisica()) {
//
//                            cliente.setClientePJ(controllerPJ.getClientePJByFK(cliente.getClientePF().getId()));
//                            controllerPJ.deletar(cliente.getClientePJ());
//
//                        }
//
//                        controllerPF.deletar(cliente.getClientePF());
//                        controller.deletar(cliente);
//
//                        salvarSharedPreferences();
//
//                        Toast.makeText(getApplicationContext(), cliente.getPrimeiroNome() + ", sua conta foi excluída, esperamos que retorne em breve...", Toast.LENGTH_SHORT).show();
//
//                    }
//                })
//                .OnNegativeClicked(new FancyAlertDialogListener() {
//                    @Override
//                    public void OnClick() {
//                        Toast.makeText(getApplicationContext(), cliente.getPrimeiroNome() + ", divirta-se com as opções do aplicativo...", Toast.LENGTH_SHORT).show();
//
//                    }
//                })
//                .build();
//    }

    public void excluirConta(View view) {

        AlertDialog.Builder meuAlert = new AlertDialog.Builder(
                MainActivity.this);
        meuAlert.setIcon(R.drawable.especializada);
        meuAlert.setTitle(R.string.txtExcluirConta);
        meuAlert.setMessage(R.string.excluirCertesa);
        meuAlert.setCancelable(false);
        meuAlert.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            String user = cliente.getPrimeiroNome();
            String r = getString(R.string.excluidoConta);
            String res = user + ", " + r;

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        res,
                        Toast.LENGTH_LONG).show();
//                cliente = new Cliente();
//                clientePF = new ClientePF();
//                clientePJ = new ClientePJ();
                //Lembrar senha automatico teremos de resetar
                cliente.setClientePF(controllerPF.getClientePFByFK(cliente.getId()));

                if(!cliente.isPessoaFisica()) {

                    cliente.setClientePJ(controllerPJ.getClientePJByFK(cliente.getClientePF().getId()));
                    controllerPJ.deletar(cliente.getClientePJ());

                }

                controllerPF.deletar(cliente.getClientePF());
                controller.deletar(cliente);

               salvarSharedPreferences();

                finish();
                return;
            }
        });
        meuAlert.setNegativeButton(R.string.nao, null);
        meuAlert.create().show();

    }

    private void buscarListaDeClientes() {

        cidades = new ArrayList<>();


        clientes = new ArrayList<>();


        cidades.add("Brasília");
        cidades.add("Campo Grande");
        cidades.add("São Paulo");
        cidades.add("Curitiba");

        for (int i = 0; i < 10; i++) {


            cliente = new Cliente();
            cliente.setPrimeiroNome("Cliente nº "+i);

            clientes.add(cliente);

        }


        for (String obj: cidades) {
            Log.i(AppUtil.LOG_APP,"Obj: "+obj);


        }


    }

    public void consultarClientesVip(View view) {
        Intent intent = new Intent(MainActivity.this,
                ConsultarClientesActivity.class);
        startActivity(intent);

    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.clear();
        dados.apply();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

        cliente.setPrimeiroNome(preferences.getString("primeiroNome", "EMPTY"));
        cliente.setSobreNome(preferences.getString("sobreNome", "EMPTY"));
        cliente.setEmail(preferences.getString("email", "EMPTY"));
        cliente.setSenha(preferences.getString("senha", "EMPTY"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica", true));
        cliente.setId(preferences.getInt("clienteID", -1));

//        clientePF.setCpf(preferences.getString("cpf", "EMPTY"));
//        clientePF.setNomeCompleto(preferences.getString("nomeCompleto", "EMPTY"));
//
//        clientePJ.setCnpj(preferences.getString("cnpj", "EMPTY"));
//        clientePJ.setRazaoSocial(preferences.getString("razaoSocial", "EMPTY"));
//        clientePJ.setDataAbertura(preferences.getString("dataAbertura", "EMPTY"));
//        clientePJ.setSimplesNacional(preferences.getBoolean("simplesNacional", false));
//        clientePJ.setMei(preferences.getBoolean("mei", false));


    }
}