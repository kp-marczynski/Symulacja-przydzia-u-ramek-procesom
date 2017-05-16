package symulacjaPrzydzialuRamek.strategia;

import symulacjaPrzydzialuRamek.Generator;
import symulacjaPrzydzialuRamek.StrategiaPrzydzialuRamek;

public class PrzydzialProporcjonalny extends StrategiaPrzydzialuRamek {
    public PrzydzialProporcjonalny(Generator generator) {
        super(generator);
    }

    @Override
    public int wykonaj() {
        int lacznaDlugoscProcesow = 0;
        for (int i = 0; i < tablicaProcesow.length; ++i)
            lacznaDlugoscProcesow += tablicaProcesow[i].getTablicaOdwolanDoStron().length;
        for (int i = 0; i < tablicaProcesow.length; ++i) {
            int ramekNaProces = ileRamek * tablicaProcesow[i].getTablicaOdwolanDoStron().length / lacznaDlugoscProcesow;
            tablicaProcesow[i].setIleRamek(ramekNaProces);
        }
        for (int i = 0; i < tablicaProcesow.length; ++i) {
            tablicaProcesow[i].wykonajWszystkie();
            licznikBledowStron += tablicaProcesow[i].getLicznikBledowStron();
        }
        return licznikBledowStron;
    }
}
