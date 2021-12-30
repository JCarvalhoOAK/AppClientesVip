package br.com.appclientesvip.datamodel;

//MOR - Modelo Objeto Relacional - SQLserver, Oracle, Postgress

public class ClienteDataModel {

    /**
     *     private int id;
     *     private String primeiroNome;
     *     private String sobreNome;
     *     private String email;
     *     private String senha;
     *     private boolean pessoaFisica;
     */

    private static final String TABELA = "cliente";
    private static final String ID = "id";
    private static final String PRIMEIRO_NOME = "primeiroNome";
    private static final String SOBRE_NOME = "sobreNome";
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";
    private static final String PESSOA_FISICA = "pessoaFisica";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query+= ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query+= PRIMEIRO_NOME+ " TEXT, ";
        query+= SOBRE_NOME+ " TEXT, ";
        query+= EMAIL+ " TEXT, ";
        query+= SENHA+ " TEXT, ";
        query+= PESSOA_FISICA+ " INTEGER, ";
        query+= DATA_INC+ " TEXT, ";
        query+= DATA_ALT+ " TEXT ";
        query+=")";

        return query;

    }


}
