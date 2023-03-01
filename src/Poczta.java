

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

import java.util.ArrayList;
import java.util.List;

public class Poczta extends BasicSimObj {

    RNGenerator rng;
    double mi;
    double ro;
    double alfa;
    double beta;
    List<Interesant> kolejka = new ArrayList<Interesant>();
    MonitoredVar czasPrzybywania = new MonitoredVar();
    MonitoredVar dlugoscKolejki = new MonitoredVar();
    MonitoredVar zajetosc = new MonitoredVar();
    int L;
    double liczbaStrat = 0;
    int M;
    int liczbaZajetychOkienek = 0;
    List<Okienko> okienka = new ArrayList<Okienko>();
    double p;
    double czasDzialania;
    double czasNieDzialania;
    boolean dziala = true;
    int licznikInteresantow = 0;
    int maxInteresantow;
    int aktualnaZajetosc = 0;
    Poczta(RNGenerator rng, double mi, int L, int M, double p, double ro, double alfa, double beta, int maxInteresantow){
        this.rng = rng;
        this.mi = mi;
        this.L=L;
        this.M=M;
        this.p=p;
        this.ro=ro;
        this.alfa = alfa;
        this.beta = beta;
        this.maxInteresantow = maxInteresantow;
        for(int i=0; i < M; i++){
            okienka.add(new Okienko(rng.exponential(alfa)));
        }
        this.czasDzialania = rng.exponential(alfa);

    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}

