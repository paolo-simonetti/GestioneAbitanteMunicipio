package it.prova.gestionemunicipiospringdatamaven.web.servlet.abitante.eliminazione;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringdatamaven.dto.AbitanteDTO;
import it.prova.gestionemunicipiospringdatamaven.dto.MunicipioDTO;
import it.prova.gestionemunicipiospringdatamaven.service.abitante.AbitanteService;
import it.prova.gestionemunicipiospringdatamaven.service.municipio.MunicipioService;


@WebServlet("/ExecuteDeleteAbitanteServlet")
public class ExecuteDeleteAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ExecuteDeleteAbitanteServlet() {
        super();
    }

    @Autowired
	private AbitanteService abitanteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String erroreNelId=new AbitanteDTO().errorId(request.getParameter("idAbitanteDaEliminare"));
		if (erroreNelId!=null) {
			request.setAttribute("messaggioErrore",erroreNelId);
			request.getRequestDispatcher("login.jsp").forward(request,response);
			request.getSession().invalidate();
			return;
		} else {
			Long idAbitante=Long.parseLong(request.getParameter("idAbitanteDaEliminare"));
			abitanteService.rimuovi(abitanteService.caricaSingoloAbitante(idAbitante));
			request.setAttribute("listaAbitanti",abitanteService.listAllAbitanti());
			request.setAttribute("messaggioConferma","Operazione eseguita con successo");
			request.getRequestDispatcher("abitante/results.jsp").forward(request,response);
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
