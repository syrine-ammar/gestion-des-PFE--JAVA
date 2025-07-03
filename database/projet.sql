drop database Gestionpfe;
CREATE DATABASE IF NOT EXISTS GestionPfe;
USE GestionPfe;
CREATE TABLE IF NOT EXISTS `gestionpfe`.`encadreur_de_stage` (
  `cin` INT NOT NULL PRIMARY KEY,
  `nom` VARCHAR(255) NULL DEFAULT NULL,
  `prenom` VARCHAR(255) NULL DEFAULT NULL,
  `adresse` VARCHAR(255) NULL DEFAULT NULL,
  `entreprise` VARCHAR(255) NULL DEFAULT NULL,
  `duree_stage` DATETIME NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `gestionpfe`.`enseignant` (
  `cin` INT NOT NULL PRIMARY KEY,
  `nom` VARCHAR(255) NULL DEFAULT NULL,
  `prenom` VARCHAR(255) NULL DEFAULT NULL,
  `adresse` VARCHAR(255) NULL DEFAULT NULL,
  `specialite` VARCHAR(255) NULL DEFAULT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `gestionpfe`.`president` (
  `cin_pres` INT NOT NULL PRIMARY KEY,
  `nom` VARCHAR(255) NULL DEFAULT NULL,
  `prenom` VARCHAR(255) NULL DEFAULT NULL,
  `adresse` VARCHAR(255) NULL DEFAULT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `gestionpfe`.`examinateur` (
  `cin` INT NOT NULL PRIMARY KEY,
  `nom` VARCHAR(255) NULL DEFAULT NULL,
  `prenom` VARCHAR(255) NULL DEFAULT NULL,
  `adresse` VARCHAR(255) NULL DEFAULT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `gestionpfe`.`rapporteur` (
  `cin_rapp` INT NOT NULL PRIMARY KEY,
  `nom` VARCHAR(255) NULL DEFAULT NULL,
  `prenom` VARCHAR(255) NULL DEFAULT NULL,
  `adresse` VARCHAR(255) NULL DEFAULT NULL,
  `rapporteurcol` VARCHAR(45) NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `gestionpfe`.`grpjury` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `specialite` VARCHAR(255) NULL DEFAULT NULL,
  `president_cin_pres` INT NOT NULL,
  `examinateur_cin` INT NOT NULL,
  `rapporteur_cin_rapp` INT NOT NULL,
  CONSTRAINT `fk_grpjury_president1`
    FOREIGN KEY (`president_cin_pres`)
    REFERENCES `gestionpfe`.`president` (`cin_pres`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grpjury_examinateur1`
    FOREIGN KEY (`examinateur_cin`)
    REFERENCES `gestionpfe`.`examinateur` (`cin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grpjury_rapporteur1`
    FOREIGN KEY (`rapporteur_cin_rapp`)
    REFERENCES `gestionpfe`.`rapporteur` (`cin_rapp`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `gestionpfe`.`personneladmin` (
  `cin_adm` INT NOT NULL,
  `nom` VARCHAR(255) NULL DEFAULT NULL,
  `prenom` VARCHAR(255) NULL DEFAULT NULL,
  `adresse` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`cin_adm`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `gestionpfe`.`projetpfe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dateProjet` DATE NULL DEFAULT NULL,
  `heure` INT NULL DEFAULT NULL,
  `type` VARCHAR(255) NULL DEFAULT NULL,
  `titre` VARCHAR(255) NULL DEFAULT NULL,
  `valide` TINYINT(1) NULL DEFAULT NULL,
  `binome` TINYINT(1) NULL DEFAULT NULL,
  `grpjury_id` INT NOT NULL,
  `personneladmin_cin_adm` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_projetpfe_grpjury1_idx` (`grpjury_id` ASC),
  INDEX `fk_projetpfe_personneladmin1_idx` (`personneladmin_cin_adm` ASC),
  CONSTRAINT `fk_projetpfe_grpjury1`
    FOREIGN KEY (`grpjury_id`)
    REFERENCES `gestionpfe`.`grpjury` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_projetpfe_personneladmin1`
    FOREIGN KEY (`personneladmin_cin_adm`)
    REFERENCES `gestionpfe`.`personneladmin` (`cin_adm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `gestionpfe`.`personne_invitee` (
  `cin` INT NOT NULL,
  `nom` VARCHAR(255) NULL DEFAULT NULL,
  `prenom` VARCHAR(255) NULL DEFAULT NULL,
  `adresse` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`cin`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `gestionpfe`.`Assistance_au_pfe` (
  `projetPFE_id` INT NOT NULL,
  `personne_cin` INT NOT NULL,
  `salle_id` INT NOT NULL,
  `date_pfe` DATE NOT NULL,
  PRIMARY KEY (`projetPFE_id`, `personne_cin`, `salle_id`, `date_pfe`),
  INDEX `personne_cin_idx` (`personne_cin` ASC),
  CONSTRAINT `fk_Assistance_au_pfe_projetPFE1`
    FOREIGN KEY (`projetPFE_id`)
    REFERENCES `gestionpfe`.`projetpfe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Assistance_au_pfe_personne_invitee1`
    FOREIGN KEY (`personne_cin`)
    REFERENCES `gestionpfe`.`personne_invitee` (`cin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `gestionpfe`.`encadreur` (
  `cin` INT NOT NULL,
  `nom` VARCHAR(255) NULL DEFAULT NULL,
  `prenom` VARCHAR(255) NULL DEFAULT NULL,
  `adresse` VARCHAR(255) NULL DEFAULT NULL,
  `entreprise` VARCHAR(255) NULL DEFAULT NULL,
  `grpjury_id` INT NOT NULL,
  PRIMARY KEY (`cin`, `grpjury_id`),
  INDEX `fk_encadreur_grpjury1_idx` (`grpjury_id` ASC),
  CONSTRAINT `fk_encadreur_grpjury1`
    FOREIGN KEY (`grpjury_id`)
    REFERENCES `gestionpfe`.`grpjury` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
ALTER TABLE `gestionpfe`.`encadreur` DROP FOREIGN KEY `fk_encadreur_grpjury1`;
ALTER TABLE `gestionpfe`.`grpjury` CHANGE COLUMN `id` `id` INT NOT NULL;
ALTER TABLE `gestionpfe`.`grpjury` DROP PRIMARY KEY;
ALTER TABLE `gestionpfe`.`grpjury` ADD PRIMARY KEY (`id`, `president_cin_pres`, `examinateur_cin`, `rapporteur_cin_rapp`);

ALTER TABLE `gestionpfe`.`grpjury` CHANGE COLUMN `id` `id` INT NOT NULL;
ALTER TABLE `gestionpfe`.`grpjury` DROP PRIMARY KEY;
ALTER TABLE `gestionpfe`.`grpjury` ADD PRIMARY KEY (`id`, `president_cin_pres`, `examinateur_cin`, `rapporteur_cin_rapp`);
ALTER TABLE `gestionpfe`.`encadreur` ADD CONSTRAINT `fk_encadreur_grpjury1` FOREIGN KEY (`grpjury_id`) REFERENCES `grpjury`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `gestionpfe`.`projetpfe` ADD CONSTRAINT `fk_projetpfe_grpjury1` FOREIGN KEY (`grpjury_id`) REFERENCES `grpjury`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

-- INSERT statements for the `encadreur_de_stage` table
INSERT INTO `encadreur_de_stage` (`cin`, `nom`, `prenom`, `adresse`, `entreprise`)
VALUES (12345678, 'Anderson', 'David', '777 Oak St', 'Tech Innovations'),
       (23456789, 'Thomas', 'Sophia', '888 Elm St', 'Data Solutions'),
       (34567890, 'Harris', 'Oliver', '999 Maple St', 'Software Co');

-- INSERT statements for the `enseignant` table
INSERT INTO `enseignant` (`cin`, `nom`, `prenom`, `adresse`, `specialite`)
VALUES (45678901, 'Lee', 'Ava', '111 Cedar St', 'Information Technology'),
       (56789012, 'Clark', 'Ethan', '222 Pine St', 'Data Science'),
       (67890123, 'Lewis', 'Isabella', '333 Walnut St', 'Software Engineering');

-- INSERT statements for the `president` table
INSERT INTO `president` (`cin_pres`, `nom`, `prenom`, `adresse`)
VALUES (78901234, 'Miller', 'Mia', '444 Maple St'),
       (89012345, 'Walker', 'Lucas', '555 Oak St'),
       (90123456, 'White', 'Mila', '666 Elm St');

-- INSERT statements for the `examinateur` table
INSERT INTO `examinateur` (`cin`, `nom`, `prenom`, `adresse`)
VALUES (12345670, 'King', 'Charlotte', '777 Cedar St'),
       (23456780, 'Green', 'Mason', '888 Pine St'),
       (34567890, 'Evans', 'Evelyn', '999 Walnut St');

-- INSERT statements for the `rapporteur` table
INSERT INTO `rapporteur` (`cin_rapp`, `nom`, `prenom`, `adresse`, `rapporteurcol`)
VALUES (45678901, 'Allen', 'Logan', '111 Birch St', 'Data Analysis'),
       (56789012, 'Perez', 'Elijah', '222 Cedar St', 'System Design'),
       (67890123, 'Nelson', 'Amelia', '333 Pine St', 'Network Security');

-- INSERT statements for the `personneladmin` table
INSERT INTO `personneladmin` (`cin_adm`, `nom`, `prenom`, `adresse`)
VALUES (23456789, 'Garcia', 'Avery', '444 Cedar St'),
       (34567890, 'Hernandez', 'Olivia', '555 Pine St'),
       (45678901, 'Young', 'Logan', '666 Walnut St');

-- INSERT statements for the `personne_invitee` table
INSERT INTO `personne_invitee` (`cin`, `nom`, `prenom`, `adresse`)
VALUES (44444444, 'Baker', 'Liam', '111 Walnut St'),
       (55555555, 'King', 'Mia', '222 Elm St'),
       (66666666, 'Gonzalez', 'Sophia', '333 Maple St');
       
       -- INSERT statements for the `grpjury` table
INSERT INTO `grpjury` (`specialite`, `president_cin_pres`, `examinateur_cin`, `rapporteur_cin_rapp`)
VALUES ('Computer Science', 78901234, 12345670, 45678901),
       ('Electrical Engineering', 89012345, 23456780, 56789012),
       ('Computer Engineering', 90123456, 34567890, 67890123);

-- INSERT statements for the `projetpfe` table
INSERT INTO `projetpfe` (`dateProjet`, `heure`, `type`, `titre`, `valide`, `binome`, `grpjury_id`, `personneladmin_cin_adm`)
VALUES ('2024-04-06', 10, 'Research', 'Automated System', 1, 0, 1, 34567890),
       ('2024-04-08', 14, 'Development', 'Mobile Application', 1, 1, 2, 45678901),
       ('2024-04-10', 16, 'Research', 'AI in Healthcare', 1, 0, 1, 23456789),
       ('2024-04-12', 10, 'Development', 'E-commerce Platform', 1, 1, 2, 34567890);

-- INSERT statements for the `encadreur` table
INSERT INTO `encadreur` (`cin`, `nom`, `prenom`, `adresse`, `entreprise`, `grpjury_id`)
VALUES (12345678, 'Anderson', 'David', '777 Oak St', 'Tech Innovations', 1),
       (23456789, 'Thomas', 'Sophia', '888 Elm St', 'Data Solutions', 2),
       (34567890, 'Harris', 'Oliver', '999 Maple St', 'Software Co', 3);

-- INSERT statements for the `Assistance_au_pfe` table
INSERT INTO `Assistance_au_pfe` (`projetPFE_id`, `personne_cin`, `salle_id`, `date_pfe`)
VALUES (5, 44444444, 103, '2024-04-14'),
       (6, 55555555, 104, '2024-04-10'),
       (7, 66666666, 105, '2024-04-12');




SELECT * FROM encadreur_de_stage;
SELECT * FROM enseignant;
SELECT * FROM president;
SELECT * FROM examinateur;
SELECT * FROM rapporteur;
SELECT * FROM personneladmin;
SELECT * FROM personne_invitee;
SELECT * FROM Assistance_au_pfe;
SELECT * FROM grpjury;
SELECT * FROM encadreur;
SELECT * FROM projetpfe;

ALTER TABLE `projetpfe`
MODIFY COLUMN `note` INT CHECK (`note` >= 0 AND `note` <= 20);


