package dao;

import model.Billet;
import model.TypeTransport;
import model.StatutBillet;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BilletDAO {

    public void addBillet(Billet billet) {
        String query = "INSERT INTO billets (id, type_transport, prix_achat, prix_vente, date_vente, statut_billet) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, billet.getId());
            statement.setString(2, billet.getTypeTransport().name());
            statement.setBigDecimal(3, billet.getPrixAchat());
            statement.setBigDecimal(4, billet.getPrixVente());
            statement.setTimestamp(5, new Timestamp(billet.getDateVente().getTime()));  // Conversion correcte
            statement.setString(6, billet.getStatutBillet().name());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Billet> getAllBillets() {
        List<Billet> billets = new ArrayList<>();
        String query = "SELECT * FROM billets";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                Billet billet = new Billet(
                        id,
                        TypeTransport.valueOf(resultSet.getString("type_transport")),
                        resultSet.getBigDecimal("prix_achat"),
                        resultSet.getBigDecimal("prix_vente"),
                        resultSet.getTimestamp("date_vente"), // Convertir en Date si nécessaire
                        StatutBillet.valueOf(resultSet.getString("statut_billet"))
                );
                billets.add(billet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billets;
    }

    public Billet getBilletById(UUID id) {
        Billet billet = null;
        String query = "SELECT * FROM billets WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                billet = new Billet(
                        id,
                        TypeTransport.valueOf(resultSet.getString("type_transport")),
                        resultSet.getBigDecimal("prix_achat"),
                        resultSet.getBigDecimal("prix_vente"),
                        resultSet.getTimestamp("date_vente"), // Convertir en Date si nécessaire
                        StatutBillet.valueOf(resultSet.getString("statut_billet"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billet;
    }

    public void updateBillet(Billet billet) {
        String query = "UPDATE billets SET type_transport = ?, prix_achat = ?, prix_vente = ?, date_vente = ?, statut_billet = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, billet.getTypeTransport().name());
            statement.setBigDecimal(2, billet.getPrixAchat());
            statement.setBigDecimal(3, billet.getPrixVente());
            statement.setTimestamp(4, new Timestamp(billet.getDateVente().getTime())); // Conversion correcte
            statement.setString(5, billet.getStatutBillet().name());
            statement.setObject(6, billet.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBillet(UUID id) {
        String query = "DELETE FROM billets WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
