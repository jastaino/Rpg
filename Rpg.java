import java.util.Scanner;

public class Main {

    // INVENTARIO 
    static String[] inventario;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Inserisci il tuo nome: ");
        String name = in.nextLine().trim();
        if (name.isEmpty()) name = "Eroe";

        String classe = "Nessuna";
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
                    inventario = new String[] {
                        "Spada di ferro",
                        "Scudo",
                        "Pozione di cura 10",
                        "Pozione di cura 20"
                    };
                    break;

                case "2":
                    classe = "Mago";
                    maxHp = 80; att = 6; def = 6; mag = 20; vel = 4;
                    inventario = new String[] {
                        "Bastone magico",
                        "Pergamena",
                        "Pozione di mana 10",
                        "Pozione di mana 20"
                    };
                    break;

                case "3":
                    classe = "Ladro";
                    maxHp = 100; att = 12; def = 8; mag = 8; vel = 8;
                    inventario = new String[] {
                        "Pugnale",
                        "Arco",
                        "pozione cura 10",
                        "pozione cura 20"
                    };
                    break;

                default:
                    System.out.println("Scelta non valida, riprova.");
                    continue;
                    
                    case "4":
            }
            break;
        }

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

        System.out.println("\n INVENTARIO ");
        for (String oggetto : inventario) {
            System.out.println("- " + oggetto);
        }

        in.close();
    }
}
