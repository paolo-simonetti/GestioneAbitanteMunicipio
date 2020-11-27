<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca</title>
</head>
<body>

	<div class="container">

		<%@ include file="../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Test per query Spring Data per Municipio</h3>
		</div>
		
		
		<%-- CERCA PER DESCRIZIONE - FINDALLBYDESCRIZIONEILIKE --%>
		<form class="form-inline" action="ExecuteTestSpringDataMunicipioServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findAllByDescrizioneContaining">
			</div>
			<input type="hidden" name="codop" value="findAllByDescrizioneContaining">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!!!</button>
		</form>
		
		
		<%-- CERCA PER UBICAZIONE MUNICIPIO CHE INIZIA CON -  FINDALLBYDESCRIZIONESTARTINGWITH --%>
		<form class="form-inline" action="ExecuteTestSpringDataMunicipioServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput2" name="queryInput"
					placeholder="findAllByUbicazioneStartingWith">
			</div>
			<input type="hidden" name="codop" value="findAllByUbicazioneStartingWith">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!!!</button>
		</form>
		
		
		<%-- CERCA PER CODICE ED UBICAZIONE FINDALLBYCODICEANDUBICAZIONE --%>
		<form class="form-inline" action="ExecuteTestSpringDataMunicipioServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput3" name="queryInputCodice"
					placeholder="findAllByCodiceAndUbicazione">
				<input
					type="text" class="form-control" id="queryInput4" name="queryInputUbicazione"
					placeholder="ubicazione">
			</div>
			<input type="hidden" name="codop" value="findAllByCodiceAndUbicazione">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!!!</button>
		</form>
		
		
		<%-- CERCA PER CODICE ORDINANDO ALFABETICAMENTE PER UBICAZIONE - FINDALLBYCODICEORDERBYUBICAZIONEDESC --%>
		<form class="form-inline" action="ExecuteTestSpringDataMunicipioServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput5" name="queryInput"
					placeholder="codice orderby ubicazione desc">
			</div>
			<input type="hidden" name="codop" value="findAllByCodiceOrderByUbicazioneDesc">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!!!</button>
		</form>
		
		
		<%-- CERCA PER ABITANTI IL CUI COGNOME LIKE - FINDALLBYABITANTI_COGNOMELIKE --%>
		<form class="form-inline" action="ExecuteTestSpringDataMunicipioServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput6" name="queryInput"
					placeholder="findAllByAbitanti_CognomeLike">
			</div>
			<input type="hidden" name="codop" value="findAllByAbitanti_CognomeLike">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!!!</button>
		</form>
		
		
		

	</div>
	<!-- /.container -->



</body>
</html>