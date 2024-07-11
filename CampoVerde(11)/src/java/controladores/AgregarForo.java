package controladores;

import DAO.ForoDAO;
import DAO.ForoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import modelo.Foro;

@WebServlet(name = "AgregarForo", urlPatterns = {"/AgregarForo"})
public class AgregarForo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Foro foro = new Foro();
        ForoDAO fDao = new ForoDAOImpl();
        String Titulo = request.getParameter("titulo");
        String Anuncio= request.getParameter("mensaje");
        int Usuario = (int) session.getAttribute("idUsuario");
        foro.setAnuncioForo(Anuncio);
        foro.setNombreForo(Titulo);
        foro.setIdUsuario(Usuario);
        fDao.AgregarForo(foro);
        response.sendRedirect("Foros.jsp");
        
        
        
        
    }

}
