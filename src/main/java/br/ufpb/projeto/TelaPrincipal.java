package br.ufpb.projeto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

public class TelaPrincipal extends JFrame {
    private final SistemaLojaGames sistema;

    public TelaPrincipal() {
        sistema = new LojaGamesSistema();

        setTitle("Loja de Games");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar barra = new JMenuBar();

        JMenu menuJogos = new JMenu("Jogos");
        JMenu menuClientes = new JMenu("Clientes");
        JMenu menuDados = new JMenu("Dados");

        JMenuItem cadastrarJogo = new JMenuItem("Cadastrar");
        JMenuItem pesquisarJogo = new JMenuItem("Pesquisar");
        JMenuItem removerJogo = new JMenuItem("Remover");
        JMenuItem atualizarPreco = new JMenuItem("Atualizar preço");
        JMenuItem atualizarEstoque = new JMenuItem("Atualizar estoque");
        JMenuItem listarJogos = new JMenuItem("Listar");

        JMenuItem cadastrarCliente = new JMenuItem("Cadastrar");
        JMenuItem pesquisarCliente = new JMenuItem("Pesquisar");
        JMenuItem removerCliente = new JMenuItem("Remover");
        JMenuItem listarClientes = new JMenuItem("Listar");

        JMenuItem salvar = new JMenuItem("Salvar");
        JMenuItem recuperar = new JMenuItem("Recuperar");
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

        menuDados.add(salvar);
        menuDados.add(recuperar);
        menuDados.addSeparator();
        menuDados.add(sair);

        barra.add(menuJogos);
        barra.add(menuClientes);
        barra.add(menuDados);

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
        String nome = JOptionPane.showInputDialog(this, "Nome do jogo:", "Cadastro de Jogo", JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.trim().isEmpty()) return;

        String categoria = JOptionPane.showInputDialog(this, "Categoria:", "Cadastro de Jogo", JOptionPane.QUESTION_MESSAGE);
        if (categoria == null || categoria.trim().isEmpty()) return;

        try {
            String precoStr = JOptionPane.showInputDialog(this, "Preço (ex: 120.00):", "Cadastro de Jogo", JOptionPane.QUESTION_MESSAGE);
            if (precoStr == null) return;
            double preco = Double.parseDouble(precoStr.replace(",", "."));

            String qtdStr = JOptionPane.showInputDialog(this, "Quantidade:", "Cadastro de Jogo", JOptionPane.QUESTION_MESSAGE);
            if (qtdStr == null) return;
            int quantidade = Integer.parseInt(qtdStr);

            boolean ok = sistema.cadastrarJogo(nome, categoria, preco, quantidade);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Jogo cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Jogo já cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Digite valores numéricos válidos para preço e quantidade.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pesquisarJogo() {
        String nome = JOptionPane.showInputDialog(this, "Nome do jogo:", "Pesquisar Jogo", JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.trim().isEmpty()) return;

        Collection<Jogo> encontrados = sistema.pesquisarJogo(nome);

        if (encontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jogo não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder("<html>");
            for (Jogo j : encontrados) {
                sb.append("<b>Nome:</b> ").append(j.getNome()).append("<br>")
                        .append("<b>Categoria:</b> ").append(j.getCategoria()).append("<br>")
                        .append("<b>Preço:</b> R$ ").append(String.format("%.2f", j.getPreco())).append("<br>")
                        .append("<b>Quantidade:</b> ").append(j.getQuantidade()).append("<br><hr>");
            }
            sb.append("</html>");
            JOptionPane.showMessageDialog(this, sb.toString(), "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void removerJogo() {
        String nome = JOptionPane.showInputDialog(this, "Nome do jogo:", "Remover Jogo", JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.trim().isEmpty()) return;

        Collection<Jogo> encontrados = sistema.pesquisarJogo(nome);
        if (encontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jogo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] options = {"Sim", "Não"};
        int resposta = JOptionPane.showOptionDialog(
                this,
                "Deseja realmente remover o jogo " + nome + "?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (resposta == 0) {
            try {
                sistema.removerJogo(nome);
                JOptionPane.showMessageDialog(this, "Jogo removido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void listarJogos() {
        Collection<Jogo> jogos = sistema.listarJogos();
        if (jogos == null || jogos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum jogo cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("<html>");
        for (Jogo j : jogos) {
            sb.append("<b>Nome:</b> ").append(j.getNome()).append("<br>")
                    .append("<b>Categoria:</b> ").append(j.getCategoria()).append("<br>")
                    .append("<b>Preço:</b> R$ ").append(String.format("%.2f", j.getPreco())).append("<br>")
                    .append("<b>Quantidade:</b> ").append(j.getQuantidade()).append("<br><hr>");
        }
        sb.append("</html>");
        JOptionPane.showMessageDialog(this, sb.toString(), "Lista de Jogos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cadastrarCliente() {
        String cpf = JOptionPane.showInputDialog(this, "CPF:", "Cadastro de Cliente", JOptionPane.QUESTION_MESSAGE);
        if (cpf == null || cpf.trim().isEmpty()) return;

        String nome = JOptionPane.showInputDialog(this, "Nome:", "Cadastro de Cliente", JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.trim().isEmpty()) return;

        String telefone = JOptionPane.showInputDialog(this, "Telefone:", "Cadastro de Cliente", JOptionPane.QUESTION_MESSAGE);
        if (telefone == null || telefone.trim().isEmpty()) return;

        boolean ok = sistema.cadastrarCliente(cpf, nome, telefone);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Cliente já cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void pesquisarCliente() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do cliente:", "Pesquisar Cliente", JOptionPane.QUESTION_MESSAGE);
        if (cpf == null || cpf.trim().isEmpty()) return;

        Collection<Cliente> encontrados = sistema.pesquisarCliente(cpf);

        if (encontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder("<html>");
            for (Cliente c : encontrados) {
                sb.append("<b>CPF:</b> ").append(c.getCpf()).append("<br>")
                        .append("<b>Nome:</b> ").append(c.getNome()).append("<br>")
                        .append("<b>Telefone:</b> ").append(c.getTelefone()).append("<br><hr>");
            }
            sb.append("</html>");
            JOptionPane.showMessageDialog(this, sb.toString(), "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void removerCliente() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do cliente:", "Remover Cliente", JOptionPane.QUESTION_MESSAGE);
        if (cpf == null || cpf.trim().isEmpty()) return;

        Collection<Cliente> encontrados = sistema.pesquisarCliente(cpf);
        if (encontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] options = {"Sim", "Não"};
        int resposta = JOptionPane.showOptionDialog(
                this,
                "Deseja realmente remover este cliente?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (resposta == 0) {
            try {
                sistema.removerCliente(cpf);
                JOptionPane.showMessageDialog(this, "Cliente removido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void listarClientes() {
        Collection<Cliente> clientes = sistema.listarClientes();
        if (clientes == null || clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("<html>");
        for (Cliente c : clientes) {
            sb.append("<b>CPF:</b> ").append(c.getCpf()).append("<br>")
                    .append("<b>Nome:</b> ").append(c.getNome()).append("<br>")
                    .append("<b>Telefone:</b> ").append(c.getTelefone()).append("<br><hr>");
        }
        sb.append("</html>");
        JOptionPane.showMessageDialog(this, sb.toString(), "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salvarDados() {
        try {
            sistema.salvarDados();
            JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recuperarDados() {
        try {
            sistema.recuperarDados();
            JOptionPane.showMessageDialog(this, "Dados recuperados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void atualizarPreco() {
        String nome = JOptionPane.showInputDialog(this, "Nome do jogo:", "Atualizar Preço", JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.trim().isEmpty()) return;

        Collection<Jogo> encontrados = sistema.pesquisarJogo(nome);
        if (encontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jogo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String precoStr = JOptionPane.showInputDialog(this, "Novo preço:", "Atualizar Preço", JOptionPane.QUESTION_MESSAGE);
            if (precoStr == null) return;
            double preco = Double.parseDouble(precoStr.replace(",", "."));

            sistema.atualizarPreco(nome, preco);
            JOptionPane.showMessageDialog(this, "Preço atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Digite um valor numérico válido para o preço.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarEstoque() {
        String nome = JOptionPane.showInputDialog(this, "Nome do jogo:", "Atualizar Estoque", JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.trim().isEmpty()) return;

        Collection<Jogo> encontrados = sistema.pesquisarJogo(nome);
        if (encontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jogo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String qtdStr = JOptionPane.showInputDialog(this, "Nova quantidade:", "Atualizar Estoque", JOptionPane.QUESTION_MESSAGE);
            if (qtdStr == null) return;
            int quantidade = Integer.parseInt(qtdStr);

            sistema.atualizarEstoque(nome, quantidade);
            JOptionPane.showMessageDialog(this, "Estoque atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Digite um valor inteiro válido para a quantidade.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}