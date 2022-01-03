package br.com.appclientesvip.model;

import java.util.Objects;

public class ClientePJ extends ClientePF{

    private int id;
    private int clientePFID;
    private String cnpj;
    private String razaoSocial;
    private String dataAbertura;
    private boolean simplesNacional;
    private boolean mei;


//    public ClientePJ(int id, int clientePFID, String cnpj, String razaoSocial, String dataAbertura, boolean simplesNacional, boolean mei) {
//        this.id = id;
//        this.clientePFID = clientePFID;
//        this.cnpj = cnpj;
//        this.razaoSocial = razaoSocial;
//        this.dataAbertura = dataAbertura;
//        this.simplesNacional = simplesNacional;
//        this.mei = mei;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ClientePJ clientePJ = (ClientePJ) o;
//        return id == clientePJ.id && clientePFID == clientePJ.clientePFID && simplesNacional == clientePJ.simplesNacional && mei == clientePJ.mei && cnpj.equals(clientePJ.cnpj) && razaoSocial.equals(clientePJ.razaoSocial) && dataAbertura.equals(clientePJ.dataAbertura);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, clientePFID, cnpj, razaoSocial, dataAbertura, simplesNacional, mei);
//    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getClientePFID() {
        return clientePFID;
    }

    public void setClientePFID(int clientePFID) {
        this.clientePFID = clientePFID;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public boolean isSimplesNacional() {
        return simplesNacional;
    }

    public void setSimplesNacional(boolean simplesNacional) {
        this.simplesNacional = simplesNacional;
    }

    public boolean isMei() {
        return mei;
    }

    public void setMei(boolean mei) {
        this.mei = mei;
    }
}
