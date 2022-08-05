CREATE TABLE `electron`.`chat` (
  `ch_id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `ch_creation_date` DATETIME NOT NULL,
  `ch_member_count` INT NOT NULL,
  `ch_is_visible` TINYINT(4) NOT NULL,
  `ch_owner_id` BIGINT(32) NULL,
  `ch_type` INT NOT NULL,
  `ch_name` VARCHAR(45) NULL,
  `ch_txt_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ch_id`, `ch_txt_id`));
