package symulacjaPrzydzialuRamek.strategia;

import symulacjaPrzydzialuRamek.Generator;
import symulacjaPrzydzialuRamek.StrategiaPrzydzialuRamek;

import java.util.HashSet;

public class PrzydzialModelStrefowy extends StrategiaPrzydzialuRamek {
    public PrzydzialModelStrefowy(Generator generator) {
        super(generator);
    }

    @Override
    public int wykonaj() {
        int ramekNaProces = ileRamek / tablicaProcesow.length;

        HashSet<Integer>[] tablicaZbiorowOstatnichOdwolan = new HashSet[tablicaProcesow.length];
        for (int i = 0; i < tablicaProcesow.length; ++i) {
            tablicaProcesow[i].setIleRamek(ramekNaProces);
            tablicaZbiorowOstatnichOdwolan[i] = new HashSet<>();
        }
        boolean czyWykonanoWszystkie = false;
        int licznik = 0;
        int ileDostepnych = 0;
        while (!czyWykonanoWszystkie) {
            licznik++;
            czyWykonanoWszystkie = true;
            for (int i = 0; i < tablicaProcesow.length; ++i) {
                if (!tablicaProcesow[i].wykonajJeden()) {
                    tablicaZbiorowOstatnichOdwolan[i].add(tablicaProcesow[i].getOstatnioUzyty());
                    czyWykonanoWszystkie = false;
                }
            }
            if (licznik >= 2 * ramekNaProces) {
                for (int i = 0; i < tablicaProcesow.length; ++i) {
                    if (tablicaProcesow[i].czyKoniec()) {
                        ileDostepnych += tablicaProcesow[i].getIleRamek();
                        tablicaProcesow[i].setIleRamek(0);
                    }
                    for (int j = tablicaZbiorowOstatnichOdwolan[i].size() - tablicaProcesow[i].getIleRamek(); j > 0 && tablicaProcesow[i].getIleRamek() > 1; --j) {
                        ileDostepnych++;
                        tablicaProcesow[i].usunRamke();
                    }
                }
                while (ileDostepnych > 0 && licznik > 0) {
                    for (int i = 0; i < tablicaProcesow.length && ileDostepnych > 0; ++i) {
                        int ilePotrzebnych = tablicaZbiorowOstatnichOdwolan[i].size() - tablicaProcesow[i].getIleRamek();
                        if (ilePotrzebnych > 0) {
                            if (ileDostepnych < ilePotrzebnych) {
                                ileDostepnych += tablicaProcesow[i].getIleRamek();
                                tablicaProcesow[i].setIleRamek(0);
                            } else {
                                tablicaProcesow[i].dodajRamke();
                                ileDostepnych--;
                            }
                        }
                    }
                    licznik--;
                }
                licznik = 0;
                for (int i = 0; i < tablicaZbiorowOstatnichOdwolan.length; ++i)
                    if (tablicaProcesow[i].getIleRamek() > 0 || tablicaProcesow[i].czyKoniec())
                        tablicaZbiorowOstatnichOdwolan[i].clear();
            }

        }
        for (int i = 0; i < tablicaProcesow.length; ++i) {
            licznikBledowStron += tablicaProcesow[i].getLicznikBledowStron();
        }
        return licznikBledowStron;
    }
}
