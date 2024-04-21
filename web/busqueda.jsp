<%@page import="java.util.List"%>
<%@page import="Modelo.Producto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Búsqueda de Productos</title>
    <!-- Incluir la CDN de Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Búsqueda de Productos</h1>
        <form action="ProductoServlet" method="get">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre">
            </div>
            <input type="hidden" name="action" value="buscar">
            <button type="submit" class="btn btn-primary">Buscar</button>
            <a href="registro.jsp" class="btn btn-info mt-3">Registrar</a>

        </form>

        <%-- Mostrar resultados si hay productos encontrados --%>
        <%
            if (request.getAttribute("productosEncontrados") != null) {
                List<Producto> productos = (List<Producto>) request.getAttribute("productosEncontrados");
                if (!productos.isEmpty()) {
        %>
                    <h2 class="mt-5">Resultados de la búsqueda:</h2>
                    <table class="table table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th>Código</th>
                                <th>Nombre</th>
                                <th>Precio</th>
                                <th>Stock</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Producto producto : productos) { %>
                                <tr>
                                    <td><%= producto.getCodigo() %></td>
                                    <td><%= producto.getNombre() %></td>
                                    <td><%= producto.getPrecio() %></td>
                                    <td><%= producto.getStock() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
        <% 
                } else {
                    out.println("<p>No se encontraron productos</p>");
                }
            }
        %>
    </div>
</body>
</html>
