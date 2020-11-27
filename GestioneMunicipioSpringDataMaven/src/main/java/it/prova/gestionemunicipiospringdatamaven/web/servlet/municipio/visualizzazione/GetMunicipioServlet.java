package it.prova.gestionemunicipiospringdatamaven.web.servlet.municipio.visualizzazione;

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

import it.prova.gestionemunicipiospringdatamaven.dto.MunicipioDTO;
import it.prova.gestionemunicipiospringdatamaven.service.municipio.MunicipioService;


@WebServlet("/GetMunicipioServlet")
public class GetMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public GetMunicipioServlet() {
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
		MunicipioDTO municipioDTO=new MunicipioDTO();
		String erroreNelId=municipioDTO.errorId(request.getParameter("idMunicipio"));
		if (erroreNelId!=null) {
			request.setAttribute("messaggioErrore",erroreNelId);
			request.getRequestDispatcher("login.jsp").forward(request,response);
			request.getSession().invalidate();
			return;
		} else {
			Long idMunicipio=Long.parseLong(request.getParameter("idMunicipio"));
			List<MunicipioDTO> listaMonoElementoMunicipio=new ArrayList<>();
			listaMonoElementoMunicipio.add(MunicipioDTO
					.buildDTOFromModel(municipioService.caricaSingoloMunicipio(idMunicipio)));
			request.setAttribute("listaMunicipi",listaMonoElementoMunicipio);
			request.setAttribute("messaggioConferma","Operazione eseguita con successo");
			request.getRequestDispatcher("municipio/results.jsp").forward(request,response);
			return;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
