

public class Interesant {
    static int ID = -1;
    int nr;
    int nr_okienka;
    double czasWejscia;
    double czasIrytacji;
    boolean cierpliwy = false;
    Interesant(double czasWejscia, double czasIrytacji){
        ID++;
        nr = ID;
        this.czasWejscia=czasWejscia;
        this.czasIrytacji = czasIrytacji;
    }
}
