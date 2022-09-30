package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends GenericDAO {

	public void insert(Profissional profissional) {

		String sql = "INSERT INTO Profissional(email, senha, CPF, nome, area, especialidade, link_curriculo) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, profissional.getEmail());
			statement.setString(2, profissional.getSenha());
			statement.setString(3, profissional.getCPF());
			statement.setString(4, profissional.getNome());
			statement.setString(5, profissional.getArea());
			statement.setString(6, profissional.getEspecialidade());
			statement.setString(7, profissional.getLinkCurriculo());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Profissional> getAll() {

		List<Profissional> listaProfissional = new ArrayList<>();

		String sql = "SELECT * from Profissional order by id";

		try {
			Connection conn = this.getConnection();
			Statement statement = conn.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {

				Long id = resultSet.getLong("id");
				String email = resultSet.getString("email");
				String senha = resultSet.getString("senha");
				String CPF = resultSet.getString("CPF");
				String nome = resultSet.getString("nome");
				String area = resultSet.getString("area");
				String especialidade = resultSet.getString("especialidade");
				String link_curriculo = resultSet.getString("link_curriculo");

				Profissional profissional = new Profissional(id, email, senha, CPF, nome, area, especialidade,
						link_curriculo);
				listaProfissional.add(profissional);
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaProfissional;
	}

	public List<Profissional> getAllbyFiltro(String filtroarea, String filtroespec) {

		List<Profissional> listaProfissionalFiltro = new ArrayList<>();

		if (filtroarea != null && filtroespec != null) {
			String sql = "SELECT * from Profissional WHERE area = ? AND especialidade = ?";
			try {
				Connection conn = this.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);

				statement.setString(1, filtroarea);
				statement.setString(2, filtroespec);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {

					Long id = resultSet.getLong("id");
					String email = resultSet.getString("email");
					String senha = resultSet.getString("senha");
					String CPF = resultSet.getString("CPF");
					String nome = resultSet.getString("nome");
					String area = resultSet.getString("area");
					String especialidade = resultSet.getString("especialidade");
					String link_curriculo = resultSet.getString("link_curriculo");

					Profissional profissional = new Profissional(id, email, senha, CPF, nome, area, especialidade,
							link_curriculo);
					listaProfissionalFiltro.add(profissional);
				}

				resultSet.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else if(filtroarea != null && filtroarea.isEmpty() == false){
			String sql = "SELECT * from Profissional WHERE area = ?";
			try {
				Connection conn = this.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);

				statement.setString(1, filtroarea);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {

					Long id = resultSet.getLong("id");
					String email = resultSet.getString("email");
					String senha = resultSet.getString("senha");
					String CPF = resultSet.getString("CPF");
					String nome = resultSet.getString("nome");
					String area = resultSet.getString("area");
					String especialidade = resultSet.getString("especialidade");
					String link_curriculo = resultSet.getString("link_curriculo");

					Profissional profissional = new Profissional(id, email, senha, CPF, nome, area, especialidade,
							link_curriculo);
					listaProfissionalFiltro.add(profissional);
				}

				resultSet.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}else{
			listaProfissionalFiltro = getAll();
		}

		return listaProfissionalFiltro;
	}

	public void delete(Profissional profissional) {
		String sql = "DELETE FROM Profissional where id = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, profissional.getId());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Profissional profissional) {
		String sql = "UPDATE Profissional SET email = ?, senha = ?, CPF = ?, nome = ?, ";
		sql += "area = ?, especialidade = ?, link_curriculo = ? WHERE id = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, profissional.getEmail());
			statement.setString(2, profissional.getSenha());
			statement.setString(3, profissional.getCPF());
			statement.setString(4, profissional.getNome());
			statement.setString(5, profissional.getArea());
			statement.setString(6, profissional.getEspecialidade());
			statement.setString(7, profissional.getLinkCurriculo());
			statement.setLong(8, profissional.getId());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Profissional get(Long id) {
		Profissional profissional = null;

		String sql = "SELECT * from Profissional WHERE id = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {

				String email = resultSet.getString("email");
				String senha = resultSet.getString("senha");
				String CPF = resultSet.getString("CPF");
				String nome = resultSet.getString("nome");
				String area = resultSet.getString("area");
				String especialidade = resultSet.getString("especialidade");
				String link_curriculo = resultSet.getString("link_curriculo");

				profissional = new Profissional(id, email, senha, CPF, nome, area, especialidade, link_curriculo);
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return profissional;
	}

	public Profissional getAllByEmail(String emailBusca) {
		Profissional profissional = null;

		String sql = "SELECT * from Profissional WHERE email = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, emailBusca);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String email = resultSet.getString("email");
				String senha = resultSet.getString("senha");
				String CPF = resultSet.getString("CPF");
				String nome = resultSet.getString("nome");
				String area = resultSet.getString("area");
				String especialidade = resultSet.getString("especialidade");
				String link_curriculo = resultSet.getString("link_curriculo");

				profissional = new Profissional(id, email, senha, CPF, nome, area, especialidade, link_curriculo);
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return profissional;
	}

}
