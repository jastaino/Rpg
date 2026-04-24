import java.util.Scanner;
import java.util.Random;

public class Main {

    static String[] inventario = new String[4];
    static Random rand = new Random();
    
    static final int RIGHE = 10;
    static final int COLONNE = 10;
    static String[][] mappa = new String[RIGHE][COLONNE];
    static int posizionePG_X = 0;
    static int posizionePG_Y = 0;
    
    static String[] tipiOggetti = {"Pozione", "Oro", "Cristallo", "Chiave"};
    static int[][] posizioniOggetti = new int[10][2];
    static int numOggetti = 0;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Inserisci il tuo nome: ");
        String name = in.nextLine().trim();
        if (name.isEmpty()) name = "Eroe";

        String classe = "";
        int maxEnergia = 0, energia = 0, att = 0, def = 0, velocita = 0, mana = 0, resistenza = 0;

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
                    mana = 20;
                    resistenza = 15;
                    break;
                case "2":
                    classe = "Mago";
                    maxEnergia = 80;
                    att = 6;
                    def = 6;
                    velocita = 30;
                    mana = 100;
                    resistenza = 5;
                    break;
                case "3":
                    classe = "Ladro";
                    maxEnergia = 100;
                    att = 12;
                    def = 8;
                    velocita = 35;
                    mana = 40;
                    resistenza = 8;
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
        
        inizializzaMappa();

        System.out.println("\n STATISTICHE ");
        System.out.println("Nome: " + name);
        System.out.println("Classe: " + classe);
        System.out.println("Energia: " + energia + " / " + maxEnergia);
        System.out.println("Attacco: " + att);
        System.out.println("Difesa: " + def);
        System.out.println("Velocità: " + velocita);
        System.out.println("Mana: " + mana);
        System.out.println("Resistenza: " + resistenza);

        stampaInventario();

        boolean esci = false;
        while (!esci) {
            System.out.println("\n MENU ");
            System.out.println("1) Esplora la mappa");
            System.out.println("2) Mostra inventario");
            System.out.println("3) Esci dal gioco");
            System.out.print("Scelta (1-3): ");
            String sceltaMenu = in.nextLine().trim();

            switch (sceltaMenu) {
                case "1":
                    stampaMappa();
                    esploraMappa(in, name, energia, att, def, velocita);
                    break;
                case "2":
                    stampaInventario();
                    break;
                case "3":
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

    public static void inizializzaMappa() {
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                mappa[i][j] = ".";
            }
        }
        
        int x = rand.nextInt(COLONNE);
        int y = rand.nextInt(RIGHE);
        
        while (!mappa[y][x].equals(".")) {
            x = rand.nextInt(COLONNE);
            y = rand.nextInt(RIGHE);
        }
        
        posizionePG_X = x;
        posizionePG_Y = y;
        mappa[posizionePG_Y][posizionePG_X] = "P";
        
        int nemiciPositi = 0;
        while (nemiciPositi < 5) {
            x = rand.nextInt(COLONNE);
            y = rand.nextInt(RIGHE);
            
            if (mappa[y][x].equals(".")) {
                mappa[y][x] = "N";
                nemiciPositi++;
            }
        }
        
        numOggetti = 0;
        int oggettiPositi = 0;
        while (oggettiPositi < 6 && numOggetti < 10) {
            x = rand.nextInt(COLONNE);
            y = rand.nextInt(RIGHE);
            
            if (mappa[y][x].equals(".")) {
                mappa[y][x] = "O";
                posizioniOggetti[numOggetti][0] = y;
                posizioniOggetti[numOggetti][1] = x;
                numOggetti++;
                oggettiPositi++;
            }
        }
    }
    
