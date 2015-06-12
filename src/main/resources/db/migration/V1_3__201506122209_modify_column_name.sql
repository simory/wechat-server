ALTER TABLE `xiaohei-wechat`.`XH_FOLLOWER`
CHANGE COLUMN `subscribe_time` `create_time` DATETIME NULL DEFAULT NULL ,
CHANGE COLUMN `nick_name` `user_name` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `sex` `user_sex` INT(1) NULL DEFAULT NULL ,
CHANGE COLUMN `language` `user_language` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `city` `user_city` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `province` `user_province` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `country` `user_country` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `head_img_url` `user_head_img_url` VARCHAR(512) NULL DEFAULT NULL ,
CHANGE COLUMN `union_id` `user_union_id` VARCHAR(128) NULL DEFAULT NULL ,
CHANGE COLUMN `remark` `user_remark` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `group_id` `user_group_id` VARCHAR(45) NULL DEFAULT NULL ;
