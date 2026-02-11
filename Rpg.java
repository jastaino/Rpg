import java.util.Scanner;

public class Main {

    // INVENTARIO
    static String[] inventario = new String[4];

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Inserisci il tuo nome: ");
        String name = in.nextLine().trim();
        if (name.isEmpty()) name = "Eroe";

        String classe = "";
        int maxHp = 0, hp = 0, att = 0, def = 0, mag = 0, vel = 0;
        int energ = 100;
        int lvl = 1;

        while (true) {
            System.out.println("\nScegli la classe:");
            System.out.println("1) Guerriero   2) Mago   3) Ladro");
            System.out.print("Scelta (1-3): ");
            String rpg = in.nextLine().trim();

            switch (rpg) {
                case "1":
                    classe = "Guerriero";
                    maxHp = 120; att = 18; def = 12; mag = 4; vel = 2;
                    break;

                case "2":
                    classe = "Mago";
                    maxHp = 80; att = 6; def = 6; mag = 20; vel = 4;
                    break;

                case "3":
                    classe = "Ladro";
                    maxHp = 100; att = 12; def = 8; mag = 8; vel = 8;
                    break;

                default:
                    System.out.println("Scelta non valida, riprova.");
                    continue;
            }
            break;
        }

        // Inizializzazione inventario
        inizializzaInventario(classe);

        // Ordinamento inventario
        ordinaInventario();

        hp = maxHp;

        System.out.println("\n STATISTICHE ");
        System.out.println("Nome: " + name);
        System.out.println("Classe: " + classe);
        System.out.println("HP: " + hp + " / " + maxHp);
        System.out.println("Energia: " + energ);
        System.out.println("Livello: " + lvl);
        System.out.println("Attacco: " + att +
                           "  Difesa: " + def +
                           "  Magia: " + mag +
                           "  Velocità: " + vel);

        System.out.println("\n INVENTARIO (ordinato) ");
        for (String oggetto : inventario) {
            System.out.println("- " + oggetto);
        }

        in.close();
    }

    // 🔹 Metodo per inizializzare inventario
    public static void inizializzaInventario(String classe) {

        if (classe.equals("Guerriero")) {
            inventario[0] = "Spada";
            inventario[1] = "Scudo";
            inventario[2] = "Pozione 10";
            inventario[3] = "Pozione 20";
        }

        else if (classe.equals("Mago")) {
            inventario[0] = "Bastone magico";
            inventario[1] = "Pergamena";
            inventario[2] = "Pozione 10";
            inventario[3] = "Pozione 20";
        }

        else if (classe.equals("Ladro")) {
            inventario[0] = "Pugnale";
            inventario[1] = "Arco";
            inventario[2] = "Pozione 10";
            inventario[3] = "Pozione 20";
        }
    }

    // 🔹 Metodo per ordinare inventario
    public static void ordinaInventario() {

        for (int i = 0; i < inventario.length - 1; i++) {
            for (int j = i + 1; j < inventario.length; j++) {

                if (inventario[i].compareToIgnoreCase(inventario[j]) > 0) {

                    // scambio
                    String temp = inventario[i];
                    inventario[i] = inventario[j];
                    inventario[j] = temp;
                }
            }
        }
    }
}
