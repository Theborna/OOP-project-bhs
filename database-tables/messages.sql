CREATE TABLE `electron`.`messages` (
  `msg_id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `msg_chat_id` BIGINT(32) NOT NULL,
  `msg_sender_id` BIGINT(32) NOT NULL,
  `msg_replied_id` BIGINT(32) NULL DEFAULT 0,
  `msg_enc` LONGTEXT NOT NULL,
  `msg_date` DATETIME NOT NULL,
  `msg_sender_priv_key_enc` VARCHAR(45) NOT NULL,
  `msg_forwarded_from` BIGINT(32) NULL DEFAULT 0,
  `msg_media_id` BIGINT(32) NOT NULL DEFAULT 0,
  PRIMARY KEY (`msg_id`));
