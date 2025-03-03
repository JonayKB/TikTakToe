USE tiktaktoeProduction;

START TRANSACTION;

CREATE TABLE `games` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `finished` bit(1) DEFAULT NULL,
  `player1_id` bigint DEFAULT NULL,
  `player2_id` bigint DEFAULT NULL,
  `winner_id` bigint DEFAULT NULL
);


INSERT INTO `games` (`id`, `created_at`, `finished`, `player1_id`, `player2_id`, `winner_id`) VALUES
(1, '2025-02-13 16:21:33.745000', b'1', 2, NULL, NULL),
(2, '2025-02-13 16:38:11.989000', b'1', 2, 1, NULL),
(3, '2025-02-13 16:39:15.585000', b'1', 1, NULL, NULL),
(4, '2025-02-13 16:41:35.029000', b'1', 1, NULL, NULL),
(5, '2025-02-13 16:49:51.867000', b'1', 1, 2, NULL),
(6, '2025-02-13 17:06:13.738000', b'1', 3, NULL, NULL),
(7, '2025-02-13 20:03:10.754000', b'1', 3, NULL, NULL),
(8, '2025-02-14 14:47:46.363000', b'1', 3, NULL, NULL),
(9, '2025-02-14 14:54:26.705000', b'1', 3, NULL, NULL),
(10, '2025-02-14 14:57:04.660000', b'1', 3, 1, NULL),
(11, '2025-02-14 15:16:25.826000', b'1', 1, 3, NULL),
(12, '2025-02-14 15:40:32.234000', b'1', 3, 1, NULL),
(13, '2025-02-14 15:48:47.661000', b'1', 1, 3, NULL),
(14, '2025-02-14 15:59:37.966000', b'1', 1, 3, NULL),
(15, '2025-02-14 16:21:42.711000', b'1', 1, 3, NULL),
(16, '2025-02-15 13:16:34.749000', b'1', 3, 1, NULL),
(17, '2025-02-16 13:48:28.106000', b'1', 1, 2, NULL),
(18, '2025-02-16 13:50:28.994000', b'1', 1, 2, NULL),
(19, '2025-02-16 13:50:53.534000', b'1', 1, 2, NULL),
(20, '2025-02-16 13:52:33.603000', b'1', 1, 2, NULL),
(21, '2025-02-16 13:57:18.692000', b'1', 1, 2, 1);


