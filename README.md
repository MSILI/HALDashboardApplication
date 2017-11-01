Description : On souhaite concevoir un tableau de bord qui va nous permettre de voir une vue globale sur l’évaluation d’un laboratoire de recherche ainsi que chacune de ses équipes en terme de production de la recherche scientifique en fonction du temps.
Pour pourvoir évaluer une structure de recherche, on a utilisé comme source de données qui est le portail HAL qui fournit une API web service, cette dernière nous permet de récupérer des données sous format JSON en l’interrogeant avec un ensemble de paramètres bien spécifiés.
Pour développer ce tableau de bord, on a utilisé la plateforme JEE, une base de données MySQL (pour gérer les utilisateurs), du JavaScript pour récupérer les données de l’API HAL, la librairie JavaScript Chart.js pour tracer des courbes et des diagrammes à partir des données JSON, et comme serveur, on a utilise Tomcat.


Fonctionnement : Tout au début un chef de laboratoire de recherche commence par créer son propre compte et créer son laboratoire en fournissant l’identifiant HAL de ces deux derniers, après il crée ses propre équipes et leur affecte des chefs d’équipes (il fournit aussi l’id HAL du chef d’équipe et de son équipe et crée des compte pour chacun des chefs d’équipe). Dans la page d’accueil de l’espace chef de laboratoire, on  expose les publications de l’année en cours de chacune de ses équipes. Il peut aussi effectuer une recherche sur les années précédentes en choisissant une période.  Pour le chef d’équipe, il peut accéder à son compte avec le login et mot de passe donnée par son chef de laboratoire auparavant. Dans sa page d’accueil, on expose les publications de l’année en cours publie par chacun de ses membre chercheur de son équipe, après les avoir ajouté à son équipe en fournissant leur ID HAL.


Exemple essaie : 
-	Chef de laboratoire : 
o	Login : fatah	
o	Mot de passe : 123456
-	Chef d’équipe: 
o	Login : ahcen
o	Mot de passe : 123456


Remarque : les noms de personne pris dans cet exemple ce sont les noms des étudiants, ils portent un Id HAL d’autre chercheurs (c’est juste un jeu d essaie).



