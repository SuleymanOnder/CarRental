/* DROP VIEWS */

DROP VIEW public.CarInformation;
DROP VIEW public.ClientOrder;

/* DROP INDEX */

DROP INDEX ClientInformation;
DROP INDEX Phone;

/* DROP TRIGGERS */

DROP TRIGGER DueDate ON public.Order;
DROP FUNCTION UnauthorizedDueDate();

DROP TRIGGER RentDataSameCar ON public.Order;
DROP FUNCTION UnauthorizedRentCarData();


/* Drop Tables */

DROP TABLE public.Clients CASCADE;
DROP TABLE public.Models CASCADE;
DROP TABLE public.Car CASCADE;
DROP TABLE public.Order;