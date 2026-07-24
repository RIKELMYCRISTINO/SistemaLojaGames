package br.ufpb.projeto;

import br.ufpb.projeto.Cliente;
import br.ufpb.projeto.Jogo;
import br.ufpb.projeto.LojaGamesSistema;
import br.ufpb.projeto.SistemaLojaGames;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

public class TelaPrincipal extends JFrame {
    private SistemaLojaGames sistema;

    public TelaPrincipal() {

        sistema = new LojaGamesSistema();

        setTitle("Loja de Games");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar barra = new JMenuBar();

        JMenu menuJogos = new JMenu("Jogos");
        JMenu menuClientes = new JMenu("Clientes");
        JMenu menuArquivo = new JMenu("Arquivo");

        JMenuItem cadastrarJogo = new JMenuItem("Cadastrar");
        JMenuItem pesquisarJogo = new JMenuItem("Pesquisar");
        JMenuItem removerJogo = new JMenuItem("Remover");
        JMenuItem atualizarPreco = new JMenuItem("Atualizar Preço");
        JMenuItem atualizarEstoque = new JMenuItem("Atualizar Estoque");
        JMenuItem listarJogos = new JMenuItem("Listar");

        JMenuItem cadastrarCliente = new JMenuItem("Cadastrar");
        JMenuItem pesquisarCliente = new JMenuItem("Pesquisar");
        JMenuItem removerCliente = new JMenuItem("Remover");
        JMenuItem listarClientes = new JMenuItem("Listar");

        JMenuItem salvar = new JMenuItem("Salvar Dados");
        JMenuItem recuperar = new JMenuItem("Recuperar Dados");
        JMenuItem sair = new JMenuItem("Sair");

        menuJogos.add(cadastrarJogo);
        menuJogos.add(pesquisarJogo);
        menuJogos.add(removerJogo);
        menuJogos.add(atualizarPreco);
        menuJogos.add(atualizarEstoque);
        menuJogos.add(listarJogos);

        menuClientes.add(cadastrarCliente);
        menuClientes.add(pesquisarCliente);
        menuClientes.add(removerCliente);
        menuClientes.add(listarClientes);

        menuArquivo.add(salvar);
        menuArquivo.add(recuperar);
        menuArquivo.addSeparator();
        menuArquivo.add(sair);

        barra.add(menuJogos);
        barra.add(menuClientes);
        barra.add(menuArquivo);

        setJMenuBar(barra);

        JLabel titulo = new JLabel("Sistema Loja de Games", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));

        add(titulo, BorderLayout.CENTER);

        cadastrarJogo.addActionListener(e -> cadastrarJogo());

        pesquisarJogo.addActionListener(e -> pesquisarJogo());

        removerJogo.addActionListener(e -> removerJogo());

        atualizarPreco.addActionListener(e -> atualizarPreco());

        atualizarEstoque.addActionListener(e -> atualizarEstoque());

        listarJogos.addActionListener(e -> listarJogos());

        cadastrarCliente.addActionListener(e -> cadastrarCliente());

        pesquisarCliente.addActionListener(e -> pesquisarCliente());

        removerCliente.addActionListener(e -> removerCliente());

        listarClientes.addActionListener(e -> listarClientes());

        salvar.addActionListener(e -> salvarDados());

        recuperar.addActionListener(e -> recuperarDados());

