package com.everis.calculadorasimples;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Copyright (c) 2021 everis Brasil under MIT License
 * 
 * Testes unitários para a classe Calculadora da package calculadorasimples
 * 
 * Observem que não estamos nem perto de ter uma cobertura de testes adequada
 * com estes exemplos abaixo. O nosso objetivo aqui é ilustrar a utilização do
 * JUnit, e buscamos fazer os testes suficientes para isto.
 */
public class CalculadoraTest {
    static Calculadora calculadora;

    @Test
    //@Disabled : notação utilizada para desabilitar um teste
    public void testaSomaMenosIngenuo() {
        Calculadora myCalc = new Calculadora();

        boolean temErro = false;
        if (myCalc.soma(2, 2) != 4) {
            temErro = true;
        }
        if (myCalc.soma(2, -2) != 0) {
            temErro = true;
        }
        if (myCalc.soma(-2, 2) != 0) {
            temErro = true;
        }
        if (myCalc.soma(-2, -2) != -4) {
            temErro = true;
        }
        if (myCalc.soma(0, 0) != 0) {
            temErro = true;
        }
        if (temErro) {
            fail("Houve um erro na validação da soma."); //dispara uma exceção quando há erro na execução
        }
    }

    @Test
    public void deveResultarQuatroAoSomarDoisEDois() {
        // Calculadora calculadora = new Calculadora();
        assertEquals(4.0, calculadora.soma(2, 2));
    }

    @Test
    public void deveResultarZeroAoSomarDoisEMenosDois() {
        // Calculadora calculadora = new Calculadora();
        assertEquals(0.0, calculadora.soma(2, -2));
    }

    @BeforeAll //notação utilizada para determinar que este método será executado antes de todos os outros
    public static void setup() {
        calculadora = new Calculadora();
    } //método responsável por instanciar uma nova calculadora antes de iniciar os testes
    // Outros: @BeforeEach, @AfterEach, @AfterAll

    @DisplayName("Valida múltiplas somas com informações em CSV") //notação que descreve o que o teste faz, deixando explícito
    @ParameterizedTest // indica que o próximo teste recebe parâmetros
    @CsvSource({ "1.0, 1.0, 2.0", "2.0, 3.0, 6.0" }) // informa que os parâmetros a serem recebidos estão no formato .csv
    void validaMultiplasSomasCSV(double parcela1, double parcela2, double resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.soma(parcela1, parcela2));
    }

    @DisplayName("Valida múltiplas somas com informações em arquivo CSV")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1) //informa que os parâmetros a serem recebidos estão em um arquivo .csv
    void validaMultiplasSomasArqCSV(double parcela1, double parcela2, double resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.soma(parcela1, parcela2));
    }

    @Test
    public void divisaoPorZeroGeraExcecao() {
        assertThrows(ArithmeticException.class, () -> {
        	int resultado = calculadora.restoDivisaoInteira(4, 0);
            System.out.println(resultado);
        });
    }

}
