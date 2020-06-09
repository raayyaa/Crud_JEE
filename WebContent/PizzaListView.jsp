<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="pizza_package.Pizza"%>
<html>
<head>
<title>Bootstrap List Pizza</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<style type="text/css">
.thumbnail {
	height: 380px;
	margin-bottom: 50px;
}

.button {
	background-color: gray;
	color: white;
}

.button:hover {
	background-color: darkgray;
}

.zoom {
	-webkit-transform: scale(1);
	transform: scale(1);
	-webkit-transition: .3s ease-in-out;
	transition: .3s ease-in-out;
}

.zoom:hover {
	-webkit-transform: scale(1.1);
	transform: scale(1.1);
}
</style>


</head>
<body>
	<div class="container">
		<h2>Liste des Pizzas</h2>

		<div class="row">
		<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/ControllerPizza?action=createpizza"
				role="button">Ajouter</a>

			<%
				List<Pizza> listedepizza = (ArrayList<Pizza>)request.getAttribute("listpizza");

				for (Pizza item : listedepizza) {
			%>
		

			<div class="col-md-4 col-sm-6 col-xs-10">
				<div class="thumbnail">
					<img src="imagesPizza/<%=item.getId()%>.jpg" class="zoom"
						alt="Lights" style="width: 100%; height: 70%">
					<div class="caption" style="text-align: center;">
						<p>
							Tarif Pizza :
							<%=item.getPrice()%>
						</p>
						<p>
							Désignation Pizza :
							<%=item.getDesignPizz()%>
						</p>
						<p style="text-align: left;">la description</p>
					</div>

					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/ControllerPizza?action=editpizza&idpizza=<%=item.getId()%>"
						role="button">Editer</a> <a class="btn btn-primary"
						href="${pageContext.request.contextPath}/ControllerPizza?action=modifpizza&idpizza=<%=item.getId()%>"
						role="button">Modifier</a> <a class="btn btn btn-warning"
						href="${pageContext.request.contextPath}/ControllerPizza?action=deletepizza&idpizza=<%=item.getId()%>"
						role="button">Supprimer</a>

				</div>
				<p
					style="border-bottom: 3px solid black; position: relative; top: -20px;"></p>
			</div>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>
