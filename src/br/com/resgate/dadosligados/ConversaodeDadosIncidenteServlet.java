package br.com.resgate.dadosligados;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

@WebServlet(urlPatterns = { "/data/Incidente/*" })
public class ConversaodeDadosIncidenteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String tipo = null;
		String id = null;

		String[] path = null;

		try {

			path = req.getPathInfo().split("/");

		} catch (Exception e) {
		}
		tipo = path[1];
		id = path[2];
		InputStream in = null;
		PrintWriter out = null;

		try {
			in = FileManager.get().open(
					"http://localhost:2020/data/Incidente/" + id);
			out = resp.getWriter();
		} catch (Exception e) {
		}
		Model model = ModelFactory.createDefaultModel(); // creates an in-memory
															// Jena Model
		model.read(in, null, "TURTLE"); // parses an InputStream assuming RDF in
										// Turtle format

		// Write the Jena Model in Turtle, RDF/XML and N-Triples format
		if (tipo.equals("RDF")) {
			resp.setContentType("text/xml");
			model.write(out, "RDF/XML");
		} else if (tipo.equals("N-Triple"))
			model.write(out, "N-TRIPLES");
		else if (tipo.equals("Turtle"))
			model.write(out, "TURTLE");

	}
}
