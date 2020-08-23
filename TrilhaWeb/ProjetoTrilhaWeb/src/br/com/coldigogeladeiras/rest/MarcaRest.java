package br.com.coldigogeladeiras.rest;


import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

import br.com.coldigogeladeiras.bd.Conexao;
import br.com.coldigogeladeiras.jdbc.JDBCMarcaDAO;
import br.com.coldigogeladeiras.jdbc.JDBCProdutoDAO;
import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.modelo.Produto;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@Path("marca")
public class MarcaRest extends UtilRest{
	
	@GET
	@Path("/buscar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar() {
		try {
			List<Marca> listaMarcas = new ArrayList<Marca>();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			listaMarcas = jdbcMarca.buscar();
			conec.fecharConexao();
			String json = new Gson().toJson(listaMarcas);
			return this.buildResponse(json);
		}catch(Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	public boolean validaMarcaExiste(String marca,JDBCMarcaDAO jdbcMarca) {
		
		if(jdbcMarca.marcaExiste(marca)) {
			return true;
		}
		return false;
		
	}
	
	@POST
	@Path("/inserir")
	@Consumes("application/*")
	public Response inserir(String marcaParam) {
		
		try {
			Marca marca = new Gson().fromJson(marcaParam, Marca.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			if(validaMarcaExiste(marca.getNome(), jdbcMarca)) {
				return this.buildErrorResponse("Marca já Cadastrada!");
			}
			boolean retorno = jdbcMarca.inserir(marca.getNome());
			String msg = "";
			
			if(retorno) {
				msg = "Marca cadastrada com sucesso!";
			}else {
				msg = "Erro ao cadastrar marca.";
			}
			
			conec.fecharConexao();
			
			return this.buildResponse(msg);
		}catch(Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
		
	}
	
	@DELETE
	@Path("/excluir/{id}")
	@Consumes("application/*")
	public Response excluir(@PathParam("id") int id) {
		
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			
			boolean retorno = jdbcMarca.deletar(id);			
			
			String msg = "";
			if(retorno) {
				msg = "Marca excluída com sucesso!";
			} else {
				msg = "Marca em uso.";
			}
			
			conec.fecharConexao();
			
			return this.buildResponse(msg);
			
		}catch(Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
		
	}
	
	@PUT
	@Path("/alterar")
	@Consumes("application/*")
	public Response alterar(String marcaParam) {
		try {
			Marca marca = new Gson().fromJson(marcaParam, Marca.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			Boolean retorno = jdbcMarca.alterar(marca);
			
			String msg = "";
			if(retorno) {
				msg = "Marca alterado com sucesso!";
			}else {
				msg = "Erro ao alterar marca.";
			}
			
			conec.fecharConexao();
			return this.buildResponse(msg);
							
		}catch(Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@PUT
	@Path("/alterarStatus/{id}")
	@Consumes("application/*")
	public Response alterarStatus(@PathParam("id") int id) {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			Boolean retorno = jdbcMarca.alterarStatus(id);
			
			String msg = "";
			if(retorno) {
				msg = "Status alterado com sucesso!";
			}else {
				conec.fecharConexao();
				return this.buildErrorResponse("Marca em uso");
			}
			
			conec.fecharConexao();
			return this.buildResponse(msg);
							
		}catch(Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
