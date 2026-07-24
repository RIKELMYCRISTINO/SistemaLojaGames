package br.ufpb.projeto;

public class JogoInexistenteException extends Exception {

    public JogoInexistenteException(String mensagem) {
        super(mensagem);
    }
}