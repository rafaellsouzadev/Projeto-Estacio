CREATE TABLE produto(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	descricao TEXT NOT NULL,
	preco DOUBLE PRECISION NOT NULL,
	quantidade INTEGER NOT NULL,
	precoTotal DOUBLE PRECISION NOT NULL
)

INSERT INTO produto (nome, descricao, preco, quantidade, precoTotal) values
('Impressora', 'Epson EcoTank L3250 - Multifuncional, Tanque de Tinta Colorida, Wi-Fi Direct', 600.00, 5, 3000.00),
('Processador AMD Ryzen 7 5700X3D', 'Processador AMD Ryzen 7 5700X3D, 3.0 GHz, (4.1GHz Max Turbo), Cache 4MB, 8 Núcleos, 16 Threads, AM4 - 100-100001503WOF', 1649.99, 3, 4949.97),
('Processador Intel Core i7-12700KF', 'i7-12700KF, 3.6GHz (5.0GHz Max Turbo), Cache 25MB, 12 Núcleos, 20 Threads, LGA 1700 - BX8071512700KF', 1569.99, 4, 6279.96);

ALTER TABLE produto
	ALTER COLUMN preco TYPE NUMERIC(10, 2),
	ALTER COLUMN precoTotal TYPE NUMERIC(10, 2);