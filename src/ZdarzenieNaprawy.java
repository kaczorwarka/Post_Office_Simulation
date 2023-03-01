import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieNaprawy extends BasicSimEvent<Poczta, Object> {

    public ZdarzenieNaprawy(Poczta poczta, double delay) throws SimControlException {
        super(poczta, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        if(!getSimObj().dziala){
            getSimObj().dziala = true;
            //getSimObj().czasDzialania = simTime() + getSimObj().rng.exponential(getSimObj().alfa);
            getSimObj().czasDzialania = getSimObj().rng.exponential(getSimObj().alfa);
            System.out.println("["+simTime()+"] :: poczta zacela dzialac., moment kolejnej awarii: "+(getSimObj().czasDzialania + simTime()));

            ZdarzenieAwarii zdarzenieAwarii = new ZdarzenieAwarii(getSimObj(), getSimObj().czasDzialania);
            ZdarzenieRozpoczeciaObslugi zdarzenieRozpoczeciaObslugi = new ZdarzenieRozpoczeciaObslugi(getSimObj());
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