        sair.addActionListener(e -> System.exit(0));
    }

    private void cadastrarJogo() {

        String nome = JOptionPane.showInputDialog(this, "Nome do jogo:");

        String categoria = JOptionPane.showInputDialog(this, "Categoria:");

        double preco = Double.parseDouble(
                JOptionPane.showInputDialog(this, "Preço:")
        );

        int quantidade = Integer.parseInt(
                JOptionPane.showInputDialog(this, "Quantidade:")
        );

        boolean ok = sistema.cadastrarJogo(
                nome,
                categoria,
                preco,
                quantidade
        );

        if (ok) {
            JOptionPane.showMessageDialog(this, "Jogo cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Jogo já cadastrado.");
        }
    }

    private void pesquisarJogo() {

        String nome = JOptionPane.showInputDialog(this, "Nome do jogo:");

        Collection<Jogo> encontrados = sistema.pesquisarJogo(nome);

        if (encontrados.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Jogo não encontrado.");

        } else {

            JOptionPane.showMessageDialog(this,
                    encontrados);

        }
    }

    private void removerJogo() {

        String nome = JOptionPane.showInputDialog(this, "Nome do jogo:");

        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja remover este jogo?"
        );

        if (resposta == JOptionPane.YES_OPTION) {

            try {

                sistema.removerJogo(nome);

                JOptionPane.showMessageDialog(this,
                        "Jogo removido.");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this,
                        e.getMessage());

            }
        }
    }

    private void listarJogos() {

        StringBuilder texto = new StringBuilder();

        for (Jogo jogo : sistema.listarJogos()) {

            texto.append(jogo);
            texto.append("\n--------------------------------\n\n");
        }

        if (texto.length() == 0) {
            texto.append("Nenhum jogo cadastrado.");
        }

        JOptionPane.showMessageDialog(this, texto.toString());
    }

    private void cadastrarCliente () {

        String cpf = JOptionPane.showInputDialog(this, "CPF:");

        String nome = JOptionPane.showInputDialog(this, "Nome:");

        String telefone = JOptionPane.showInputDialog(this, "Telefone:");

        boolean ok = sistema.cadastrarCliente(
                cpf,
                nome,
                telefone
        );

        if (ok) {

            JOptionPane.showMessageDialog(this,
                    "Cliente cadastrado com sucesso!");

        } else {

            JOptionPane.showMessageDialog(this,
                    "Cliente já cadastrado.");

        }
    }
    private void pesquisarCliente() {

        String cpf = JOptionPane.showInputDialog(this, "CPF:");

        Collection<Cliente> encontrados = sistema.pesquisarCliente(cpf);

        if (encontrados.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Cliente não encontrado.");

        } else {

            JOptionPane.showMessageDialog(this,
                    encontrados);

        }
    }

    private void removerCliente() {

        String cpf = JOptionPane.showInputDialog(this, "CPF:");

        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja remover este cliente?"
        );

        if (resposta == JOptionPane.YES_OPTION) {

            try {

                sistema.removerCliente(cpf);

                JOptionPane.showMessageDialog(this,
                        "Cliente removido.");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this,
                        e.getMessage());

            }
        }
    }

    private void listarClientes() {

        StringBuilder texto = new StringBuilder();

        for (Cliente cliente : sistema.listarClientes()) {

            texto.append(cliente);
            texto.append("\n--------------------------------\n\n");
        }

        if (texto.length() == 0) {
            texto.append("Nenhum cliente cadastrado.");
        }

        JOptionPane.showMessageDialog(this, texto.toString());
    }

    private void salvarDados() {

        try {

            sistema.salvarDados();

            JOptionPane.showMessageDialog(
                    this,
                    "Dados salvos com sucesso!"
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar os dados."
            );

        }
    }

    private void recuperarDados() {

        try {

            sistema.recuperarDados();

            JOptionPane.showMessageDialog(
                    this,
                    "Dados recuperados com sucesso!"
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao recuperar os dados."
            );

        }
    }
    private void atualizarPreco() {

        String nome = JOptionPane.showInputDialog(this, "Nome do jogo:");

        double preco = Double.parseDouble(
                JOptionPane.showInputDialog(this, "Novo preço:")
        );

        try {

            sistema.atualizarPreco(nome, preco);

            JOptionPane.showMessageDialog(this,
                    "Preço atualizado!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    e.getMessage());

        }
    }
    private void atualizarEstoque() {

        String nome = JOptionPane.showInputDialog(this, "Nome do jogo:");

        int quantidade = Integer.parseInt(
                JOptionPane.showInputDialog(this, "Nova quantidade:")
        );

        try {

            sistema.atualizarEstoque(nome, quantidade);

            JOptionPane.showMessageDialog(this,
                    "Estoque atualizado!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    e.getMessage());

        }
    }
}

