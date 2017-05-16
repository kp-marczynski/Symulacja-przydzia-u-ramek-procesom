package symulacjaPrzydzialuRamek;

public abstract class StrategiaPrzydzialuRamek {
    protected AlgorytmLRU[] tablicaProcesow;
    protected int ileRamek;
    protected int licznikBledowStron;

    public StrategiaPrzydzialuRamek(Generator generator) {
        this.ileRamek = generator.ileRamek;
        this.tablicaProcesow = new AlgorytmLRU[generator.tablicaProcesow.length];
        for (int i = 0; i < tablicaProcesow.length; ++i) {
            this.tablicaProcesow[i] = new AlgorytmLRU(generator.tablicaProcesow[i]);
        }
    }

    public abstract int wykonaj();

    public int getWynikProcesu(int nrProcesu) {
        return tablicaProcesow[nrProcesu].getLicznikBledowStron();
    }
}
