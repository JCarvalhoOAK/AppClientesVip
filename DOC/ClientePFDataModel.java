package app.daazi.v2.appclientevip.datamodel;

// MOR - Modelo Objeto Relacional - SQLserver, Oracle, Postgress

public class ClientePFDataModel {


    /**
     *     private int fk;
     *     private String cpf;
     *     private String nomeCompleto;
     */

    private static final String TABELA = "clientePF";

    private static final String ID = "id";
    private static final String FK = "clienteID";
    private static final String CPF = "cpf";
    private static final String NOME_COMPLETO = "nomeCompleto";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static String query;

    /**
     * CREATE TABLE clientePF (
     *     id           INTEGER PRIMARY KEY AUTOINCREMENT,
     *     clienteID    INTEGER,
     *     cpf          TEXT,
     *     nomeCompleto TEXT,
     *     datainc      TEXT,
     *     dataalt      TEXT,
     *     FOREIGN KEY (
     *         clienteID
     *     )
     *     REFERENCES cliente (id)
     * );
     * @return
     */

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FK+" INTEGER, ";
        query += CPF+" TEXT, ";
        query += NOME_COMPLETO+" TEXT, ";
        query += DATA_INC+" TEXT, ";
        query += DATA_ALT+" TEXT, ";
        query += "FOREIGN KEY("+FK+") REFERENCES cliente(id) ";

        query += ")";

        return query;
    }



}
