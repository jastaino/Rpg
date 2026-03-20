import java.util.Scanner;
import java.util.Random;

public class Main {

    static String[] inventario = new String[4];
    static Random rand = new Random();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Inserisci il tuo nome: ");
        String name = in.nextLine().trim();
        if (name.isEmpty()) name = "Eroe";

        String classe = "";
        int maxEnergia = 0, energia = 0, att = 0, def = 0, velocita = 0;

        // Scelta classe
        while (true) {
            System.out.println("\nScegli la classe:");
            System.out.println("1) Guerriero   2) Mago   3) Ladro");
            System.out.print("Scelta (1-3): ");
            String scelta = in.nextLine().trim();

            switch (scelta) {
                case "1":
                    classe = "Guerriero";
                    maxEnergia = 120;
                    att = 18;
                    def = 12;
                    velocita = 25;
                    break;
                case "2":
                    classe = "Mago";
                    maxEnergia = 80;
                    att = 6;
                    def = 6;
                    velocita = 30;
                    break;
                case "3":
                    classe = "Ladro";
                    maxEnergia = 100;
                    att = 12;
                    def = 8;
                    velocita = 35;
                    break;
                default:
                    System.out.println("Scelta non valida, riprova.");
                    continue;
            }
            break;
        }

        inizializzaInventario(classe);
        ordinaInventario();
        energia = maxEnergia;

        System.out.println("\n===== STATISTICHE =====");
        System.out.println("Nome: " + name);
        System.out.println("Classe: " + classe);
        System.out.println("Energia: " + energia + " / " + maxEnergia);
        System.out.println("Attacco: " + att);
        System.out.println("Difesa: " + def);
        System.out.println("Velocità: " + velocita);

        stampaInventario();

        // Menu principale
        boolean esci = false;
        while (!esci) {
            System.out.println("\n===== MENU =====");
            System.out.println("1) Esplora la foresta");
            System.out.println("2) Combatti un nemico");
            System.out.println("3) Mostra inventario");
            System.out.println("4) Esci dal gioco");
            System.out.print("Scelta (1-4): ");
            String sceltaMenu = in.nextLine().trim();

            switch (sceltaMenu) {
                case "1":
                    boolean missioneCompletata = missioneEsplorazione(in);
                    if (missioneCompletata) {
                        System.out.println("Hai trovato almeno un oggetto durante l'esplorazione!");
                    } else {
                        System.out.println("Non hai trovato nulla di utile.");
                    }
                    ordinaInventario();
                    break;
                case "2":
                    combattiNemico(name, energia, att, def, velocita);
                    break;
                case "3":
                    stampaInventario();
                    break;
                case "4":
                    System.out.println("Grazie per aver giocato!");
                    esci = true;
                    break;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }

        in.close();
    }

    // ===== INVENTARIO =====

    public static void inizializzaInventario(String classe) {
        inventario[0] = inventario[1] = inventario[2] = inventario[3] = null;

        switch (classe) {
            case "Guerriero":
                inventario[0] = "Spada";
                inventario[1] = "Scudo";
                inventario[2] = "Pozione 10";
                break;
            case "Mago":
                inventario[0] = "Bastone magico";
                inventario[1] = "Pergamena";
                inventario[2] = "Pozione 10";
                break;
            case "Ladro":
                inventario[0] = "Pugnale";
                inventario[1] = "Arco";
                inventario[2] = "Pozione 10";
                break;
        }
    }

    public static void stampaInventario() {
        System.out.println("\n===== INVENTARIO =====");
        for (String oggetto : inventario) {
            if (oggetto != null)
                System.out.println("- " + oggetto);
        }
    }

    public static void aggiungiInventario(String oggetto) {
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] == null) {
                inventario[i] = oggetto;
                System.out.println(oggetto + " aggiunto all'inventario!");
                return;
            }
        }
        System.out.println("Inventario pieno! Non puoi raccogliere: " + oggetto);
    }

    public static void ordinaInventario() {
        for (int i = 0; i < inventario.length - 1; i++) {
            for (int j = i + 1; j < inventario.length; j++) {
                if (inventario[i] == null && inventario[j] != null) {
                    String temp = inventario[i];
                    inventario[i] = inventario[j];
                    inventario[j] = temp;
                } else if (inventario[i] != null && inventario[j] != null &&
                        inventario[i].compareToIgnoreCase(inventario[j]) > 0) {
                    String temp = inventario[i];
                    inventario[i] = inventario[j];
                    inventario[j] = temp;
                }
            }
        }
    }

    // ===== ESPLORAZIONE =====

    public static boolean missioneEsplorazione(Scanner in) {
        boolean missioneCompletata = false;

        System.out.println("Ti addentri nella foresta...");
        int evento = rand.nextInt(3);

        switch (evento) {
            case 0:
                String oggetto = generaOggettoRandom();
                System.out.println("Hai trovato un oggetto: " + oggetto);
                aggiungiInventario(oggetto);
                missioneCompletata = true;
                break;
            case 1:
                System.out.println("Non trovi nulla...");
                break;
            case 2:
                System.out.println("Un pericolo ti spaventa e torni indietro!");
                break;
        }

        return missioneCompletata;
    }

    public static String generaOggettoRandom() {
        switch (rand.nextInt(4)) {
            case 0: return "Pozione 10";
            case 1: return "Pozione 20";
            case 2: return "Elisir";
            case 3: return "Chiave misteriosa";
            default: return "Oggetto sconosciuto";
        }
    }

    // ===== COMBATTIMENTO =====

    public static String generaNemico(int[] statsNemico) {
        switch (rand.nextInt(3)) {
            case 0:
                statsNemico[0] = 60;
                statsNemico[1] = 10;
                return "Goblin";
            case 1:
                statsNemico[0] = 80;
                statsNemico[1] = 14;
                return "Scheletro";
            case 2:
                statsNemico[0] = 100;
                statsNemico[1] = 18;
                return "Orco";
            default:
                statsNemico[0] = 50;
                statsNemico[1] = 8;
                return "Creatura misteriosa";
        }
    }

    public static void combattiNemico(String name, int energia, int att, int def, int velocita) {
        int[] statsNemico = new int[2];
        String nomeNemico = generaNemico(statsNemico);

        int energiaNemico = statsNemico[0];
        int attNemico = statsNemico[1];

        System.out.println("\nÈ apparso un " + nomeNemico + "!");
        System.out.println(nomeNemico + " Energia: " + energiaNemico);

        while (energia > 0 && energiaNemico > 0) {

            int danno = att + rand.nextInt(6);
            energiaNemico -= danno;
            System.out.println(name + " infligge " + danno + " danni!");

            if (energiaNemico <= 0) break;

            if (rand.nextInt(100) < velocita) {
                System.out.println("Hai schivato l'attacco!");
            } else {
                int dannoNemico = attNemico + rand.nextInt(6) - def / 2;
                if (dannoNemico < 0) dannoNemico = 0;

                energia -= dannoNemico;
                System.out.println(nomeNemico + " infligge " + dannoNemico + " danni!");
            }

            System.out.println("Energia tua: " + energia + " | Energia nemico: " + energiaNemico);
        }

        if (energia > 0) {
            System.out.println("\nHai sconfitto il " + nomeNemico + "!");
        } else {
            System.out.println("\nSei stato sconfitto...");
        }
    }
}
