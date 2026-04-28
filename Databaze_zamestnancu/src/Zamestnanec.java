import java.util.ArrayList;
import java.io.Serializable;

public abstract class Zamestnanec implements Serializable {
    private int id;
    private final String jmeno;
    private final String prijmeni;
    private final int rok_narozeni;
    private final ArrayList<Spoluprace> spoluprace;

    public Zamestnanec(String jmeno, String prijmeni, int rok_narozeni) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rok_narozeni = rok_narozeni;
        this.spoluprace = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public int getRok_narozeni() {
        return rok_narozeni;
    }

    public ArrayList<Spoluprace> getSpoluprace(){
        return spoluprace;
    }

    public void pridatSpolupraci(Spoluprace s){
        spoluprace.add(s);
    }

    public abstract void dovednost();
}
