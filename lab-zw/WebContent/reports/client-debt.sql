SELECT price FROM prices_by_test_desc pt JOIN
	(test_description td JOIN (test t JOIN
		(animal a JOIN labcase l ON l.id = a.labcase)
		ON a.id = t.animal)
	ON td.id = t.test_description)
ON td.id = pt.test_description




/*Para el reporte de documento similar a factura*/
SELECT * FROM (
SELECT b.bill_number, e.first_name, l.code, l.reception_date, a.name, td.description,
	bd.price, bd.tax, b.total_before_taxes, b.total_after_taxes
FROM (bill_detail bd JOIN
		((test t JOIN (animal a JOIN labcase l ON a.labcase = l.id)
			ON t.animal = a.id)
			JOIN test_description td ON t.test_description = td.id)
		ON bd.test = t.id)
	JOIN (bill b JOIN enterprise e ON b.client = e.id) ON bd.bill = b.id
WHERE b.status = 'V'
	AND b.bill_number = 4
UNION ALL
SELECT DISTINCT b.bill_number, e.first_name, l.code, l.reception_date, a.name, p.description,
	bd.price, bd.tax, b.total_before_taxes, b.total_after_taxes
FROM (bill_detail bd JOIN
		((test_profile tp JOIN profile p ON tp.profile = p.id)
			JOIN (test t JOIN (animal a JOIN labcase l ON a.labcase = l.id)
				ON t.animal = a.id)
			ON t.test_profile = tp.id) ON bd.test_profile = tp.id)
	JOIN (bill b JOIN enterprise e ON b.client = e.id) ON bd.bill = b.id
WHERE b.status = 'V'
	AND b.bill_number = 4) AS bills
ORDER BY 3
