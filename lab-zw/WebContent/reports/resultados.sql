/*
SELECT at.id, avg(rc.string_value::float)
FROM (assembly_test at JOIN (assembly_test c JOIN
			((assembly_control ac JOIN assembly_descriptor ad
				ON ac.assembly_descriptor = ad.id)
				JOIN result rc ON ac.result = rc.id)
			ON c.control = ac.id)
		ON at.plaque = c.plaque AND c.control IS NOT NULL)
	JOIN assembly a ON a.id = at.assembly
WHERE ad.name = 'C++'
	AND at.test IS NOT NULL
	AND a.id = 24
GROUP BY at.id


SELECT avg(r.string_value::float), at.plaque
FROM result r JOIN ((assembly_test at JOIN (assembly_control ac
				JOIN assembly_descriptor ad ON ac.assembly_descriptor = ad.id)
			ON at.control = ac.id)
		JOIN assembly a ON at.assembly = a.id)
	ON r.assembly_test = at.id
WHERE a.id = 24
	AND ad.name = 'C++'
	AND r.test IS NULL
GROUP BY a.id, at.plaque, ad.name
ORDER BY at.plaque, ad.name;
*/

SELECT *, ((results.res/results.control_pf)*100) AS pp, (CASE WHEN ((results.res/results.control_pf)*100) < 30
	THEN 'Negativo' ELSE 'Positivo' END) AS interpretation FROM (
SELECT l.code, l.reception_date, e.first_name, e.numero_ica, l.sender, p.description AS city, p1.description AS state,
	l.zone, l.farm, l.owner, l.analysis_purpose, l.reproductive_problem, l.results_number,
	tech.first_name || ' ' || tech.last_name AS techdir, lp.first_name || ' ' || lp.last_name AS labpro,
	a.name, a.gender, a.years, a.months, ra.name AS race, s.name AS specie, a.observations, td.description,
	td.ica_acronym, avg(r.string_value::float) AS res, ass.id AS assembly, ass.assembly_date,
(
SELECT avg(rc.string_value::float)
FROM (assembly_test at2 JOIN (assembly_test c JOIN
			((assembly_control ac JOIN assembly_descriptor ad
				ON ac.assembly_descriptor = ad.id)
				JOIN result rc ON ac.result = rc.id)
			ON c.control = ac.id)
		ON at2.plaque = c.plaque AND c.control IS NOT NULL)
	JOIN assembly a ON a.id = at2.assembly
WHERE ad.name = 'C++'
	AND at2.test = t.id
	AND a.id = ass.id
) AS control_pf
FROM result r JOIN (((test t JOIN
		(((animal a JOIN (((labcase l JOIN lab_professional lp ON l.lab_professional = lp.id)
					JOIN lab_professional tech ON l.technical_director = tech.id)
				JOIN enterprise e ON l.enterprise = e.id)
			ON a.labcase = l.id) JOIN (place p JOIN place p1
				ON p.placed_on = p1.id) ON l.city = p.id)
			JOIN (race ra JOIN specie s ON ra.specie = s.id) ON a.race = ra.id)
		ON a.id = t.animal) JOIN test_description td
			ON t.test_description = td.id)
	JOIN (assembly_test at JOIN assembly ass
		ON at.assembly = ass.id)
	ON at.test = t.id)
ON t.id = r.test AND r.assembly_test = at.id
WHERE l.id = 46
GROUP BY l.code, l.reception_date, e.first_name, e.numero_ica, l.sender, p.description,	p1.description,
	l.zone, l.farm, l.owner, l.analysis_purpose, l.reproductive_problem, l.results_number, techdir, labpro,
	a.name, a.gender, a.years, a.months, ra.name, s.name, a.observations, td.description, td.ica_acronym,
	ass.id, ass.assembly_date, a.position, t.id
ORDER BY a.position
) AS results
