-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 12 Juin 2017 à 16:56
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `dashboardhal`
--

-- --------------------------------------------------------

--
-- Structure de la table `chefequipe`
--

CREATE TABLE IF NOT EXISTS `chefequipe` (
  `id` int(11) NOT NULL,
  `equipe` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `equipe` (`equipe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `chefequipe`
--

INSERT INTO `chefequipe` (`id`, `equipe`) VALUES
(14, 7),
(18, 8),
(19, 9),
(20, 10),
(21, 11);

-- --------------------------------------------------------

--
-- Structure de la table `cheflabo`
--

CREATE TABLE IF NOT EXISTS `cheflabo` (
  `id` int(11) NOT NULL,
  `laboratoire` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `laboratoire` (`laboratoire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `cheflabo`
--

INSERT INTO `cheflabo` (`id`, `laboratoire`) VALUES
(12, 7);

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `typeCompte` int(11) NOT NULL,
  `personnel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `personnel` (`personnel`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`id`, `userName`, `password`, `typeCompte`, `personnel`) VALUES
(13, 'fatah', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 1, 12),
(14, 'yanis', 'ei7ED/ihJHxTIwk1X3mKd54ArP9XnGPuw2Nv+ykCwaw=', 2, NULL),
(15, 'yanis', 'ei7ED/ihJHxTIwk1X3mKd54ArP9XnGPuw2Nv+ykCwaw=', 2, 14),
(16, 'ahcen', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 2, 18),
(17, 'lyes', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 2, 19),
(18, 'ghiles', 'w+0WbEXb1QT5HJin7X/kKHr2u06iT/RamPsSeExY4PA=', 2, 20),
(19, 'ahcen', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 2, 21),
(20, 'aa', '7QJFe1xB2WTb0vKmCdY/4bt1KNvlXhq/W1LCSc1zV5c=', 2, 31);

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

CREATE TABLE IF NOT EXISTS `equipe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acronyme` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `idHal` int(11) NOT NULL,
  `laboratoire` int(11) NOT NULL,
  `chefEquipe` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idHal` (`idHal`),
  KEY `chefEquipe` (`laboratoire`),
  KEY `chefEquipe_2` (`chefEquipe`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `equipe`
--

INSERT INTO `equipe` (`id`, `acronyme`, `nom`, `idHal`, `laboratoire`, `chefEquipe`) VALUES
(7, 'CACS_COM', 'Lab-STICC_TB_CACS_COM', 391223, 7, 14),
(8, 'MOM_DIM', 'Lab-STICC_TB_MOM_DIM', 391950, 7, 18),
(9, 'UBS_CACS', 'Lab-STICC_UBS_CACS_MOCS', 389915, 7, 19),
(10, 'CACS_IAS', 'Lab-STICC_TB_CACS_IAS', 390336, 7, 20),
(11, 'UBO_CACS', 'Lab-STICC_UBO_CACS_MOCS', 388505, 7, 21);

-- --------------------------------------------------------

--
-- Structure de la table `laboratoire`
--

CREATE TABLE IF NOT EXISTS `laboratoire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acronyme` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `idHal` int(11) NOT NULL,
  `chefLabo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idHal` (`idHal`),
  UNIQUE KEY `chefLabo` (`chefLabo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `laboratoire`
--

INSERT INTO `laboratoire` (`id`, `acronyme`, `nom`, `idHal`, `chefLabo`) VALUES
(7, 'Lab-STICC', 'Laboratoire des Sciences et Techniques de l''Information, de la Communication et de la Connaissance', 81533, 12);

-- --------------------------------------------------------

--
-- Structure de la table `membre`
--

CREATE TABLE IF NOT EXISTS `membre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=28 ;

--
-- Contenu de la table `membre`
--

INSERT INTO `membre` (`id`) VALUES
(22),
(25),
(26),
(27);

-- --------------------------------------------------------

--
-- Structure de la table `membreequipe`
--

CREATE TABLE IF NOT EXISTS `membreequipe` (
  `equipe` int(11) NOT NULL,
  `membre` int(11) NOT NULL,
  PRIMARY KEY (`equipe`,`membre`),
  KEY `membre` (`membre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `membreequipe`
--

INSERT INTO `membreequipe` (`equipe`, `membre`) VALUES
(11, 22),
(11, 25),
(11, 26),
(11, 27);

-- --------------------------------------------------------

--
-- Structure de la table `personnel`
--

CREATE TABLE IF NOT EXISTS `personnel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `fonction` varchar(255) NOT NULL,
  `idHal` int(11) NOT NULL,
  `compte` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `compte` (`compte`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=32 ;

--
-- Contenu de la table `personnel`
--

INSERT INTO `personnel` (`id`, `nom`, `prenom`, `fonction`, `idHal`, `compte`) VALUES
(12, 'MSILI', 'Fatah', 'Professeur', 123456, 13),
(14, 'REMILA', 'Yanis', 'Professeur', 147258, 15),
(17, 'HADJ SAID', 'Massi', 'M.C.B', 159753, NULL),
(18, 'DJEDDI', 'Ahcen', 'Professeur', 1245, 16),
(19, 'Allache', 'Lyes', 'Professeur', 1236, 17),
(20, 'MSILI', 'Ghiles', 'Professeur', 12345, 18),
(21, 'DJEDDI', 'Ahcen', 'M.C.B', 987456, 19),
(22, 'Boukhobza', 'Jalil', 'M.C.B', 1840, NULL),
(25, 'Frank', 'Singhoff', 'Professeur', 2860, NULL),
(26, 'Rubini', 'Stéphane', 'Professeur', 13231, NULL),
(27, 'Lemarchand', 'Loran', 'M.C.B', 1346, NULL),
(31, 'dqsddqs', 'sqdqsd', 'Professeur', 123456, 20);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `chefequipe`
--
ALTER TABLE `chefequipe`
  ADD CONSTRAINT `fkidEquipe` FOREIGN KEY (`equipe`) REFERENCES `equipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkidPersonnel3` FOREIGN KEY (`id`) REFERENCES `personnel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `cheflabo`
--
ALTER TABLE `cheflabo`
  ADD CONSTRAINT `fkidChefLabo` FOREIGN KEY (`laboratoire`) REFERENCES `laboratoire` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkidPersonnel4` FOREIGN KEY (`id`) REFERENCES `personnel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `personnelFK` FOREIGN KEY (`personnel`) REFERENCES `personnel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD CONSTRAINT `fkChefeq` FOREIGN KEY (`chefEquipe`) REFERENCES `chefequipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkLabo` FOREIGN KEY (`laboratoire`) REFERENCES `laboratoire` (`id`);

--
-- Contraintes pour la table `laboratoire`
--
ALTER TABLE `laboratoire`
  ADD CONSTRAINT `fkidchLab` FOREIGN KEY (`chefLabo`) REFERENCES `cheflabo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `membre`
--
ALTER TABLE `membre`
  ADD CONSTRAINT `fkidPersonnel1` FOREIGN KEY (`id`) REFERENCES `personnel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `membreequipe`
--
ALTER TABLE `membreequipe`
  ADD CONSTRAINT `equipe` FOREIGN KEY (`equipe`) REFERENCES `equipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `membre` FOREIGN KEY (`membre`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `personnel`
--
ALTER TABLE `personnel`
  ADD CONSTRAINT `fkidCompte` FOREIGN KEY (`compte`) REFERENCES `compte` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
