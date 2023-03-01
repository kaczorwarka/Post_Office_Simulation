

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieRozpoczeciaObslugi extends BasicSimEvent<Poczta, Object> {

    public ZdarzenieRozpoczeciaObslugi(Poczta entity) throws SimControlException {
        super(entity);
    }

    @Override
    protected void stateChange() throws SimControlException {

        int j = 0;

        while(j<getSimObj().kolejka.size()){
            if(getSimObj().kolejka.get(j).czasIrytacji <= simTime()){
                getSimObj().liczbaStrat ++;
                System.out.println("["+ simTime()+"] :: interesant sie zirytowal i wyszedl z kolejki (strata)., "+
                        getSimObj().kolejka.get(j).nr+", dlugosc kolejki: "+(getSimObj().kolejka.size()-1)+
                        ", czas irytacji: "+getSimObj().kolejka.get(j).czasIrytacji);
                getSimObj().czasPrzybywania.setValue(simTime() - getSimObj().kolejka.get(j).czasWejscia);
                getSimObj().kolejka.remove(j);
                getSimObj().dlugoscKolejki.setValue(getSimObj().kolejka.size());
                getSimObj().aktualnaZajetosc --;
            }else{
                j++;
            }
        }





        if(!super.getSimObj().kolejka.isEmpty()){
            for(int i=0; i < super.getSimObj().M; i++){
                if(!super.getSimObj().okienka.get(i).zajete && super.getSimObj().dziala){
                    Interesant interesant = super.getSimObj().kolejka.get(0);
                    interesant.cierpliwy = true;
                    interesant.nr_okienka = super.getSimObj().okienka.get(i).nr;
                    super.getSimObj().kolejka.remove(0);
                    super.getSimObj().okienka.get(i).interesant = interesant;
                    super.getSimObj().dlugoscKolejki.setValue(super.getSimObj().kolejka.size());
                    super.getSimObj().okienka.get(interesant.nr_okienka).zajete = true;
                    super.getSimObj().liczbaZajetychOkienek ++;
                    super.getSimObj().zajetosc.setValue(super.getSimObj().liczbaZajetychOkienek);
                    System.out.println("["+simTime()+"] :: podejscie interesatna do "+ interesant.nr_okienka +" okienka ., "+interesant.nr+
                            ", dlugosc kolejki: "+super.getSimObj().kolejka.size());
                    double delay = super.getSimObj().rng.exponential(super.getSimObj().mi);
                    ZdarzenieZakonczeniaObslugi zdarzenieZakonczeniaObslugi = new ZdarzenieZakonczeniaObslugi(super.getSimObj(),delay,interesant);
                    break;
                }
            }
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
