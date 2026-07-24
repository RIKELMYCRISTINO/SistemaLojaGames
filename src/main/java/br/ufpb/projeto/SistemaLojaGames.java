package br.ufpb.projeto;

import java.io.IOException;
import java.util.Collection;

public interface SistemaLojaGames {

    // =========================
    // MÉTODOS DE JOGOS
    // =========================

    boolean cadastrarJogo(String nome,
                          String categoria,
                          double preco,
                          int quantidade);

    Collection<Jogo> pesquisarJogo(String nome);

    boolean removerJogo(String nome)
            throws JogoInexistenteException;

    boolean atualizarPreco(String nome,
                           double novoPreco)
            throws JogoInexistenteException;

    boolean atualizarEstoque(String nome,
                             int quantidade)
            throws JogoInexistenteException;

    Collection<Jogo> listarJogos();

    Collection<Jogo> pesquisarPorCategoria(String categoria);

    Collection<Jogo> pesquisarPorPreco(double precoMaximo);

    // =========================
    // MÉTODOS DE CLIENTES
    // =========================

    boolean cadastrarCliente(String cpf,
                             String nome,
                             String telefone);

    Collection<Cliente> pesquisarCliente(String cpf);

    boolean removerCliente(String cpf)
            throws ClienteInexistenteException;

    Collection<Cliente> listarClientes();

    // =========================
    // PERSISTÊNCIA
    // =========================

    void salvarDados()
            throws IOException;

    void recuperarDados()
            throws IOException;
}