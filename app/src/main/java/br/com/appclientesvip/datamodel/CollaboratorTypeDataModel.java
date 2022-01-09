package br.com.appclientesvip.datamodel;

//MOR - Modelo Objeto Relacional - SQLserver, Oracle, Postgress

public class CollaboratorTypeDataModel {

    /**
     *     private int id;
     *     private String primeiroNome;
     *     private String sobreNome;
     *     private String email;
     *     private String senha;
     *     private boolean pessoaFisica;
     */

    public static final String TABELA = "collaboratorType";

    public static final String ID = "id";
    public static final String NAME = "name";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query+= ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query+= NAME+ " TEXT, ";
        query+= DATA_INC+ " datetime default current_timestamp, ";
        query+= DATA_ALT+ " datetime default current_timestamp ";
        query+=")";

        return query;

    }


}
