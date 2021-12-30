package br.com.appclientesvip.api;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import br.com.appclientesvip.datamodel.ClienteDataModel;
import br.com.appclientesvip.datamodel.ClientePFDataModel;
import br.com.appclientesvip.datamodel.ClientePJDataModel;

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

    SQLiteDatabase db;

    public AppDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Criar as tabelas
        //String sqlTabelaCliente = "CREATE TABLE cliente (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, email TEXT, status INTEGER, datainc TEXT, dataalt TEXT)";

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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Atualizar o DB inclusive as TAbelas

    }
}
