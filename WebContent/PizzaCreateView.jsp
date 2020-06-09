<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form ajout</title>
</head>
<body>


 <form method="post"  action="${pageContext.request.contextPath}/ControllerPizza" enctype="multipart/form-data">
                  <input type="hidden" value="0"	name="id" />
                  
                  
                  <fieldset class="form-group">
                     <label>Nom Pizza</label> 
                     <input type="text" value="" class="form-control" name="DesignPizz" required />
                  </fieldset>
                  
                  <fieldset class="form-group">
                     <label>Prix</label> <input type="text" value=""
                        class="form-control" name="price" required />
                  </fieldset>
                  
      <%--            <fieldset class="form-group">
                     <label>Image</label> <input type="file"  value=""  id="imageSelected" name="Image" class="form-control" accept=".jpg, .jpeg, .png" />
                  </fieldset>  --%>
               
                  <input type="submit" class="btn btn-success" value="Enregistrer" />
               </form>



</body>
</html>