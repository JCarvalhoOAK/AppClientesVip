package br.com.appclientesvip.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.appclientesvip.Controller.ClienteController;
import br.com.appclientesvip.R;
import br.com.appclientesvip.api.ClienteAdapter;
import br.com.appclientesvip.model.Cliente;

public class ConsultarClientesActivity extends AppCompatActivity {

    List<Cliente> clientes;
    ClienteAdapter clienteAdapter;

    Cliente obj;
    ClienteController controller;

    RecyclerView rvClientesVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_clientes);

        rvClientesVip = findViewById(R.id.rvClientesVip);

        controller = new ClienteController(getApplicationContext());

        clientes = controller.listar();
//        clientes = new ArrayList<>();

        for (int i = 0; i < 50; i++) {

            obj = new Cliente();
            obj.setPrimeiroNome("Cliente "+i);
            obj.setPessoaFisica(i % 2==0);

            clientes.add(obj);

        }

        clienteAdapter = new ClienteAdapter(clientes, getApplicationContext());

        rvClientesVip.setAdapter(clienteAdapter);
        rvClientesVip.setLayoutManager(new LinearLayoutManager(this));

    }
}