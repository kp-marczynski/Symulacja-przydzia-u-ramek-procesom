package symulacjaPrzydzialuRamek.strategia;

import symulacjaPrzydzialuRamek.AlgorytmLRU;
import symulacjaPrzydzialuRamek.Generator;
import symulacjaPrzydzialuRamek.StrategiaPrzydzialuRamek;

public class PrzydzialRowny extends StrategiaPrzydzialuRamek {
    public PrzydzialRowny(Generator generator) {
        super(generator);
    }

    public int wykonaj() {
        int ramekNaProces = ileRamek / tablicaProcesow.length;
        for (AlgorytmLRU aTablicaProcesow : tablicaProcesow) aTablicaProcesow.setIleRamek(ramekNaProces);
        for (int i = 0; i < tablicaProcesow.length; ++i) {
            tablicaProcesow[i].wykonajWszystkie();
            licznikBledowStron += tablicaProcesow[i].getLicznikBledowStron();
        }
        return licznikBledowStron;
    }
}
