package com.upsolucoes.gestaocomercial.model.cliente;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private String cep;
    private String endereco;
    private String senha;
    private LocalDate dataCadastro;

    public Cliente() {
        this.dataCadastro = LocalDate.now();
    }

    public Cliente(String nome, String telefone, String cpf, String email, String cep, String endereco, String senha) {
        this.nome = nome;
        setTelefone(telefone);
        setCpf(cpf);
        this.email = email;
        setCep(cep);
        this.endereco = endereco;
        this.senha = senha;
        this.dataCadastro = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        String digits = telefone.replaceAll("\\D", "");
        if (digits.length() == 11) {
            this.telefone = String.format("(%s) %s-%s", digits.substring(0, 2), digits.substring(2, 7), digits.substring(7));
        } else if (digits.length() == 10) {
            this.telefone = String.format("(%s) %s-%s", digits.substring(0, 2), digits.substring(2, 6), digits.substring(6));
        } else {
            this.telefone = telefone;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf != null) {
            cpf = cpf.replaceAll("[^\\d]", "");
            if (cpf.length() == 11) {
                this.cpf = cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            } else {
                this.cpf = cpf;
            }
        } else {
            this.cpf = null;
        }
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if (cep != null) {
            cep = cep.replaceAll("[^\\d]", "");
            if (cep.length() == 8) {
                this.cep = cep.replaceFirst("(\\d{5})(\\d{3})", "$1-$2");
            } else {
                this.cep = cep;
            }
        } else {
            this.cep = null;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
