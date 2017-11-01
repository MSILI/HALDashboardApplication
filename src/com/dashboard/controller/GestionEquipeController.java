package com.dashboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.dashboard.dao.ChefEquipeDao;
import com.dashboard.dao.ChefEquipeDaoImp;
import com.dashboard.dao.CompteDao;
import com.dashboard.dao.CompteDaoImpl;
import com.dashboard.dao.EquipeDao;
import com.dashboard.dao.EquipeDaoImp;
import com.dashboard.dao.PersonnelDao;
import com.dashboard.dao.PersonnelDaoImpl;
import com.dashboard.model.ChefEquipe;
import com.dashboard.model.ChefLabo;
import com.dashboard.model.Compte;
import com.dashboard.model.Equipe;
import com.dashboard.model.Personnel;

/**
 * Servlet implementation class GestionEquipe
 */
@WebServlet("/gestionEquipe")
public class GestionEquipeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String VUEAJOUT = "/WEB-INF/views/chefLabo/GEquipe/ajouter.jsp";
	private final String VUELIST = "/WEB-INF/views/chefLabo/GEquipe/lister.jsp";
	private final String VUEEDIT = "/WEB-INF/views/chefLabo/GEquipe/modifier.jsp";

	private EquipeDao equipeDao;
	private CompteDao compteDao;
	private PersonnelDao personnelDao;
	private ChefEquipeDao chefEquipeDao;

	private Equipe equipe = null;
	private Compte compte = null;
	private ChefEquipe chefEquipe = null;
	private Personnel personnel = null;
	private String nom = null, prenom = null, grade = null, userName = null, password = null, oldPassword = null,
			nomEq = null, acronymeEq = null;
	private int idPers, idHal, idEq, idHalEq, idCompte;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionEquipeController() {
		super();
		equipeDao = new EquipeDaoImp();
		compteDao = new CompteDaoImpl();
		personnelDao = new PersonnelDaoImpl();
		chefEquipeDao = new ChefEquipeDaoImp();

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
			case "add":
				request.getRequestDispatcher(VUEAJOUT).forward(request, response);
				break;
			case "list":
				ChefLabo chefLabo = (ChefLabo) session.getAttribute("userSession");
				request.setAttribute("listEquipes", equipeDao.listByLaboratoire(chefLabo.getLaboratoire().getId()));
				request.getRequestDispatcher(VUELIST).forward(request, response);
				break;
			case "delete":
				if (request.getParameter("id") != null) {
					int idEquipe = Integer.parseInt(request.getParameter("id"));
					equipeDao.delete(idEquipe);
					response.sendRedirect(request.getContextPath() + "/gestionEquipe?action=list");
				}
				break;
			case "edit":
				if (request.getParameter("id") != null) {
					int idEquipe = Integer.parseInt(request.getParameter("id"));
					request.setAttribute("chefEquipe", chefEquipeDao.getChefEquipeByEquipe(idEquipe));
					request.getRequestDispatcher(VUEEDIT).forward(request, response);
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
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-256");
		passwordEncryptor.setPlainDigest(true);
		HttpSession session = request.getSession();
		String param = request.getParameter("action");
		switch (param) {
		case "add":
			idHal = Integer.parseInt(request.getParameter("idHal"));
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			grade = request.getParameter("grade");
			userName = request.getParameter("userName");
			password = passwordEncryptor.encryptPassword(request.getParameter("password"));
			idHalEq = Integer.parseInt(request.getParameter("idHalEq"));
			nomEq = request.getParameter("nomEq");
			acronymeEq = request.getParameter("acronymeEq");
			// add Equipe
			ChefLabo chefLabo = (ChefLabo) session.getAttribute("userSession");
			equipe = new Equipe(acronymeEq, nomEq, chefLabo.getLaboratoire());
			equipe.setIdHal(idHalEq);
			equipeDao.add(equipe);
			// add compte
			compte = new Compte(userName, password, 2);
			compteDao.add(compte);
			// add personnel
			personnel = new Personnel(nom, prenom, grade);
			personnel.setIdHal(idHal);
			personnel.setCompte(compte);
			personnelDao.add(personnel);
			// update compte foreign key (personnel)
			compte.setPersonnel(personnel);
			compteDao.updateIDPersonnel(compte);
			// add chefEquipe
			chefEquipe = new ChefEquipe(personnel.getId(), personnel.getNom(), personnel.getPrenom(),
					personnel.getFonction(), equipe);
			chefEquipeDao.add(chefEquipe);
			// update foreign key chefEquipe
			equipe.setChefEquipe(chefEquipe);
			equipeDao.updateIDChefEquipe(equipe);

			response.sendRedirect(request.getContextPath() + "/gestionEquipe?action=list");
			break;
		case "edit":
			idPers = Integer.parseInt(request.getParameter("id"));
			idHal = Integer.parseInt(request.getParameter("idHal"));
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			grade = request.getParameter("grade");
			idCompte = Integer.parseInt(request.getParameter("idCompte"));
			userName = request.getParameter("userName");
			oldPassword = request.getParameter("oldPassword");
			password = passwordEncryptor.encryptPassword(request.getParameter("password"));
			idEq = Integer.parseInt(request.getParameter("idEq"));
			idHalEq = Integer.parseInt(request.getParameter("idHalEq"));
			nomEq = request.getParameter("nomEq");
			acronymeEq = request.getParameter("acronymeEq");

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

			equipe = new Equipe(idEq, acronymeEq, nomEq, null);
			equipe.setIdHal(idHalEq);
			equipeDao.edit(equipe);
			response.sendRedirect(request.getContextPath() + "/gestionEquipe?action=list");
			break;
		default:
			break;
		}
	}

}
