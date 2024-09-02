package dao;

import model.OffrePromotionnelle;
import model.StatutContrat; // Assurez-vous que vous utilisez le bon Enum
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OffrePromotionnelleDAO {

    public void ajouterOffre(OffrePromotionnelle offre) {
        String sql = "INSERT INTO offre_promotionnelle (id, date_debut, date_fin, tarif_special, conditions_acord, renouvelable, statut_contrat) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, offre.getId());
            pstmt.setDate(2, offre.getDateDebut());
            pstmt.setDate(3, offre.getDateFin());
            pstmt.setBigDecimal(4, offre.getTarifSpecial());
            pstmt.setString(5, offre.getConditionsAccord());
            pstmt.setBoolean(6, offre.isRenouvelable());
            pstmt.setString(7, offre.getStatutContrat().name());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierOffre(OffrePromotionnelle offre) {
        String sql = "UPDATE offre_promotionnelle SET date_debut = ?, date_fin = ?, tarif_special = ?, conditions_acord = ?, renouvelable = ?, statut_contrat = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, offre.getDateDebut());
            pstmt.setDate(2, offre.getDateFin());
            pstmt.setBigDecimal(3, offre.getTarifSpecial());
            pstmt.setString(4, offre.getConditionsAccord());
            pstmt.setBoolean(5, offre.isRenouvelable());
            pstmt.setString(6, offre.getStatutContrat().name());
            pstmt.setObject(7, offre.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerOffre(UUID id) {
        String sql = "DELETE FROM offre_promotionnelle WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OffrePromotionnelle lireOffre(UUID id) {
        String sql = "SELECT * FROM offre_promotionnelle WHERE id = ?";
        OffrePromotionnelle offre = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                offre = new OffrePromotionnelle(
                        (UUID) rs.getObject("id"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getBigDecimal("tarif_special"),
                        rs.getString("conditions_acord"),
                        rs.getBoolean("renouvelable"),
                        StatutContrat.valueOf(rs.getString("statut_contrat"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offre;
    }

    public List<OffrePromotionnelle> lireToutesLesOffres() {
        String sql = "SELECT * FROM offre_promotionnelle";
        List<OffrePromotionnelle> offres = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                OffrePromotionnelle offre = new OffrePromotionnelle(
                        (UUID) rs.getObject("id"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getBigDecimal("tarif_special"),
                        rs.getString("conditions_acord"),
                        rs.getBoolean("renouvelable"),
                        StatutContrat.valueOf(rs.getString("statut_contrat"))
                );
                offres.add(offre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offres;
    }
}