CREATE TABLE `moves` (
  `posy` int NOT NULL,
  `posx` int NOT NULL,
  `played_at` datetime(6) DEFAULT NULL,
  `game_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL
);


INSERT INTO `moves` (`posy`, `posx`, `played_at`, `game_id`, `user_id`) VALUES
(0, 0, '2025-02-14 11:48:13.605000', 7, 3),
(1, 1, '2025-02-14 14:49:07.592000', 8, 3),
(1, 1, '2025-02-14 14:54:35.644000', 9, 3),
(0, 0, '2025-02-14 15:09:21.035000', 10, 3),
(2, 0, '2025-02-14 15:11:27.266000', 10, 1),
(0, 1, '2025-02-14 15:10:29.580000', 10, 3),
(1, 1, '2025-02-14 15:07:07.119000', 10, 1),
(0, 2, '2025-02-14 15:11:09.996000', 10, 3),
(0, 0, '2025-02-14 15:17:02.558000', 11, 1),
(1, 0, '2025-02-14 15:20:07.027000', 11, 3),
(2, 0, '2025-02-14 15:19:01.585000', 11, 3),
(0, 1, '2025-02-14 15:36:00.933000', 11, 1),
(1, 1, '2025-02-14 15:17:26.704000', 11, 3),
(2, 1, '2025-02-14 15:17:58.268000', 11, 3),
(0, 2, '2025-02-14 15:35:50.516000', 11, 3),
(1, 2, '2025-02-14 15:25:38.757000', 11, 1),
(2, 2, '2025-02-14 15:17:43.766000', 11, 1),
(0, 0, '2025-02-14 15:41:11.385000', 12, 3),
(0, 1, '2025-02-14 15:42:33.507000', 12, 1),
(0, 3, '2025-02-14 15:46:15.576000', 12, 3),
(0, 0, '2025-02-14 15:49:05.191000', 13, 1),
(1, 0, '2025-02-14 15:52:08.228000', 13, 1),
(2, 0, '2025-02-14 15:53:01.551000', 13, 1),
(0, 2, '2025-02-14 15:51:19.945000', 13, 3),
(1, 2, '2025-02-14 15:52:28.915000', 13, 3),
(2, 2, '2025-02-14 15:55:49.176000', 13, 3),
(0, 0, '2025-02-14 16:00:16.734000', 14, 1),
(1, 0, '2025-02-14 16:00:32.720000', 14, 1),
(2, 0, '2025-02-14 16:00:43.672000', 14, 1),
(0, 1, '2025-02-14 16:00:24.075000', 14, 3),
(1, 1, '2025-02-14 16:00:38.257000', 14, 3),
(2, 2, '2025-02-14 16:04:07.681000', 14, 3),
(0, 0, '2025-02-15 12:26:59.936000', 15, 1),
(1, 0, '2025-02-15 12:27:17.067000', 15, 1),
(2, 0, '2025-02-15 12:27:29.185000', 15, 1),
(0, 1, '2025-02-15 12:27:11.382000', 15, 3),
(1, 1, '2025-02-15 12:27:20.837000', 15, 3),
(0, 0, '2025-02-15 13:18:18.503000', 16, 3),
(1, 0, '2025-02-15 13:18:40.048000', 16, 3),
(2, 0, '2025-02-15 13:18:46.811000', 16, 3),
(0, 1, '2025-02-15 13:18:27.406000', 16, 1),
(1, 1, '2025-02-15 13:18:43.234000', 16, 1),
(2, 1, '2025-02-15 13:18:54.882000', 16, 1),
(2, 2, '2025-02-15 13:20:15.850000', 16, 3);

CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `rol` varchar(45) NOT NULL,
  `verification_token` varchar(255) DEFAULT NULL,
  `verified` bit(1) NOT NULL,
  `image_path` varchar(255) DEFAULT NULL
);


INSERT INTO `users` (`id`, `created_at`, `mail`, `name`, `password`, `rol`, `verification_token`, `verified`, `image_path`) VALUES
(1, '2025-02-01 00:00:00.000000', 'admin@gmail.com', 'admin', '$2a$10$LCUqJMSwXWRwCmhcQH2Ur.Xjz8Agn4AyQvi1U/ARr7Sq5jsaAayrq', 'ROLE_ADMIN', NULL, b'1', NULL),
(2, '2025-02-01 00:00:00.000000', 'user@gmail.com', 'user', '$2a$10$LCUqJMSwXWRwCmhcQH2Ur.Xjz8Agn4AyQvi1U/ARr7Sq5jsaAayrq', 'ROLE_USER', NULL, b'1', NULL),
(3, '2025-02-13 17:05:14.456000', 'jonaykb@gmail.com', 'JonayKB', '$2a$10$/unvV95l.UWVwQ3bbzOdS.a9667JMh6stc7LzimwOmmkXAh.4ZumC', 'ROLE_USER', NULL, b'1', NULL),
(5, '2025-02-15 14:31:03.112000', 'asdafdasdghasdhasdasdhsdfdsgdfg@gmail.com', 'asdasdasdsdfsadasdasd', '$2a$10$3HL8JD85c2KaAeaYEY9NwuQH7dnDuMNsSfRipQuq1tpHZTQ3WLBKe', 'ROLE_USER', '51eaf5a4-6e56-4540-9e17-fe6384d51e74', b'0', NULL),
(7, '2025-02-15 14:31:49.019000', 'jonaykb.2@gmail.com', 'jonaykb.2@gmail.com', '$2a$10$SIobU2lI4rvZD.5YX3YSfOD84F61QLBjVec.WEnEZC.WPmocoG0B6', 'ROLE_USER', NULL, b'1', NULL),
(8, '2025-02-16 12:23:43.643000', '$2a$10$mDObFmjyOjMbBJdn2RldzeipeW4mxCU/kh90XyvrS/io0t0iceiXa', 'testName', 'test@gmail.com', 'ROLE_USER', 'string', b'1', 'string');

ALTER TABLE `games`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp3rwwk871fnhxnb5w6cn50bfq` (`player1_id`),
  ADD KEY `FKt3h7cejsfr7mdg8ol5jlvi2pg` (`player2_id`),
  ADD KEY `FKb7rxqdrxqhliqc8xqn2hub9xq` (`winner_id`);

ALTER TABLE `moves`
  ADD PRIMARY KEY (`game_id`,`posx`,`posy`),
  ADD KEY `FKsk8dbhcafwi2qqwwsmcwn5r4f` (`user_id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKjhck7kjdogc7yia7qamc89ypv` (`mail`),
  ADD UNIQUE KEY `UK3g1j96g94xpk3lpxl2qbl985x` (`name`);

ALTER TABLE `games`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

ALTER TABLE `users`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

ALTER TABLE `games`
  ADD CONSTRAINT `FKb7rxqdrxqhliqc8xqn2hub9xq` FOREIGN KEY (`winner_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKp3rwwk871fnhxnb5w6cn50bfq` FOREIGN KEY (`player1_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKt3h7cejsfr7mdg8ol5jlvi2pg` FOREIGN KEY (`player2_id`) REFERENCES `users` (`id`);

ALTER TABLE `moves`
  ADD CONSTRAINT `FKjv33kkwwhe6121266nmuk7y1d` FOREIGN KEY (`game_id`) REFERENCES `games` (`id`),
  ADD CONSTRAINT `FKsk8dbhcafwi2qqwwsmcwn5r4f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;