    public static void stampaMappa() {
        System.out.println("\n MAPPA " + RIGHE + "x" + COLONNE + " ");
        
        System.out.print("  ");
        for (int j = 0; j < COLONNE; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        
        for (int i = 0; i < RIGHE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < COLONNE; j++) {
                System.out.print(mappa[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("P = Tu | N = Nemico | O = Oggetto | . = Vuoto");
    }
    
    public static void esploraMappa(Scanner in, String name, int energia, int att, int def, int velocita) {
        boolean esplorazioneAttiva = true;
        
        while (esplorazioneAttiva) {
            stampaMappa();
            System.out.println("\nMuoviti con W/A/S/D (su/giu/destra/sinistra) o 4 per tornare al menu:");
            String input = in.nextLine().toLowerCase().trim();
            
            switch (input) {
                case "w":
                case "su":
                    if (posizionePG_Y > 0 && !mappa[posizionePG_Y - 1][posizionePG_X].equals("N")) {
                        mappa[posizionePG_Y][posizionePG_X] = ".";
                        posizionePG_Y--;
                        mappa[posizionePG_Y][posizionePG_X] = "P";
                        controllaInterazioni(name, energia, att, def, velocita);
                    } else {
                        System.out.println("Non puoi muoverti in questa direzione!");
                    }
                    break;
                    
                case "s":
                case "giu":
                    if (posizionePG_Y < RIGHE - 1 && !mappa[posizionePG_Y + 1][posizionePG_X].equals("N")) {
                        mappa[posizionePG_Y][posizionePG_X] = ".";
                        posizionePG_Y++;
                        mappa[posizionePG_Y][posizionePG_X] = "P";
                        controllaInterazioni(name, energia, att, def, velocita);
                    } else {
                        System.out.println("Non puoi muoverti in questa direzione!");
                    }
                    break;
                    
                case "a":
                case "sinistra":
                    if (posizionePG_X > 0 && !mappa[posizionePG_Y][posizionePG_X - 1].equals("N")) {
                        mappa[posizionePG_Y][posizionePG_X] = ".";
                        posizionePG_X--;
                        mappa[posizionePG_Y][posizionePG_X] = "P";
                        controllaInterazioni(name, energia, att, def, velocita);
                    } else {
                        System.out.println("Non puoi muoverti in questa direzione!");
                    }
                    break;
                    
                case "d":
                case "destra":
                    if (posizionePG_X < COLONNE - 1 && !mappa[posizionePG_Y][posizionePG_X + 1].equals("N")) {
                        mappa[posizionePG_Y][posizionePG_X] = ".";
                        posizionePG_X++;
                        mappa[posizionePG_Y][posizionePG_X] = "P";
                        controllaInterazioni(name, energia, att, def, velocita);
                    } else {
                        System.out.println("Non puoi muoverti in questa direzione!");
                    }
                    break;
                    
                case "4":
                    esplorazioneAttiva = false;
                    break;
                    
                default:
                    System.out.println("Input non valido!");
            }
        }
    }
    
    public static void controllaInterazioni(String name, int energia, int att, int def, int velocita) {
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                if (mappa[i][j].equals("N")) {
                    int distanzaX = Math.abs(i - posizionePG_Y);
                    int distanzaY = Math.abs(j - posizionePG_X);
                    
                    if ((distanzaX == 1 && distanzaY == 0) || (distanzaX == 0 && distanzaY == 1)) {
                        System.out.println("Hai incontrato un nemico!");
                        combattiNemico(name, energia, att, def, velocita);
                        mappa[i][j] = ".";
                        return;
                    }
                }
            }
        }
        
        for (int i = 0; i < numOggetti; i++) {
            int oggY = posizioniOggetti[i][0];
            int oggX = posizioniOggetti[i][1];
            
            if (oggY >= 0 && mappa[oggY][oggX].equals("O")) {
                int distanzaX = Math.abs(oggY - posizionePG_Y);
                int distanzaY = Math.abs(oggX - posizionePG_X);
                
                if ((distanzaX == 1 && distanzaY == 0) || (distanzaX == 0 && distanzaY == 1)) {
                    String oggetto = tipiOggetti[rand.nextInt(tipiOggetti.length)];
                    System.out.println("Hai trovato un " + oggetto + "!");
                    aggiungiInventario(oggetto);
                    mappa[oggY][oggX] = ".";
                    posizioniOggetti[i][0] = -1;
                    posizioniOggetti[i][1] = -1;
                    stampaInventario();
                    return;
                }
            }
        }
    }

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
        System.out.println("\n INVENTARIO ");
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
                ordinaInventario();
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
        }
        return "Orco";
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
