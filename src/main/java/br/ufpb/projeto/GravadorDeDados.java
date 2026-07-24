package br.ufpb.projeto;

import java.io.*;

public class GravadorDeDados {

    private final String arquivo = "dados.dat";

    public void salvar(Object objeto) throws IOException {

        ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(arquivo));

        out.writeObject(objeto);

        out.close();
    }

    public Object recuperar() throws IOException {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(arquivo));

            Object objeto = in.readObject();

            in.close();

            return objeto;

        } catch (ClassNotFoundException e) {

            throw new IOException(e);

        } catch (FileNotFoundException e) {

            return null;
        }
    }
}