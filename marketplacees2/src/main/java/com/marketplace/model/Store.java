package com.marketplace.model;

import java.io.Serializable;

public class Store implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String email;
    private String password;
    private String cnpj;
    private String address;

    public Store() {

    }

    public Store(String name, String email, String password, String cnpj, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cnpj = cnpj;
        this.address = address;
    }

    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public String getEmail() { 
        return email; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getPassword() { 
        return password; 
    }

    public void setPassword(String password) { 
        this.password = password; 
    }

    public String getCnpj() { 
        return cnpj; 
    }

    public void setCnpj(String cnpj) { 
        this.cnpj = cnpj; 
    }

    public String getAddress() { 
        return address; 
    }

    public void setAddress(String address) { 
        this.address = address; 
    }

    @Override
    public String toString() {
        return "Store{" +
               "nome='" + name + '\'' +
               ", email='" + email + '\'' +
               ", cnpj='" + cnpj + '\'' +
               ", endereco='" + address + '\'' +
               '}';
    }
}

