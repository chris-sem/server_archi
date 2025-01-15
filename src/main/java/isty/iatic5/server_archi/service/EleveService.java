package isty.iatic5.server_archi.service;

import isty.iatic5.server_archi.api.model.Eleve;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class EleveService {

    private String type = "eleve";

    public boolean createEleve(String nom, String prenom) {
        String sql = "INSERT INTO personne (nom, prenom, type) VALUES (?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, type);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteEleve(int id) {
        String deleteFromGroupeSQL = "DELETE FROM groupe WHERE id_eleve = ?";
        String deleteEleveSQL = "DELETE FROM personne WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement stmtGroupe = connection.prepareStatement(deleteFromGroupeSQL);
            stmtGroupe.setInt(1, id);
            stmtGroupe.executeUpdate();


            PreparedStatement stmtEleve = connection.prepareStatement(deleteEleveSQL);
            stmtEleve.setInt(1, id);
            int rowsAffected = stmtEleve.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<Eleve> eleves() {
        ArrayList<Eleve> eleves = new ArrayList<>();
        String sql = "SELECT * FROM personne WHERE type = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    eleves.add(new Eleve(id, nom, prenom));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eleves;
    }

}
