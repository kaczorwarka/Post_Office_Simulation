

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieZakonczeniaObslugi extends BasicSimEvent<Poczta, Interesant> {

    public ZdarzenieZakonczeniaObslugi(Poczta entity, double delay, Interesant params) throws SimControlException {
        super(entity, delay, params);
    }

    @Override
    protected void stateChange() throws SimControlException {

        int j = 0;

        while(j<getSimObj().kolejka.size()){
            if(super.getSimObj().kolejka.get(j).czasIrytacji <= simTime()){
                super.getSimObj().liczbaStrat ++;
                System.out.println("["+ simTime()+"] :: interesant sie zirytowal i wyszedl z kolejki (strata)., "+
                        super.getSimObj().kolejka.get(j).nr+", dlugosc kolejki: "+(super.getSimObj().kolejka.size()-1)+
                        ", czas irytacji: "+super.getSimObj().kolejka.get(j).czasIrytacji);
                super.getSimObj().czasPrzybywania.setValue(simTime() - super.getSimObj().kolejka.get(j).czasWejscia);
                super.getSimObj().kolejka.remove(j);
                getSimObj().dlugoscKolejki.setValue(getSimObj().kolejka.size());
                getSimObj().aktualnaZajetosc --;
            }else{
                j++;
            }
        }





        if(super.getSimObj().okienka.get(getEventParams().nr_okienka).interesant != null){
            super.getSimObj().okienka.get(getEventParams().nr_okienka).zajete = false;
            super.getSimObj().liczbaZajetychOkienek --;
            super.getSimObj().zajetosc.setValue(super.getSimObj().liczbaZajetychOkienek);


            double pL = getSimObj().rng.uniform(0,1);
            if(pL <= getSimObj().p){
                //if(super.getSimObj().aktualnaZajetosc < super.getSimObj().L){
                    getEventParams().czasIrytacji = simTime() + getSimObj().rng.exponential(getSimObj().ro);
                    super.getSimObj().kolejka.add(getEventParams());
                    super.getSimObj().dlugoscKolejki.setValue(super.getSimObj().kolejka.size());
                    System.out.println("["+simTime()+"] :: zkonczyl bycie oblusgiwanym przy okienku "+ getEventParams().nr_okienka +" i poszedl na koniec kolejki., "+getEventParams().nr+
                            ", dlugosc kolejki: "+super.getSimObj().kolejka.size());

               // }else{
                //    super.getSimObj().liczbaStrat++;
                //    System.out.println("["+simTime()+"] :: zakonczyl bycie obslugiwanym przy okienku "+ getEventParams().nr_okienka +" i pomimo checi pojscia na koniec kolejki nie ma miejsca (strata)., "
                //            +getEventParams().nr+", dlugosc kolejki: "+super.getSimObj().kolejka.size());
                //    super.getSimObj().czasPrzybywania.setValue(simTime() - getEventParams().czasWejscia);
                //    getSimObj().aktualnaZajetosc --;
                //}
            }else{
                System.out.println("["+simTime()+"] :: zakonczyl bycie oblusgiwanym przy okienku "+ getEventParams().nr_okienka +"., "+getEventParams().nr+
                        ", dlugosc kolejki: "+super.getSimObj().kolejka.size());
                super.getSimObj().czasPrzybywania.setValue(simTime() - getEventParams().czasWejscia);
                getSimObj().aktualnaZajetosc --;
            }
            super.getSimObj().okienka.get(getEventParams().nr_okienka).interesant = null;
            ZdarzenieRozpoczeciaObslugi zdarzenieRozpoczeciaObslugi = new ZdarzenieRozpoczeciaObslugi(super.getSimObj());

        }
    }

    @Override
    protected void onTermination() throws SimControlException {
    }

    @Override
    public Interesant getEventParams() {
        return eventParams;
    }
}
