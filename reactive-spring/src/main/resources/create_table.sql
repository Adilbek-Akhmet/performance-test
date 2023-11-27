CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description TEXT
);

CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price NUMERIC NOT NULL,
                          description TEXT,
                          manufacture_date DATE,
                          category_id BIGINT,
                          FOREIGN KEY (category_id) REFERENCES categories(id)
);