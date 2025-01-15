package isty.iatic5.server_archi.service;

import isty.iatic5.server_archi.api.model.Sujet;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class SujetService {

    public boolean createSujet(String intitule) {
        String sql = "INSERT INTO sujet (intitule) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, intitule);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean deleteSujet(int id) {
        String deleteFromGroupeSQL = "DELETE FROM groupe WHERE id_sujet = ?";
        String deleteSujetSQL = "DELETE FROM sujet WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement stmtGroupe = connection.prepareStatement(deleteFromGroupeSQL);
            stmtGroupe.setInt(1, id);
            stmtGroupe.executeUpdate();

            PreparedStatement stmtSujet = connection.prepareStatement(deleteSujetSQL);
            stmtSujet.setInt(1, id);
            int rowsAffected = stmtSujet.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public ArrayList<Sujet> listSujets() {
        ArrayList<Sujet> sujets = new ArrayList<>();
        String sql = "SELECT * FROM sujet";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String intitule = rs.getString("intitule");
                sujets.add(new Sujet(id, intitule));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sujets;
    }
}
