/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import DAO.ProductoDAO;
import DAO.ProductoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.Producto;


@WebServlet(name = "EliminarProducto", urlPatterns = {"/EliminarProducto"})
public class EliminarProducto extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductoDAO daoProducto = new ProductoDAOImpl();
        Producto producto = new Producto();
        int idProducto = 0;
        idProducto = Integer.parseInt(request.getParameter("idProducto"));
        producto.setIdProducto(idProducto);
        daoProducto.InhabilitarProducto(producto);
        response.sendRedirect("listaProductos.jsp");
    }


}
