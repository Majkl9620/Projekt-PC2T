import java.util.Scanner;

public class Main {
    public static int pouzeCelaCisla(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Zadejte prosím celé číslo ");
            sc.nextLine();
        }
        return sc.nextInt();
    }

    public static String vyplnenyString(Scanner sc) {
        String vstup = sc.next();
        while (vstup.length() < 2) {
            System.out.println("Zadejte prosím jméno delší než 1 znak");
            vstup = sc.next();
        }
        return vstup;
    }

static void main() {
        Scanner sc = new Scanner(System.in);
        Databaze databazeZamestnancu = new Databaze();
        int volba;
        int podVolba;
        boolean run = true;
        while (run) {
            System.out.println("Vítejte v databázovém systému firmy\n");
            System.out.println("Vyberte požadovanou činnost:");
            System.out.println("1 ... Vytvoření nového zaměstnance");
            System.out.println("2 ... Odstranění existujícího zaměstnance");
            System.out.println("3 ... Přidání spolupráce");
            System.out.println("4 ... Vyhledání zaměstnance");
            System.out.println("5 ... Spuštění dovednosti zaměstnance");
            System.out.println("6 ... Výpis zaměstnanců");
            System.out.println("7 ... Vypsat statistiky");
            System.out.println("8 ... Ukončit program");

            volba = pouzeCelaCisla(sc);
            switch (volba) {
                case 1:
                    System.out.println("Skupiny:");
                    System.out.println(" (1) Datový analytik\t(2) Bezpečnostní specialista");
                    System.out.print("Vyberte skupinu:");
                    podVolba = pouzeCelaCisla(sc);
                    System.out.print("Zadejte jméno: ");
                    String jmeno = vyplnenyString(sc);
                    System.out.print("Zadejte příjmení: ");
                    String prijmeni = vyplnenyString(sc);
                    System.out.print("Zadejte rok narození: ");
                    int rok_narozeni = pouzeCelaCisla(sc);
                    switch (podVolba) {
                        case 1:
                            databazeZamestnancu.pridatZamestnance(new DatovyAnalytik(jmeno, prijmeni, rok_narozeni));
                            break;
                        case 2:
                            databazeZamestnancu.pridatZamestnance(new BezpecnostniSpecialista(jmeno, prijmeni, rok_narozeni));
                            break;
                        default:
                            System.out.println("Neplatná volba skupiny");
                            break;
                    }
                    break;
                case 2:
                    System.out.print("Zadejte ID zaměstnance pro odstranění: ");
                    databazeZamestnancu.odebratZamestnance(pouzeCelaCisla(sc));
                    break;
                case 3:
                    System.out.print("Zadejte ID zaměstnance: ");
                    int z = pouzeCelaCisla(sc);
                    System.out.print("\nZadejte ID kolegy: ");
                    int k = pouzeCelaCisla(sc);
                    System.out.println("Úrovně spolupráce: (1)SPATNA\t(2)PRUMERNA\t(3)DOBRA");
                    System.out.print("Zadejte úroveň spolupráce: ");
                    podVolba = pouzeCelaCisla(sc);
                    KvalitaSpoluprace[] us = KvalitaSpoluprace.values();
                    if (podVolba >= 1 && podVolba <= us.length) {
                        databazeZamestnancu.pridatSpolupraci(z, k, us[podVolba - 1]);
                    } else {
                        System.out.println("Neplatná volba spolupráce");
                    }
                    break;
                case 4:
                    System.out.print("Zadejte ID zaměstnance: ");
                    Zamestnanec vz = databazeZamestnancu.getZamestnanec(pouzeCelaCisla(sc));
                    if (vz == null) {
                        System.out.println("Zaměstnanec nenalezen");
                    } else {
                        databazeZamestnancu.vypsatZamestnance(vz);
                    }
                    break;
                case 5:
                    System.out.print("Zadejte ID zaměstnance: ");
                    databazeZamestnancu.getDovednost(pouzeCelaCisla(sc));
                    break;
                case 6:
                    databazeZamestnancu.vypsatVsechnyZamestnance();
                    break;
                case 7:
                    databazeZamestnancu.vypsatStatistiky();
                    break;
                case 8:
                    run = false;
                    break;
            }
        }
    }
}