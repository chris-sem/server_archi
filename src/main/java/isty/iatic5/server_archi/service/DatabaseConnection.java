package isty.iatic5.server_archi.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabaseConnection {

    
    //private static final String ENV_CONFIG_PATH = "DB_CONFIG_PATH"; // Nom de la variable d'environnement

    private static Connection connection;

    // Chargement des propriétés avec une block static (l'idée est d'exécuté une seule fois ce block lors d'une premier chargement par le JVM)
    /*static {
    	Properties prop = new Properties();
        String configPath = System.getenv(ENV_CONFIG_PATH); // Récupérer le chemin depuis la variable d'environnement

        if (configPath == null || configPath.isEmpty()) {
            System.err.println("Erreur : La variable d'environnement 'DB_CONFIG_PATH' n'est pas définie.");
        }

        try (FileInputStream fis = new FileInputStream(configPath)) {
            // Charger les propriétés du fichier
            prop.load(fis);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier de configuration : " + e.getMessage());
        }
        
        url = prop.getProperty("db.url");
        user = prop.getProperty("db.user");
        password = prop.getProperty("db.password");
    }
    /*
     * Méthode static permettant d'assurer l'utilisation d'une seule connection dans toute l'application 
     */
    public static Connection getConnection() throws SQLException {

        Map<String, String> configParams = new HashMap<>();

        try {
            // Charger les paramètres depuis le fichier .txt
            BufferedReader reader = new BufferedReader(new FileReader("../settings.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                // Si la ligne contient un "=", on l'exploite pour extraire la clé et la valeur
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    configParams.put(parts[0].trim(), parts[1].trim());
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Extraire les paramètres du map
        String adresse = configParams.get("adresse");
        String port = configParams.get("port");
        String nomBase = configParams.get("nom_base");
        String user = configParams.get("user");
        String password = configParams.get("password");

        String url = "jdbc:mysql://" + adresse + ":" + port + "/" + nomBase;


        if (connection == null || connection.isClosed()) {

            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
    
}
