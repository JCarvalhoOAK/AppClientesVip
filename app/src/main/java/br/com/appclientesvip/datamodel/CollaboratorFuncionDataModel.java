package br.com.appclientesvip.datamodel;

//MOR - Modelo Objeto Relacional - SQLserver, Oracle, Postgress

public class CollaboratorFuncionDataModel {

    /**
     *     private int id;
     *     private String primeiroNome;
     *     private String sobreNome;
     *     private String email;
     *     private String senha;
     *     private boolean pessoaFisica;
     */

    public static final String TABELA = "collaboratorFuncion";

    public static final String ID = "id";
    public static final String ID_COLLABORATOR = "idCollaborator";
    public static final String ID_COLLABORATOR_TYPE = "idCollaboratorType";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query+= ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query+= ID_COLLABORATOR+ " INTEGER, ";
        query+= ID_COLLABORATOR_TYPE+ " INTEGER, ";
        query+= DATA_INC+ " datetime default current_timestamp, ";
        query += DATA_ALT+" datetime default current_timestamp, ";
        query += "FOREIGN KEY("+ID_COLLABORATOR+") REFERENCES collaborator(id), ";
        query += "FOREIGN KEY("+ID_COLLABORATOR_TYPE+") REFERENCES collaboratorType(id) ";

        query += ")";

        return query;

    }


}
