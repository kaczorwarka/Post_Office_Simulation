import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class zdarzenieIrytacji extends BasicSimEvent<Poczta, Interesant> {
    public zdarzenieIrytacji(Poczta entity, double delay, Interesant interesant) throws SimControlException {
        super(entity, delay, interesant);
    }

    @Override
    protected void stateChange() throws SimControlException {
        if(!getEventParams().cierpliwy){
            getSimObj().kolejka.remove(getEventParams());
            System.out.println("["+ simTime()+"] :: interesant sie zirytowal i wyszedl z kolejki (strata)., "+
                    getEventParams().nr+", dlugosc kolejki: "+getSimObj().kolejka.size());
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
