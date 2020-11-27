<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aggiorna Abitante</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jqueryUI/jquery-ui.min.css" />
<style>
.ui-autocomplete-loading {
	background: white url("img/anim_16x16.gif") right center no-repeat;
}
</style>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

	<div class="container">

		<%@ include file="../header.jsp"%>
		<div class="alert alert-danger ${not empty abitanteErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${abitanteErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>

		<div class="page-header">
			<h3>Pagina di Aggiornamento Abitante</h3>
		</div>

		<form class="form-horizontal" action="ExecuteUpdateAbitanteServlet"
			method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="nomeInputId">Nome:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="nomeInputId"
						name="nomeInput" value="${requestScope.abitanteDaAggiornare.nome}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="cognomeInputId">Cognome:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="cognomeInputId"
						name="cognomeInput" value="${requestScope.abitanteDaAggiornare.cognome}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="etaInputId">Eta:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="etaInputId"
						name="etaInput" value="${requestScope.abitanteDaAggiornare.eta}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="residenzaInputId">Residenza:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="residenzaInputId"
						name="residenzaInput" value="${requestScope.abitanteDaAggiornare.residenza}">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="municipioInputId">Municipio:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="municipioInputId"
						name="municipioInput" value="${requestScope.abitanteDaAggiornare.municipio.descrizione}">
					<input type="hidden" name="municipioId" id="municipioId" value="${requestScope.abitanteDaAggiornare.municipio.id}">
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-md">Effetua
						Aggiornamento</button>
				</div>
			</div>
			
			<%-- FUNZIONE JQUERY UI CON AJAX PER AUTOCOMPLETE --%>
			<script>
				$( "#municipioInputId" ).autocomplete({
					 source: function(request, response) {
					        $.ajax({
					            url: "SearchMunicipioAjaxServlet",
					            datatype: "json",
					            data: {
					                term: request.term,   
					            },
					            success: function(data) {
					                response($.map(data, function(item) {
					                    return {
						                    label: item.label,
						                    value: item.value
					                    }
					                }))
					            }
					        })
					    },
					//quando seleziono la voce nel campo deve valorizzarsi la descrizione
				    focus: function(event, ui) {
				        $("#municipioInputId").val(ui.item.label)
				        return false
				    },
				    minLength: 2,
				    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
				    select: function( event, ui ) {
				    	$('#municipioId').val(ui.item.value);
				    	console.log($('#municipioId').val())
				        return false;
				    },
				});
			</script>
			
			
		</form>

	</div>
	<!-- /.container -->



</body>


</html>