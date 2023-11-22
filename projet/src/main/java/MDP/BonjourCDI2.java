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
                    return "Bonjour";
                } else {
                    System.out.println("Password incorrect");
                    return "AuthentificationEchouee";
                }
            } else {
                System.out.println("Utilisateur non trouvé");
                return "UtilisateurNonTrouve";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "ErreurBaseDeDonnees";
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
                System.out.println("Impossible de créer un nouvel utilisateur avec le même nom.");
                return "utilisateurexistant";
            } else {
                
                String sql = "INSERT INTO utilisateurs (nom_utilisateur, mot_de_passe) VALUES (?, ?)";
                pst = connection.prepareStatement(sql);
                pst.setString(1, login);
                pst.setString(2, pwd);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Nouvel utilisateur ajouté avec succès !");
                    return "utilisateurbienenregister";
                } else {
                    System.out.println("Erreur lors de l'ajout du nouvel utilisateur");
                    return "utilisateurnonenregister";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "ErreurBaseDeDonnees";
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
