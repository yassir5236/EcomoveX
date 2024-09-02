package console.commands;

import model.OffrePromotionnelle;
import dao.OffrePromotionnelleDAO;
import model.TypeReduction;
import model.StatutOffre;

import java.util.Scanner;
import java.util.UUID;
import java.sql.Date;
import java.math.BigDecimal;
import java.util.List;

public class OffrePromotionnelleCommands {
    private final OffrePromotionnelleDAO offreDAO = new OffrePromotionnelleDAO();
    private final Scanner scanner = new Scanner(System.in);

    public void ajouterOffre() {
        System.out.println("=== Ajouter une offre promotionnelle ===");

        UUID id = UUID.randomUUID();

        System.out.print("Entrez l'ID du contrat associé : ");
        String contratIdStr = scanner.nextLine();
        UUID contratId = UUID.fromString(contratIdStr);

        System.out.print("Entrez le nom de l'offre : ");
        String nomOffre = scanner.nextLine();

        System.out.print("Entrez la description de l'offre : ");
        String description = scanner.nextLine();

        System.out.print("Entrez la date de début (YYYY-MM-DD) : ");
        String dateDebutStr = scanner.nextLine();
        Date dateDebut = Date.valueOf(dateDebutStr);

        System.out.print("Entrez la date de fin (YYYY-MM-DD) : ");
        String dateFinStr = scanner.nextLine();
        Date dateFin = Date.valueOf(dateFinStr);

        System.out.print("Entrez le type de réduction (POURCENTAGE, MONTANT_FIXE) : ");
        String typeReductionStr = scanner.nextLine();
        TypeReduction typeReduction = TypeReduction.valueOf(typeReductionStr);

        System.out.print("Entrez la valeur de la réduction : ");
        BigDecimal valeurReduction = BigDecimal.valueOf(scanner.nextDouble());
        scanner.nextLine(); // Consomme la nouvelle ligne

        System.out.print("Entrez les conditions de l'offre : ");
        String conditions = scanner.nextLine();

        System.out.print("Entrez le statut de l'offre (ACTIVE, EXPIREE) : ");
        String statutOffreStr = scanner.nextLine();
        StatutOffre statutOffre = StatutOffre.valueOf(statutOffreStr);

        OffrePromotionnelle offre = new OffrePromotionnelle(id, nomOffre, description, dateDebut, dateFin,
                typeReduction, valeurReduction, conditions, statutOffre, contratId);

        offreDAO.addOffrePromotionnelle(offre);

        System.out.println("Offre promotionnelle ajoutée avec succès.");
    }

    public void afficherToutesLesOffres() {
        System.out.println("=== Liste des offres promotionnelles ===");
        List<OffrePromotionnelle> offres = offreDAO.getAllOffresPromotionnelles();

        for (OffrePromotionnelle offre : offres) {
            System.out.println(offre);
        }
    }

    public void mettreAJourOffre() {
        System.out.println("=== Mettre à jour une offre promotionnelle ===");

        System.out.print("Entrez l'ID de l'offre à mettre à jour : ");
        String idStr = scanner.nextLine();
        UUID id = UUID.fromString(idStr);

        OffrePromotionnelle offre = offreDAO.getOffreById(id);
        if (offre == null) {
            System.out.println("Offre non trouvée.");
            return;
        }

        System.out.print("Entrez le nouveau nom de l'offre (laisser vide pour ne pas modifier) : ");
        String nomOffre = scanner.nextLine();
        if (!nomOffre.isEmpty()) {
            offre.setNomOffre(nomOffre);
        }

        System.out.print("Entrez la nouvelle description de l'offre (laisser vide pour ne pas modifier) : ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            offre.setDescription(description);
        }

        System.out.print("Entrez la nouvelle date de début (YYYY-MM-DD) (laisser vide pour ne pas modifier) : ");
        String dateDebutStr = scanner.nextLine();
        if (!dateDebutStr.isEmpty()) {
            Date dateDebut = Date.valueOf(dateDebutStr);
            offre.setDateDebut(dateDebut);
        }

        System.out.print("Entrez la nouvelle date de fin (YYYY-MM-DD) (laisser vide pour ne pas modifier) : ");
        String dateFinStr = scanner.nextLine();
        if (!dateFinStr.isEmpty()) {
            Date dateFin = Date.valueOf(dateFinStr);
            offre.setDateFin(dateFin);
        }

        System.out.print("Entrez le nouveau type de réduction (laisser vide pour ne pas modifier) : ");
        String typeReductionStr = scanner.nextLine();
        if (!typeReductionStr.isEmpty()) {
            TypeReduction typeReduction = TypeReduction.valueOf(typeReductionStr);
            offre.setTypeReduction(typeReduction);
        }

        System.out.print("Entrez la nouvelle valeur de la réduction (laisser vide pour ne pas modifier) : ");
        String valeurReductionStr = scanner.nextLine();
        if (!valeurReductionStr.isEmpty()) {
            BigDecimal valeurReduction = new BigDecimal(valeurReductionStr);
            offre.setValeurReduction(valeurReduction);
        }

        System.out.print("Entrez les nouvelles conditions de l'offre (laisser vide pour ne pas modifier) : ");
        String conditions = scanner.nextLine();
        if (!conditions.isEmpty()) {
            offre.setConditions(conditions);
        }

        System.out.print("Entrez le nouveau statut de l'offre (laisser vide pour ne pas modifier) : ");
        String statutOffreStr = scanner.nextLine();
        if (!statutOffreStr.isEmpty()) {
            StatutOffre statutOffre = StatutOffre.valueOf(statutOffreStr);
            offre.setStatutOffre(statutOffre);
        }

        offreDAO.updateOffrePromotionnelle(offre);
        System.out.println("Offre promotionnelle mise à jour avec succès.");
    }

    public void supprimerOffre() {
        System.out.println("=== Supprimer une offre promotionnelle ===");

        System.out.print("Entrez l'ID de l'offre à supprimer : ");
        String idStr = scanner.nextLine();
        UUID id = UUID.fromString(idStr);

        offreDAO.deleteOffrePromotionnelle(id);
        System.out.println("Offre promotionnelle supprimée avec succès.");
    }
}
