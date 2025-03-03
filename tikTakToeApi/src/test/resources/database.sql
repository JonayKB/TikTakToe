SET MODE MYSQL;
DROP TABLE IF EXISTS `asignatura_matricula`;
DROP TABLE IF EXISTS `asignaturas`;
DROP TABLE IF EXISTS `matriculas`;
DROP TABLE IF EXISTS `alumnos`;
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT,
    name VARCHAR(45) UNIQUE NOT NULL,
    password VARCHAR(200) NOT NULL,
    mail VARCHAR(100) UNIQUE NOT NULL,
    rol VARCHAR(45) NOT NULL,
    verified TINYINT(1) DEFAULT 0,
    verification_token VARCHAR(255),
    created_at DATE NOT NULL
);



INSERT INTO `usuarios` (
        `id`,
        `name`,
        `password`,
        `mail`,
        `rol`,
        `verified`,
        `verification_token`,
        `created_at`
    )
VALUES (
        '2',
        'user',
        '$2a$10$LCUqJMSwXWRwCmhcQH2Ur.Xjz8Agn4AyQvi1U/ARr7Sq5jsaAayrq',
        'user@gmail.com',
        'ROLE_USER',
        1,
        null,
        '2025-02-01'
    );

INSERT INTO `usuarios` (
        `id`,
        `name`,
        `password`,
        `mail`,
        `rol`,
        `verified`,
        `verification_token`,
        `created_at`
    )
VALUES (
        '1',
        'admin',
        '$2a$10$LCUqJMSwXWRwCmhcQH2Ur.Xjz8Agn4AyQvi1U/ARr7Sq5jsaAayrq',
        'admin@gmail.com',
        'ROLE_ADMIN',
        1,
        null,
        '2025-02-01'
    );