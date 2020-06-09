<%@page import="pizza_package.Pizza"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modification</title>
</head>
<body>
<p>formulaire de modif</p>
	<%   Pizza pizza = (Pizza) request.getAttribute("pizza");
	%>

    <form method="post"  action="${pageContext.request.contextPath}/ControllerPizza" enctype = "multipart/form-data">
                  <input type="hidden" value="<%out.print(pizza.getId());%>"	name="id" />
                  
<%--                   <input type="hidden"  id="monImage" value="<%out.print(pizza.getImage());%>"	name="monImage" />
 --%>                  <%-- <fieldset class="form-group">
                     <img src="assets/images/<%out.print(pizza.getImage());%>"
                        id="imageToChange" width="100%" height="auto" />
                  </fieldset> --%>
                  
                  <fieldset class="form-group">
                     <label>Nom Pizza</label> 
                     <input type="text" value="<%if (pizza != null) out.print(pizza.getDesignPizz());%>" class="form-control" name="DesignPizz" required />
                  </fieldset>
                  
                  <fieldset class="form-group">
                     <label>Prix</label> <input type="text" value="<%if (pizza != null)  out.print(pizza.getPrice());%>"
                        class="form-control" name="price" required />
                  </fieldset>
                  
                  <fieldset class="form-group">
                     <label>Image</label> <input type="file"  value="<%if (pizza != null)  out.print(pizza.getImage());%>"  id="imageSelected" name="Image" class="form-control" accept=".jpg, .jpeg, .png" />
                  </fieldset>
               
                  <input type="submit" class="btn btn-success" value="Enregistrer" />
               </form>
</body>
</html>