use player;
CREATE TABLE IF NOT EXISTS `player_score` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `player` VARCHAR(255) NOT NULL,
    `score` INTEGER NOT NULL,
    `time` DATETIME NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
    )
    COLLATE='utf8mb4_0900_ai_ci'
    ENGINE=InnoDB
    AUTO_INCREMENT=5
;
