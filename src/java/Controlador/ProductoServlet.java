package Controlador;

import Modelo.Producto;
import Modelo.ProductoDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {
    private Connection connection;
    private ProductoDAO productoDAO;

    public void init() throws ServletException {
        String url = "jdbc:mysql://localhost:3306/vtienda";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            productoDAO = new ProductoDAO(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Error de inicialización", e);
        }
    }

    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("registrar".equals(action)) {
            registrarProducto(request, response);
        } else if ("eliminar".equals(action)) {
            eliminarProducto(request, response);
        } else {
            // Si la acción no es registrar ni eliminar, redirecciona a la página de inicio
            response.sendRedirect("index.jsp");
        }
    }

    private void registrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);

        try {
            productoDAO.insertarProducto(producto);
            // Después de insertar, redirige a la página de registro con los campos vaciados
            response.sendRedirect("registro.jsp");
        } catch (SQLException e) {
            throw new ServletException("Error en el registro del producto", e);
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        try {
            productoDAO.eliminarProductoPorCodigo(codigo);
            response.sendRedirect("listado.jsp");
        } catch (SQLException e) {
            throw new ServletException("Error al eliminar el producto", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("buscar".equals(action)) {
            buscarProductos(request, response);
        } else {
            response.sendRedirect("busqueda.jsp");
        }
    }

    private void buscarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");

        try {
            List<Producto> productos = productoDAO.buscarProductoPorNombre(nombre);
            request.setAttribute("productosEncontrados", productos);
            // Redirige a busqueda.jsp para mostrar los resultados
            request.getRequestDispatcher("busqueda.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al buscar los productos", e);
        }
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Producto> productos = productoDAO.listarProductos();
            request.setAttribute("productos", productos); // Agregar la lista de productos al atributo de solicitud
            request.getRequestDispatcher("listado.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al listar los productos", e);
        }
    }
}
