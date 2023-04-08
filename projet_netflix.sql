-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 08 avr. 2023 à 20:05
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projet_netflix`
--

-- --------------------------------------------------------

--
-- Structure de la table `acteurs`
--

DROP TABLE IF EXISTS `acteurs`;
CREATE TABLE IF NOT EXISTS `acteurs` (
  `PrenomActeur` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `NomActeur` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `DateNaissance` date NOT NULL,
  PRIMARY KEY (`NomActeur`,`DateNaissance`),
  UNIQUE KEY `DateNaissance_idx` (`DateNaissance`) USING BTREE,
  KEY `NomActeur_idx` (`NomActeur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Structure de la table `casting`
--

DROP TABLE IF EXISTS `casting`;
CREATE TABLE IF NOT EXISTS `casting` (
  `NomFilm` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `NomActeur` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `DateNaissance` date NOT NULL,
  PRIMARY KEY (`NomFilm`,`NomActeur`,`DateNaissance`),
  KEY `NomFilm` (`NomFilm`),
  KEY `NomActeur` (`NomActeur`),
  KEY `DateNaissance` (`DateNaissance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Structure de la table `categorisation`
--

DROP TABLE IF EXISTS `categorisation`;
CREATE TABLE IF NOT EXISTS `categorisation` (
  `NomFilm` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `Categorie` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`NomFilm`,`Categorie`),
  KEY `NomFilm` (`NomFilm`,`Categorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Structure de la table `films`
--

DROP TABLE IF EXISTS `films`;
CREATE TABLE IF NOT EXISTS `films` (
  `NomFilm` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `Duree` int NOT NULL,
  `Lien` longtext COLLATE utf8mb4_bin NOT NULL,
  `Synopsis` longtext COLLATE utf8mb4_bin NOT NULL,
  `NomRealisateur` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `PrenomRealisateur` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `Annee` date NOT NULL,
  PRIMARY KEY (`NomFilm`),
  KEY `NomFilm_idx` (`NomFilm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Structure de la table `historique`
--

DROP TABLE IF EXISTS `historique`;
CREATE TABLE IF NOT EXISTS `historique` (
  `Pseudo` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `NomFilm` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `TimecodePauseDernierVisionnage` int NOT NULL,
  `FilmDejaVu` tinyint(1) NOT NULL,
  PRIMARY KEY (`NomFilm`,`Pseudo`),
  KEY `Contrainte_Pseudo` (`Pseudo`),
  KEY `NomFilm` (`NomFilm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Structure de la table `listelecture`
--

DROP TABLE IF EXISTS `listelecture`;
CREATE TABLE IF NOT EXISTS `listelecture` (
  `Pseudo` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `NomFilm` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`Pseudo`,`NomFilm`),
  KEY `Pseudo` (`Pseudo`,`NomFilm`),
  KEY `Contrainte_NomFilm3` (`NomFilm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `Pseudo` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `MotDePasse` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `DroitsAdministrateur` tinyint(1) NOT NULL,
  PRIMARY KEY (`Pseudo`),
  KEY `Pseudo_idx` (`Pseudo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `casting`
--
ALTER TABLE `casting`
  ADD CONSTRAINT `Contrainte_DateNaissance` FOREIGN KEY (`DateNaissance`) REFERENCES `acteurs` (`DateNaissance`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Contrainte_NomActeur` FOREIGN KEY (`NomActeur`) REFERENCES `acteurs` (`NomActeur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Contrainte_NomFilm` FOREIGN KEY (`NomFilm`) REFERENCES `films` (`NomFilm`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `historique`
--
ALTER TABLE `historique`
  ADD CONSTRAINT `Contrainte_NomFilm2` FOREIGN KEY (`NomFilm`) REFERENCES `films` (`NomFilm`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Contrainte_Pseudo` FOREIGN KEY (`Pseudo`) REFERENCES `utilisateur` (`Pseudo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `listelecture`
--
ALTER TABLE `listelecture`
  ADD CONSTRAINT `Contrainte_NomFilm3` FOREIGN KEY (`NomFilm`) REFERENCES `films` (`NomFilm`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Contrainte_Pseudo2` FOREIGN KEY (`Pseudo`) REFERENCES `utilisateur` (`Pseudo`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
