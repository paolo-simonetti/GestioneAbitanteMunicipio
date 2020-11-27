package it.prova.gestionemunicipiospringdatamaven.web.servlet.municipio.aggiornamento;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringdatamaven.dto.MunicipioDTO;
import it.prova.gestionemunicipiospringdatamaven.model.Municipio;
import it.prova.gestionemunicipiospringdatamaven.service.municipio.MunicipioService;


@WebServlet("/ExecuteUpdateMunicipioServlet")
public class ExecuteUpdateMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ExecuteUpdateMunicipioServlet() {
        super();
    }

    @Autowired
	private MunicipioService municipioService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		MunicipioDTO municipioDTO=new MunicipioDTO(request.getParameter("descrizione"),
				request.getParameter("codice"),request.getParameter("ubicazione"));
		if(municipioDTO.errorId(request.getParameter("idMunicipioDaAggiornare"))!=null)	{
			request.setAttribute("messaggioErrore",
					municipioDTO.errorId(request.getParameter("idMunicipioDaAggiornare")));
			request.getRequestDispatcher("login.jsp").forward(request,response);
			request.getSession().invalidate();
			return;
		} else {
			municipioDTO.setId(Long.parseLong(request.getParameter("idMunicipioDaAggiornare")));
		} 
		List<String> municipioErrors = municipioDTO.errors();
		if (!municipioErrors.isEmpty()) {
			request.setAttribute("municipioAttribute", municipioDTO);
			request.setAttribute("municipioErrors", municipioErrors);
			request.getRequestDispatcher("/municipio/update.jsp").forward(request, response);
			return;
		}
		
		//se arrivo qui significa che va bene
		Municipio municipioInstance = MunicipioDTO.buildModelFromDto(municipioDTO);
		municipioService.aggiorna(municipioInstance);
		
		//vado in pagina con ok
		request.setAttribute("messaggioConferma", "Aggiornamento avvenuto con successo");
		request.setAttribute("listaMunicipi", municipioService.listAllMunicipi());
		request.getRequestDispatcher("/municipio/results.jsp").forward(request, response);
	}

}
