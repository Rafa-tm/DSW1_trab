package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;

public class ConsultaDAO extends GenericDAO {

    public ClienteDAO daoCliente;
    public ProfissionalDAO daoProfissional;

    public void insert(Consulta consulta) {

        String sql = "INSERT INTO Consulta(idcliente, idprofissional, data, hora, estado) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, consulta.getCliente().getId());
            statement.setLong(2, consulta.getProfissional().getId());
            String data = (new SimpleDateFormat("yyyy-MM-dd").format(consulta.getData()));
            statement.setDate(3, java.sql.Date.valueOf(data));
            statement.setInt(4, consulta.getHora());
            statement.setString(5, consulta.getEstado());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Consulta consulta) {
        String sql = "DELETE FROM Consulta where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, consulta.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Consulta consulta) {
        String sql = "UPDATE Consulta SET idcliente = ?, idprofissional = ?, data = ?, hora = ?, estado = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, consulta.getCliente().getId());
            statement.setLong(2, consulta.getProfissional().getId());
            String data = (new SimpleDateFormat("yyyy-MM-dd").format(consulta.getData()));
            statement.setDate(3, java.sql.Date.valueOf(data));
            statement.setInt(4, consulta.getHora());
            statement.setString(5, consulta.getEstado());
            statement.setLong(6, consulta.getId());

            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Consulta get(Long idConsulta) {
        Consulta consulta = null;
        daoCliente = new ClienteDAO();
        daoProfissional = new ProfissionalDAO();
        String sql = "SELECT * from Consulta where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, idConsulta);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Long id = resultSet.getLong("id");
                Long idcliente = resultSet.getLong("idcliente");
                Long idprofissional = resultSet.getLong("idprofissional");
                Date data = resultSet.getDate("data");
                int hora = resultSet.getInt("hora");
                String estado = resultSet.getString("estado");

                Cliente clienteConsulta = daoCliente.get(idcliente);
                Profissional profissionalConsulta = daoProfissional.get(idprofissional);

                consulta = new Consulta(id, clienteConsulta, profissionalConsulta, data, hora, estado);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consulta;
    }

    public List<Consulta> getAllbyCliente(Long idCliente) {

        List<Consulta> listaConsulta = new ArrayList<>();
        daoCliente = new ClienteDAO();
        daoProfissional = new ProfissionalDAO();

        String sql = "SELECT * from Consulta WHERE idcliente = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, idCliente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                Long idcliente = resultSet.getLong("idcliente");
                Long idprofissional = resultSet.getLong("idprofissional");
                Date data = resultSet.getDate("data");
                int hora = resultSet.getInt("hora");
                String estado = resultSet.getString("estado");

                Cliente clienteConsulta = daoCliente.get(idcliente);
                Profissional profissionalConsulta = daoProfissional.get(idprofissional);

                Consulta consulta = new Consulta(id, clienteConsulta, profissionalConsulta, data, hora, estado);
                listaConsulta.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (listaConsulta.isEmpty()) {
            return null;
        } else {
            return listaConsulta;
        }

    }

    public List<Consulta> getAllbyProfissional(Long idProfissional) {

        List<Consulta> listaConsulta = new ArrayList<>();
        daoCliente = new ClienteDAO();
        daoProfissional = new ProfissionalDAO();

        String sql = "SELECT * from Consulta WHERE idprofissional = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, idProfissional);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                Long idcliente = resultSet.getLong("idcliente");
                Long idprofissional = resultSet.getLong("idprofissional");
                Date data = resultSet.getDate("data");
                int hora = resultSet.getInt("hora");
                String estado = resultSet.getString("estado");

                Cliente clienteConsulta = daoCliente.get(idcliente);
                Profissional profissionalConsulta = daoProfissional.get(idprofissional);

                Consulta consulta = new Consulta(id, clienteConsulta, profissionalConsulta, data, hora, estado);
                listaConsulta.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (listaConsulta.isEmpty()) {
            return null;
        } else {
            return listaConsulta;
        }

    }

    public boolean clienteDisponivel(Date dataConsulta, int horaConsulta, Long idcliente) {
        String sql = "SELECT * from Consulta WHERE data = ? AND hora = ? AND idcliente = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            String data = (new SimpleDateFormat("yyyy-MM-dd").format(dataConsulta));

            statement.setDate(1, java.sql.Date.valueOf(data));
            statement.setInt(2, horaConsulta);
            statement.setLong(3, idcliente);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultSet.close();
                statement.close();
                conn.close();
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public boolean profissionalDisponivel(Date dataConsulta, int horaConsulta, Long idprofissional) {
        String sql = "SELECT * from Consulta WHERE data = ? AND hora = ? AND idprofissional = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            String data = (new SimpleDateFormat("yyyy-MM-dd").format(dataConsulta));
            statement.setDate(1, java.sql.Date.valueOf(data));
            statement.setInt(2, horaConsulta);
            statement.setLong(3, idprofissional);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultSet.close();
                statement.close();
                conn.close();
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
