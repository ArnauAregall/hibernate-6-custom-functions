CREATE TABLE IF NOT EXISTS product (
    id UUID UNIQUE NOT NULL,
    sku VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(200) NOT NULL,
    price FLOAT NOT NULL,
    tags varchar[],
    PRIMARY KEY (id)
);