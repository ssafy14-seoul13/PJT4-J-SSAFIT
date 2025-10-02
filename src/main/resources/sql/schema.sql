CREATE SCHEMA IF NOT EXISTS ssafit;

USE ssafit;


CREATE TABLE IF NOT EXISTS user (
	`id` VARCHAR(32) NOT NULL PRIMARY KEY,
    `user_id` VARCHAR(20) NOT NULL,
    `user_pwd` VARCHAR(20) NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `email` VARCHAR(255) NOT NULL
);
-- DESC user;

CREATE TABLE IF NOT EXISTS video (
    `id` VARCHAR(32) NOT NULL PRIMARY KEY,
    `title` VARCHAR(100) NOT NULL,
    `channel_name` VARCHAR(100) NOT NULL,
    `author` VARCHAR(20) NOT NULL,
    `url` VARCHAR(255) NOT NULL,
    `length` INT,
    `created_at` TIMESTAMP DEFAULT NOW(),
    `updated_at` TIMESTAMP DEFAULT NOW() ON UPDATE NOW(),
    `view_cnt` INT DEFAULT 0,
    FOREIGN KEY (`author`)
        REFERENCES user (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
-- DESC video;

CREATE TABLE IF NOT EXISTS review (
	`id` VARCHAR(32) NOT NULL PRIMARY KEY,
    `user_id` VARCHAR(20) NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `video_id` VARCHAR(32) NOT NULL,
    `content` TEXT NOT NULL,
    `created_at` TIMESTAMP DEFAULT now(),
    FOREIGN KEY (`user_id`) REFERENCES user(`id`)
    ON DELETE CASCADE
	ON UPDATE CASCADE,
    FOREIGN KEY (`video_id`) REFERENCES video(`id`)
    ON DELETE CASCADE
	ON UPDATE CASCADE
);
-- DESC review;