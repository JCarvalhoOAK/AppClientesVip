package br.com.appclientesvip.model;

public class CollaboratorType extends Cliente{

    private int id;
//    private int clienteID;  //FK
    private String name;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
