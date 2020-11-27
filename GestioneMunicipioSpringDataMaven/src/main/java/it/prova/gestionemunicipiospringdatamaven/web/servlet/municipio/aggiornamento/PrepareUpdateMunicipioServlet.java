package it.prova.gestionemunicipiospringdatamaven.web.servlet.municipio.aggiornamento;

import java.io.IOException;


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


@WebServlet("/PrepareUpdateMunicipioServlet")
public class PrepareUpdateMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PrepareUpdateMunicipioServlet() {
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
			Municipio municipioDaAggiornare=municipioService.caricaSingoloMunicipio(idMunicipio);
			if (municipioDaAggiornare==null) {
				request.setAttribute("messaggioErrore","Municipio non esistente");
				request.getRequestDispatcher("login.jsp").forward(request,response);
				request.getSession().invalidate();
			}
			request.setAttribute("municipioDaAggiornare",municipioDaAggiornare);
			request.getRequestDispatcher("municipio/get.jsp").forward(request,response);
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
