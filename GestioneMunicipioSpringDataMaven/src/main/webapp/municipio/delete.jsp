<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conferma eliminazione Abitante</title>
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
	  <div class="jumbotron" >
	    <div class="container">
	      <h1 class="display-3">Conferma eliminazione</h1>
	      <p>Pazzo scatenato, vuoi davvero eliminare questo municipio?</p>
	      <p><a class="btn btn-primary btn-lg" 
	        href="${pageContext.request.contextPath}/ExecuteDeleteMunicipioServlet?idMunicipioDaEliminare=${requestScope.idMunicipioDaEliminare}" role="button">
	            Sei un folle &raquo;
	      </a></p>
	      <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/EliminazioneNonConfermataMunicipioServlet" role="button">
	          Bravo, torna alla lista dei municipi  &raquo;
	      </a></p>
	    </div>
	  </div>
	  	</div>
	<!-- /.container -->



</body>


</html>