package com.marketplace.model;

import java.io.Serializable;

public class Admin  implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String address;

    public Admin() {

    }

    public Admin(String name, String email, String password, String cpf, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
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

    public String getCpf() { 
        return cpf; 
    }

    public void setCpf(String cpf) { 
        this.cpf = cpf; 
    }

    public String getAddress() { 
        return address; 
    }

    public void setAddress(String address) { 
        this.address = address; 
    }

    @Override
    public String toString() {
        return "Admin{" +
               "nome='" + name + '\'' +
               ", email='" + email + '\'' +
               ", cpf='" + cpf + '\'' +
               ", endereco='" + address + '\'' +
               '}';
    }
}

