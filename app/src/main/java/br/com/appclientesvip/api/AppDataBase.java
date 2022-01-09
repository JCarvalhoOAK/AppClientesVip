package br.com.appclientesvip.api;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.appclientesvip.controller.ClientePFController;
import br.com.appclientesvip.controller.ClientePJController;
import br.com.appclientesvip.datamodel.ClientPermissionDataModel;
import br.com.appclientesvip.datamodel.ClienteDataModel;
import br.com.appclientesvip.datamodel.ClientePFDataModel;
import br.com.appclientesvip.datamodel.ClientePJDataModel;
import br.com.appclientesvip.datamodel.CollaboratorDataModel;
import br.com.appclientesvip.datamodel.CollaboratorFuncionDataModel;
import br.com.appclientesvip.datamodel.CollaboratorPermissionDataModel;
import br.com.appclientesvip.datamodel.CollaboratorTypeDataModel;
import br.com.appclientesvip.datamodel.PermissionDataModel;
import br.com.appclientesvip.datamodel.TesteDataModel;
import br.com.appclientesvip.model.Cliente;
import br.com.appclientesvip.model.ClientePF;
import br.com.appclientesvip.model.ClientePJ;
import br.com.appclientesvip.model.CollaboratorType;

public class AppDataBase extends SQLiteOpenHelper {

    /**
     * TYPES
     * NULO. O valor é um valor NULL.
     *
     * INTEGER. O valor é um número inteiro com sinal, armazenado em 1, 2, 3, 4, 6 ou 8 bytes dependendo da magnitude do valor.
     *
     * REAL. O valor é um valor de ponto flutuante, armazenado como um número de ponto flutuante IEEE de 8 bytes.
     *
     * TEXT. O valor é uma string de texto, armazenada usando a codificação do banco de dados (UTF-8, UTF-16BE ou UTF-16LE).
     *
     * BLOB. O valor é um blob de dados, armazenado exatamente como foi inserido.
     */

    private static final String DB_NAME = "clienteDB.sqlite";
    private static final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db;

    Context context;

    public AppDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.context = context;

        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Criar as tabelas
        //String sqlTabelaCliente = "CREATE TABLE cliente (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, email TEXT, status INTEGER, datainc TEXT, dataalt TEXT)";
//        String sqlTabelaCliente = "CREATE TABLE clientePJ (id INTEGER PRIMARY KEY AUTOINCREMENT, clientePFID INTEGER, cnpj TEXT, " +
//                "razao_social TEXT, data_abertura TEXT, simplesNacional INTEGER, mei INTEGER, datainc datetime default current_timestamp, dataalt datetime default current_timestamp)";

        try{
           //Tenta executar o que desejamos
//            db.execSQL(sqlTabelaCliente);
            db.execSQL(ClienteDataModel.gerarTabela());
            Log.i(AppUtil.LOG_APP, "TB Cliente: " + ClienteDataModel.gerarTabela());

        }catch(SQLException e){
            //Capturar o erro
            Log.e(AppUtil.LOG_APP,"Erro TB CLiente" + e.getMessage());

        }

        try {

            // Executar o que desejamos
            db.execSQL(ClientePFDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB ClientePF: " + ClientePFDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB ClientePF: " + e.getMessage());

        }

        try {

            // Executar o que desejamos
            db.execSQL(ClientePJDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB ClientePJ: " + ClientePJDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB ClientePJ: " + e.getMessage());

        }

        try {

            // Executar o que desejamos
            db.execSQL(ClientPermissionDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB ClientPermission: " + ClientPermissionDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB ClientPermission: " + e.getMessage());

        }

        try {

            // Executar o que desejamos
            db.execSQL(CollaboratorDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB Collaborator: " + CollaboratorDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB Collaborator: " + e.getMessage());

        }

        try {

            // Executar o que desejamos
            db.execSQL(CollaboratorFuncionDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB CollaboratorFuncion: " + CollaboratorFuncionDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB CollaboratorFuncion: " + e.getMessage());

        }

        try {

            // Executar o que desejamos
            db.execSQL(CollaboratorPermissionDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB CollaboratorPermission: " + CollaboratorPermissionDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB CollaboratorPermission: " + e.getMessage());

        }

        try {

            // Executar o que desejamos
            db.execSQL(CollaboratorTypeDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB CollaboratorType: " + CollaboratorTypeDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB CollaboratorType: " + e.getMessage());

        }

        try {

            // Executar o que desejamos
            db.execSQL(PermissionDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB Permission: " + PermissionDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB Permission: " + e.getMessage());

        }

        try {

            // Executar o que desejamos
            db.execSQL(TesteDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB Teste: " + TesteDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB Teste: " + e.getMessage());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Atualizar o DB inclusive as TAbelas

    }

