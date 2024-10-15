package com.example.apirest.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DNAServiceTest {

    @Autowired
    private DNAService dnaService;

    // Tests posibles pruebas unitarias
    @Test
    public void shouldIdentifyMutantOne() {
        String[] dna = {"AAAA","CCCC","TCAG","GGTC"};
        try {
            assertTrue(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldNotIdentifyMutantOne() {
        String[] dna = {"AAAT","AACC","AAAC","CGGG"};
        try {
            assertFalse(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia no debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldIdentifyMutantTwo() {
        String[] dna = {"TGAC","AGCC","TGAC","GGTC"};
        try {
            assertTrue(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldNotIdentifyMutantTwo() {
        String[] dna = {"AAAA","AAAA","AAAA","AAAA"};
        try {
            assertFalse(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia no debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldNotIdentifyMutantThree() {
        String[] dna = {"TGAC", "ATCC", "TAAC", "GGTC"};
        try {
            assertFalse(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia no debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // Pruebas adicionales
    @Test
    public void shouldIdentifyHorizontalMutant() {
        String[] dna = {
                "AAAAAA",
                "CAGTGC",
                "TTATGT",
                "AGATGG",
                "CCCCTA",
                "TCACTG"
        };
        try {
            assertTrue(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia horizontal debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldIdentifyVerticalMutant() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGATGG",
                "CCCCTA",
                "TCACTG"
        };
        try {
            assertTrue(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia vertical debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldIdentifyDiagonalMutant() {
        String[] dna = {
                "ATGTCA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        try {
            assertTrue(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia diagonal debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldIdentifyInverseDiagonalMutant() {
        String[] dna = {
                "ATGCGA",
                "CATTAC",
                "TTAACT",
                "AGACTG",
                "CTCCTA",
                "TCACTG"
        };
        try {
            assertTrue(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia diagonal inversa debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

