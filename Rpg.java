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

        System.out.println("\n===== INVENTARIO =====");
        for (String oggetto : inventario) {
            if (oggetto != null)
                System.out.println("- " + oggetto);
        }

        combattiNemico(name, energia, att, def, velocita);

        in.close();
    }

    public static void inizializzaInventario(String classe) {

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

        inventario[3] = null;
    }

    public static void ordinaInventario() {

        for (int i = 0; i < inventario.length - 1; i++) {
            for (int j = i + 1; j < inventario.length; j++) {

                if (inventario[i] == null && inventario[j] != null) {
                    String temp = inventario[i];
                    inventario[i] = inventario[j];
                    inventario[j] = temp;
                }
                else if (inventario[i] != null && inventario[j] != null &&
                        inventario[i].compareToIgnoreCase(inventario[j]) > 0) {

                    String temp = inventario[i];
                    inventario[i] = inventario[j];
                    inventario[j] = temp;
                }
            }
        }
    }

    public static String generaNemico(int[] statsNemico) {

        int tipo = rand.nextInt(3);

        switch (tipo) {

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

            int schivata = rand.nextInt(100);

            if (velocita > schivata) {
                System.out.println("Hai schivato l'attacco! Il nemico salta il turno!");
            } else {

                int dannoNemico = attNemico + rand.nextInt(6) - def / 2;
                if (dannoNemico < 0) dannoNemico = 0;

                energia -= dannoNemico;

                System.out.println(nomeNemico + " infligge " + dannoNemico + " danni!");
            }

            System.out.println("Energia tua: " + energia +
                               " | Energia nemico: " + energiaNemico);
        }

        if (energia > 0) {
            System.out.println("\nHai sconfitto il " + nomeNemico + "!");
        } else {
            System.out.println("\nSei stato sconfitto...");
        }
    }
}
