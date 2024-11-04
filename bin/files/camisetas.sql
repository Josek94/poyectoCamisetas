CREATE DATABASE camisetas;
show databases;
USE camisetas;
CREATE TABLE camisetas (cantidad LONG, color VARCHAR(30), marca VARCHAR(30), modelo VARCHAR(30), talla VARCHAR(30));
DESCRIBE camisetas;
INSERT INTO camisetas (cantidad, color , marca, modelo, talla) VALUES 
('2904', 'verde', 'superdry', manga larga unisex, xxl),
('2083', 'rojo', 'superdry', manga larga hombre, s/m),
('1627', 'verde', 'zara', manga corta hombre, l/xl),
('413', 'naranja', 'hacket', manga larga mujer, s/m),
('2977', 'malva', 'banana blue', manga corta mujer, xs),
('1635', 'gris', 'ralph lauren', manga larga unisex, m/l),
('1584', 'cian', 'silbon', manga larga hombre, m/l),
('446', 'lavanda', 'edmmond studios', manga corta mujer, xl),
('2832', 'ocre', 'hacket', manga corta hombre, xxl),
('2720', 'naranja', 'mango', manga larga unisex, xl),
('300', 'zinc', 'silbon', manga larga hombre, xl),
('738', 'purpura', 'banana blue', manga larga mujer, xl/xxl),
('2239', 'zinc', 'mango', manga corta unisex, xs),
('2006', 'ocre', 'desigual', manga corta unisex, xxl),
('1446', 'morado', 'silbon', manga larga hombre, xxl),
('2448', 'malva', 'jack & jones', manga corta mujer, xxl),
('2501', 'ambar', '120% lino', manga corta unisex, s/m),
('2877', 'gris', 'napapijri', manga larga unisex, m/l),
('2385', 'morado', 'dior', manga corta hombre, xxl),
('1165', 'amarillo', 'hacket', manga larga unisex, m/l),
('2495', 'morado', 'scotta 1985', manga corta unisex, m/l),
('541', 'azul', 'the untouchables', manga larga unisex, xxl),
('1298', 'morado', 'dior', manga larga hombre, s),
('2379', 'lavanda', 'hacket', manga larga mujer, xs),
('1388', 'ambar', 'edmmond studios', manga corta hombre, l/xl),
('444', 'morado', 'the untouchables', manga corta unisex, s),
('444', 'morado', 'the untouchables', manga corta unisex, s);