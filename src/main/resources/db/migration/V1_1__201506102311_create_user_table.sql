CREATE TABLE `xiaohei-wechat`.`XH_FOLLOWER` (
  `id` INT NOT NULL,
  `public_id` VARCHAR(45) NULL,
  `subscribe_time` DATETIME NULL,
  `nick_name` VARCHAR(45) NULL,
  `sex` INT(1) NULL,
  `language` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `province` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  `head_img_url` VARCHAR(512) NULL,
  `union_id` VARCHAR(128) NULL,
  `remark` VARCHAR(45) NULL,
  `group_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
