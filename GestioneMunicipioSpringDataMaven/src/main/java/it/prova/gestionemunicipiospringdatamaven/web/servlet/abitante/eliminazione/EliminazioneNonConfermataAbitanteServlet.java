package it.prova.gestionemunicipiospringdatamaven.web.servlet.abitante.eliminazione;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringdatamaven.service.abitante.AbitanteService;



@WebServlet("/EliminazioneNonConfermataAbitanteServlet")
public class EliminazioneNonConfermataAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EliminazioneNonConfermataAbitanteServlet() {
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
		request.setAttribute("listaAbitanti", abitanteService.listAllAbitanti());
		request.getRequestDispatcher("/abitante/results.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
