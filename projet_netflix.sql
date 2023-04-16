-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 10 avr. 2023 à 17:38
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
  `PrenomActeur` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NomActeur` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DateNaissance` date NOT NULL,
  PRIMARY KEY (`NomActeur`,`DateNaissance`),
  UNIQUE KEY `DateNaissance_idx` (`DateNaissance`) USING BTREE,
  KEY `NomActeur_idx` (`NomActeur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `casting`
--

DROP TABLE IF EXISTS `casting`;
CREATE TABLE IF NOT EXISTS `casting` (
  `NomFilm` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NomActeur` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DateNaissance` date NOT NULL,
  PRIMARY KEY (`NomFilm`,`NomActeur`,`DateNaissance`),
  KEY `NomFilm` (`NomFilm`),
  KEY `NomActeur` (`NomActeur`),
  KEY `DateNaissance` (`DateNaissance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `categorisation`
--

DROP TABLE IF EXISTS `categorisation`;
CREATE TABLE IF NOT EXISTS `categorisation` (
  `NomFilm` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Categorie` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`NomFilm`,`Categorie`),
  KEY `NomFilm` (`NomFilm`,`Categorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categorisation`
--

INSERT INTO `categorisation` (`NomFilm`, `Categorie`) VALUES
('', ''),
('', '---Ajouter Catégorie---'),
('', 'Action'),
('', 'Animation'),
('', 'Aventure'),
('', 'Comédie'),
('', 'Drame'),
('', 'Film Policier'),
('', 'Guerre'),
('', 'Horreur'),
('', 'Musical'),
('', 'Science-fiction'),
('', 'Western'),
('Aquaman', 'Action'),
('Avatar', 'Science-fiction'),
('Avengers: Endgame', 'Science-fiction'),
('Blade Runner', 'Science-fiction'),
('Fight Club', 'Action'),
('Get Out', 'Horreur'),
('Inception', 'Science-fiction'),
('Interstellar', 'Science-fiction'),
('John Wick', 'Action'),
('Le loup de Wall Street', 'Action'),
('Le Parrain', 'Drame'),
('Les insurgés', 'Guerre'),
('Pulp Fiction', 'Action'),
('Red Army', 'Guerre'),
('Seven', 'Film Policier'),
('Shrek 2', 'Animation'),
('Tenet', 'Science-fiction'),
('Usual SUspects', 'Film Policier');

-- --------------------------------------------------------

--
-- Structure de la table `films`
--

DROP TABLE IF EXISTS `films`;
CREATE TABLE IF NOT EXISTS `films` (
  `NomFilm` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Duree` int NOT NULL,
  `Lien` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Synopsis` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NomRealisateur` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PrenomRealisateur` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Annee` int NOT NULL,
  `Miniature` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`NomFilm`),
  KEY `NomFilm_idx` (`NomFilm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `films`
--

INSERT INTO `films` (`NomFilm`, `Duree`, `Lien`, `Synopsis`, `NomRealisateur`, `PrenomRealisateur`, `Annee`, `Miniature`) VALUES
('Aquaman', 143, 'https://www.youtube.com/watch?v=ivvOKs7B-Vw', 'Jadis l\'accueil de la civilisation ancienne la plus avancée de la Terre, Atlantide est maintenant un royaume sous-marin gouverné par le Roi Orm. Aidé par une armée, Orm désire conquérir les autres civilisations océaniques -- et ensuite le monde terrain. Aquaman, le demi-frère d\'Orm et le véritable héritier du trône, se tient sur son chemin. Avec l\'aide de Vulko, Aquaman doit récupérer le légendaire trident d\'Atlan et embrasser son destin de protecteur des profondeurs.', 'Wan', 'James', 2018, 'https://thepowerzone.files.wordpress.com/2019/01/aquaman-affiche-film.jpg'),
('Avatar', 162, 'https://www.youtube.com/watch?v=O1CzgULNRGs', 'Sur le monde extraterrestre luxuriant de Pandora vivent les Na\'vi, des êtres qui semblent primitifs, mais qui sont très évolués. Jake Sully, un ancien Marine paralysé, redevient mobile grâce à un tel Avatar et tombe amoureux d\'une femme Na\'vi. Alors qu\'un lien avec elle grandit, il est entraîné dans une bataille pour la survie de son monde.', 'Cameron', 'James', 2009, 'https://fr.web.img4.acsta.net/pictures/22/11/02/14/49/4565071.jpg'),
('Avengers: Endgame', 182, 'https://www.youtube.com/watch?v=wV-Q0o2OQjQ', 'Le Titan Thanos, ayant réussi à s\'approprier les six Pierres d\'Infinité et à les réunir sur le Gantelet doré, a pu réaliser son objectif de pulvériser la moitié de la population de l\'Univers. Cinq ans plus tard, Scott Lang, alias Ant-Man, parvient à s\'échapper de la dimension subatomique où il était coincé. Il propose aux Avengers une solution pour faire revenir à la vie tous les êtres disparus, dont leurs alliés et coéquipiers : récupérer les Pierres d\'Infinité dans le passé.', 'Russo', 'Anthony', 2019, 'https://fr.web.img2.acsta.net/pictures/19/04/04/09/04/0472053.jpg'),
('Blade Runner', 117, 'https://www.youtube.com/watch?v=O4C5cwSbXZ8', 'En 2049, la société est fragilisée par les nombreuses tensions entre les humains et leurs esclaves créés par bio-ingénierie. L\'officier K est un blade runner: il fait partie d\'une force d\'intervention d\'élite chargée de trouver et d\'éliminer ceux qui n\'obéissent pas aux ordres des humains. Lorsqu\'il découvre un secret enfoui depuis longtemps et capable de changer le monde, les plus hautes instances décident que c\'est à son tour d\'être traqué et éliminé. Il doit trouver Rick Deckard.', 'Scott', 'Ridley', 1982, 'https://fr.web.img4.acsta.net/pictures/17/08/24/15/18/597734.jpg'),
('Fight Club', 139, 'https://www.youtube.com/watch?v=c_Sf-XY3t-I', 'Pourvu d\'une situation des plus enviable, un jeune homme à bout de nerfs retrouve un équilibre relatif en compagnie de Marla, rencontrée dans un groupe d\'entraide. Il fait également la connaissance de Tyler Durden, personnage enthousiaste et charismatique. Ensemble, ils fondent le Fight Club, où ils organisent des combats clandestins et violents, destinés à évacuer l\'énergie négative de chacun.', 'Fincher', 'David', 1999, 'https://www.ecranlarge.com/media/cache/1600x1200/uploads/image/001/079/fight-club-affiche-1079106.jpg'),
('Get Out', 104, 'https://www.youtube.com/watch?v=tygbmB7TrsA', 'Maintenant que Chris et sa copine Rose vont rencontrer leurs parents respectifs, elle l\'invite dans la résidence secondaire de sa famille pour un week-end. D\'abord Chris comprend que le comportement un peu étrange de la famille de Rose est lié au fait qu\'il est noir et qu\'elle est blanche. Cependant, il découvre que la vérité est bien plus dérangeante.', 'Peele', 'Jordan', 2017, 'https://fr.web.img4.acsta.net/pictures/17/03/16/13/25/487327.jpg'),
('Inception', 148, 'https://www.youtube.com/watch?v=CPTIgILtna8', 'Dom Cobb est un voleur expérimenté dans l\'art périlleux de `l\'extraction\' : sa spécialité consiste à s\'approprier les secrets les plus précieux d\'un individu, enfouis au plus profond de son subconscient, pendant qu\'il rêve et que son esprit est particulièrement vulnérable. Très recherché pour ses talents dans l\'univers trouble de l\'espionnage industriel, Cobb est aussi devenu un fugitif traqué dans le monde entier. Cependant, une ultime mission pourrait lui permettre de retrouver sa vie d\'avant.', 'Nolan', 'Christopher', 2010, 'https://fr.web.img2.acsta.net/medias/nmedia/18/72/34/14/19476654.jpg'),
('Interstellar', 169, 'https://www.youtube.com/watch?v=0rDczIsHJn4', 'Dans un proche futur, la Terre est devenue hostile pour l\'homme. Les tempêtes de sable sont fréquentes et il n\'y a plus que le maïs qui peut être cultivé, en raison d\'un sol trop aride. Cooper est un pilote, recyclé en agriculteur, qui vit avec son fils et sa fille dans la ferme familiale. Lorsqu\'une force qu\'il ne peut expliquer lui indique les coordonnées d\'une division secrète de la NASA, il est alors embarqué dans une expédition pour sauver l\'humanité.', 'Nolan', 'Christopher', 2014, 'https://fr.web.img6.acsta.net/pictures/14/09/24/12/08/158828.jpg'),
('John Wick', 96, 'https://www.youtube.com/watch?v=pWK5crMuhHY', 'Ce qui aurait pu être le banal vol d\'une voiture de collection se transforme en une chasse à l\'homme sans merci entre un légendaire ex-tueur à gages et le fils d\'un des plus grands pontes de la mafia. Entre un homme qui se croit au-dessus des lois et un autre à qui on a pris les deux seuls souvenirs qui le rendaient encore humain, l\'affrontement sera de haute volée. Personne n\'est décidé à rendre les armes et la guerre sera totale.', 'Stahelski', 'Chad', 2014, 'https://fr.web.img4.acsta.net/pictures/14/10/08/11/49/255061.jpg'),
('Le loup de Wall Street', 179, 'https://www.youtube.com/watch?v=GT9UfSqBz9o', 'Sa licence de courtier en poche, et les narines déjà pleines de cocaïne, Jordan Belfort est prêt à conquérir Wall Street. Ce jour d\'octobre, un krach, le plus important depuis 1929, vient piétiner ses rêves de grandeur. C\'est finalement à Long Island qu\'il échoue et qu\'il monte sa propre affaire de courtage. Son créneau : le hors-cote. Sa méthode : l\'arnaque. Son équipe : des vendeurs ou des petits trafiquants.', 'Scorsese', 'Martin', 2013, 'https://fr.web.img6.acsta.net/pictures/210/604/21060483_20131125114549726.jpg'),
('Le Parrain', 175, 'https://www.youtube.com/watch?v=bmtuIhesQWA', 'En 1945, à New York, les Corleone sont une des 5 familles de la mafia. Don Vito Corleone, `parrain\' de cette famille, marie sa fille à un bookmaker. Sollozzo, `parrain\' de la famille Tattaglia, propose à Don Vito une association dans le trafic de drogue, mais celui-ci refuse. Sonny, un de ses fils, y est quant à lui favorable. Afin de traiter avec Sonny, Sollozzo tente de faire tuer Don Vito, mais celui-ci en réchappe.', 'Coppola', 'Francis Ford', 1972, 'https://img-4.linternaute.com/U33Gfhf2Daop0ZFmMqbgjsFlbnc=/1500x/smart/85466d7eebed4f248a6c2d4c7f9d436a/ccmcms-linternaute/11411700.jpg'),
('Les insurgés', 137, 'https://www.youtube.com/watch?v=z0HnXYZJrII', 'En 1941, les armées d\'Hitler envahissent l\'Europe. Leur implacable progression coûte la vie à des millions de juifs. Pour trois hommes, cette tragédie marque le début d\'une guerre dans la guerre. Lorsque leur petit village d\'Europe de l\'Est est envahi, les frères Bielski se réfugient dans une profonde forêt qu\'ils connaissent depuis leur enfance. Ils se contentent d\'abord de survivre mais la rumeur de leur exploit se répand et d\'autres les rejoignent, hommes et femmes, jeunes et moins jeunes, prêts à tout risquer pour rester vivants et libres. Peu à peu, les trois frères vont recueillir des centaines de pourchassés et contrecarrer les plans de leurs redoutables attaquants. Face à l\'adversité, au nom de ceux qu\'ils ont perdu, ils vont sauver plus d\'un millier de vies...', 'Zwick', 'Edward', 2008, 'https://fr.web.img5.acsta.net/medias/nmedia/18/66/70/74/19025982.jpg'),
('Pulp Fiction', 165, 'https://www.youtube.com/watch?v=gjAJnzTPltc', 'L\'odyssée sanglante et burlesque de petits malfrats dans la jungle de Hollywood à travers trois histoires qui s\'entremêlent. Dans un restaurant, un couple de jeunes braqueurs, Pumpkin et Yolanda, discutent des risques que comporte leur activité. Deux truands, Jules Winnfield et son ami Vincent Vega, qui revient d\'Amsterdam, ont pour mission de récupérer une mallette au contenu mystérieux et de la rapporter à Marsellus Wallace.', 'Tarantino', 'Quentin', 1994, 'https://fr.web.img4.acsta.net/medias/nmedia/18/36/02/52/18846059.jpg'),
('Red Army', 85, 'https://www.youtube.com/watch?v=-KeelHPHV_M', 'Le documentaire retrace le destin croisé de l\'URSS et de l\'équipe de hockey sur glace surnommée \"l\'Armée rouge:\" une dynastie unique dans l\'histoire du sport. L\'ancien capitaine de l\'équipe Slava Fetisov revient sur son parcours hors du commun: d\'abord adulé en héros national, il sera bientôt condamné comme ennemi politique. Le film raconte l\'histoire extraordinaire de la guerre froide menée sur la glace, et la vie d\'un homme qui a tenu tête au système soviétique.', 'Polsky', 'Gabe', 2014, 'https://fr.web.img6.acsta.net/pictures/14/11/18/12/20/534408.jpg'),
('Seven', 127, 'https://www.youtube.com/watch?v=BLY4treE0XU', 'Peu avant sa retraite, l\'inspecteur William Somerset, un flic désabusé, est chargé de faire équipe avec un jeune idéaliste, David Mills. Ils enquêtent tout d\'abord sur le meurtre d\'un homme obèse que son assassin a obligé à manger jusqu\'à ce que mort s\'ensuive. L\'enquête vient à peine de commencer qu\'un deuxième crime, tout aussi macabre, est commis, puis un troisième. Petit à petit, les deux policiers font le lien entre tous ces assassinats.', 'Fincher', 'David', 1995, 'https://fr.web.img2.acsta.net/medias/nmedia/18/35/91/33/19255605.jpg'),
('Shrek 2', 93, 'https://www.youtube.com/watch?v=MQ5CP5uouLg', 'Devenus de jeunes mariés, Shrek et Fiona rentrent de leur heureuse lune de miel. Ils sont invités par les parents de Fiona à venir dîner dans leur royaume, à Far Far Away. Cependant, ils ne se doutent pas que leur fille est devenue une ravissante ogresse. Ce mariage met par ailleurs en péril l\'avenir et les projets les plus secrets du Roi.', 'Adamson', 'Andrew', 2004, 'https://www.ecranlarge.com/uploads/image/001/190/2yyp0pqjg8zvqturh1baqu2tixl-437.jpg'),
('Tenet', 150, 'https://www.youtube.com/watch?v=6UG5LJQNjts', 'Muni d\'un seul mot, \"Tenet,\" et décidé à se battre pour sauver le monde, un homme sillonne l\'univers crépusculaire de l\'espionnage international. Sa mission le projettera dans une dimension qui dépasse le temps. Pourtant, il ne s\'agit pas d\'un voyage dans le temps, mais d\'un renversement temporel.', 'Nolan', 'Christopher', 2020, 'https://fr.web.img2.acsta.net/pictures/20/08/03/12/15/2118693.jpg'),
('Usual SUspects', 106, 'https://www.youtube.com/watch?v=T0suzNWrn24', 'Interrogé par la police à la suite de l\'explosion criminelle d\'un cargo, Verbal Kint se met à table : avec quatre autres gangsters, il s\'est vu imposer une mission périlleuse par Keyser Söze, un malfrat craint de tous mais que personne ne connaît. Qui est ce mystérieux commanditaire ? Existe-t-il vraiment ? Bryan Singer nous entraîne dans les méandres d\'un scénario éblouissant, multipliant les fausses pistes jusqu\'au dénouement final.', 'Singer', 'Bryan', 1995, 'https://static.fnac-static.com/multimedia/images_produits/ZoomPE/5/0/8/3700259801805/tsp20130902121857/Usual-suspects.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `historique`
--

DROP TABLE IF EXISTS `historique`;
CREATE TABLE IF NOT EXISTS `historique` (
  `Pseudo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NomFilm` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TimecodePauseDernierVisionnage` int NOT NULL,
  `FilmDejaVu` tinyint(1) NOT NULL,
  PRIMARY KEY (`NomFilm`,`Pseudo`),
  KEY `Contrainte_Pseudo` (`Pseudo`),
  KEY `NomFilm` (`NomFilm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `listelecture`
--

DROP TABLE IF EXISTS `listelecture`;
CREATE TABLE IF NOT EXISTS `listelecture` (
  `Pseudo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NomFilm` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`Pseudo`,`NomFilm`),
  KEY `Pseudo` (`Pseudo`,`NomFilm`),
  KEY `Contrainte_NomFilm3` (`NomFilm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `listelecture`
--

INSERT INTO `listelecture` (`Pseudo`, `NomFilm`) VALUES
('Yassine', 'Avengers: Endgame'),
('Yassine', 'Inception'),
('Yassine', 'Interstellar'),
('Yassine', 'Tenet');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `Pseudo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MotDePasse` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DroitsAdministrateur` tinyint(1) NOT NULL,
  PRIMARY KEY (`Pseudo`),
  KEY `Pseudo_idx` (`Pseudo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`Pseudo`, `MotDePasse`, `DroitsAdministrateur`) VALUES
('Anatole', 'Anatole123', 1),
('Yassine', 'Yass123', 1);

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
-- Contraintes pour la table `categorisation`
--
ALTER TABLE `categorisation`
  ADD CONSTRAINT `Contrainte_NomFilm4` FOREIGN KEY (`NomFilm`) REFERENCES `films` (`NomFilm`) ON DELETE CASCADE ON UPDATE CASCADE;

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
