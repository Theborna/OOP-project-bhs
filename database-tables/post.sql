CREATE TABLE `electron`.`post` (
  `post_id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `post_data` LONGTEXT NOT NULL,
  `post_date` DATETIME NOT NULL,
  `post_sender_id` BIGINT(32) NOT NULL,
  `post_replied_id` BIGINT(32) NULL DEFAULT 0,
  `post_likes_count` INT NOT NULL,
  `post_view_count` INT NOT NULL,
  `post_comments_count` INT NOT NULL,
  `post_type` TINYINT(4) NOT NULL,
  `post_media_id` BIGINT(32) NOT NULL DEFAULT 0,
  PRIMARY KEY (`post_id`));
