/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection connection;

    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    
    public void insertarProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO producto (codigo, nombre, precio, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getStock());
            statement.executeUpdate();
        }
    }

   public List<Producto> listarProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT codigo, nombre, precio, stock FROM producto";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setCodigo(resultSet.getInt("codigo"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setStock(resultSet.getInt("stock"));
                productos.add(producto);
            }
        }
        return productos;
    }

    public List<Producto> buscarProductoPorNombre(String nombre) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT codigo, nombre, precio, stock FROM producto WHERE nombre LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + nombre + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Producto producto = new Producto();
                    producto.setCodigo(resultSet.getInt("codigo"));
                    producto.setNombre(resultSet.getString("nombre"));
                    producto.setPrecio(resultSet.getDouble("precio"));
                    producto.setStock(resultSet.getInt("stock"));
                    productos.add(producto);
                }
            }
        }
        return productos;
    }
    public void eliminarProductoPorCodigo(int codigo) throws SQLException {
        String query = "DELETE FROM producto WHERE codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codigo);
            statement.executeUpdate();
        }
    }
}
