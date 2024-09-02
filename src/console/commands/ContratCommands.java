package console.commands;


import model.Contrat;
import model.StatutContrat;
import dao.ContratDAO;

import java.util.Scanner;
import java.util.UUID;
import java.util.List;

import java.sql.Date;
import java.math.BigDecimal;













public class ContratCommands {
    private final ContratDAO contratDAO = new ContratDAO();
    private final Scanner scanner = new Scanner(System.in);


    public void ajouterContrat() {
        System.out.println("=== Ajouter un contrat ===");

        UUID id = UUID.randomUUID();

        System.out.print("Entrez l'ID du partenaire : ");
        String partenaireIdStr = scanner.nextLine();
        UUID partenaireId = UUID.fromString(partenaireIdStr);

        System.out.print("Entrez la date de début (YYYY-MM-DD) : ");
        String dateDebutStr = scanner.nextLine();
        Date dateDebut = Date.valueOf(dateDebutStr);

        System.out.print("Entrez la date de fin (YYYY-MM-DD) (laisser vide si indéfinie) : ");
        String dateFinStr = scanner.nextLine();
        Date dateFin = dateFinStr.isEmpty() ? null : Date.valueOf(dateFinStr);

        System.out.print("Entrez le tarif spécial : ");
        BigDecimal tarifSpecial = BigDecimal.valueOf(scanner.nextDouble());
        scanner.nextLine(); // Consomme la nouvelle ligne

        System.out.print("Entrez les conditions de l'accord : ");
        String conditionsAccord = scanner.nextLine();

        System.out.print("Le contrat est-il renouvelable ? (true/false) : ");
        boolean renouvelable = scanner.nextBoolean();
        scanner.nextLine(); // Consomme la nouvelle ligne

        System.out.print("Entrez le statut du contrat (EN_COURS, TERMINE, SUSPENDU) : ");
        String statutContratStr = scanner.nextLine();
        StatutContrat statutContrat = StatutContrat.valueOf(statutContratStr);

        Contrat contrat = new Contrat(id, dateDebut, dateFin, tarifSpecial, conditionsAccord, renouvelable, statutContrat);
        contrat.setPartenaireId(partenaireId); // Associe le contrat à un partenaire

        contratDAO.addContrat(contrat);

        System.out.println("Contrat ajouté avec succès.");
    }

    public void afficherTousLesContrats() {
        System.out.println("=== Liste des contrats ===");
        List<Contrat> contrats = contratDAO.getAllContrats();

        for (Contrat contrat : contrats) {
            System.out.println(contrat);
        }
    }

    public void mettreAJourContrat() {
        System.out.println("=== Mettre à jour un contrat ===");

        System.out.print("Entrez l'ID du contrat à mettre à jour : ");
        String idStr = scanner.nextLine();
        UUID id = UUID.fromString(idStr);

        Contrat contrat = contratDAO.getContratById(id);
        if (contrat == null) {
            System.out.println("Contrat non trouvé.");
            return;
        }

        System.out.print("Entrez la nouvelle date de début (YYYY-MM-DD) (laisser vide pour ne pas modifier) : ");
        String dateDebutStr = scanner.nextLine();
        if (!dateDebutStr.isEmpty()) {
            Date dateDebut = Date.valueOf(dateDebutStr);
            contrat.setDateDebut(dateDebut);
        }

        System.out.print("Entrez la nouvelle date de fin (YYYY-MM-DD) (laisser vide pour ne pas modifier) : ");
        String dateFinStr = scanner.nextLine();
        if (!dateFinStr.isEmpty()) {
            Date dateFin = Date.valueOf(dateFinStr);
            contrat.setDateFin(dateFin);
        }

        System.out.print("Entrez le nouveau tarif spécial (laisser vide pour ne pas modifier) : ");
        String tarifSpecialStr = scanner.nextLine();
        if (!tarifSpecialStr.isEmpty()) {
            BigDecimal tarifSpecial = new BigDecimal(tarifSpecialStr);
            contrat.setTarifSpecial(tarifSpecial);
        }

        System.out.print("Entrez les nouvelles conditions de l'accord (laisser vide pour ne pas modifier) : ");
        String conditionsAccord = scanner.nextLine();
        if (!conditionsAccord.isEmpty()) {
            contrat.setConditionsAccord(conditionsAccord);
        }

        System.out.print("Le contrat est-il renouvelable ? (true/false, laisser vide pour ne pas modifier) : ");
        String renouvelableStr = scanner.nextLine();
        if (!renouvelableStr.isEmpty()) {
            boolean renouvelable = Boolean.parseBoolean(renouvelableStr);
            contrat.setRenouvelable(renouvelable);
        }

        System.out.print("Entrez le nouveau statut du contrat (laisser vide pour ne pas modifier) : ");
        String statutContratStr = scanner.nextLine();
        if (!statutContratStr.isEmpty()) {
            StatutContrat statutContrat = StatutContrat.valueOf(statutContratStr);
            contrat.setStatutContrat(statutContrat);
        }

        contratDAO.updateContrat(contrat);
        System.out.println("Contrat mis à jour avec succès.");
    }

    public void supprimerContrat() {
        System.out.println("=== Supprimer un contrat ===");

        System.out.print("Entrez l'ID du contrat à supprimer : ");
        String idStr = scanner.nextLine();
        UUID id = UUID.fromString(idStr);

        contratDAO.deleteContrat(id);
        System.out.println("Contrat supprimé avec succès.");
    }
}
