import java.util.HashMap;
import java.util.Map;

public class Databaze {
    private Map<Integer, Zamestnanec> prvkyDatabaze = new HashMap<>();
    private int posledniId = 1;

    public void pridatZamestnance(Zamestnanec z) {
        z.setId(posledniId);
        prvkyDatabaze.put(posledniId, z);
        posledniId++;
    }

    public void pridatSpolupraci(int id_z, int id_k, UrovenSpoluprace u){
        Zamestnanec z = prvkyDatabaze.get(id_z);
        Zamestnanec k = prvkyDatabaze.get(id_k);
        if (z == null || k == null){
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
}
