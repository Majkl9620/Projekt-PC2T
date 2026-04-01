public class Spoluprace {
    private Zamestnanec zamestnanec;
    private UrovenSpoluprace urovenSpoluprace;

    public Spoluprace(Zamestnanec zamestnanec, UrovenSpoluprace urovenSpoluprace) {
        this.zamestnanec = zamestnanec;
        this.urovenSpoluprace = urovenSpoluprace;
    }

    public Zamestnanec getZamestnanec() {
        return zamestnanec;
    }

    public UrovenSpoluprace getUrovenSpoluprace() {
        return urovenSpoluprace;
    }
}
