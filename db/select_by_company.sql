select
    p.name as person_name,
    c.name as company_name
from
    people p
    join companies c on p.company_id = c.id
where p.company_id != 5;

WITH company_counts AS (
    SELECT
        c.name,
        COUNT(p.id) AS person_count
    FROM
        companies c
    LEFT JOIN
        people p ON c.id = p.company_id
    GROUP BY
        c.id, c.name
)
SELECT
    name,
    person_count
FROM
    company_counts
WHERE
    person_count = (SELECT MAX(person_count) FROM company_counts);

