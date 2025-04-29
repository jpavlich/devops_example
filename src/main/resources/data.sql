DROP TABLE IF EXISTS Cuenta;

CREATE TABLE IF NOT EXISTS Cuenta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    saldo DECIMAL(19, 2)
);


INSERT INTO CUENTA(id, name, saldo) values (1, 'Alice Alisson', 5000);
INSERT INTO CUENTA(id, name, saldo) values (2, 'Bob Bobson', 4000);
INSERT INTO CUENTA(id, name, saldo) values (3, 'Carla Carlason', 6000);