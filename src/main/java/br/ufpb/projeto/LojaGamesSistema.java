package br.ufpb.projeto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LojaGamesSistema implements SistemaLojaGames {

    private Map<String, Jogo> jogos;
    private Map<String, Cliente> clientes;
    private GravadorDeDados gravador;

    public LojaGamesSistema() {

        this.jogos = new HashMap<>();
        this.clientes = new HashMap<>();


        this.gravador = new GravadorDeDados();
    }

    // ==========================
    // JOGOS
    // ==========================

    @Override
    public boolean cadastrarJogo(String nome,
                                 String categoria,
                                 double preco,
                                 int quantidade) {

        if (jogos.containsKey(nome)) {
            return false;
        }

        Jogo jogo = new Jogo(nome, categoria, preco, quantidade);
        jogos.put(nome, jogo);

        return true;
    }


    @Override
    public Collection<Jogo> pesquisarJogo(String nome) {

        return jogos.values()
                .stream()
                .filter(j -> j.getNome().equalsIgnoreCase(nome))
                .toList();
    }

    @Override
    public boolean removerJogo(String nome)
            throws JogoInexistenteException {

        if (!jogos.containsKey(nome)) {
            throw new JogoInexistenteException("Jogo não encontrado.");
        }

        jogos.remove(nome);
        return true;
    }

    @Override
    public boolean atualizarPreco(String nome,
                                  double novoPreco)
            throws JogoInexistenteException {

        if (!jogos.containsKey(nome)) {
            throw new JogoInexistenteException("Jogo não encontrado.");
        }

        jogos.get(nome).setPreco(novoPreco);

        return true;
    }

    @Override
    public boolean atualizarEstoque(String nome,
                                    int quantidade)
            throws JogoInexistenteException {

        if (!jogos.containsKey(nome)) {
            throw new JogoInexistenteException("Jogo não encontrado.");
        }

        jogos.get(nome).setQuantidade(quantidade);

        return true;
    }

    @Override
    public Collection<Jogo> listarJogos() {

        return jogos.values();

    }

    @Override
    public Collection<Jogo> pesquisarPorCategoria(String categoria) {

        return jogos.values()
                .stream()
                .filter(j -> j.getCategoria().equalsIgnoreCase(categoria))
                .toList();

    }

    @Override
    public Collection<Jogo> pesquisarPorPreco(double precoMaximo) {

        return jogos.values()
                .stream()
                .filter(j -> j.getPreco() <= precoMaximo)
                .toList();

    }

    // ==========================
    // CLIENTES
    // ==========================

    @Override
    public boolean cadastrarCliente(String cpf,
                                    String nome,
                                    String telefone) {

        if (clientes.containsKey(cpf)) {
            return false;
        }

        Cliente cliente = new Cliente(cpf, nome, telefone);

        clientes.put(cpf, cliente);

        return true;
    }

    @Override
    public Collection<Cliente> pesquisarCliente(String cpf) {

        Collection<Cliente> encontrados = new ArrayList<>();

        if (clientes.containsKey(cpf)) {
            encontrados.add(clientes.get(cpf));
        }

        return encontrados;
    }

    @Override
    public boolean removerCliente(String cpf)
            throws ClienteInexistenteException {

        if (!clientes.containsKey(cpf)) {
            throw new ClienteInexistenteException("Cliente não encontrado.");
        }

        clientes.remove(cpf);
        return true;
    }

    @Override
    public Collection<Cliente> listarClientes() {
        return clientes.values();
    }

    // ==========================
    // PERSISTÊNCIA
    // ==========================

    @Override
    public void salvarDados() throws IOException {

        gravador.salvar(jogos);

    }

    @Override
    @SuppressWarnings("unchecked")
    public void recuperarDados() throws IOException {

        Object objeto = gravador.recuperar();

        if (objeto != null) {

            jogos = (Map<String, Jogo>) objeto;

        }
    }
}