Description : On souhaite concevoir un tableau de bord qui va nous permettre de voir une vue globale sur l��valuation d�un laboratoire de recherche ainsi que chacune de ses �quipes en terme de production de la recherche scientifique en fonction du temps.
Pour pourvoir �valuer une structure de recherche, on a utilis� comme source de donn�es qui est le portail HAL qui fournit une API web service, cette derni�re nous permet de r�cup�rer des donn�es sous format JSON en l�interrogeant avec un ensemble de param�tres bien sp�cifi�s.
Pour d�velopper ce tableau de bord, on a utilis� la plateforme JEE, une base de donn�es MySQL (pour g�rer les utilisateurs), du JavaScript pour r�cup�rer les donn�es de l�API HAL, la librairie JavaScript Chart.js pour tracer des courbes et des diagrammes � partir des donn�es JSON, et comme serveur, on a utilise Tomcat.


Fonctionnement : Tout au d�but un chef de laboratoire de recherche commence par cr�er son propre compte et cr�er son laboratoire en fournissant l�identifiant HAL de ces deux derniers, apr�s il cr�e ses propre �quipes et leur affecte des chefs d��quipes (il fournit aussi l�id HAL du chef d��quipe et de son �quipe et cr�e des compte pour chacun des chefs d��quipe). Dans la page d�accueil de l�espace chef de laboratoire, on  expose les publications de l�ann�e en cours de chacune de ses �quipes. Il peut aussi effectuer une recherche sur les ann�es pr�c�dentes en choisissant une p�riode.  Pour le chef d��quipe, il peut acc�der � son compte avec le login et mot de passe donn�e par son chef de laboratoire auparavant. Dans sa page d�accueil, on expose les publications de l�ann�e en cours publie par chacun de ses membre chercheur de son �quipe, apr�s les avoir ajout� � son �quipe en fournissant leur ID HAL.


Exemple essaie : 
-	Chef de laboratoire : 
o	Login : fatah	
o	Mot de passe : 123456
-	Chef d��quipe: 
o	Login : ahcen
o	Mot de passe : 123456


Remarque : les noms de personne pris dans cet exemple ce sont les noms des �tudiants, ils portent un Id HAL d�autre chercheurs (c�est juste un jeu d essaie).



