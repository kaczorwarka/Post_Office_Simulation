


import dissimlab.monitors.Diagram;
import dissimlab.monitors.Statistics;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

import java.awt.*;

public class Main {
    public static void main(String args[]) throws SimControlException {
        SimManager sm = SimManager.getInstance();

        RNGenerator rng = new RNGenerator();
        /*
        double mi = 0.03;
        double lambda = 0.1;
        double ro = 0.1;
        double alfa = 0.1;
        double beta = 0.25;
        int L = 10;
        int M = 3;
        double p = 0.7;
         */
        double mi = 0.2;
        double lambda = 0.9;
        double ro = 0.2;
        double alfa = 0.05;
        double beta = 0.6;
        int L = 5;
        int M = 3;
        double p = 0.4;
        Poczta poczta = new Poczta(rng, mi, L, M, p, ro, alfa, beta, 60);
        Otoczenie otoczenie = new Otoczenie(rng, poczta, lambda, 60);
        double delay = rng.exponential(lambda);
        ZdarzenieAwarii zdarzenieAwarii = new ZdarzenieAwarii(poczta, poczta.czasDzialania);
        ZdarzenieNowyInteresant zdarzenieNowyInteresant = new ZdarzenieNowyInteresant(otoczenie,delay);
        sm.startSimulation();

        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Srednia dlugosc kolejki: " + Statistics.arithmeticMean(poczta.dlugoscKolejki));
        System.out.println("Srednia czas przebywania: " + Statistics.arithmeticMean(poczta.czasPrzybywania));
        System.out.println("Srednia zajetosc: " + Statistics.arithmeticMean(poczta.zajetosc));
        System.out.println("Prawdopodobienstwo straty: " + poczta.liczbaStrat/ poczta.maxInteresantow);
        Diagram diagramDlugosc = new Diagram(Diagram.DiagramType.TIME, "dlugosc kolejki");
        diagramDlugosc.add(poczta.dlugoscKolejki, Color.CYAN);
        diagramDlugosc.show();

        Diagram diagramDlugoscDystrybuanta = new Diagram(Diagram.DiagramType.DISTRIBUTION, "dystrybuanta kolejki");
        diagramDlugoscDystrybuanta.add(poczta.dlugoscKolejki, Color.black);
        diagramDlugoscDystrybuanta.show();

        Diagram diagramCzasPrzebywania = new Diagram(Diagram.DiagramType.DISTRIBUTION, "czas przebywania");
        diagramCzasPrzebywania.add(poczta.czasPrzybywania, Color.RED);
        diagramCzasPrzebywania.show();

        Diagram diagramZajetoscOkienek = new Diagram(Diagram.DiagramType.TIME, "zajetosc kolejki");
        diagramZajetoscOkienek.add(poczta.zajetosc, Color.GREEN);
        diagramZajetoscOkienek.show();
    }
}
