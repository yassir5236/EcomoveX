package dao;

import model.Contrat;
import model.StatutContrat;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContratDAO {

    public void addContrat(Contrat contrat) {
        String query = "INSERT INTO contrats (id, date_debut, date_fin, tarif_special, conditions_acord, renouvelable, statut_contrat) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, contrat.getId());
            statement.setDate(2, new java.sql.Date(contrat.getDateDebut().getTime())); // Conversion correcte
            statement.setDate(3, new java.sql.Date(contrat.getDateFin().getTime())); // Conversion correcte
            statement.setBigDecimal(4, contrat.getTarifSpecial());
            statement.setString(5, contrat.getConditionsAccord());
            statement.setBoolean(6, contrat.isRenouvelable());
            statement.setString(7, contrat.getStatutContrat().name());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contrat> getAllContrats() {
        List<Contrat> contrats = new ArrayList<>();
        String query = "SELECT * FROM contrats";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Contrat contrat = new Contrat(
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin"),
                        resultSet.getBigDecimal("tarif_special"),
                        resultSet.getString("conditions_acord"),
                        resultSet.getBoolean("renouvelable"),
                        StatutContrat.valueOf(resultSet.getString("statut_contrat"))
                );
                contrat.setId(UUID.fromString(resultSet.getString("id")));
                contrats.add(contrat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contrats;
    }

    public Contrat getContratById(UUID id) {
        Contrat contrat = null;
        String query = "SELECT * FROM contrats WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                contrat = new Contrat(
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin"),
                        resultSet.getBigDecimal("tarif_special"),
                        resultSet.getString("conditions_acord"),
                        resultSet.getBoolean("renouvelable"),
                        StatutContrat.valueOf(resultSet.getString("statut_contrat"))
                );
                contrat.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contrat;
    }

    public void updateContrat(Contrat contrat) {
        String query = "UPDATE contrats SET date_debut = ?, date_fin = ?, tarif_special = ?, conditions_acord = ?, renouvelable = ?, statut_contrat = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDate(1, new java.sql.Date(contrat.getDateDebut().getTime())); // Conversion correcte
            statement.setDate(2, new java.sql.Date(contrat.getDateFin().getTime())); // Conversion correcte
            statement.setBigDecimal(3, contrat.getTarifSpecial());
            statement.setString(4, contrat.getConditionsAccord());
            statement.setBoolean(5, contrat.isRenouvelable());
            statement.setString(6, contrat.getStatutContrat().name());
            statement.setObject(7, contrat.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContrat(UUID id) {
        String query = "DELETE FROM contrats WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
