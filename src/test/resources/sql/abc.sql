CREATE TABLE abc
(
    id INT PRIMARY KEY,
    a  INT,
    b  INT,
    c  INT
);

ALTER TABLE abc
    ADD INDEX idx_abc (a, b, c);
SHOW INDEX FROM abc;


EXPLAIN
SELECT *
FROM abc
WHERE a = 1
  AND b = 2
  AND c = 3;

EXPLAIN
SELECT *
FROM abc
WHERE b = 2
  AND c = 3;

EXPLAIN
SELECT *
FROM abc
WHERE c = 3;

CREATE TABLE abc2
(
    id INT PRIMARY KEY,
    a  INT,
    b  INT,
    c  INT,
    d  INT
);
ALTER TABLE abc2
    ADD INDEX idx_abc (a, b, c);
SHOW INDEX FROM abc2;


EXPLAIN
SELECT *
FROM abc2
WHERE a = 1
  AND b = 2
  AND c = 3;

EXPLAIN
SELECT *
FROM abc2
WHERE b = 2
  AND c = 3;

EXPLAIN
SELECT *
FROM abc2
WHERE c = 3;
