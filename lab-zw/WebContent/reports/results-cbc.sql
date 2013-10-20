SELECT l.reception_date, l.code, a.name AS animal, a.age, ra.name AS race,
        a.gender, e.first_name, p.name AS city, dpt.name AS state, l.zone, l.farm, l.owner,
        lp.first_name || ' ' || lp.last_name AS professional, s.name AS specie, td.description,
        la.description AS area, rf._group, rf.name, r.string_value, r.result_date, l.analysis_purpose,
        t.observations, rv.min_abs_ref_value, rv.max_abs_ref_value, rv.min_rel_ref_value,
        rv.max_rel_ref_value, td.show_reference_value, l.sender, rf.calculated, rf.computed_value,
        r.relative_value, rf.unit
FROM (result r JOIN lab_professional lp ON r.lab_professional = lp.id)
        JOIN (((test t JOIN ((animal a JOIN
                        (race ra JOIN specie s ON ra.specie = s.id)
                        ON a.race = ra.id)
                JOIN ((labcase l
                        JOIN (place p JOIN place dpt ON p.placed_on = dpt.id)
                                ON l.city = p.id)
                        JOIN enterprise e ON l.enterprise = e.id)
                ON l.id = a.labcase)
        ON a.id = t.animal) JOIN
                ((test_description td JOIN result_factor rf
                        ON td.id = rf.test_description)
                        JOIN lab_area la ON td.lab_area = la.id)
                ON td.id = t.test_description) LEFT JOIN reference_value rv
                        ON rv.result_factor = rf.id AND rv.specie = s.id)
        ON t.id = r.test AND r.result_factor = rf.id
WHERE t.id = 10908
ORDER BY a.id, rf.number_order, rf._group, rf.id