package br.com.coldigogeladeiras.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import br.com.coldigogeladeiras.jdbcinterface.ProdutoDAO;
import br.com.coldigogeladeiras.modelo.Produto;

public class JDBCProdutoDAO implements ProdutoDAO{
	private Connection conexao;
	
	public JDBCProdutoDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public boolean inserir(Produto produto) throws SQLException {
		
		if(!validarMarca(produto.getMarcaId())) {
			return false;
		}
		
		String comando = "INSERT INTO produtos "
				+ "(id, categoria, modelo, capacidade, valor, marcas_id)"
				+ "VALUES(?,?,?,?,?,?)";
		PreparedStatement p;
		
		try {
			
			p = this.conexao.prepareStatement(comando);
			
			p.setInt(1, produto.getId());
			p.setString(2, produto.getCategoria());
			p.setString(3, produto.getModelo());
			p.setInt(4, produto.getCapacidade());
			p.setFloat(5, produto.getValor());
			p.setInt(6, produto.getMarcaId());
			
			p.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<JsonObject> buscarPorNome(String nome){
		
		String comando = "SELECT produtos.*, marcas.nome as marca from produtos "
				+ "INNER JOIN marcas ON produtos.marcas_id = marcas.id";
		
		if(!"".equals(nome)) {
			comando += "WHERE modelo LIKE '%" + nome + "%' ";
		}
		
//		comando += "ORDER BY produtos.categoria, marcas.nome, produtos.modelo ASC";
		
		List<JsonObject> listaProdutos = new ArrayList<JsonObject>();
		JsonObject produto = null;
		
		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			
			while (rs.next()) {
				
				String categoria = rs.getString("categoria");
				if(categoria.equals("1")) {
					categoria = "Geladeira";
				}else if(categoria.equals("2")) {
					categoria = "Freezar";
				}
				
				produto = new JsonObject();
				produto.addProperty("id", rs.getInt("id"));
				produto.addProperty("categoria", categoria);
				produto.addProperty("modelo", rs.getString("modelo"));
				produto.addProperty("capacidade", rs.getInt("capacidade"));
				produto.addProperty("valor", rs.getFloat("valor"));
				produto.addProperty("marcaNome", rs.getString("marca"));
				
				listaProdutos.add(produto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaProdutos;
	}
	
	public boolean deletar(int id) {
		String comando = "DELETE FROM produtos WHERE id = ?";
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
	
	public Produto buscarPorId(int id) {
		String comando = "SELECT * FROM produtos WHERE produtos.id = ?";
		Produto produto = new Produto();
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1,id);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("modelo");
				int capacidade = rs.getInt("capacidade");
				float valor = rs.getFloat("valor");
				int marcaId = rs.getInt("marcas_id");
				
				produto.setId(id);
				produto.setCategoria(categoria);
				produto.setMarcaId(marcaId);
				produto.setModelo(modelo);
				produto.setCapacidade(capacidade);
				produto.setValor(valor);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produto;
	}
	
	public boolean alterar(Produto produto) {
		
		String comando = "UPDATE produtos "
				+ "SET categoria=?, modelo=?, capacidade=?, valor=?, marcas_id=? "
				+ " WHERE id=?";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, produto.getCategoria());
			p.setString(2, produto.getModelo());
			p.setInt(3, produto.getCapacidade());
			p.setFloat(4, produto.getValor());
			p.setInt(5, produto.getMarcaId());
			p.setInt(6, produto.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 		
		return true;
		
	}
	
	public boolean validarMarca(int id) throws SQLException{
		String comando = "SELECT * FROM marcas where id=?";
		PreparedStatement p;
		p = this.conexao.prepareStatement(comando);
		p.setInt(1,id);
		ResultSet rs = p.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
}
