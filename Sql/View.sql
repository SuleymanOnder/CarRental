CREATE VIEW public.CarInformation AS
SELECT manufacturer, brand, year, licence_plate, price 
FROM models AS A
	INNER JOIN car AS B
		ON b.model = a.id
GROUP BY b.licence_plate, a.id;

SELECT * FROM CarInformation;


CREATE VIEW public.ClientRent AS
SELECT name, familyname, orderdate, returndate, manufacturer, brand, year, licence_plate, price
FROM clients AS A
	INNER JOIN order AS B
		ON b.client = a.id
	INNER JOIN CarInformation as C
		ON b.car = c.licence_plate;

SELECT * FROM ClientRent;