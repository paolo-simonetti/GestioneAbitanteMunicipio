package it.prova.gestionemunicipiospringdatamaven.web.servlet.abitante.eliminazione;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestionemunicipiospringdatamaven.dto.AbitanteDTO;


@WebServlet("/PrepareDeleteAbitanteServlet")
public class PrepareDeleteAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PrepareDeleteAbitanteServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AbitanteDTO abitanteDTO=new AbitanteDTO();
		String erroreNelId=abitanteDTO.errorId(request.getParameter("idAbitante"));
		if (erroreNelId!=null) {
			request.setAttribute("messaggioErrore",erroreNelId);
			request.getRequestDispatcher("login.jsp").forward(request,response);
			request.getSession().invalidate();
			return;
		} else {
			Long idAbitante=Long.parseLong(request.getParameter("idAbitante"));
			request.setAttribute("idAbitanteDaEliminare",idAbitante);
			request.getRequestDispatcher("abitante/delete.jsp").forward(request,response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
