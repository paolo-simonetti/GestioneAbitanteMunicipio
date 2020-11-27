package it.prova.gestionemunicipiospringdatamaven.web.servlet.abitante.aggiornamento;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringdatamaven.dto.AbitanteDTO;
import it.prova.gestionemunicipiospringdatamaven.dto.AbstractDTO;
import it.prova.gestionemunicipiospringdatamaven.model.Abitante;
import it.prova.gestionemunicipiospringdatamaven.service.abitante.AbitanteService;
import it.prova.gestionemunicipiospringdatamaven.service.municipio.MunicipioService;


@WebServlet("/ExecuteUpdateAbitanteServlet")
public class ExecuteUpdateAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExecuteUpdateAbitanteServlet() {
        super();
    }
    
	@Autowired
	private AbitanteService abitanteService;

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
		AbstractDTO<Abitante,AbitanteDTO> abstractAbitanteDTO=new AbitanteDTO();
		
		AbitanteDTO abitanteDTO=(AbitanteDTO) abstractAbitanteDTO;
		
		
		abitanteDTO.setIdMunicipio(request.getParameter("municipioId"));

		abitanteDTO.setNome(request.getParameter("nomeInput"));
		abitanteDTO.setCognome(request.getParameter("cognomeInput"));
		abitanteDTO.setEta(request.getParameter("etaInput"));
		abitanteDTO.setResidenza(request.getParameter("residenzaInput"));
		if(abitanteDTO.errorId(request.getParameter("municipioId"))!=null) {
			request.setAttribute("messaggioErrore",abstractAbitanteDTO.errorId(request.getParameter("municipioId")));
			request.getRequestDispatcher("login.jsp").forward(request,response);
			request.getSession().invalidate();
			return;
		}
		if(!abstractAbitanteDTO.errors().isEmpty()) {
			request.setAttribute("abitanteAttribute", abstractAbitanteDTO);
			request.setAttribute("abitanteErrors", abstractAbitanteDTO.errors());
			request.getRequestDispatcher("/abitante/get.jsp").forward(request, response);
			return;
		}
				
		Abitante abitanteInstance = abstractAbitanteDTO.buildModelFromDTO(abitanteDTO);
		abitanteInstance.setMunicipio(municipioService
				.caricaSingoloMunicipio(Long.parseLong(request.getParameter("municipioId"))));
		
		abitanteService.aggiorna(abitanteInstance);
		//vado in pagina con ok
		request.setAttribute("messaggioConferma", "Aggiornamento avvenuto con successo");
		request.setAttribute("listaAbitanti", abitanteService.listAllAbitanti());
		request.getRequestDispatcher("/abitante/results.jsp").forward(request, response);
	}

}
