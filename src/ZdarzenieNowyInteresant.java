

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;


public class ZdarzenieNowyInteresant extends BasicSimEvent<Otoczenie,Object> {


    public ZdarzenieNowyInteresant(Otoczenie entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    double delay;
    @Override
    protected void stateChange() throws SimControlException {

        int j =0;

        while(j<getSimObj().poczta.kolejka.size()){
            if(getSimObj().poczta.kolejka.get(j).czasIrytacji <= simTime()){
                getSimObj().poczta.liczbaStrat ++;
                System.out.println("["+ simTime()+"] :: interesant sie zirytowal i wyszedl z kolejki (strata)., "+
                        getSimObj().poczta.kolejka.get(j).nr+", dlugosc kolejki: "+(getSimObj().poczta.kolejka.size()-1)+
                        ", czas irytacji: "+getSimObj().poczta.kolejka.get(j).czasIrytacji);
                getSimObj().poczta.czasPrzybywania.setValue(simTime() - getSimObj().poczta.kolejka.get(j).czasWejscia);
                getSimObj().poczta.kolejka.remove(j);
                getSimObj().poczta.dlugoscKolejki.setValue(getSimObj().poczta.kolejka.size());
                getSimObj().poczta.aktualnaZajetosc --;
            }else{
                j++;
            }
        }



        if(super.getSimObj().licznikInteresantow < super.getSimObj().maxInteresantow){

            Interesant interesant = new Interesant(simTime(), simTime() + getSimObj().rng.exponential(getSimObj().poczta.ro));
            super.getSimObj().licznikInteresantow++;
            getSimObj().poczta.licznikInteresantow++;
            if(super.getSimObj().poczta.aktualnaZajetosc < super.getSimObj().poczta.L){
                super.getSimObj().poczta.aktualnaZajetosc ++;
                super.getSimObj().poczta.kolejka.add(interesant);
                //zdarzenieIrytacji zdarzenieIrytacji = new zdarzenieIrytacji(getSimObj().poczta, interesant.czasIrytacji - simTime(), interesant);

                super.getSimObj().poczta.dlugoscKolejki.setValue(super.getSimObj().poczta.kolejka.size());
                System.out.println("["+simTime()+"] :: pojawienie sie nowego interesanta i dodanie go do kolejki., "+interesant.nr+
                        ", dlugosc kolejki: "+super.getSimObj().poczta.kolejka.size()+", czas irytacji: "+interesant.czasIrytacji);
                ZdarzenieRozpoczeciaObslugi zdarzenieRozpoczeciaObslugi = new ZdarzenieRozpoczeciaObslugi(super.getSimObj().poczta);

            }
            else{
                super.getSimObj().poczta.liczbaStrat ++;
                System.out.println("["+ simTime()+"] :: pojawienie sie nowego interesatna ale brak miejsca w kolejce (strata)., "+interesant.nr+
                        ", dlugosc kolejki: "+super.getSimObj().poczta.kolejka.size());
            }
            delay = super.getSimObj().rng.exponential(super.getSimObj().lambda);
            ZdarzenieNowyInteresant zdarzenieNowyInteresant = new ZdarzenieNowyInteresant(super.getSimObj(),delay);
            //ZdarzenieIrytacji zdarzenieIrytacji = new ZdarzenieIrytacji(getSimObj().poczta, interesant.czasIrytacji - simTime(), interesant);
        }else if(!getSimObj().poczta.kolejka.isEmpty()){

            delay = super.getSimObj().rng.exponential(super.getSimObj().lambda);
            ZdarzenieNowyInteresant zdarzenieNowyInteresant = new ZdarzenieNowyInteresant(super.getSimObj(),delay);
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
