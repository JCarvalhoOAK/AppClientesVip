package br.com.appclientesvip.datamodel;

//MOR - Modelo Objeto Relacional - SQLserver, Oracle, Postgress

public class CollaboratorDataModel {

    /**
     *     private int id;
     *     private String primeiroNome;
     *     private String sobreNome;
     *     private String email;
     *     private String senha;
     *     private boolean pessoaFisica;
     */

    public static final String TABELA = "collaborator";

    public static final String ID = "id";
    public static final String FK = "clientPPID";
    public static final String TYPE = "type";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query+= ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query+= FK+ " INTEGER, ";
        query+= TYPE+ " TEXT, ";
        query+= DATA_INC+ " datetime default current_timestamp, ";
        query+= DATA_ALT+ " datetime default current_timestamp ";
        query+=")";

        return query;

    }


}
