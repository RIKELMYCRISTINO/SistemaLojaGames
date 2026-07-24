package br.ufpb.projeto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LojaGamesSistemaTest {

    @Test
    public void testCadastrarJogo() {
        LojaGamesSistema sistema = new LojaGamesSistema();

        assertTrue(sistema.cadastrarJogo("Minecraft", "Sandbox", 120, 5));
    }

    @Test
    public void testPesquisarJogo() {
        LojaGamesSistema sistema = new LojaGamesSistema();

        sistema.cadastrarJogo("FIFA", "Esporte", 250, 3);

        assertFalse(sistema.pesquisarJogo("FIFA").isEmpty());
    }

    @Test
    public void testRemoverJogo() throws Exception {
        LojaGamesSistema sistema = new LojaGamesSistema();

        sistema.cadastrarJogo("GTA V", "Ação", 150, 2);

        assertTrue(sistema.removerJogo("GTA V"));
    }

    @Test
    public void testCadastrarCliente() {
        LojaGamesSistema sistema = new LojaGamesSistema();

        assertTrue(sistema.cadastrarCliente("123", "Rikelmy", "83999999999"));
    }

    @Test
    public void testPesquisarCliente() {
        LojaGamesSistema sistema = new LojaGamesSistema();

        sistema.cadastrarCliente("123", "Rikelmy", "83999999999");

        assertFalse(sistema.pesquisarCliente("123").isEmpty());
    }
}