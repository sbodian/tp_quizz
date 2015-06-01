DELIMITER $$
drop schema if exists quiz$$
create schema quiz character set=utf8 collate=utf8_general_ci$$
use quiz$$

CREATE TABLE IF NOT EXISTS `question` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `question` varchar(50) NOT NULL,
  `id_questionnaire` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkquestion1` (`id_questionnaire`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1
$$

CREATE TABLE IF NOT EXISTS `questionnaire` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1
$$


CREATE TABLE IF NOT EXISTS `reponse` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `reponse` varchar(50) DEFAULT NULL,
  `id_question` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk` (`id_question`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1
$$

ALTER TABLE `question`
  ADD CONSTRAINT `fkquestion1` FOREIGN KEY (`id_questionnaire`) 
  REFERENCES `questionnaire` (`id`)
$$

ALTER TABLE `reponse`
  ADD CONSTRAINT `fk` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`)
$$


DROP PROCEDURE IF EXISTS quiz_reset
$$
CREATE DEFINER=`root`@`localhost` PROCEDURE quiz_reset()
BEGIN
    SET FOREIGN_KEY_CHECKS = 0;
    TRUNCATE TABLE questionnaire;
    TRUNCATE TABLE question;
    TRUNCATE TABLE option;
    SET FOREIGN_KEY_CHECKS = 1;

    INSERT INTO `questionnaire` (`id`, `libelle`) VALUES
    (1, 'culture generale'),
    (2, 'Les scandales'),
    (3, 'Les surnoms des sportifs'),
    (4, 'Quiz 4 sur les surnoms des sportifs');


    INSERT INTO `question` (`id`, `question`, `id_questionnaire`) VALUES
    (61, 'A quel ecrivaint doit on le personnage de Boule de', 1),
    (62, 'A quel pape a succédé Jean-Paul II ?', 1),
    (63, 'Quel nom rime avec Paul-Loup Sulitzer dans Foule s', 1),
    (64, 'De quel pays Tirana est-elle la capitale ?', 1),
    (65, 'Quel est l''impératif du verbe peindre à la 2e pers', 1),
    (66, 'De quel groupe Jim Morrison était-il le chanteur ?', 1),
    (67, 'Qui est le père de la fille de Vincent Cassel ?', 1),
    (68, 'Quel nom porte le bateau dont Théodore Géricault p', 1),
    (69, 'Quel chanteur a longtemps formé un duo avec Art Ga', 1),
    (70, 'Si ce n''est pas un fruit, qu''est-ce qu''un kiwi ?', 1),
    (71, '', 2),
    (72, 'Quel journal a révélé l’affaire du Watergate ?', 2),
    (73, 'Quel ancien premier ministre a été mis en cause da', 2),
    (74, 'Quel tableau de Géricault fit scandale sous la Res', 2),
    (75, 'Quel docteur fut mis en cause dans l’affaire de do', 2),
    (76, 'Pour quelle marque un détournement de La Cène fit-', 2),
    (77, 'Dans quel hôtel, l''affaire Dominique Strauss Kahn ', 2),
    (78, 'Quel entrepreneur est à l’origine du scandale de P', 2),
    (79, 'Quel président français a été impliqué dans l’Affa', 2),
    (80, ' Dans quel pays s’est déroulée l’affaire du bus de', 2),
    (81, 'Quel est le nom du laboratoire pharmaceutique à l’', 2),
    (82, 'Quel sportif était surnommé Boum Boum ?', 3),
    (83, 'Quel sportif était surnommé le Cannibale ?', 3),
    (84, 'Quel footballeur était surnommé le King ?', 3),
    (85, 'Quel sportif était surnommé Guga ?', 3),
    (86, 'Quel sportif était surnommé l’Intello ?', 3),
    (87, 'Quel sportif était surnommé The Juice ?', 3),
    (88, 'Quel sportif était surnommé Kid Dynamite ?', 3),
    (89, 'Quel sportif était surnommé Kaiser Franz ?', 3),
    (90, 'Quel sportif était surnommé le Kid de Las Vegas ?', 4),
    (91, 'Quel basketteur était surnommé Le Facteur ?', 4);

    --
-- Contenu de la table `option`
--

INSERT INTO `option` (`id`, `libelle`, `correct`, `idquestion`) VALUES
(1, 'Victor Hugo', 0, 61),
(2, 'Honore de Balzac', 0, 61),
(3, 'Voltaire', 0, 61),
(4, 'Saint Pierre', 0, 62),
(5, 'Saint sixte 1er', 0, 62),
(6, 'Saint Denys', 0, 62),
(7, 'Bonnie Tayler', 0, 63),
(8, 'Mylene Farmer', 0, 63),
(9, 'Donna Summer', 0, 63),
(10, 'Tchad', 0, 64),
(11, 'Russie', 0, 64),
(12, 'Yougoslavie', 0, 64),
(13, 'Peindrez', 0, 65),
(14, 'Peindriez', 0, 65),
(15, 'Paintrez', 0, 65),
(16, 'Aerosmith', 0, 66),
(17, 'R.E.M', 0, 66),
(18, 'Buju Banton', 0, 66),
(19, 'Monica', 0, 67),
(20, 'Toi', 0, 67),
(21, 'Le cheval blanc d Henry IV', 0, 67),
(22, 'Le Goeland', 0, 68),
(23, 'Le Rafiot', 0, 68),
(24, 'La chouette', 0, 68),
(25, 'Eric Clapton', 0, 69),
(26, 'George Benson', 0, 69),
(27, 'Youssou Ndour', 0, 69),
(28, 'Une fleure', 0, 70),
(29, 'Un legume', 0, 70),
(30, 'Une methode abstract', 0, 70),
(31, 'Le New York Times', 0, 72),
(32, 'Le figaro', 0, 72),
(33, 'L''Humanite', 0, 72),
(34, 'Apparu Benoist', 0, 73),
(35, 'Baumel Laurent', 0, 73),
(36, 'Kert Christian', 0, 73),
(37, 'Guy de Maupassant', 1, 61),
(38, 'Jean-Paul Ier', 1, 62),
(39, 'Claudia Schiffer', 1, 63),
(40, 'L''Albanie', 1, 64),
(41, 'Peignez', 1, 65),
(42, 'The Doors', 1, 66),
(43, 'Vincent Cassel', 1, 67),
(44, 'La Méduse', 1, 68),
(45, 'Paul Simon', 1, 69),
(46, 'Un oiseau', 1, 70),
(47, 'Le Washington Post', 1, 72),
(48, 'Laurent Fabius', 1, 73),
(49, 'Le Radeau de la Méduse', 1, 74),
(50, 'Dr Fuentes', 1, 75),
(51, 'Marithé et François Girbaud', 1, 76),
(52, 'Au Sofitel', 1, 77),
(53, 'Ferdinand de Lesseps', 1, 78),
(54, 'Valéry Giscard d’Estaing', 1, 79),
(55, 'Afrique du Sud', 1, 80),
(56, 'Servier', 1, 81),
(57, 'Boris Becker', 1, 82),
(58, 'Eddy Merx', 1, 83),
(59, 'Éric Cantona', 1, 84),
(60, 'Gustavo Kuerten', 1, 85),
(61, 'Laurent Fignon', 1, 86),
(62, 'O.J. Simpson', 1, 87),
(63, 'Mike Tyson', 1, 88),
(64, 'Franz Beckenbauer', 1, 89),
(65, 'André Agassis', 1, 90),
(66, 'Karl Malone', 1, 91);

-- --------------------------------------------------------



END
$$

CALL quiz_reset$$