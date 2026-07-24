package br.ufpb.projeto;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String cpf;
    private String nome;
    private String telefone;

    public Cliente(String cpf,
                   String nome,
                   String telefone) {

        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "CPF: " + cpf +
                "\nNome: " + nome +
                "\nTelefone: " + telefone;
    }
}