package console.commands;

import dao.BilletDAO;
import model.Billet;
import model.TypeTransport;
import model.StatutBillet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class BilletCommands {

    private final BilletDAO billetDAO;

    public BilletCommands() {
        this.billetDAO = new BilletDAO();
    }

 /*   public void ajouterBillet() {
        Scanner scanner = new Scanner(System.in);

        //System.out.print("ID (UUID) : ");
        // UUID id = UUID.fromString(scanner.nextLine());
        UUID id = UUID.randomUUID();

        System.out.print("Entrez l'ID du contrat associé : ");
        String contratIdStr = scanner.nextLine();
        UUID contratId = UUID.fromString(contratIdStr);

        System.out.print("Type de transport (avion, train, bus, etc.) : ");
        TypeTransport typeTransport = TypeTransport.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Prix d'achat : ");
        BigDecimal prixAchat = scanner.nextBigDecimal();

        System.out.print("Prix de vente : ");
        BigDecimal prixVente = scanner.nextBigDecimal();

        System.out.print("Date de vente (YYYY-MM-DD HH:MM:SS) : ");
        LocalDateTime dateVente = LocalDateTime.parse(scanner.nextLine());

        System.out.print("Statut du billet (vendu, annulé, en attente) : ");
        StatutBillet statutBillet = StatutBillet.valueOf(scanner.nextLine().toUpperCase());

        // Notez que vous passez ici deux UUID au constructeur
        Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, Date.valueOf(dateVente.toLocalDate()), statutBillet);
        billetDAO.addBillet(billet);

        System.out.println("Billet ajouté avec succès!");
    }

  */

    public void ajouterBillet() {
        Scanner scanner = new Scanner(System.in);

        UUID id = UUID.randomUUID();

        System.out.print("Entrez l'ID du contrat associé : ");
        String contratIdStr = scanner.nextLine();
        UUID contratId = UUID.fromString(contratIdStr);

        System.out.print("Type de transport (avion, train, bus) : ");
        TypeTransport typeTransport = TypeTransport.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Prix d'achat : ");
        BigDecimal prixAchat = scanner.nextBigDecimal();

        System.out.print("Prix de vente : ");
        BigDecimal prixVente = scanner.nextBigDecimal();

        scanner.nextLine(); // Consommer la nouvelle ligne restante après nextBigDecimal()

        System.out.print("Date de vente (YYYY-MM-DD HH:MM:SS) : ");
        String dateVenteInput = scanner.nextLine();

        LocalDateTime dateVente = null;
        try {
            dateVente = LocalDateTime.parse(dateVenteInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD HH:MM:SS.");
            return ;
        }

        System.out.print("Statut du billet (vendu, annulé, en attente) : ");
        StatutBillet statutBillet = StatutBillet.valueOf(scanner.nextLine().toUpperCase());

        // Notez que vous passez ici deux UUID au constructeur
        Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, Date.valueOf(dateVente.toLocalDate()), statutBillet);
        billetDAO.addBillet(billet);

        System.out.println("Billet ajouté avec succès!");
    }




    public void afficherTousLesBillets() {
        List<Billet> billets = billetDAO.getAllBillets();
        System.out.println("=== Liste des billets ===");
        for (Billet billet : billets) {
            System.out.println(billet);
        }
    }

    public void mettreAJourBillet() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID du billet à mettre à jour (UUID) : ");
        UUID id = UUID.fromString(scanner.nextLine());

        System.out.print("Entrez l'ID du contrat associé : ");
        String contratIdStr = scanner.nextLine();
        UUID contratId = UUID.fromString(contratIdStr);

        System.out.print("Nouveau type de transport (avion, train, bus) : ");
        TypeTransport typeTransport = TypeTransport.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Nouveau prix d'achat : ");
        BigDecimal prixAchat = scanner.nextBigDecimal();

        System.out.print("Nouveau prix de vente : ");
        BigDecimal prixVente = scanner.nextBigDecimal();

        scanner.nextLine();  // Consommer la nouvelle ligne restante après nextBigDecimal()

        System.out.print("Nouvelle date de vente (YYYY-MM-DD HH:MM:SS) : ");
        String dateVenteInput = scanner.nextLine();
        LocalDateTime dateVente = null;

        try {
            dateVente = LocalDateTime.parse(dateVenteInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD HH:MM:SS.");
            return;  // Arrêter l'exécution si la date n'est pas correcte
        }

        System.out.print("Nouveau statut du billet (vendu, annulé, en attente) : ");
        StatutBillet statutBillet = StatutBillet.valueOf(scanner.nextLine().toUpperCase());

        // Notez que vous passez ici les deux UUID au constructeur
        Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, Date.valueOf(dateVente.toLocalDate()), statutBillet);
        billetDAO.updateBillet(billet);

        System.out.println("Billet mis à jour avec succès!");
    }




    public void supprimerBillet() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID du billet à supprimer (UUID) : ");
        UUID id = UUID.fromString(scanner.nextLine());

        billetDAO.deleteBillet(id);

        System.out.println("Billet supprimé avec succès!");
    }
}




