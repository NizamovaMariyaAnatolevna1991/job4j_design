CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    birth_year INT
);

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    publication_year INT,
    author_id INT REFERENCES authors(id)
);

SELECT 
    b.title AS book_title,
    a.name AS author_name
FROM 
    books b
INNER JOIN 
    authors a ON b.author_id = a.id;

SELECT 
    b.title AS book_title,
    a.name AS author_name,
    b.publication_year
FROM 
    books b
INNER JOIN 
    authors a ON b.author_id = a.id
WHERE 
    b.publication_year > 1950;

SELECT 
    a.name AS author_name,
    COUNT(b.id) AS total_books
FROM 
    authors a
INNER JOIN 
    books b ON a.id = b.author_id
GROUP BY 
    a.name;
