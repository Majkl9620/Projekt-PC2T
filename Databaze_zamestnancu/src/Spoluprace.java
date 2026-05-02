import java.io.Serializable;

public record Spoluprace(Zamestnanec zamestnanec, KvalitaSpoluprace kvalitaSpoluprace) implements Serializable {
}