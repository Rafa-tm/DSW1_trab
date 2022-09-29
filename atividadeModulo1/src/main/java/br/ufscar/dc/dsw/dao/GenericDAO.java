package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract public class GenericDAO {

    public GenericDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/LifeCare1?useTimezone=true&serverTimezone=UTC",
                "root", "root");
    }

    public boolean verificaCPFUnico(String cpf) {
        Boolean resposta = false;
        String sql = "SELECT * from Cliente where CPF = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString("CPF").equalsIgnoreCase(cpf)) {
                    resultSet.close();
                    statement.close();
                    conn.close();
                    resposta = true;
                    return resposta;
                }
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (resposta == false) {
            sql = "SELECT * from Profissional where CPF = ?";
            try {
                Connection conn = this.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, cpf);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    if (resultSet.getString("CPF").equalsIgnoreCase(cpf)) {
                        resultSet.close();
                        statement.close();
                        conn.close();
                        resposta = true;
                        return resposta;
                    }
                }
                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resposta;

    }
}