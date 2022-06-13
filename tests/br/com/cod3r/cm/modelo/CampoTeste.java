package br.com.cod3r.cm.modelo;

import br.com.cod3r.cm.excecao.ExplosaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTeste {
    private Campo campo;

    @BeforeEach //para cada teste reinicializar o campo com este valor
    void iniciarCampo (){
        campo = new Campo(3,3);
    }

    @Test
    void testeExisteVizinhoVerticalHorizontal (){
        Campo vizinho = new Campo(3,2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeExisteVizinhoDiagonal (){
        Campo vizinho1 = new Campo(4,2);
        Campo vizinho2 = new Campo(3,4);
        boolean resultado1 = campo.adicionarVizinho(vizinho1);
        boolean resultado2 = campo.adicionarVizinho(vizinho2);
        Assertions.assertTrue(resultado1);
        Assertions.assertTrue(resultado2);
    }

    @Test
    void testeNaoVizinho (){
        Campo vizinho1 = new Campo(1, 1);
        boolean resultado1 = campo.adicionarVizinho(vizinho1);
        Assertions.assertFalse(resultado1);
    }

    @Test
    void testeToggleMarcacaoCampo (){
        campo.toggleMarcacaoCampo();
        Assertions.assertTrue(campo.isMarcado());
    }

    @Test
    void testeToggleMarcacaoCampoDuasChamadas (){
        campo.toggleMarcacaoCampo();
        campo.toggleMarcacaoCampo();
        Assertions.assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirCampoNaoMinadoNaoMarcado (){
        Assertions.assertTrue(campo.abrirCampo());
    }

    @Test
    void testeAbrirCampoNaoMinadoSimMarcado (){
        campo.toggleMarcacaoCampo();
        Assertions.assertFalse(campo.abrirCampo());
    }

    @Test
    void testeAbrirCampoSimMinadoNaoMarcado (){
        campo.minarCampo();
        //aqui se o campo tiver minado eu espero uma exceção, então faço o test com assertThrows
        Assertions.assertThrows(ExplosaoException.class, () -> {
            campo.abrirCampo();
        });
    }

    @Test
    void testeAbrirCampoSimMinadoSimMarcado (){
        campo.toggleMarcacaoCampo();
        campo.minarCampo();
        Assertions.assertFalse(campo.abrirCampo());
    }


}
