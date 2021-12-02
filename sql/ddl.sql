use player;
CREATE TABLE IF NOT EXISTS `player_score` (
             `id` BIGINT NOT NULL AUTO_INCREMENT,
             `player_name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
             `score` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
             `create_time` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
             PRIMARY KEY (`id`) USING BTREE
             )
             COLLATE='utf8mb4_0900_ai_ci'
             ENGINE=InnoDB
             AUTO_INCREMENT=5
;