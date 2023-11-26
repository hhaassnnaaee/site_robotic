package MDP;
import java.sql.Connection;

public class AbstractDAOA {
    protected Connection connection = DatabaseConnection.getConnection();
}