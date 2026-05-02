import java.util.ArrayList;

public class DatovyAnalytik extends Zamestnanec {
    public DatovyAnalytik(String jmeno, String prijmeni, int rok_narozeni) {
        super(jmeno, prijmeni, rok_narozeni);
    }

    @Override
    public void dovednost() {
        ArrayList<Spoluprace> analytikS = getSpoluprace();
        if (analytikS.isEmpty()) {
            System.out.println(getJmeno() + " " + getPrijmeni() + " nemá spolupráce.");
            return;
        }

        Zamestnanec nejviceS = null;
        int maxSpolecnychK = -1;
        for (Spoluprace s : analytikS) {
            Zamestnanec k = s.zamestnanec();
            int spolecnychK = 0;
            for (Spoluprace ks : k.getSpoluprace()) {
                for (Spoluprace zs : analytikS) {
                    if (ks.zamestnanec().getId() == zs.zamestnanec().getId()) {
                        spolecnychK++;
                    }
                }
            }
            if (spolecnychK > maxSpolecnychK) {
                maxSpolecnychK = spolecnychK;
                nejviceS = k;
            }
        }
        if (nejviceS != null) {
            System.out.println("Nejvíce společných spolupracovníků má s: " + nejviceS.getJmeno() + " " + nejviceS.getPrijmeni() + " (celkem " + maxSpolecnychK + " spolupracovníků)");
        }
    }
}
