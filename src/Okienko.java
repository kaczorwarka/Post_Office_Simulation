

public class Okienko {
    static int ID = -1;
    int nr;
    boolean zajete = false;
    double czasDzialania;
    double czasNieDzialania;
    boolean dziala = true;
    Interesant interesant = null;
    Okienko(double czasDzialania){
        ID++;
        nr = ID;
        this.czasDzialania = czasDzialania;
    }
}
