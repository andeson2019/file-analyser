package br.com.andeson.fileanalyzer.model;

import java.util.Objects;

public class Client extends BaseModel {
    private String cnpj;
    private String name;
    private String businessArea;

    public Client(String cnpj, String name, String businessArea) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    @Override
    public String toString() {
        return "Client{" +
                "cnpj='" + cnpj + '\'' +
                ", name='" + name + '\'' +
                ", businessArea='" + businessArea + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) &&
                Objects.equals(cnpj, client.cnpj) &&
                Objects.equals(businessArea, client.businessArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cnpj, businessArea);
    }
}
