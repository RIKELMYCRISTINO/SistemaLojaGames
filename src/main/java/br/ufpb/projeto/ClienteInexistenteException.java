package br.ufpb.projeto;

public class ClienteInexistenteException extends Exception {

    public ClienteInexistenteException(String mensagem) {
        super(mensagem);
    }
}