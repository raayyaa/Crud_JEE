<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="pizza_package.Pizza" %>
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>

 
	
<h3> pizzalist Information </h3>
<% List<Pizza> listedepizza = (ArrayList<Pizza>)request.getAttribute("listpizza");
 
    for(Pizza item : listedepizza)
    {
        out.print("Id: " + item.getId() );
        out.print("<br/>");
        out.print("Name: " + item.getDesignPizz() );
        out.print("<br/>");
        out.print("Price: " + item.getPrice() );
        
        out.print("<br/>");
        out.print("<br/>");
    }
 
%>
    <center>
        <h1>Pizza Management</h1>
        <h2>
            <a href="/new">Add New Pizza</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Pizza</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Pizza</h2></caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="itempizza" items="${listpizza}">
                <tr>
                    <td><c:out value="${itempizza.getId()}" /></td>
                    <td><c:out value="${itempizza.getDesignPizz()}" /></td>
                    <td><c:out value="${itempizza.getPrice()}" /></td>
                   
                    <td>
                        <a href="/edit?id=<c:out value='${itempizza.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${itempizza.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>