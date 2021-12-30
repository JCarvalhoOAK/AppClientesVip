package br.com.appclientesvip.Controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;

import br.com.appclientesvip.api.AppDataBase;
import br.com.appclientesvip.datamodel.ClienteDataModel;
import br.com.appclientesvip.model.Cliente;

public class ClienteController extends AppDataBase {

    private static final  String TABELA = ClienteDataModel.TABELA;
    private ContentValues dados;

    public ClienteController(@Nullable Context context) {
        super(context);
    }

    public boolean incluir(Cliente obj){

        dados = new ContentValues();
        dados.put(ClienteDataModel.PRIMEIRO_NOME, obj.getPrimeiroNome());
        dados.put(ClienteDataModel.SOBRE_NOME, obj.getSobreNome());
        dados.put(ClienteDataModel.EMAIL, obj.getEmail());
        dados.put(ClienteDataModel.SENHA, obj.getSenha());
        dados.put(ClienteDataModel.PESSOA_FISICA, obj.isPessoaFisica());

        return insert(TABELA, dados);

    }
    public boolean alterar(Cliente obj){

        dados = new ContentValues();
        dados.put(ClienteDataModel.ID, obj.getId());
        dados.put(ClienteDataModel.PRIMEIRO_NOME, obj.getPrimeiroNome());
        dados.put(ClienteDataModel.SOBRE_NOME, obj.getSobreNome());
        dados.put(ClienteDataModel.EMAIL, obj.getEmail());
        dados.put(ClienteDataModel.SENHA, obj.getSenha());
        dados.put(ClienteDataModel.PESSOA_FISICA, obj.isPessoaFisica());

        return update(TABELA, dados);

    }
    public boolean deletar(Cliente obj){

        return delete(TABELA, obj.getId());

    }
    public List<Cliente> listar(){

        return list(TABELA);

    }

//    public static boolean validarDadosDoCliente( Cliente cliente, String email, String senha){
//
//        boolean retorno = ((cliente.getEmail().equals(email)) && (cliente.getSenha().equals(senha)));
//
//        return retorno;
//
//    }

//    Cliente Fake
//    public static Cliente getClienteFake(){
//        Cliente fake = new Cliente();
//        fake.setPrimeiroNome("Jay");
//        fake.setSobreNome("Oak");
//        fake.setEmail("mr.j.oak@gmail.com");
//        fake.setSenha("12345");
//        fake.setPessoaFisica(true);
//
//        return  fake;
//    }

}
