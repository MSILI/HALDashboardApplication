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
import com.dashboard.dao.ChefLaboDao;
import com.dashboard.dao.ChefLaboDaoImp;
import com.dashboard.dao.CompteDao;
import com.dashboard.dao.CompteDaoImpl;
import com.dashboard.dao.LaboratoireDao;
import com.dashboard.dao.LaboratoireDaoImpl;
import com.dashboard.dao.PersonnelDao;
import com.dashboard.dao.PersonnelDaoImpl;
import com.dashboard.model.ChefLabo;
import com.dashboard.model.Compte;
import com.dashboard.model.Laboratoire;
import com.dashboard.model.Personnel;

/**
 * Servlet implementation class ConnexionController
 */
@WebServlet("/compte")
public class CompteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String VUEINSC = "/WEB-INF/views/commun/inscription.jsp";
	private final String VUECONN = "/WEB-INF/views/commun/connexion.jsp";

	private LaboratoireDao laboratoireDao;
	private CompteDao compteDao;
	private PersonnelDao personnelDao;
	private ChefLaboDao chefLaboDao;
	private ChefEquipeDao chefEquipeDao;
	private Compte compte = null;
	private ChefLabo chefLabo = null;
	private Personnel personnel = null;
	private Laboratoire laboratoire = null;
	private String nom = null, prenom = null, grade = null, userName = null, password = null, nomLab = null,
			acronymeLab = null;
	private int idHal, idHaLabo;

	public CompteController() {
		super();
		laboratoireDao = new LaboratoireDaoImpl();
		compteDao = new CompteDaoImpl();
		personnelDao = new PersonnelDaoImpl();
		chefLaboDao = new ChefLaboDaoImp();
		chefEquipeDao = new ChefEquipeDaoImp();
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
			case "insc":
				request.getRequestDispatcher(VUEINSC).forward(request, response);
				break;
			case "conn":
				request.getRequestDispatcher(VUECONN).forward(request, response);
				break;
			case "deconn":
				if (session.getAttribute("userSession") != null) {
					session.invalidate();
					response.sendRedirect(this.getServletContext().getContextPath() + "/compte?action=conn");
				}
				break;
			default:
				break;
			}
		} else {
			// erreur 404 not Found
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-256");
		passwordEncryptor.setPlainDigest(true);
		String param = request.getParameter("action");
		HttpSession session = request.getSession();
		if (param != null) {
			switch (param) {
			case "insc":
				idHal = Integer.parseInt(request.getParameter("idHal"));
				nom = request.getParameter("nom");
				prenom = request.getParameter("prenom");
				grade = request.getParameter("grade");
				userName = request.getParameter("userName");
				password = passwordEncryptor.encryptPassword(request.getParameter("password"));
				nomLab = request.getParameter("nomLab");
				acronymeLab = request.getParameter("acronymeLab");
				idHaLabo = Integer.parseInt(request.getParameter("idHaLabo"));
				// add labo
				laboratoire = new Laboratoire(acronymeLab, nomLab);
				laboratoire.setIdHal(idHaLabo);
				laboratoireDao.add(laboratoire);
				// add compte
				compte = new Compte(userName, password, 1);
				compteDao.add(compte);
				// add personnel
				personnel = new Personnel(nom, prenom, grade);
				personnel.setIdHal(idHal);
				personnel.setCompte(compte);
				personnelDao.add(personnel);
				// update compte foreign key (personnel)
				compte.setPersonnel(personnel);
				compteDao.updateIDPersonnel(compte);
				// add chefLabo
				chefLabo = new ChefLabo(personnel.getId(), personnel.getNom(), personnel.getPrenom(),
						personnel.getFonction(), laboratoire);
				chefLaboDao.add(chefLabo);
				// update laboratoire foreign key (chefLabo)
				laboratoire.setChefLabo(chefLabo);
				laboratoireDao.updateIDChefLabo(laboratoire);
				response.sendRedirect(this.getServletContext().getContextPath() + "/compte?action=conn");
				break;
			case "conn":
				userName = request.getParameter("login");
				password = request.getParameter("password");
				if (!userName.trim().isEmpty() && !password.trim().isEmpty()) {
					personnel = personnelDao.getPersonnelWithLoginAndPassword(userName,
							passwordEncryptor.encryptPassword(password));
					if (personnel != null) {
						if (personnel.getCompte().getTypeCompte() == 1) {
							session.setAttribute("userSession", chefLaboDao.getChefLabo(personnel.getId()));
							response.sendRedirect(
									this.getServletContext().getContextPath() + "/chef-labo-dashboard?action=cure");
						} else {
							session.setAttribute("userSession", chefEquipeDao.getChefEquipe(personnel.getId()));
							response.sendRedirect(
									this.getServletContext().getContextPath() + "/chef-equipe-dashboard?action=cure");
						}
					} else {
						request.setAttribute("message", "mot de passe ou login incorrect !");
						request.getRequestDispatcher(VUECONN).forward(request, response);
					}
				} else {
					request.setAttribute("message", "mot de passe ou login incorrect !");
					request.getRequestDispatcher(VUECONN).forward(request, response);
				}
				break;
			default:
				break;
			}
		} else {
			// erreur 404 not Found
		}
	}

}
