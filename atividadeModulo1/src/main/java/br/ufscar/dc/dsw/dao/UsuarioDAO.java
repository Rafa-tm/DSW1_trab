package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {

  private int verificaADM(String emailLogin) throws SQLException {
    int papel = 3;

    String sql = "SELECT * from Cliente where email = ?";
    Connection conn = this.getConnection();
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setString(1, emailLogin);
    ResultSet resultSet = statement.executeQuery();

    if (resultSet.next()) {
      Boolean adm = resultSet.getBoolean("adm");
      if (adm) {
        papel = 0;
      } else {
        papel = 1;
      }
    }
    resultSet.close();
    statement.close();
    conn.close();
    return papel;
  }

  private Usuario buscaUsuario(String emailLogin, String papelLogin) throws SQLException {
    Usuario usuarioLogado = null;
    String sql = "SELECT * from " + papelLogin + " WHERE email = ?";
    int papel = 3;

    if (papelLogin.equalsIgnoreCase("Cliente")) {
      papel = verificaADM(emailLogin);
    } else if (papelLogin.equalsIgnoreCase("Profissional")) {
      papel = 2;
    }

    Connection conn = this.getConnection();
    PreparedStatement pst = conn.prepareStatement(sql);
    pst.setString(1, emailLogin);
    ResultSet resultSet = pst.executeQuery();

    if (resultSet.next()) {
      String email = resultSet.getString("email");
      String senha = resultSet.getString("senha");
      String CPF = resultSet.getString("CPF");
      String nome = resultSet.getString("nome");
      usuarioLogado = new Usuario(email, senha, CPF, nome, papel);
      resultSet.close();
      pst.close();
      conn.close();
      return usuarioLogado;
    }
    resultSet.close();
    pst.close();
    conn.close();
    return null;
  }

  public Usuario buscaUsuarioPorEmail(String email) throws SQLException {
    Usuario usuarioLogado = null;
    usuarioLogado = buscaUsuario(email, "Cliente");
    if (usuarioLogado != null) {
      return usuarioLogado;
    } else {
      usuarioLogado = buscaUsuario(email, "Profissional");
      if (usuarioLogado != null) {
        return usuarioLogado;
      }
    }
    return null;
  }

}
