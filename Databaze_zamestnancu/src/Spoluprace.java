public class Spoluprace {
    private final Zamestnanec zamestnanec;
    private final KvalitaSpoluprace kvalitaSpoluprace;

    public Spoluprace(Zamestnanec zamestnanec, KvalitaSpoluprace kvalitaSpoluprace) {
        this.zamestnanec = zamestnanec;
        this.kvalitaSpoluprace = kvalitaSpoluprace;
    }

    public Zamestnanec getZamestnanec() {
        return zamestnanec;
    }

    public KvalitaSpoluprace getKvalitaSpoluprace() {
        return kvalitaSpoluprace;
    }
}
