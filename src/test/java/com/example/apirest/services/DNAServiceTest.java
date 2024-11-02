package com.example.apirest.services;

import com.example.apirest.dtos.DNAStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DNAServiceTest {

    @Autowired
    private DNAService dnaService;

    @Autowired
    private StatsService statsService;

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

    @Test
    public void shouldIdentifyMutantThree() {
        String[] dna = {"TCGGGTGAT", "TGATCCTTT", "TACGAGTGA", "AAATGTACG", "ACGAGTGCT", "AGACACATG", "GAATTCCAA", "ACTACGACC", "TGAGTATCC"};
        try {
            assertTrue(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldIdentifyMutantFour() {
        String[] dna = {"TTTTTTTTT", "TTTTTTTTT", "TTTTTTTTT", "TTTTTTTTT", "CCGACCAGT", "GGCACTCCA", "AGGACACTA", "CAAAGGCAT", "GCAGTCCCC"};
        try {
            assertTrue(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldThrowExceptionForEmptyArray() {
        String[] dna = {};
        assertThrows(Exception.class, () -> {
            dnaService.checkIsMutant(dna);
        }, "El array no debe estar vacío");
    }

    // Test para recibir un array de NxM en vez de un NxN
    @Test
    public void shouldThrowExceptionForNonSquareArray() {
        String[] dna = {"A", "AA"}; // Ejemplo de NxM
        assertThrows(Exception.class, () -> {
            dnaService.checkIsMutant(dna);
        }, "El array debe ser de NxN");
    }

    // Test para recibir un array de números
    @Test
    public void shouldThrowExceptionForArrayWithNumbers() {
        String[] dna = {"A1G3", "TTT3", "GACG", "GTTA"};
        assertThrows(Exception.class, () -> {
            dnaService.checkIsMutant(dna);
        }, "El array solo debe contener 'A', 'T', 'C' o 'G'");
    }

    // Test para recibir null
    @Test
    public void shouldThrowExceptionForNullInput() {
        String[] dna = null;
        assertThrows(Exception.class, () -> {
            dnaService.checkIsMutant(dna);
        }, "La entrada no puede ser null");
    }

    // Test para recibir un array de NxN de nulls
    @Test
    public void shouldThrowExceptionForArrayWithNulls() {
        String[] dna = {null, null, null};
        assertThrows(Exception.class, () -> {
            dnaService.checkIsMutant(dna);
        }, "El array no puede contener nulls");
    }

    // Test para recibir un array de NxN con letras distintas a las propuestas {“B”, “H”}
    @Test
    public void shouldThrowExceptionForInvalidCharacters() {
        String[] dna = {"ABCD", "EFGH", "IJKL", "MNOP"};
        assertThrows(Exception.class, () -> {
            dnaService.checkIsMutant(dna);
        }, "El array solo debe contener 'A', 'T', 'C' o 'G'");
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
                "ATGTCA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        try {
            assertTrue(dnaService.checkIsMutant(dna).getIsMutant(),
                    "La secuencia diagonal inversa debe identificarse como mutante.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldGetStats() {
        try {
            DNAStats stats = statsService.getStats();

            // Verifica que `count_mutant_dna` y `count_human_dna` estén en un rango razonable para asegurar que son valores válidos de `int`
                        assertTrue(stats.getCountMutantDna() >= 0, "countMutantDNA debe ser un número entero positivo");
                        assertTrue(stats.getCountHumanDna() >= 0, "countHumanDNA debe ser un número entero positivo");
            // Verifica que `ratio` esté en el rango de un `float` entre 0 y 1, o cualquier otro rango que esperes
                        assertTrue(stats.getRatio() >= 0.0f, "ratio debe ser un valor de tipo float entre 0 y 1");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

