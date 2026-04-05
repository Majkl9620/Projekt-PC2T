import java.util.ArrayList;

public class BezpecnostniSpecialista extends Zamestnanec {
    public BezpecnostniSpecialista(String jmeno, String prijmeni, int rok_narozeni) {
        super(jmeno, prijmeni, rok_narozeni);
    }

    @Override
    public void dovednost() {
        ArrayList<Spoluprace> specialistaS = getSpoluprace();
        if(specialistaS.isEmpty()){
            System.out.println(getJmeno() + " " + getPrijmeni() + " nemá spolupráce.");
            return;
        }

        int riziko = 0;
        for (Spoluprace s : specialistaS) {
            switch (s.getKvalitaSpoluprace()) {
                case SPATNA:
                    riziko += 4;
                    break;
                case PRUMERNA:
                    riziko += 2;
                    break;
                case DOBRA:
                    riziko += 1;
                    break;
            }
        }
        riziko = riziko*getSpoluprace().size();
        System.out.println("Riziko spolupráce je " + riziko + " bodů");
    }
}
