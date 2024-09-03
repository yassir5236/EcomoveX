package console.ui;

import console.ui.PartenaireUI;

import console.ui.ContratUI;
import console.ui.OffreUI;
import console.ui.BilletUI;

import java.util.Scanner;

public class MainConsole {

    private final PartenaireUI partenaireUI;
    private final ContratUI contratUI;
    private final OffreUI offreUI;
    private final BilletUI billetUI;

    public MainConsole() {
        this.partenaireUI = new PartenaireUI();
        this.contratUI = new ContratUI();
        this.offreUI = new OffreUI();
        this.billetUI = new BilletUI();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Gestion des Partenaires");
            System.out.println("2. Gestion des Contrats");
            System.out.println("3. Gestion des Offres Promotionnelles");
            System.out.println("4. Gestion des Billets");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    partenaireUI.start();
                    break;
                case 2:
                    contratUI.start();
                    break;
                case 3:
                    offreUI.start();
                    break;
                case 4:
                    billetUI.start();
                    break;
                case 5:
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Option invalide. Essayez encore.");
                    break;
            }
        } while (choix != 5);

        scanner.close();
    }

    public static void main(String[] args) {
        MainConsole mainConsole = new MainConsole();
        mainConsole.start();
    }
}
