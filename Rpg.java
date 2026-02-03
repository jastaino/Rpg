import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Inserisci il tuo nome: ");
        String name = in.nextLine().trim();
        if (name.isEmpty()) name = "Eroe";
         
        String classe = "Nessuna";
        int maxHp = 0, hp = 0, att = 0, def = 0, mag = 0,  vel =0;
        int energ=100;
        int lvl=1;
        
        while (true) {
            System.out.println("\nScegli la classe:");
            System.out.println("1) Guerriero   2) Mago   3) Ladro");
            System.out.print("Scelta (1-3): ");
            String frix = in.nextLine().trim();
            switch (frix) {
                case "1":
                    classe = "Guerriero"; maxHp = 120; att = 18; def = 12; mag = 4; vel =2; break;
                case "2":
                    classe = "Mago";      maxHp = 80;  att = 6;  def = 6;  mag = 20; vel =4; break;
                case "3":
                    classe = "Ladro";     maxHp = 100; att = 12; def = 8;  mag = 8; vel =8; break;
                default:
                    System.out.println("Scelta non valida, riprova."); continue;
            }
            break;
        }

        hp = maxHp;
        System.out.println("\n Statistiche ");
        System.out.println("Nome: " + name);
        System.out.println("Classe: " + classe);
        System.out.println("HP: " + hp + " / " + maxHp);
        System.out.println("energ 100");
        System.out.println("xp lvl 1");
        System.out.println("Attacco: " + att + "  Difesa: " + def + "  Magia: " + mag + " velocità " + vel) ;
        in.close();
    }
