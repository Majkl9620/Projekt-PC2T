import java.sql.*;
import java.util.Map;

public class SpravceSQL {
    private Connection conn;

    public boolean pripojit() {
        conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:firma.db");
            vytvorTabulky();
            return true;
        } catch (SQLException e) {
            System.out.println("Chyba připojení k SQL: " + e.getMessage());
            return false;
        }
    }

    public void odpojit() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void vytvorTabulky() throws SQLException {
        String sqlZamestnanci = "CREATE TABLE IF NOT EXISTS zamestnanci (id INTEGER PRIMARY KEY, typ TEXT, jmeno TEXT, prijmeni TEXT, rok_narozeni INTEGER)";
        String sqlSpoluprace = "CREATE TABLE IF NOT EXISTS spoluprace (id_zamestnance INTEGER, id_kolegy INTEGER, kvalita TEXT)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlZamestnanci);
            stmt.execute(sqlSpoluprace);
        }
    }

    public void ulozZalohu(Map<Integer, Zamestnanec> databaze) {
        try (Statement stmt = conn.createStatement(); PreparedStatement psZam = conn.prepareStatement("INSERT INTO zamestnanci(id, typ, jmeno, prijmeni, rok_narozeni) VALUES(?,?,?,?,?)"); PreparedStatement psSpol = conn.prepareStatement("INSERT INTO spoluprace(id_zamestnance, id_kolegy, kvalita) VALUES(?,?,?)")) {
            stmt.execute("DELETE FROM spoluprace");
            stmt.execute("DELETE FROM zamestnanci");

            for (Zamestnanec z : databaze.values()) {
                psZam.setInt(1, z.getId());
                psZam.setString(2, z instanceof DatovyAnalytik ? "DA" : "BS");
                psZam.setString(3, z.getJmeno());
                psZam.setString(4, z.getPrijmeni());
                psZam.setInt(5, z.getRok_narozeni());
                psZam.executeUpdate();

                for (Spoluprace s : z.getSpoluprace()) {
                    psSpol.setInt(1, z.getId());
                    psSpol.setInt(2, s.zamestnanec().getId());
                    psSpol.setString(3, s.kvalitaSpoluprace().name());
                    psSpol.executeUpdate();
                }
            }
            System.out.println("Záloha do SQL úspěšně vytvořena.");
        } catch (SQLException e) {
            System.out.println("Chyba při ukládání zálohy: " + e.getMessage());
        }
    }

    public void nactiZalohu(Databaze db) {
        try (Statement stmtZ = conn.createStatement(); Statement stmtS = conn.createStatement()) {
            ResultSet rsZ = stmtZ.executeQuery("SELECT * FROM zamestnanci");

            while (rsZ.next()) {
                int id = rsZ.getInt("id");
                String typ = rsZ.getString("typ");
                String jmeno = rsZ.getString("jmeno");
                String prijmeni = rsZ.getString("prijmeni");
                int rok = rsZ.getInt("rok_narozeni");

                Zamestnanec z = "DA".equals(typ) ? new DatovyAnalytik(jmeno, prijmeni, rok) : new BezpecnostniSpecialista(jmeno, prijmeni, rok);
                db.nahratZeZalohy(id, z);
            }


            ResultSet rsS = stmtS.executeQuery("SELECT * FROM spoluprace");
            while (rsS.next()) {
                int idZ = rsS.getInt("id_zamestnance");
                int idK = rsS.getInt("id_kolegy");
                KvalitaSpoluprace kvalita = KvalitaSpoluprace.valueOf(rsS.getString("kvalita"));

                db.pridatSpolupraci(idZ, idK, kvalita);
            }
            System.out.println("Data z SQL zálohy byla úspěšně načtena.\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}