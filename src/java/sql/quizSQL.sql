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
    TRUNCATE TABLE reponse;
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

    INSERT INTO `reponse` (`id`, `reponse`, `id_question`) VALUES
    (11, 'Guy de Maupassant', 61),
    (12, 'Jean-Paul Ier', 62),
    (13, 'Claudia Schiffer', 63),
    (14, 'L''Albanie', 64),
    (15, 'Peignez', 65),
    (16, 'The Doors', 66),
    (17, 'Vincent Cassel', 67),
    (18, 'La Méduse', 68),
    (19, 'Paul Simon', 69),
    (20, 'Un oiseau', 70),
    (21, 'Le Washington Post', 72),
    (22, 'Laurent Fabius', 73),
    (23, 'Le Radeau de la Méduse', 74),
    (24, 'Dr Fuentes', 75),
    (25, 'Marithé et François Girbaud', 76),
    (26, 'Au Sofitel', 77),
    (27, 'Ferdinand de Lesseps', 78),
    (28, 'Valéry Giscard d’Estaing', 79),
    (29, 'Afrique du Sud', 80),
    (30, 'Servier', 81),
    (31, 'Boris Becker', 82),
    (32, 'Eddy Merx', 83),
    (33, 'Éric Cantona', 84),
    (34, 'Gustavo Kuerten', 85),
    (35, 'Laurent Fignon', 86),
    (36, 'O.J. Simpson', 87),
    (37, 'Mike Tyson', 88),
    (38, 'Franz Beckenbauer', 89),
    (39, 'André Agassi', 90),
    (40, 'Karl Malone', 91);
END
$$

CALL quiz_reset$$