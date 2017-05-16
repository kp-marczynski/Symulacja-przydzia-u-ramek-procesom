package symulacjaPrzydzialuRamek.strategia;

import symulacjaPrzydzialuRamek.Generator;
import symulacjaPrzydzialuRamek.StrategiaPrzydzialuRamek;

public class PrzydzialSterowanyCzestosciaBledowStrony extends StrategiaPrzydzialuRamek {
    public PrzydzialSterowanyCzestosciaBledowStrony(Generator generator) {
        super(generator);
    }

    @Override
    public int wykonaj() {
        int ramekNaProces = ileRamek / tablicaProcesow.length;
        for (int i = 0; i < tablicaProcesow.length; ++i) tablicaProcesow[i].setIleRamek(ramekNaProces);
        boolean czyWykonanoWszystkie = false;
        int licznik = 0;
        int[] tablicaBledowStron = new int[tablicaProcesow.length];
        int[] tablicaPriorytetow = new int[tablicaProcesow.length];
        int ileDostepnych = 0;
        while (!czyWykonanoWszystkie) {
            licznik++;
            czyWykonanoWszystkie = true;
            for (int i = 0; i < tablicaProcesow.length; ++i) {
                if (!tablicaProcesow[i].wykonajJeden()) czyWykonanoWszystkie = false;
            }
            if (licznik >= ramekNaProces) {
                for (int i = 0; i < tablicaProcesow.length; ++i) {
                    tablicaPriorytetow[i] = tablicaProcesow[i].getLicznikBledowStron() - tablicaBledowStron[i];
                    tablicaBledowStron[i] = tablicaProcesow[i].getLicznikBledowStron();
                    if (tablicaPriorytetow[i] <= licznik / 3 && tablicaProcesow[i].getIleRamek() > 1) {
                        tablicaProcesow[i].usunRamke();
                        ileDostepnych++;
                    }
                }
                while (ileDostepnych > 0 && licznik > 0) {
                    for (int i = 0; i < tablicaProcesow.length && ileDostepnych > 0; ++i) {
                        if (tablicaPriorytetow[i] >= licznik) {
                            tablicaProcesow[i].dodajRamke();
                            ileDostepnych--;
                        }
                    }
                    licznik--;
                }
                licznik = 0;
            }

        }
        for (int i = 0; i < tablicaProcesow.length; ++i) {
            licznikBledowStron += tablicaProcesow[i].getLicznikBledowStron();
        }
        return licznikBledowStron;
    }
}
