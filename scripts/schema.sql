USE BAZA;

CREATE TABLE user (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      role VARCHAR(255),
                      ime VARCHAR(20) NOT NULL,
                      prezime VARCHAR(20) NOT NULL,
                      adresa VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      lozinka VARCHAR(255) NOT NULL,
                      datum VARCHAR(255),
                      UNIQUE (email)
);

INSERT INTO user(id, role, ime, prezime, adresa, email, lozinka, datum) VALUES(999, 'ADMIN', 'admin', 'admin', 'admintest', 'admin@test.com', '$2a$10$sJoi1We9s3mDR7Z69ksp4.faVTr6FgsVewK/OpCeTL9zE6oRk614q', '1970-01-01')