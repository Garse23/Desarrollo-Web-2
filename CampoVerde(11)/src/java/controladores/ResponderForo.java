package controladores;

import DAO.RespuestasForoDAO;
import DAO.RespuestasForoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import modelo.RespuestasForo;

@WebServlet(name = "ResponderForo", urlPatterns = {"/ResponderForo"})
public class ResponderForo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RespuestasForo RF = new RespuestasForo();
        RespuestasForoDAO RFdao = new RespuestasForoDAOImpl();
        HttpSession session = request.getSession();
        String Respuesta = request.getParameter("mensajeforo");
        Integer IdUsuario = (Integer) session.getAttribute("idUsuario");
        Integer IdForo = (Integer) Integer.parseInt(request.getParameter("IdForo"));
        RF.setRespuestaForo(Respuesta);
        RF.setIdUsuario(IdUsuario);
        RF.setIdForo(IdForo);
        RFdao.AgregarRespuesta(RF);
        response.sendRedirect("Foro?Foro="+IdForo);
        
    }

}
