package br.ufpb.projeto;

import java.io.Serializable;

public class Jogo implements Serializable {

    private String nome;
    private String categoria;
    private double preco;
    private int quantidade;

    public Jogo(String nome,
                String categoria,
                double preco,
                int quantidade) {

        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                "\nCategoria: " + categoria +
                "\nPreço: R$ " + preco +
                "\nQuantidade: " + quantidade;
    }
}