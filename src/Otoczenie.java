

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

public class Otoczenie extends BasicSimObj {

    RNGenerator rng;
    Poczta poczta;
    double lambda;
    int licznikInteresantow = 0;
    int maxInteresantow;

    Otoczenie(RNGenerator rng, Poczta poczta, double lambda, int maxInteresantow){
        this.rng = rng;
        this.poczta = poczta;
        this.lambda = lambda;
        this.maxInteresantow = maxInteresantow;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
