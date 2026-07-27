package br.ufpb.projeto;

import javax.swing.SwingUtilities;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}