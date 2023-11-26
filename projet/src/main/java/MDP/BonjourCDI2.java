package MDP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;


@RequestScoped
@Named
public class BonjourCDI2 {
    private String hello = "Hello ....";
    private String login;
    private String pwd;
    private String role;
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getrole() {
        return role;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getMessage() {
        return hello + "  " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
    
    
//les fonctions d'utilisateur


    public String authentifier(String login, String pwd) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();

            String sql = "SELECT mot_de_passe FROM utilisateurs WHERE nom_utilisateur = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, login);
            rs = pst.executeQuery();

            if (rs.next()) {
                String passwordFromDB = rs.getString("mot_de_passe");

                if (pwd.equals(passwordFromDB)) {
                    System.out.println("Bonjour");
                    return "home1";
                } else {
                    System.out.println("Password incorrect");
                    return "incorrectpassword";
                }
            } else {
                System.out.println("Utilisateur non trouvé");
                return "UtilisateurNonTrouve";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "AuthentificationEchouee";
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

 
    @SuppressWarnings("resource")
	public String signin(String login, String pwd) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();

           
            String verSql = "SELECT nom_utilisateur FROM utilisateurs WHERE nom_utilisateur = ?";
            pst = connection.prepareStatement(verSql);
            pst.setString(1, login);
            rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Impossible de créer un nouveau utilisateur avec le même nom.");
                return "utilisateurexistant";
            } else {
                
                String sql = "INSERT INTO utilisateurs (nom_utilisateur, mot_de_passe) VALUES (?, ?)";
                pst = connection.prepareStatement(sql);
                pst.setString(1, login);
                pst.setString(2, pwd);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Nouvel utilisateur ajouté avec succès !");
                    return "home1";
                } else {
                    System.out.println("Erreur lors de l'ajout du nouveau utilisateur");
                    return "AuthentificationEchouee";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "AuthentificationEchouee";
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
    
    
    
    
    
 //les fonctions de projet 
    
    String projectName;
    String description;
    private String composants;
    private String etapes; 

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getComposants() {
		return composants;
	}

	public void setComposants(String composants) {
		this.composants = composants;
	}

	public String getEtapes() {
		return etapes;
	}

	public void setEtapes(String etapes) {
		this.etapes = etapes;
	}
 
    


	public void add(String projectName, String description, String composants, String etapes) {
	    Connection connection = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        connection = DatabaseConnection.getConnection();

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


    
    
    
    public String chercher(String projectName) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();

            String sql = "SELECT nomProjet, description, composants, etapes FROM projetx WHERE nomProjet = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, projectName); // Utilise le projectName saisi dans la barre de recherche
            rs = pst.executeQuery();

            if (rs.next()) {
                projectName = rs.getString("nomProjet");
                description = rs.getString("description");
                composants = rs.getString("composants");
                etapes = rs.getString("etapes");

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
    
    

