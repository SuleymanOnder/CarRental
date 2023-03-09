INSERT INTO public.Clients VALUES('39510102889', 'Henri', 'Griffith');
INSERT INTO public.Clients VALUES('48508097779', 'Susan', 'Goodman', '+12025550117');
INSERT INTO public.Clients VALUES('37508097779', 'Angela', 'Caldwell', '+12025550117', 'Gaithersburg, Maryland');
INSERT INTO public.Clients VALUES('44404044444', 'Nancy', 'Davila', '+12025550117', 'Morganville, New Mexico');


INSERT INTO public.Models VALUES(default, 'Opel', 'Ascona', '1995');
INSERT INTO public.Models VALUES(default, 'Audi', '100', '1997');
INSERT INTO public.Models VALUES(default, 'Audi', 'A6', '2004');
INSERT INTO public.Models VALUES(default, 'Toyota', 'Avensis', '2004');

INSERT INTO public.Car VALUES('ABC123', 1, 140);
INSERT INTO public.Car VALUES('ABC124', 1, 150);
INSERT INTO public.Car VALUES('ABC125', 1, 160);
INSERT INTO public.Car VALUES('AUA789', 2, 210);
INSERT INTO public.Car VALUES('HUB007', 3, 220);
INSERT INTO public.Car VALUES('AGC111', 2, 230);

INSERT INTO public.Order VALUES(default, '39510102889', 'ABC123', '2020-09-23', '2020-10-01');
INSERT INTO public.Order  VALUES(default, '37508097779', 'AUA789', '2020-10-13');
INSERT INTO public.Order VALUES(default, '37508097779', 'HUB007', '2020-10-19');
INSERT INTO public.Order VALUES(default, '39510102889', 'ABC124', '2020-10-19');





-- Iade tarihi nedeniyle mevcut degil > bugunden
INSERT INTO public.Order VALUES(default, '48508097779', 'ABC125', '2019-10-23', '2020-10-16'); 

--Istisna "Bir araba, iade edilene kadar birden fazla kisiye kiralanamaz"
INSERT INTO public.Order VALUES(default, '39510102889', 'HUB007', '2020-10-13');
--Istisna "Geriye donuk olarak kiralanamaz"
INSERT INTO public.Order VALUES(default, '39510102889', 'AGC111', '2020-10-12');



SELECT * 
FROM public.Clients;

SELECT *
FROM public.Models;

SELECT *
FROM public.Car;

SELECT *
FROM public.Order;