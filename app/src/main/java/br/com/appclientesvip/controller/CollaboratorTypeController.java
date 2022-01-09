package br.com.appclientesvip.controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;

import br.com.appclientesvip.api.AppDataBase;
import br.com.appclientesvip.datamodel.CollaboratorTypeDataModel;
import br.com.appclientesvip.model.CollaboratorType;

public class CollaboratorTypeController extends AppDataBase {

    private static final  String TABELA = CollaboratorTypeDataModel.TABELA;
    private ContentValues dados;

    public CollaboratorTypeController(@Nullable Context context) {
        super(context);
    }

    public boolean incluir(CollaboratorType obj){

        dados = new ContentValues();
        dados.put(CollaboratorTypeDataModel.NAME, obj.getName());

        return insert(TABELA, dados);

    }



    public boolean alterar(CollaboratorType obj){

        dados = new ContentValues();

        dados.put(CollaboratorTypeDataModel.NAME, obj.getName());

        return update(TABELA, dados);

    }

    public boolean deletar(CollaboratorType obj){

        return delete(TABELA, obj.getId());

    }

    public List<CollaboratorType> listar(){

        return listCollaboratorType(TABELA);

    }

    public int getUltimoID(){

        return getLastPK(TABELA);

    }

//    public CollaboratorType getClientePFByFK(int idFK){
//
//        // idPK é a chave primária da tabela Cliente (id)
//
//        return getClientePFByFK(ClientePFDataModel.TABELA, idFK);
//
//
//    }

}
