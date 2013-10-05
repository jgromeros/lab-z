CREATE OR REPLACE FUNCTION numero_caso(date) RETURNS integer AS $$
        SELECT COUNT(*) + 1
        FROM labcase
        WHERE reception_date = $1
        FOR UPDATE;
        UPDATE tpimnudo SET nudo_numero = nudo_numero + 1 WHERE nudo_codigo = $1;
        SELECT nudo_numero FROM tpimnudo WHERE nudo_codigo = $1;
$$ LANGUAGE sql

--funcion para mantener un numeros foliados
CREATE OR REPLACE FUNCTION numero_foliado(integer) RETURNS integer AS $$
	SELECT seq_number + 1
	FROM sequential_numbers
	WHERE id = $1
	FOR UPDATE;
	UPDATE sequential_numbers SET seq_number = seq_number + 1 WHERE id = $1;
	SELECT seq_number FROM sequential_numbers WHERE id = $1;
$$ LANGUAGE sql

CREATE OR REPLACE FUNCTION view_assembly(assembly int, OUT row_number smallint, OUT assembly int, OUT plaque int, OUT col1 text,
	OUT col2 text, OUT col3 text, OUT col4 text, OUT col5 text, OUT col6 text, OUT col7 text, OUT col8 text, OUT col9 text,
	OUT col10 text, OUT col11 text, OUT col12 text) RETURNS SETOF record AS $$
SELECT ct.* FROM crosstab(E'
    SELECT at.row, a.id, at.plaque, at.col, COALESCE(l.code || \'-\' || an.name, l.code || an.name, ad.name::text)
    FROM assembly a JOIN ((assembly_test at
            LEFT JOIN (test t JOIN (animal an JOIN labcase l ON an.labcase = l.id)
                    ON t.animal = an.id) ON at.test = t.id)
            LEFT JOIN (assembly_control ac JOIN assembly_descriptor ad ON ac.assembly_descriptor = ad.id)
            ON at.control = ac.id) ON a.id = at.assembly
    WHERE a.id = ' || $1 || '
    ORDER BY at.plaque, at.row, at.col', 'SELECT DISTINCT(col) FROM assembly_test ORDER BY col')
AS ct(row smallint, assembly int, plaque int, col1 text, col2 text, col3 text, col4 text, col5 text, col6 text, col7 text,
	col8 text, col9 text, col10 text, col11 text, col12 text)
$$ LANGUAGE sql
