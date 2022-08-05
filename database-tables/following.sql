CREATE TABLE `electron`.`following` (
  `follower_id` BIGINT(32) NOT NULL,
  `following_id` BIGINT(32) NOT NULL,
  `date_followed` DATETIME NOT NULL,
  `promo_index` INT NOT NULL DEFAULT 0);
