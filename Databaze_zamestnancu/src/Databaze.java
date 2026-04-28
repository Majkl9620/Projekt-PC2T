import java.util.*;

public class Databaze {
    private final Map<Integer, Zamestnanec> prvkyDatabaze = new HashMap<>();
    private int posledniId = 1;

    public void pridatZamestnance(Zamestnanec z) {
        z.setId(posledniId);
        prvkyDatabaze.put(posledniId, z);
        posledniId++;
    }

    public void pridatSpolupraci(int id_z, int id_k, KvalitaSpoluprace u) {
        Zamestnanec z = prvkyDatabaze.get(id_z);
        Zamestnanec k = prvkyDatabaze.get(id_k);
        if (z == null || k == null) {
            System.out.println("Zaměstnanec nenalezen");
            return;
        }
        z.pridatSpolupraci(new Spoluprace(k, u));
    }

    public void odebratZamestnance(int id_z) {
        prvkyDatabaze.remove(id_z);
        for (Zamestnanec z : prvkyDatabaze.values()) {
            z.getSpoluprace().removeIf(s -> s.getZamestnanec().getId() == id_z);
        }
    }

    public Zamestnanec getZamestnanec(int id_z) {
        return prvkyDatabaze.get(id_z);
    }

    public void getDovednost(int id_z) {
        prvkyDatabaze.get(id_z).dovednost();
    }

    public void vypsatZamestnance(Zamestnanec z) {
        System.out.print("\nID: " + z.getId());
        System.out.print("\tJméno: " + z.getJmeno() + " " + z.getPrijmeni());
        System.out.print("\tRok narození: " + z.getRok_narozeni());
        System.out.println("\nSpolupráce:\n");
        for (Spoluprace s : z.getSpoluprace()) {
            System.out.println("\t" + s.getZamestnanec().getJmeno() + " " + s.getZamestnanec().getPrijmeni() + ": " + s.getKvalitaSpoluprace());
        }
    }

    public void vypsatVsechnyZamestnance() {
        List<Zamestnanec> serazenaDatabaze = new ArrayList<>(prvkyDatabaze.values());
        serazenaDatabaze.sort(Comparator.comparing(Zamestnanec::getPrijmeni));
        int pocetDA = 0;
        int pocetBS = 0;

        System.out.print("\nDatoví analytici:");
        for (Zamestnanec z : serazenaDatabaze) {
            if (z instanceof DatovyAnalytik) {
                vypsatZamestnance(z);
                pocetDA++;
            }
        }
        System.out.print("Celkem " + pocetDA + " datových analytiků v databázi.\n");

        System.out.println("\nBezpečnostní specialisté:");
        for (Zamestnanec z : serazenaDatabaze) {
            if (z instanceof BezpecnostniSpecialista) {
                vypsatZamestnance(z);
                pocetBS++;
            }
        }
        System.out.println("Celkem " + pocetBS + " bezpečnostních specialistů v databázi.\n");
    }

    public void vypsatStatistiky() {
        if (prvkyDatabaze.isEmpty()) {
            System.out.println("Databáze je prázdná, nejsou k dispozici žádné statistiky.");
            return;
        }
        int spatnychS = 0;
        int prumernychS = 0;
        int dobrychS = 0;
        int maxPocetS = 0;
        Zamestnanec nejviceS = null;

        for (Zamestnanec z : prvkyDatabaze.values()) {
            int pocetS = z.getSpoluprace().size();
            if (pocetS > maxPocetS) {
                maxPocetS = pocetS;
                nejviceS = z;
            }

            for (Spoluprace s : z.getSpoluprace()) {
                switch (s.getKvalitaSpoluprace()) {
                    case SPATNA:
                        spatnychS++;
                        break;
                    case PRUMERNA:
                        prumernychS++;
                        break;
                    case DOBRA:
                        dobrychS++;
                        break;
                }
            }

            int maximumU = Math.max(dobrychS, Math.max(prumernychS, spatnychS));
            
            if (maximumU == 0) {
            System.out.println("V databázi zatím nejsou zaznamenány žádné spolupráce.");
            } else {
                if (maximumU == dobrychS) {
                    System.out.println("Převažující kvalita spolupráce je dobrá.");
                } else if (maximumU == prumernychS) {
                    System.out.println("Převažující kvalita spolupráce je průměrná.");
                } else if (maximumU == spatnychS) {
                    System.out.println("Převažující kvalita spolupráce je špatná.");
                }
            }
            if (nejviceS != null && maxPocetS > 0) {
                System.out.println("Nejvíce vazeb má zaměstnanec " + nejviceS.getJmeno() + " " + nejviceS.getPrijmeni() + " (celkem " + maxPocetS + "vazeb).");
            }
            }
        }
    public Map<Integer, Zamestnanec> getPrvkyDatabaze() {
    return prvkyDatabaze;
    }
    
    public void nahratZeZalohy(int id, Zamestnanec z) {
        z.setId(id);
        prvkyDatabaze.put(id, z);
        if (id >= posledniId) {
            posledniId = id + 1;
        }
    }
    
}