    /**
     * Incluir dados no Banco de Dados
     * @return
     */
    public boolean insert(String tabela, ContentValues dados){

        boolean sucesso = true;

        try{
            Log.i(AppUtil.LOG_APP,"Sucesso ao executar o INSERT(): ");
            sucesso = db.insert(tabela,null, dados)>0;

        }catch(SQLException e){
            Log.e(AppUtil.LOG_APP,"Falha ao executar o INSERT(): " +e.getMessage());
        }
        //TODO: Aqui está retornando falso, não está inserindo na tabela
        return sucesso;

    }
    /**
     * Alterar dados no Banco de Dados
     * @return
     */
    public boolean update(String tabela, ContentValues dados){

        boolean sucesso = true;
        try{

            int id = dados.getAsInteger("id");

            Log.i(AppUtil.LOG_APP,"Sucesso ao executar o UPDATE(): ");
            sucesso = db.update(tabela,dados,"id=?",new String[]{Integer.toString(id)}) > 0;

        }catch(SQLException e){
            Log.e(AppUtil.LOG_APP,"Falha ao executar o UPDATE(): " +e.getMessage());
        }

        return sucesso;

    }
    /**
     * Excluir dados no Banco de Dados
     * @return
     */
    public boolean delete(String tabela, int id){

        boolean sucesso = true;
        try{
            Log.i(AppUtil.LOG_APP,"Sucesso ao executar o DELETE(): ");
            sucesso = db.delete(tabela,"id=?",new String[]{Integer.toString(id)}) > 0;

        }catch(SQLException e){
            Log.e(AppUtil.LOG_APP,"Falha ao executar o DELETE(): " +e.getMessage());
        }

        return sucesso;

    }
    /**
     * Consultar/ Listar dados no Banco de Dados
     * @return
     */
    //ANOTATION COLOCADA POR MIM, VERIFICAR NECESSIDADE:
    @SuppressLint("Range")
    public List<Cliente> listClientes(String tabela) {

        List<Cliente> list = new ArrayList<>();

        Cliente cliente;
        ClientePFController controllerPF = new ClientePFController(context);
        ClientePJController controllerPJ = new ClientePJController(context);

        // Select no banco de dados
        // SELECT * FROM tabela

        String sql = "SELECT * FROM " + tabela;

        try {

            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {

                do {

                    cliente = new Cliente();

                    cliente.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                    cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRO_NOME)));
                    cliente.setSobreNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SOBRE_NOME)));
                    cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
                    cliente.setSenha(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA)));
                    cliente.setPessoaFisica(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.PESSOA_FISICA)) == 1);


                    cliente.setClientePF(controllerPF.getClientePFByFK(cliente.getId()));

                    if(!cliente.isPessoaFisica())
                        cliente.setClientePJ(controllerPJ.getClientePJByFK(cliente.getClientePF().getId()));


                    list.add(cliente);

                } while (cursor.moveToNext());

                Log.i(AppUtil.LOG_APP, tabela + " lista gerada com sucesso.");

            }

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados: " + tabela);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }

        return list;
    }

    @SuppressLint("Range")
    public List<ClientePF> listClientesPessoaFisica(String tabela) {

        List<ClientePF> list = new ArrayList<>();

        ClientePF clientePF;

        String sql = "SELECT * FROM " + tabela;

        try {

            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {

                do {

                    clientePF = new ClientePF();

                    clientePF.setId(cursor.getInt(cursor.getColumnIndex(ClientePFDataModel.ID)));
                    clientePF.setClienteID(cursor.getInt(cursor.getColumnIndex(ClientePFDataModel.FK)));
                    clientePF.setNomeCompleto(cursor.getString(cursor.getColumnIndex(ClientePFDataModel.NOME_COMPLETO)));
                    clientePF.setCpf(cursor.getString(cursor.getColumnIndex(ClientePFDataModel.CPF)));

                    list.add(clientePF);

                } while (cursor.moveToNext());

                Log.i(AppUtil.LOG_APP, tabela + " lista gerada com sucesso.");

            }

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados: " + tabela);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }

        return list;
    }

    @SuppressLint("Range")
    public List<ClientePJ> listClientesPessoaJuridica(String tabela) {

        List<ClientePJ> list = new ArrayList<>();

        ClientePJ clientePJ;

        String sql = "SELECT * FROM " + tabela;

        try {

            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {

                do {

                    clientePJ = new ClientePJ();

                    clientePJ.setId(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.ID)));
                    clientePJ.setClientePFID(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.FK)));
                    clientePJ.setRazaoSocial(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.RAZAO_SOCIAL)));
                    clientePJ.setCnpj(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.CNPJ)));
                    clientePJ.setDataAbertura(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.DATA_ABERTURA)));

                    clientePJ.setSimplesNacional
                            (cursor.getInt(
                                    cursor.getColumnIndex(ClientePJDataModel.SIMPLES_NACIONAL)) == 1);

                    clientePJ.setMei
                            (cursor.getInt(
                                    cursor.getColumnIndex(ClientePJDataModel.MEI)) == 1);

                    list.add(clientePJ);

                } while (cursor.moveToNext());

                Log.i(AppUtil.LOG_APP, tabela + " lista gerada com sucesso.");

            }

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados: " + tabela);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }

        return list;
    }

    @SuppressLint("Range")
    public List<CollaboratorType> listCollaboratorType(String tabela) {

        List<CollaboratorType> list = new ArrayList<>();

        CollaboratorType collaboratorType;

        String sql = "SELECT * FROM " + tabela;

        try {

            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {

                do {

                    collaboratorType = new CollaboratorType();

                    collaboratorType.setId(cursor.getInt(cursor.getColumnIndex(CollaboratorTypeDataModel.ID)));
                    collaboratorType.setName(cursor.getString(cursor.getColumnIndex(CollaboratorTypeDataModel.NAME)));

                    list.add(collaboratorType);

                } while (cursor.moveToNext());

                Log.i(AppUtil.LOG_APP, tabela + " lista gerada com sucesso.");

            }

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados: " + tabela);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }

        return list;
    }

    @SuppressLint("Range")
    public int getLastPK(String tabela) {

        // SELECT seq FROM sqlite_sequence WHERE name = "tabela"

        String sql = "SELECT seq FROM sqlite_sequence WHERE name = '" + tabela + "'";

        try {

            Log.e(AppUtil.LOG_APP, "SQL RAW: " + sql);

            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {

                do {

                    return  cursor.getInt(cursor.getColumnIndex("seq"));

                } while (cursor.moveToNext());

            }

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, "Erro recuperando último PK: " + tabela);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }

        return -1;
    }

    @SuppressLint("Range")
    public Cliente getClienteByID(String tabela, Cliente obj){

        Cliente cliente = new Cliente();

        String sql = "SELECT * FROM "+tabela+" WHERE id = "+obj.getId();

        try{

            cursor = db.rawQuery(sql, null);

            if(cursor.moveToNext()){

                cliente.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRO_NOME)));
                cliente.setSobreNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SOBRE_NOME)));
                cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
                cliente.setSenha(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA)));
                cliente.setPessoaFisica(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.PESSOA_FISICA)) == 1);

            }


        }catch (SQLException e){

            Log.e(AppUtil.LOG_APP,"Erro getClienteByID "+obj.getId());
            Log.e(AppUtil.LOG_APP,"Erro  "+e.getMessage());
        }

        return  cliente;

    }

    @SuppressLint("Range")
    public ClientePF getClientePFByFK(String tabela, int idFK){

        ClientePF clientePF = new ClientePF();

        String sql = "SELECT * FROM "+tabela+" WHERE  "+ClientePFDataModel.FK+" = "+idFK;

        try{

            cursor = db.rawQuery(sql, null);

            if(cursor.moveToNext()){

                clientePF.setId(cursor.getInt(cursor.getColumnIndex(ClientePFDataModel.ID)));
                clientePF.setClienteID(cursor.getInt(cursor.getColumnIndex(ClientePFDataModel.FK)));
                clientePF.setNomeCompleto(cursor.getString(cursor.getColumnIndex(ClientePFDataModel.NOME_COMPLETO)));
                clientePF.setCpf(cursor.getString(cursor.getColumnIndex(ClientePFDataModel.CPF)));
//                clientePF.setCpf("111.222.333-44");

            }


        }catch (SQLException e){

            Log.e(AppUtil.LOG_APP,"Erro getClientePFByFK "+idFK);
            Log.e(AppUtil.LOG_APP,"Erro  "+e.getMessage());
        }

        return  clientePF;

    }

    @SuppressLint("Range")
    public ClientePJ getClientePJByFK(String tabela, int idFK){

        ClientePJ clientePJ = new ClientePJ();

        String sql = "SELECT * FROM "+tabela+" WHERE "+ClientePJDataModel.FK+" = "+idFK;

        try{

            cursor = db.rawQuery(sql, null);

            if(cursor.moveToNext()){

                clientePJ.setId(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.ID)));
                clientePJ.setClientePFID(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.FK)));
                clientePJ.setCnpj(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.CNPJ)));
                clientePJ.setDataAbertura(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.DATA_ABERTURA)));
                clientePJ.setRazaoSocial(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.RAZAO_SOCIAL)));
                clientePJ.setSimplesNacional(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.SIMPLES_NACIONAL)) == 1);
                clientePJ.setMei(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.MEI)) == 1);


            }


        }catch (SQLException e){

            Log.e(AppUtil.LOG_APP,"Erro getClientePJByFK "+idFK);
            Log.e(AppUtil.LOG_APP,"Erro  "+e.getMessage());
        }

        return  clientePJ;

    }


}
