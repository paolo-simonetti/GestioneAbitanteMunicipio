package it.prova.gestionemunicipiospringdatamaven.web.servlet.municipio.eliminazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestionemunicipiospringdatamaven.dto.MunicipioDTO;

@WebServlet("/PrepareDeleteMunicipioServlet")
public class PrepareDeleteMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareDeleteMunicipioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MunicipioDTO municipioDTO=new MunicipioDTO();
		String erroreNelId=municipioDTO.errorId(request.getParameter("idMunicipio"));
		if (erroreNelId!=null) {
			request.setAttribute("messaggioErrore",erroreNelId);
			request.getRequestDispatcher("login.jsp").forward(request,response);
			request.getSession().invalidate();
			return;
		} else {
			Long idMunicipio=Long.parseLong(request.getParameter("idMunicipio"));
			request.setAttribute("idMunicipioDaEliminare",idMunicipio);
			request.getRequestDispatcher("municipio/delete.jsp").forward(request,response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
