SELECT price FROM prices_by_test_desc pt JOIN
	(test_description td JOIN (test t JOIN
		(animal a JOIN labcase l ON l.id = a.labcase)
		ON a.id = t.animal)
	ON td.id = t.test_description)
ON td.id = pt.test_description