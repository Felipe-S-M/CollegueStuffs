COLDIGO.product = new Object();
COLDIGO.marca = new Object();

$(document).ready(function() {
	COLDIGO.product.carregarMarcas = function(id){
		if(id){
			select = "#selMarcaEdicao";
		}else{
			select = "#selMarca";
		}
		$.ajax({
			type: "GET",
			url: COLDIGO.PATH + "marca/buscar",
			success: function(marcas) {
				
				
				marcas = JSON.parse(marcas);
				if(marcas!="") { 
					
					$(select).html("");
					var option = document.createElement("option");
					option.setAttribute ("value","");
					option.innerHTML = ("Escolha");
					$(select).append(option);
					
			
					for (var i=0; i < marcas.length; i++){
						
						if(marcas[i].status >0){
							var option = document.createElement("option");
							option.setAttribute("value", marcas[i].id);
							
							if((id) && (id==marcas[i].id)){
								option.setAttribute ("selected","selected");
							}
							
							option.innerHTML = (marcas[i].nome);
							$(select).append(option);
						}
					}
					
				}else{
					
					$(select).html("");
					
					var option = document.createElement("option");
					option.setAttribute ("value","");
					option.innerHTML = ("Cadastre uma marca primeiro!");
					$(select).append(option);
					$(select).addClass("aviso");
					
				}
				
			},
			error: function(info) {
				COLDIGO.exibirAviso("Erro ao buscar as marcas: "+
				info.satus + " - " + info.statusText);
				
				$(select).html("");
				var option = document.createElement("option");
				option.setAttribute("value","");
				option.innerHTML = ("Erro ao carregar marcas!");
				$(select).append(option);
				$(select).addClass("aviso");
			}
		});
		
	}
	COLDIGO.product.carregarMarcas();
	
	COLDIGO.product.cadastrar = function(){
		
		var produto = {};
		produto.categoria = document.frmAddProduto.categoria.value;
		produto.marcaId = document.frmAddProduto.marcaId.value;
		produto.modelo = document.frmAddProduto.modelo.value;
		produto.capacidade = document.frmAddProduto.capacidade.value;
		produto.valor = document.frmAddProduto.valor.value;		
		if((produto.categoria=="") || (produto.marcaId=="") || (produto.modelo=="")
			||(produto.capacidade=="") ||(produto.valor=="")){
			COLDIGO.exibirAviso("Preencha todos os campos!");
		} else {
			$.ajax({
				type:"POST",
				url: COLDIGO.PATH + "produto/inserir",
				data:JSON.stringify(produto),
				success: function (msg) {
					COLDIGO.exibirAviso(msg);
					$("#addProduto").trigger("reset");
				},
				error: function(info) {
					COLDIGO.exibirAviso("Erro ao cadastrar um novo produto: "+ info.responseText);
				}
			});
			
		}
	};
	
	COLDIGO.product.buscar = function(){
		
		var valorBusca = $("#campoBuscaProduto").val();
		
		$.ajax({
			type: "GET",
			url: COLDIGO.PATH + "produto/buscar",
			data:"valorBusca=" + valorBusca,
			success: function(dados){

				dados = JSON.parse(dados);
			    $("#listaProdutos").html(COLDIGO.product.exibir(dados));
			    
				
			},
			error: function(info){
				COLDIGO.exibirAviso("Erro ao consultar os contatos: "+ info.responseText);
			}
		});
		
	};
	
	COLDIGO.product.exibir = function(listaDeProdutos){
		
		var tabela = "<table>" + 
		"<tr>" +
		"<th>Categoria</th>"+
		"<th>Marca</th>" +
		"<th>Modelo</th>" +
		"<th>Cap.(1)</th>" +
		"<th>Valor</th>" +
		"<th class='acoes'>Ações</th>" +
		"</tr>";
		
		if(listaDeProdutos != undefined && listaDeProdutos.length > 0){
			
			for (var i=0; i<listaDeProdutos.length; i++){
				tabela += "<tr>" +
						"<td>"+listaDeProdutos[i].categoria+"</td>" +
						"<td>"+listaDeProdutos[i].marcaNome+"</td>" +
						"<td>"+listaDeProdutos[i].modelo+"</td>" +
						"<td>"+listaDeProdutos[i].capacidade+"</td>" +
						"<td>R$ "+COLDIGO.formatarDinheiro(listaDeProdutos[i].valor)+"</td>" +
						"<td>" +
							"<a onclick=\"COLDIGO.product.exibirEdicao('" + listaDeProdutos[i].id + "')\"><img src='../../imgs/edit.png' alt='Editar registro'></a>" +
							"<a onclick=\"COLDIGO.product.excluir('" + listaDeProdutos[i].id + "')\"><img src='../../imgs/delete.png' alt='Excluir registro'></a>" +
						"</td>" +
						"</tr>"
			}
			
		} else if (listaDeProdutos == ""){
			tabela += "<tr><td colspan='6'>Nenhum registro encontrado</td></tr>";	
		}
		tabela += "</table>";
		
		return tabela;
		
	};
	
	COLDIGO.formatarDinheiro = function(valor){
		return valor.toFixed(2).replace('.',',').replace(/(\d)(?=(\d{3])+\,)/g, "$1.")
	};
	
	COLDIGO.product.buscar();
	
	COLDIGO.product.excluir = function(id){
		$.ajax({
			type: "DELETE",
			url: COLDIGO.PATH + "produto/excluir/"+id,
			success: function(msg){
				COLDIGO.exibirAviso(msg);
				COLDIGO.product.buscar();
			},
			error: function(info){
				COLDIGO.exibirAviso("Erro ao excluir produto"+ info.responseText);
			}
		});
		
	};
	
	COLDIGO.product.exibirEdicao = function(id){
		$.ajax({
			type:"GET",
			url: COLDIGO.PATH + "produto/buscarPorId",
			data: "id="+id,
			success: function(produto){
				
				document.frmEditarProduto.idProduto.value = produto.id;
				document.frmEditarProduto.modelo.value = produto.modelo;
				document.frmEditarProduto.capacidade.value = produto.capacidade;
				document.frmEditarProduto.valor.value = produto.valor;
				
				var selCategoria = document.getElementById('selCategoriaEdicao');
				for(var i=0; i < selCategoria.length; i++){
					if(selCategoria.options[i].value == produto.categoria){
						selCategoria.options[i].setAttribute("selected", "selected");
					}else{
						selCategoria.options[i].removeAttribute("selected");
					}
				}
				
				COLDIGO.product.carregarMarcas(produto.marcaId);
				
				var modalEditaProduto = {
					title: "Editar Produto",
					height: 400,
					width: 550,
					modal: true,
					buttons:{
						"Salvar": function(){
							COLDIGO.product.editar();
						},
						"Cancelar": function(){
							$(this).dialog("close");
						}
					},
					close: function(){
						
					}
				};
				
				$("#modalEditarProduto").dialog(modalEditaProduto);
				
			},
			error: function(info){
				COLDIGO.exibirAviso("Erro ao buscar produto para edição: "+ info.responseText);
			}
		});
	};
	COLDIGO.product.editar = function(){
		
		var produto = new Object();
		produto.id = document.frmEditarProduto.idProduto.value;
		produto.categoria = document.frmEditarProduto.categoria.value;
		produto.marcaId = document.frmEditarProduto.marcaId.value;
		produto.modelo = document.frmEditarProduto.modelo.value;
		produto.capacidade = document.frmEditarProduto.capacidade.value;
		produto.valor = document.frmEditarProduto.valor.value;
		
		$.ajax({
			type: "PUT",
			url: COLDIGO.PATH + "produto/alterar",
			data: JSON.stringify(produto),
			success: function(msg){
				COLDIGO.exibirAviso();
				COLDIGO.product.buscar();
				$("#modalEditarProduto").dialog("close");
			},
			error: function(info){
				
				COLDIGO.exibirAviso("Erro ao editar produto: "+ info.responseText);
				
			}
		});
		
	}
//////////////////////
	COLDIGO.marca.buscar = function(){
		
		$.ajax({
			type: "GET",
			url: COLDIGO.PATH + "marca/buscar",
			success: function(dados){ 
				dados = JSON.parse(dados);
			    $("#listaMarcas").html(COLDIGO.marca.exibir(dados));			   
			},
			error: function(info){
				COLDIGO.exibirAviso("Erro ao consultar os contatos: "+ info.responseText);
			}
		});
		
	};
	
	COLDIGO.marca.alterarStatus = function(id,status){
				
		$.ajax({
			type: "PUT",
			url: COLDIGO.PATH + "marca/alterarStatus/"+id,
			success: function(dados){ 
				alert("Status alterado com sucesso!")
				COLDIGO.marca.buscar();
				COLDIGO.product.carregarMarcas();
			},
			error: function(info){
				$("#status"+id).prop( 'checked', status );
				COLDIGO.exibirAviso("Erro ao alterar status da marca: "+ info.responseText);
				
			}
		});
		
	};
	
	COLDIGO.marca.exibir = function(listaDeMarcas){
		
		var tabela = "<table>" + 
		"<tr>" +
		"<th>Marca</th>" +
		"<th class='acoes'>Ações</th>" +
		"<th class='acoes'>Status</th>" +
		
		"</tr>";
		
		if(listaDeMarcas != undefined && listaDeMarcas.length > 0){
			
			for (var i=0; i<listaDeMarcas.length; i++){
				tabela += "<tr>" +
						"<td>"+listaDeMarcas[i].nome+"</td>" +
						"<td>" +
							"<a onclick=\"COLDIGO.marca.exibirEdicao('" + listaDeMarcas[i].id + "','" + listaDeMarcas[i].nome + "')\"><img src='../../imgs/edit.png' alt='Editar registro'></a>" +
							"<a onclick=\"COLDIGO.marca.excluir('" + listaDeMarcas[i].id + "')\"><img src='../../imgs/delete.png' alt='Excluir registro'></a>" +
						"</td>" +
						"<td>"+
							"<label class='switch'>"
							if(listaDeMarcas[i].status > 0){
						  		tabela +="<input type='checkbox' onchange=\"COLDIGO.marca.alterarStatus('" + listaDeMarcas[i].id + "','" + listaDeMarcas[i].status + "')\" id='status"+listaDeMarcas[i].id+"' checked>"
							}else {
								tabela +="<input type='checkbox' onchange=\"COLDIGO.marca.alterarStatus('" + listaDeMarcas[i].id + "','" + listaDeMarcas[i].status + "')\" id='status"+listaDeMarcas[i].id+"' >"
							}
				tabela +="<span class='slider round'></span>"+
						 "</label>"+
						 "</td>"+
						 "</tr>"
		 }
			
		} else if (listaDeMarcas == ""){
			tabela += "<tr><td colspan='6'>Nenhuma marca encontrada</td></tr>";	
		}
		tabela += "</table>";
		
		return tabela;
		
	};
	
	COLDIGO.marca.buscar();
	
	COLDIGO.marca.excluir = function(id){
		$.ajax({
			type: "DELETE",
			url: COLDIGO.PATH + "marca/excluir/"+id,
			success: function(msg){
				COLDIGO.exibirAviso(msg);
				COLDIGO.marca.buscar();
			},
			error: function(info){
				COLDIGO.exibirAviso("Erro ao excluir marcaq"+ info.responseText);
			}
		});
		
	};
	
	COLDIGO.marca.exibirCriacao = function(){

		var modalCriarMarca = {
					title: "Criar marca",
					height: 400,
					width: 550,
					modal: true,
					buttons:{
						"Salvar": function(){
							COLDIGO.marca.salvar();
						},
						"Cancelar": function(){
							$(this).dialog("close");
						}
					},
					close: function(){}
		};		
		$("#modalCriarMarca").dialog(modalCriarMarca);
	};
	
	COLDIGO.marca.salvar = function(){
		
		var marca = new Object();
		marca.nome = document.frmCriarMarca.nome.value;
		
		$.ajax({
			type: "POST",
			url: COLDIGO.PATH + "marca/inserir",
			data: JSON.stringify(marca),
			success: function(msg){
				COLDIGO.exibirAviso(msg);
				COLDIGO.marca.buscar();
				$("#modalCriarMarca").dialog("close");
			},
			error: function(info){
				COLDIGO.exibirAviso("Erro ao salvar marca: "+ info.responseText);
				
			}
		});
		
	}
	
	COLDIGO.marca.exibirEdicao = function(id,nome){

		document.frmEditarMarca.idMarca.value = id;
		document.frmEditarMarca.nome.value = nome;
								
		var modalEditaMarca = {
					title: "Editar marca",
					height: 400,
					width: 550,
					modal: true,
					buttons:{
						"Salvar": function(){
							COLDIGO.marca.editar();
						},
						"Cancelar": function(){
							$(this).dialog("close");
						}
					},
					close: function(){}
		};		
		$("#modalEditarMarca").dialog(modalEditaMarca);
	};
	
	COLDIGO.marca.editar = function(){
		
		var marca = new Object();
		marca.id = document.frmEditarMarca.idMarca.value;
		marca.nome = document.frmEditarMarca.nome.value;
		
		$.ajax({
			type: "PUT",
			url: COLDIGO.PATH + "marca/alterar",
			data: JSON.stringify(marca),
			success: function(msg){
				COLDIGO.exibirAviso(msg);
				COLDIGO.marca.buscar();
				$("#modalEditarMarca").dialog("close");
			},
			error: function(info){
				
				COLDIGO.exibirAviso("Erro ao editar marca: "+ info.responseText);
				
			}
		});
		
	}
	
});