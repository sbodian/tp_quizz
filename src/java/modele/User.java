package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author plasse
 */
public class User {
  private int id;
  private String login, password;

  public User(int id, String login, String password) {
    this.id = id;
    this.login = login;
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public static User getByLoginPwd(String login, String password) throws SQLException {
    User result = null;
    Connection connexion = Database.getConnection();
    // Supposons quele mot de passe est le nom
    String sql = "SELECT num, mel, nom FROM adherent WHERE mel=? AND nom=?";
    PreparedStatement ordre = connexion.prepareStatement(sql);
    ordre.setString(1, login);
    ordre.setString(2, password);
    ResultSet rs = ordre.executeQuery();
    if (rs.next()) {
      result = new User(rs.getInt("num"),
          rs.getString("mel"), rs.getString("nom"));
    }
    return result;
  }
}
