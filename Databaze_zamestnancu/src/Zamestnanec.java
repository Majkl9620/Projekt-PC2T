import java.util.ArrayList;

public abstract class Zamestnanec {
    private int id;
    private String jmeno;
    private String prijmeni;
    private int rok_narozeni;
    private ArrayList<Spoluprace> spoluprace;

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

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public int getRok_narozeni() {
        return rok_narozeni;
    }

    public void setRok_narozeni(int rok_narozeni) {
        this.rok_narozeni = rok_narozeni;
    }

    public ArrayList<Spoluprace> getSpoluprace(){
        return spoluprace;
    }

    public void pridatSpolupraci(Spoluprace s){
        spoluprace.add(s);
    }

    public abstract void dovednost();
}
