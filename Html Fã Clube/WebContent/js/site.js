function validaInscricao(){
	
	var email = document.frm.txtemail.value;
	var expRegEmail = new RegExp("^[A-z]{2,}[@][A-z]{2,}\.[a-z]{3,}$");
	
	if(!expRegEmail.test(email)){
		alert("Preencha o campo E-mail corretamente.");
		document.frm.txtemail.focus();
		return false;
	}
	
	var dataNascimento = document.frm.txtdataNascimento.value;
	var expRegdataNascimento = new RegExp("^[01-31]{1,}[01-13]{1,}[0-9]{4,}$");
	
	if(!expRegdataNascimento){
		alert("Preencha o campo Data.");
		document.frm.txtdataNascimento.focus();
		return false;
	}
	
	if(document.frm.txtnome.value==""){
		alert("Preencha o campo Nome.");
		document.frm.txtnome.focus();
		return false;
	}else if(document.frm.txtfone.value==""){
		alert("Preencha o campo Telefone.");
		document.frm.txtfone.focus();
		return false;
	}else if(document.frm.genero.value==""){
		alert("Preencha o campo Genero.");
		return false;
	}else if(!document.frm.desejoParticipar.checked){
		alert("Preencha o campo Desejo Participar.");
		return false;
	}	
	return true;
}
$(document).ready(function(){
	$("header").load("/faclube/pages/site/general/cabecalho.html");
	$("nav").load("/faclube/pages/site/general/menu.html");
	$("footer").load("/faclube/pages/site/general/rodape.html");
});
