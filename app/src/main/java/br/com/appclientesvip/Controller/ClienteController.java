package br.com.appclientesvip.Controller;

import br.com.appclientesvip.model.Cliente;

public class ClienteController {

    public static boolean validarDadosDoCliente( Cliente cliente, String email, String senha){

        boolean retorno = ((cliente.getEmail().equals(email)) && (cliente.getSenha().equals(senha)));

        return retorno;

    }

//    Cliente Fake
    public static Cliente getClienteFake(){
        Cliente fake = new Cliente();
        fake.setPrimeiroNome("Jay");
        fake.setSobreNome("Oak");
        fake.setEmail("mr.j.oak@gmail.com");
        fake.setSenha("12345");
        fake.setPessoaFisica(true);

        return  fake;
    }

}
