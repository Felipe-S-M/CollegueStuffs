package br.com.coldigogeladeiras.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.coldigogeladeiras.jdbcinterface.MarcaDAO;
import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.modelo.Produto;

public class JDBCMarcaDAO implements MarcaDAO{
	
	private Connection conexao;
	
	public JDBCMarcaDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	
	public List<Marca> buscar(){
		String comando = "SELECT * FROM marcas";
		List<Marca> listMarcas = new ArrayList<Marca>();
		Marca marca = null;
		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()) {
				marca = new Marca();
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				boolean status = rs.getBoolean("status");
				marca.setId(id);
				marca.setNome(nome);
				marca.setStatus(status);
				listMarcas.add(marca);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listMarcas;
	}
	
	public boolean marcaExiste(String marca) {
		String comando = "SELECT * FROM marcas where nome=?";
		PreparedStatement p;
		try{
			p = this.conexao.prepareStatement(comando);
			p.setString(1,marca);
			ResultSet rs = p.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception ex) {
			return false;
		}
		return false;
	}
	
	public boolean inserir(String marca) {
		
		
		String comando = "INSERT INTO marcas "
				+ "(nome)"
				+ "VALUES(?)";
		PreparedStatement p;
		
		try {
			
			p = this.conexao.prepareStatement(comando);
			p.setString(1, marca);
			p.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deletar(int id) throws SQLException {
		
		if(validarUsoMarca(id)) {
			return false;
		}
		String comando = "DELETE FROM marcas WHERE id = ?";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	public boolean alterar(Marca marca) {
		String comando = "UPDATE marcas "
				+ "SET nome=? "
				+ " WHERE id=?";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, marca.getNome());
			p.setInt(2, marca.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 		
		return true;
		
	}
	
	public boolean validarUsoMarca(int id) throws SQLException{
		String comando = "SELECT * FROM produtos where marcas_id=?";
		PreparedStatement p;
		p = this.conexao.prepareStatement(comando);
		p.setInt(1,id);
		ResultSet rs = p.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	public int buscarStatusAlterado(int id) throws SQLException {
		
		String comando = "SELECT status FROM marcas where id=?";
		PreparedStatement p;
		p = this.conexao.prepareStatement(comando);
		p.setInt(1,id);
		ResultSet rs = p.executeQuery();
		int status = 3;
		if(rs.next()) {
			status = rs.getInt(1);
			if(status == 0) {
				status = 1;
			}else {
				status = 0;
			}
			return status; 
		}
		return status;
	}
	
	public boolean alterarStatus(int id) throws SQLException {
		
 		if(validarUsoMarca(id)) {
			return false;
		}
		int status = buscarStatusAlterado(id);
		String comando = "UPDATE marcas "
				+ "SET status=? "
				+ " WHERE id=?";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setInt(1, status);
			p.setInt(2, id);
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
}
