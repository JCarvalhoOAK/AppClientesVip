package br.com.appclientesvip.datamodel;

//MOR - Modelo Objeto Relacional - SQLserver, Oracle, Postgress

public class ClientPermissionDataModel {

    /**
     *     private int id;
     *     private String primeiroNome;
     *     private String sobreNome;
     *     private String email;
     *     private String senha;
     *     private boolean pessoaFisica;
     */

    public static final String TABELA = "clientPermission";

    public static final String ID = "id";
    public static final String ID_PERMISSION = "idPermission";
    public static final String ID_CLIENT = "idClient";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query+= ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query+= ID_PERMISSION+ " INTEGER, ";
        query+= ID_CLIENT+ " INTEGER, ";
        query+= DATA_INC+ " datetime default current_timestamp, ";
        query += DATA_ALT+" datetime default current_timestamp, ";
        query += "FOREIGN KEY("+ID_CLIENT+") REFERENCES cliente(id), ";
        query += "FOREIGN KEY("+ID_PERMISSION+") REFERENCES permission(id) ";

        query += ")";

        return query;

    }


}
