package it.prova.gestionemunicipiospringdatamaven.web.servlet.municipio.test;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringdatamaven.service.municipio.MunicipioService;


@WebServlet("/ExecuteTestSpringDataMunicipioServlet")
public class ExecuteTestSpringDataMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteTestSpringDataMunicipioServlet() {
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
		String codiceOperazione = request.getParameter("codop");
		String queryInput = request.getParameter("queryInput");

		Object resultFromQuery = null;

		switch (codiceOperazione) {
		case "findAllByDescrizioneContaining":
			resultFromQuery = municipioService.cercaByDescrizioneILike(queryInput);
			break;
		case "findAllByUbicazioneStartingWith":
			resultFromQuery = municipioService.cercaByUbicazioneIniziaCon(queryInput);
			break;

		case "findAllByCodiceAndUbicazione":
			resultFromQuery = municipioService.cercaByCodiceAndUbicazione(request.getParameter("queryInputCodice"),
					request.getParameter("queryInputUbicazione"));
			break;
			
		case "findAllByCodiceOrderByUbicazioneDesc":
			resultFromQuery = municipioService
					.cercaByCodiceOrdinandoAlfabeticamentePer(request.getParameter("queryInput"));
			break;
			
		case "findAllByAbitanti_CognomeLike":
			resultFromQuery = municipioService.cercaByAbitanti_CognomeLike(queryInput);
			break;
			
		default:
			break;
		}

		String result = resultFromQuery == null ? "" : resultFromQuery.toString();

		response.getWriter().append("Risultato: =====>>> " + codiceOperazione).append("\n").append(result);
	}

}
