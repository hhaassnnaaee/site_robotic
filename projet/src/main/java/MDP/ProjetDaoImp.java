package MDP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public abstract class ProjetDaoImp extends AbstractDAOA implements ProjetIdao {

    @Override
    public void add(String projectName, String description, String composants, String etapes) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();

            System.out.println(projectName);
            String sql = "INSERT INTO projetx (nomProjet, description, composants, etapes) VALUES (?, ?, ?, ?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, projectName);
            pst.setString(2, description);
            pst.setString(3, composants);
            pst.setString(4, etapes);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Nouveau projet ajouté avec succès !");
            } else {
                System.out.println("Erreur lors de l'ajout du nouveau projet");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du nouveau projet");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
	 public String chercher(String projectName) {
	        Connection connection = null;
	        PreparedStatement pst = null;
	        ResultSet rs = null;
            Projet obj = new Projet(projectName, projectName, projectName, projectName);
	        try {
	            connection = DatabaseConnection.getConnection();

	            String sql = "SELECT nomProjet, description, composants, etapes FROM projetx WHERE nomProjet = ?";
	            pst = connection.prepareStatement(sql);
	            pst.setString(1, projectName); // Utilise le projectName saisi dans la barre de recherche
	            rs = pst.executeQuery();

	            if (rs.next()) {
	               obj.projectName = rs.getString("nomProjet");
	               obj.description = rs.getString("description");
	               obj.composants = rs.getString("composants");
	               obj.etapes = rs.getString("etapes");

	                return "projetchercher"; // Ou un autre résultat pour la navigation
	            } else {
	                // Aucun projet trouvé
	                // Vous pouvez définir un message pour indiquer à l'utilisateur qu'aucun projet correspondant n'a été trouvé
	                return "AuthentificationEchouee";
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return "AuthentificationEchouee"; // Ou un autre message d'erreur approprié
	        } finally {
	        	 try {
	                 if (rs != null) {
	                     rs.close();
	                 }
	                 if (pst != null) {
	                     pst.close();
	                 }
	                 if (connection != null) {
	                     connection.close();
	                 }
	             } catch (SQLException ex) {
	                 ex.printStackTrace();
	             }
	           
	        }

}
    
}



