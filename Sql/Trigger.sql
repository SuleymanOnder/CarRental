Trigger

--Geriye donuk kiralamalara izin verme
CREATE FUNCTION UnauthorizedDueDate() RETURNS "trigger" AS 
$$
	BEGIN
		IF NEW.OrderDate < CURRENT_DATE
			THEN RAISE EXCEPTION 'Geriye dönük olarak kiralanamaz!';
		END IF;
		RETURN NEW;
	END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER DueDate
BEFORE INSERT ON public.Order
FOR EACH ROW EXECUTE PROCEDURE UnauthorizedDueDate();

--Iade edilmedigi takdirde ayni aracin kiralanmasina izin vermeyin
CREATE FUNCTION UnauthorizedRentCarData() RETURNS "trigger" AS 
$$
	BEGIN
		IF (SELECT COUNT(*) FROM public.Order
			WHERE (ReturnDate IS NULL AND  car = NEW.car OR public.Order.OrderDate IS NULL)) > 0  
		THEN
			RAISE EXCEPTION 'Bir araba, iade edilene kadar birkac kisiye kiralanamaz!';
		END IF;
		RETURN NEW;
	END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER RentDataSameCar
BEFORE INSERT ON public.Order
FOR EACH ROW EXECUTE PROCEDURE UnauthorizedRentCarData();