--
-- Base de données : `bdLocationVoiture`
--
CREATE DATABASE IF NOT EXISTS `bdLocationVoiture` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `bdLocationVoiture`;

--
-- Structure de la table `clients`
--

 CREATE TABLE `clients` (
  `matricule` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `permis` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `adresse` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `telephone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  CONSTRAINT `clients_matricule_PK` PRIMARY KEY(`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
