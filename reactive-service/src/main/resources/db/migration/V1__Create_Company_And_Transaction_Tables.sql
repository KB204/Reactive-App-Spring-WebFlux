CREATE TABLE company (
                         id SERIAL NOT NULL PRIMARY KEY,
                         name VARCHAR(255),
                         price DOUBLE PRECISION
);

CREATE TABLE transaction (
                             id SERIAL NOT NULL PRIMARY KEY,
                             identifier VARCHAR(255),
                             created_at TIMESTAMP,
                             amount DOUBLE PRECISION,
                             company_id INT,
                             CONSTRAINT fk_company
                                 FOREIGN KEY (company_id)
                                     REFERENCES company (id)
);
