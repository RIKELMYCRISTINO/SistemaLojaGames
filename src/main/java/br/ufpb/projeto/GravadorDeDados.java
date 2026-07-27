package br.ufpb.projeto;

import java.io.*;

public class GravadorDeDados {

    private final String arquivo = "dados.dat";

    public void salvar(Object objeto) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(objeto);
        }
    }

    public Object recuperar() throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return in.readObject();
        } catch (FileNotFoundException e) {
            return null;
        } catch (ClassNotFoundException e) {
            throw new IOException("Classe não encontrada ao recuperar os dados.", e);
        }
    }
}