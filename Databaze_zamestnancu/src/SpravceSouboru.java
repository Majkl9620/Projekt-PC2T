import java.io.*;

public class SpravceSouboru {
    
    public static boolean ulozZamestnance(Zamestnanec z, String nazevSouboru) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nazevSouboru))) {
            oos.writeObject(z);
            System.out.println("Zaměstnanec úspěšně uložen do souboru " + nazevSouboru);
            return true;
        } catch (IOException e) {
            System.out.println("Chyba při ukládání do souboru: " + e.getMessage());
            return false;
        }
    }

    public static Zamestnanec nactiZamestnance(String nazevSouboru) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nazevSouboru))) {
            System.out.println("Zaměstnanec úspěšně načten ze souboru " + nazevSouboru);
            return (Zamestnanec) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Chyba při načítání ze souboru: " + e.getMessage());
            return null;
        }
    }
}