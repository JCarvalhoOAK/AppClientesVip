package br.com.appclientesvip.api;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AppDataBase extends SQLiteOpenHelper {

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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Atualizar o DB inclusive as TAbelas

    }
}
