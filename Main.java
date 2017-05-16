package symulacjaPrzydzialuRamek;

import symulacjaPrzydzialuRamek.strategia.PrzydzialModelStrefowy;
import symulacjaPrzydzialuRamek.strategia.PrzydzialProporcjonalny;
import symulacjaPrzydzialuRamek.strategia.PrzydzialRowny;
import symulacjaPrzydzialuRamek.strategia.PrzydzialSterowanyCzestosciaBledowStrony;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Generator generator = new Generator(scan);

        StrategiaPrzydzialuRamek[] strategia = new StrategiaPrzydzialuRamek[4];
        strategia[0] = new PrzydzialRowny(generator);
        strategia[1] = new PrzydzialProporcjonalny(generator);
        strategia[2] = new PrzydzialSterowanyCzestosciaBledowStrony(generator);
        strategia[3] = new PrzydzialModelStrefowy(generator);

        System.out.println("***** Wyniki globalne: *****");
        for (int i = 0; i < strategia.length; ++i) {
            System.out.println(strategia[i].getClass().getSimpleName() + ": " + strategia[i].wykonaj());
        }

        for (int i = 0; i < generator.tablicaProcesow.length; ++i) {
            System.out.println();
            System.out.println("***** Wyniki dla procesu " + i + ": *****");
            for (int j = 0; j < strategia.length; ++j) {
                System.out.println(strategia[j].getClass().getSimpleName() + ": " + strategia[j].getWynikProcesu(i));
            }
        }
    }
}
