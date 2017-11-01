package com.dashboard.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.dashboard.dao.CompteDao;
import com.dashboard.dao.CompteDaoImpl;
import com.dashboard.dao.MembreEquipeDao;
import com.dashboard.dao.MembreEquipeDaoImp;
import com.dashboard.dao.PersonnelDao;
import com.dashboard.dao.PersonnelDaoImpl;
import com.dashboard.model.ChefEquipe;
import com.dashboard.model.Compte;
import com.dashboard.model.Personnel;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet("/chef-equipe-dashboard")
public class ChefEquipeDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String VUEHOME = "/WEB-INF/views/chefEquipe/dashBoardHome.jsp";
	private final String VUESEARCH = "/WEB-INF/views/chefEquipe/dashBoardSearch.jsp";
	private final String VUEEVALUER = "/WEB-INF/views/chefEquipe/GMembre/evaluerMembre.jsp";
	private final String VUEPRAM = "/WEB-INF/views/chefEquipe/param.jsp";

	private MembreEquipeDao membreEquipeDao;
	private CompteDao compteDao;

	private ChefEquipe chefEquipe = null;
	private PersonnelDao personnelDao;
	private Compte compte = null;
	private Personnel personnel = null;
	private String nom = null, prenom = null, grade = null, userName = null, password = null;
	private int idHal;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChefEquipeDashboardController() {
		super();
		membreEquipeDao = new MembreEquipeDaoImp();
		compteDao = new CompteDaoImpl();
		personnelDao = new PersonnelDaoImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Integer> listAnnnee = null;
		List<Integer> listMois = null;
		String param = request.getParameter("action");
		if (param != null) {
			switch (param) {
			case "cure":
				chefEquipe = (ChefEquipe) session.getAttribute("userSession");
				request.setAttribute("cureYear", LocalDate.now().getYear());
				request.setAttribute("cureMonth", LocalDate.now().getMonthValue());
				request.setAttribute("listMembres", membreEquipeDao.listByTeam(chefEquipe.getEquipe().getId()));
				request.getRequestDispatcher(VUEHOME).forward(request, response);
				break;
			case "search":
				listAnnnee = new ArrayList<Integer>();
				for (int i = 1990; i <= LocalDate.now().getYear(); i++) {
					listAnnnee.add(i);
				}
				listMois = new ArrayList<Integer>();
				for (int i = 1; i <= 12; i++) {
					listMois.add(i);
				}
				chefEquipe = (ChefEquipe) session.getAttribute("userSession");
				request.setAttribute("annees", listAnnnee);
				request.setAttribute("mois", listMois);
				request.setAttribute("listMembres", membreEquipeDao.listByTeam(chefEquipe.getEquipe().getId()));
				request.getRequestDispatcher(VUESEARCH).forward(request, response);
				break;
			case "membre":
				listAnnnee = new ArrayList<Integer>();
				for (int i = 1990; i <= LocalDate.now().getYear(); i++) {
					listAnnnee.add(i);
				}
				listMois = new ArrayList<Integer>();
				for (int i = 1; i <= 12; i++) {
					listMois.add(i);
				}
				chefEquipe = (ChefEquipe) session.getAttribute("userSession");
				request.setAttribute("annees", listAnnnee);
				request.setAttribute("mois", listMois);
				request.setAttribute("listMembres", membreEquipeDao.listByTeam(chefEquipe.getEquipe().getId()));
				request.getRequestDispatcher(VUEEVALUER).forward(request, response);
				break;
			case "param":
				request.getRequestDispatcher(VUEPRAM).forward(request, response);
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
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-256");
		passwordEncryptor.setPlainDigest(true);
		HttpSession session = request.getSession();
		String param = request.getParameter("action");
		if (param != null) {
			switch (param) {
			case "param":
				int idPers = Integer.parseInt(request.getParameter("id"));
				idHal = Integer.parseInt(request.getParameter("idHal"));
				nom = request.getParameter("nom");
				prenom = request.getParameter("prenom");
				grade = request.getParameter("grade");
				int idCompte = Integer.parseInt(request.getParameter("idCompte"));
				userName = request.getParameter("userName");
				password = passwordEncryptor.encryptPassword(request.getParameter("password"));
				String oldPassword = request.getParameter("oldPassword");

				personnel = new Personnel(idPers, nom, prenom, grade);
				personnel.setIdHal(idHal);
				personnelDao.edit(personnel);

				if (request.getParameter("password").trim().equals("")) {
					compte = new Compte(idCompte, userName, oldPassword, 2);
					compteDao.edit(compte);
				} else {
					compte = new Compte(idCompte, userName, password, 2);
					compteDao.edit(compte);
				}

				chefEquipe = (ChefEquipe) session.getAttribute("userSession");
				chefEquipe.setCompte(compte);
				chefEquipe.setNom(nom);
				chefEquipe.setPrenom(prenom);
				chefEquipe.setIdHal(idHal);
				chefEquipe.setFonction(grade);
				session.setAttribute("userSession", chefEquipe);
				response.sendRedirect(this.getServletContext().getContextPath() + "/chef-equipe-dashboard?action=cure");

				break;

			default:
				break;
			}
		}
	}

}
