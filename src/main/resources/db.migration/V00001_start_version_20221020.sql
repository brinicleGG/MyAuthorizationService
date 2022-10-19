CREATE TABLE `users` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(1024) NOT NULL,
  `role` varchar(45) NOT NULL DEFAULT 'USER',
  `status` varchar(45) NOT NULL DEFAULT 'NOT_ACTIVE',
  PRIMARY KEY (`email`,`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
);