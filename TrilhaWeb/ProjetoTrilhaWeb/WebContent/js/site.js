function validaFaleConosco(){
	var nome = document.frmfaleconosco.txtnome.value;
	var expRegNome = new RegExp("^[A-zÀ-ü]{3,}([ ]{1}[A-zÀ-ü]{2,})+$");
	
	if(!expRegNome.test(nome)){
		alert("Preencha o campo Nome.");
		document.frmfaleconosco.txtnome.focus();
		return false;
	}
	
	var fone = document.frmfaleconosco.txtfone.value;
	var expRegfone = new RegExp("^[(]{1}[1-9]{2}[)]{1}[0-9]{4,5}[-]{1}[0-9]{4}$");
	
	if(!expRegNome.test(fone)){
	alert("Preencha o campo Telefone.");
	document.frmfaleconosco.txtfone.focus();
	return false;

	}else if(document.frmfaleconosco.txtemail.value==""){
		alert("Preencha o campo E-mail.");
		document.frmfaleconosco.txtemail.focus();
		return false;
	}else if(document.frmfaleconosco.selmotivo.value==""){
		alert("Preencha o campo Motivo.");
		document.frmfaleconosco.selmotivo.focus();
		return false;
	}else if(document.frmfaleconosco.selproduto.value=="" && document.frmfaleconosco.selmotivo.value=="PR"){
		alert("Preencha o campo Produto.");
		document.frmfaleconosco.selproduto.focus();
		return false;
	}else if(document.frmfaleconosco.txtcomentario.value==""){
		alert("Preencha o campo Comentario.");
		document.frmfaleconosco.txtnome.focus();
		return false;
	}
	return true;
}
function verificaMotivo(motivo){
	var elemento = document.getElementById("opcaoProduto");
	
	
	
	if(motivo=="PR"){
		var select = document.createElement("select");
		select.setAttribute("name","selproduto");
		var option = document.createElement("option");
		option.setAttribute("value", "");
		var texto = document.createTextNode("Escolha");
		option.appendChild(texto);
		select.appendChild(option);
		
		
		var option = document.createElement("option");
		option.setAttribute("value","FR");
		var texto = document.createTextNode("Freezer");
		option.appendChild(texto);
		select.appendChild(option);
		s
		var option = document.createElement("option");
		option.setAttribute("value","GE");
		var texto = document.createTextNode("Geladeira");
		option.appendChild(texto);
		select.appendChild(option);
		
		elemento.appendChild(select);
		
	}else{
		if(elemento.firstChild){
			elemento.removeChild(elemento.firstChild);
		}
			
	}
}



