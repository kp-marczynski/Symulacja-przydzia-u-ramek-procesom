package symulacjaPrzydzialuRamek;


import java.util.Random;
import java.util.Scanner;

public class Generator {
    int[][] tablicaProcesow;
    int ileRamek;

    Generator(Scanner scan) {
        int ileStron;
        int ileProcesow;
        System.out.println("Ilość procesów");
        ileProcesow = scan.nextInt();
        System.out.println("Ilość stron: ");
        ileStron = scan.nextInt();
        System.out.println("Minimalna Długość ciągu odwołań: ");
        int minOdwolan = scan.nextInt();
        System.out.println("Maksymalna Długość ciągu odwołań: ");
        int maxOdwolan = scan.nextInt();
        tablicaProcesow = new int[ileProcesow][];
        System.out.println("Promień sąsiedztwa: "); //zasada lokalności odwołań
        int promien = scan.nextInt();
        for (int j = 0; j < tablicaProcesow.length; ++j) {
            int ileOdwolan = losuj(minOdwolan, maxOdwolan);
            int[] tablicaOdwolanDoStron = new int[ileOdwolan];
            tablicaOdwolanDoStron[0] = losuj(0, ileStron);
            for (int i = 1; i < ileOdwolan; ++i) {
                int min = (tablicaOdwolanDoStron[i - 1] - promien > 0)
                        ? tablicaOdwolanDoStron[i - 1] - promien
                        : 0;
                int max = (tablicaOdwolanDoStron[i - 1] + promien < ileStron)
                        ? tablicaOdwolanDoStron[i - 1] + promien
                        : ileStron;
                tablicaOdwolanDoStron[i] = losuj(min, max);
            }
            tablicaProcesow[j] = tablicaOdwolanDoStron;
        }
        System.out.println("Ilość ramek: ");
        ileRamek = scan.nextInt();
    }

    private static int losuj(int minLiczba, int maxLiczba) {
        if (maxLiczba == 0) return 0;
        return new Random().nextInt(maxLiczba - minLiczba) + minLiczba;
    }
}
