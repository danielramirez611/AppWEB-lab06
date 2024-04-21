<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Productos</title>
    <!-- Incluir la CDN de Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Listado de Productos</h1>
        <table class="table table-bordered mt-3">
            <thead class="thead-dark">
                <tr>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Acciones</th> <!-- Nueva columna para el botón de Eliminar -->
                </tr>
            </thead>
            <tbody>
                <% 
                    Connection conn = null;
                    Statement stmt = null;
                    ResultSet rs = null;

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vtienda", "root", "");

                        String sql = "SELECT * FROM producto";
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);

                        while (rs.next()) {
                %>
                            <tr>
                                <td><%= rs.getInt("codigo") %></td>
                                <td><%= rs.getString("nombre") %></td>
                                <td><%= rs.getDouble("precio") %></td>
                                <td><%= rs.getInt("stock") %></td>
                                <td>
                                    <!-- Botón para eliminar el producto -->
                                    <form action="ProductoServlet" method="post">
                                        <input type="hidden" name="action" value="eliminar">
                                        <input type="hidden" name="codigo" value="<%= rs.getInt("codigo") %>">
                                        <button type="submit" class="btn btn-danger">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (rs != null) rs.close();
                            if (stmt != null) stmt.close();
                            if (conn != null) conn.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                %>
            </tbody>
        </table>
        <!-- Botón para regresar al formulario para agregar más productos -->
        <a href="registro.jsp" class="btn btn-primary">Regresar</a>
    </div>
</body>
</html>
