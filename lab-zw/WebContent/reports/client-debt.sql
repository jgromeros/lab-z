/*Para el reporte de documento similar a factura*/
SELECT * FROM (
	SELECT b.bill_number, b.bill_date, e.first_name, e.dni, e.address, e.city, e.phone,
		td.description, count(td.description), bd.price, sum(bd.price),
		bd.tax, b.total_before_taxes, b.total_after_taxes
	FROM (bill_detail bd JOIN
			((test t JOIN (animal a JOIN labcase l ON a.labcase = l.id)
				ON t.animal = a.id)
				JOIN test_description td ON t.test_description = td.id)
			ON bd.test = t.id)
		JOIN (bill b JOIN enterprise e ON b.client = e.id) ON bd.bill = b.id
	WHERE b.status = 'V'
		AND b.bill_number = 19
	GROUP BY 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 13, 14
	UNION ALL
	SELECT b.bill_number, b.bill_date, e.first_name, e.dni, e.address, e.city, e.phone,
		p.description, count(p.description), bd.price, sum(bd.price),
		bd.tax, b.total_before_taxes, b.total_after_taxes
	FROM bill_detail bd
		JOIN bill b  ON bd.bill = b.id
		JOIN enterprise e ON b.client = e.id
		JOIN test_profile tp ON bd.test_profile = tp.id
		JOIN profile p ON tp.profile = p.id
	WHERE b.status = 'V'
		AND b.bill_number = 19
	GROUP BY 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 13, 14) AS bills
ORDER BY 3
