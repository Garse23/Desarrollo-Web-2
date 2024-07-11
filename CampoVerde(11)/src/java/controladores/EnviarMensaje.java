package controladores;

import DAO.MensajesDAO;
import DAO.MensajesDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import modelo.Mensajes;

@WebServlet(name = "EnviarMensaje", urlPatterns = {"/EnviarMensaje"})
public class EnviarMensaje extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Mensajes mensaje = new Mensajes();
        MensajesDAO msgdao = new MensajesDAOImpl();
        HttpSession session = request.getSession();
        Integer IdUsuario = (Integer) session.getAttribute("idUsuario");
        Integer IdDestino = (Integer) session.getAttribute("IdDestinatario");
        String msg = request.getParameter("mensaje");

        mensaje.setIdUEnvio(IdUsuario);
        mensaje.setIdURecibido(IdDestino);
        mensaje.setMensaje(msg);
        if (msg!=""){
            msgdao.EnviarMensaje(mensaje);
        }
        response.sendRedirect("Chat.jsp");
    }
}
