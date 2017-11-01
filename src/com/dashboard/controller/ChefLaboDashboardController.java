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
import com.dashboard.dao.EquipeDao;
import com.dashboard.dao.EquipeDaoImp;
import com.dashboard.dao.PersonnelDao;
import com.dashboard.dao.PersonnelDaoImpl;
import com.dashboard.model.ChefLabo;
import com.dashboard.model.Compte;
import com.dashboard.model.Personnel;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet("/chef-labo-dashboard")
public class ChefLaboDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String VUEHOME = "/WEB-INF/views/chefLabo/dashBoardHome.jsp";
	private final String VUESEARCH = "/WEB-INF/views/chefLabo/dashBoardSearch.jsp";
	private final String VUEPRAM = "/WEB-INF/views/chefLabo/param.jsp";

	private EquipeDao equipeDao;

	private CompteDao compteDao;
	private PersonnelDao personnelDao;
	private ChefLabo chefLabo = null;
	private Compte compte = null;
	private Personnel personnel = null;
	private String nom = null, prenom = null, grade = null, userName = null, password = null;
	private int idHal;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChefLaboDashboardController() {
		super();
		equipeDao = new EquipeDaoImp();
		compteDao = new CompteDaoImpl();
		personnelDao = new PersonnelDaoImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String param = request.getParameter("action");
		HttpSession session = request.getSession();
		if (param != null) {
			switch (param) {
			case "cure":
				chefLabo = (ChefLabo) session.getAttribute("userSession");
				request.setAttribute("cureYear", LocalDate.now().getYear());
				request.setAttribute("cureMonth", LocalDate.now().getMonthValue());
				request.setAttribute("listEquipes", equipeDao.listByLaboratoire(chefLabo.getLaboratoire().getId()));
				request.getRequestDispatcher(VUEHOME).forward(request, response);
				break;

			case "search":

				List<Integer> listAnnnee = new ArrayList<Integer>();
				for (int i = 1990; i <= LocalDate.now().getYear(); i++) {
					listAnnnee.add(i);
				}
				List<Integer> listMois = new ArrayList<Integer>();
				for (int i = 1; i <= 12; i++) {
					listMois.add(i);
				}
				chefLabo = (ChefLabo) session.getAttribute("userSession");
				request.setAttribute("annees", listAnnnee);
				request.setAttribute("mois", listMois);
				request.setAttribute("listEquipes", equipeDao.listByLaboratoire(chefLabo.getLaboratoire().getId()));
				request.getRequestDispatcher(VUESEARCH).forward(request, response);
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
					compte = new Compte(idCompte, userName, oldPassword, 1);
					compteDao.edit(compte);
				} else {
					compte = new Compte(idCompte, userName, password, 1);
					compteDao.edit(compte);
				}

				chefLabo = (ChefLabo) session.getAttribute("userSession");
				chefLabo.setCompte(compte);
				chefLabo.setNom(nom);
				chefLabo.setPrenom(prenom);
				chefLabo.setIdHal(idHal);
				chefLabo.setFonction(grade);
				session.setAttribute("userSession", chefLabo);
				response.sendRedirect(this.getServletContext().getContextPath() + "/chef-labo-dashboard?action=cure");

				break;

			default:
				break;
			}
		}
	}

}
