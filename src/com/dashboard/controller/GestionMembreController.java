package com.dashboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dashboard.dao.MembreDao;
import com.dashboard.dao.MembreDaoImpl;
import com.dashboard.dao.MembreEquipeDao;
import com.dashboard.dao.MembreEquipeDaoImp;
import com.dashboard.dao.PersonnelDao;
import com.dashboard.dao.PersonnelDaoImpl;
import com.dashboard.model.ChefEquipe;
import com.dashboard.model.Membre;
import com.dashboard.model.MembreEquipe;
import com.dashboard.model.Personnel;

/**
 * Servlet implementation class GestionEquipe
 */
@WebServlet("/gestionMembre")
public class GestionMembreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String VUEAJOUT = "/WEB-INF/views/chefEquipe/GMembre/ajouter.jsp";
	private final String VUELIST = "/WEB-INF/views/chefEquipe/GMembre/lister.jsp";
	private final String VUEMODIF = "/WEB-INF/views/chefEquipe/GMembre/modifier.jsp";

	private PersonnelDao personnelDao;
	private MembreDao membreDao;
	private MembreEquipeDao membreEquipeDao;

	private Personnel personnel = null;
	private Membre membre = null;
	private MembreEquipe membreEquipe = null;

	private String nom = null, prenom = null, grade = null;
	private int idHal,id;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionMembreController() {
		super();
		personnelDao = new PersonnelDaoImpl();
		membreDao = new MembreDaoImpl();
		membreEquipeDao = new MembreEquipeDaoImp();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String param = request.getParameter("action");
		if (param != null) {
			switch (param) {
			case "add":
				request.getRequestDispatcher(VUEAJOUT).forward(request, response);
				break;
			case "list":
				ChefEquipe chefEquipe = (ChefEquipe) session.getAttribute("userSession");
				request.setAttribute("listMembres", membreEquipeDao.listByTeam(chefEquipe.getEquipe().getId()));
				request.getRequestDispatcher(VUELIST).forward(request, response);
				break;
			case "delete":
				if (request.getParameter("id") != null) {
					int idMembre = Integer.parseInt(request.getParameter("id"));
					personnelDao.delete(idMembre);
					response.sendRedirect(request.getContextPath() + "/gestionMembre?action=list");
				}
				break;
			case "edit":
				if (request.getParameter("id") != null) {
					int idMembre = Integer.parseInt(request.getParameter("id"));
					request.setAttribute("membre", personnelDao.getPersonnel(idMembre));
					request.getRequestDispatcher(VUEMODIF).forward(request, response);
				}
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String param = request.getParameter("action");
		switch (param) {
		case "add":
			
			idHal = Integer.parseInt(request.getParameter("idHal"));
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			grade = request.getParameter("grade");

			personnel = new Personnel(nom, prenom, grade);
			personnel.setIdHal(idHal);
			personnelDao.addMembreEquipe(personnel);

			membre = new Membre(personnel.getId(), personnel.getNom(), personnel.getPrenom(), personnel.getFonction());
			membreDao.add(membre);
			ChefEquipe chefEquipe = (ChefEquipe) session.getAttribute("userSession");
			membreEquipe = new MembreEquipe(chefEquipe.getEquipe(), membre);
			membreEquipeDao.add(membreEquipe);

			response.sendRedirect(request.getContextPath() + "/gestionMembre?action=list");

			break;
		case "edit":
			id = Integer.parseInt(request.getParameter("id"));
			idHal = Integer.parseInt(request.getParameter("idHal"));
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			grade = request.getParameter("grade");
			
			personnel = new Personnel(id, nom, prenom, grade);
			personnel.setIdHal(idHal);
			personnelDao.edit(personnel);
			
			response.sendRedirect(request.getContextPath() + "/gestionMembre?action=list");
			
			break;
		default:
			break;
		}
	}

}
