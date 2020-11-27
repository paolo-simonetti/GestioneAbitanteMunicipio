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
import it.prova.gestionemunicipiospringdatamaven.model.Abitante;
import it.prova.gestionemunicipiospringdatamaven.service.abitante.AbitanteService;


@WebServlet("/PrepareUpdateAbitanteServlet")
public class PrepareUpdateAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PrepareUpdateAbitanteServlet() {
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
		AbitanteDTO abitanteDTO=new AbitanteDTO();
		String erroreNelId=abitanteDTO.errorId(request.getParameter("idAbitante"));
		if (erroreNelId!=null) {
			request.setAttribute("messaggioErrore",erroreNelId);
			request.getRequestDispatcher("login.jsp").forward(request,response);
			request.getSession().invalidate();
			return;
		} else {
			Long idAbitante=Long.parseLong(request.getParameter("idAbitante"));
			Abitante abitanteDaAggiornare=abitanteService.caricaSingoloAbitanteConMunicipio(idAbitante);
			if (abitanteDaAggiornare==null) {
				request.setAttribute("messaggioErrore","Abitante non esistente");
				request.getRequestDispatcher("login.jsp").forward(request,response);
				request.getSession().invalidate();
				return;
			}
			request.setAttribute("abitanteDaAggiornare",abitanteDaAggiornare);
			request.getRequestDispatcher("abitante/get.jsp").forward(request,response);
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
