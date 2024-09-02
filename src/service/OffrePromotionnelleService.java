package service;

import dao.OffrePromotionnelleDAO;
import model.OffrePromotionnelle;

import java.util.List;
import java.util.UUID;

public class OffrePromotionnelleService {
    private final OffrePromotionnelleDAO offrePromotionnelleDAO = new OffrePromotionnelleDAO();

    public void addOffrePromotionnelle(OffrePromotionnelle offrePromotionnelle) {
        offrePromotionnelleDAO.ajouterOffre(offrePromotionnelle);

    }

    public List<OffrePromotionnelle> getAllOffresPromotionnelles() {
        return offrePromotionnelleDAO.lireToutesLesOffres();

    }

    public OffrePromotionnelle getOffrePromotionnelleById(UUID id) {
        return offrePromotionnelleDAO.lireOffre(id);
    }

    public void updateOffrePromotionnelle(OffrePromotionnelle offrePromotionnelle) {
        offrePromotionnelleDAO.modifierOffre(offrePromotionnelle);
    }

    public void deleteOffrePromotionnelle(UUID id) {
        offrePromotionnelleDAO.supprimerOffre(id);
    }
}
