import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieAwarii extends BasicSimEvent<Poczta, Object> {
    public ZdarzenieAwarii(Poczta poczta, double delay) throws SimControlException {
        super(poczta, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        if(getSimObj().dziala && getSimObj().licznikInteresantow < getSimObj().maxInteresantow){
            getSimObj().dziala = false;

            getSimObj().czasNieDzialania = getSimObj().rng.exponential(getSimObj().beta);
            System.out.println("["+simTime()+"] :: okienka przestaja dzialac ., moment naprawy: " +  (getSimObj().czasNieDzialania + simTime()));
            for(int i=0; i < getSimObj().okienka.size(); i++){
                if(getSimObj().okienka.get(i).interesant != null){
                    getSimObj().okienka.get(i).zajete = false;
                    getSimObj().liczbaZajetychOkienek --;

                    //getSimObj().okienka.get(i).interesant.czasIrytacji = simTime() + getSimObj().rng.exponential(getSimObj().ro);

                    getSimObj().kolejka.add(0,getSimObj().okienka.get(i).interesant);
                    getSimObj().dlugoscKolejki.setValue(getSimObj().kolejka.size());
                    System.out.println("["+simTime()+"] :: interesant wrocil do kolejki z powodu awarii "+getSimObj().okienka.get(i).nr+" okienka., "+
                            getSimObj().okienka.get(i).interesant.nr+
                            ", dlugosc kolejki: "+getSimObj().kolejka.size()+", czas irytacji: "+
                            getSimObj().okienka.get(i).interesant.czasIrytacji);

                    getSimObj().okienka.get(i).interesant = null;
                }
            }
            getSimObj().zajetosc.setValue(getSimObj().liczbaZajetychOkienek);
            ZdarzenieNaprawy zdarzenieNaprawy = new ZdarzenieNaprawy(getSimObj(), getSimObj().czasNieDzialania);
        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
