CREATE TABLE public.Clients(
	ID VARCHAR(11) NOT NULL,
	Name VARCHAR(30),
	FamilyName VARCHAR(30),
	PhoneNumber VARCHAR(12) DEFAULT '-',
	Address VARCHAR(30) DEFAULT '-',
	PRIMARY KEY (ID)
);

CREATE TABLE public.Models(
	ID SERIAL NOT NULL,
	Manufacturer VARCHAR(30),
	Brand VARCHAR(30),
	Year INT NOT NULL CHECK (Year> 1900 AND  Year< date_part('year', CURRENT_DATE) + 1),
	PRIMARY KEY (ID)
);

CREATE TABLE public.Car(
	License_Plate VARCHAR(6) NOT NULL,
	Model INT,
	Price INT DEFAULT 0 CHECK(Price > 0),
	PRIMARY KEY (License_Plate ),
	FOREIGN KEY (Model) REFERENCES public.Models ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE public.Order(
	ID SERIAL NOT NULL,
	Client VARCHAR(11),
	Car VARCHAR(6),
	OrderDate DATE NOT NULL,
	ReturnDate DATE CHECK (ReturnDate  <= CURRENT_DATE),
	PRIMARY KEY (ID),
	FOREIGN KEY (Client) REFERENCES public.Clients ON DELETE RESTRICT ON UPDATE RESTRICT,
	FOREIGN KEY (Car) REFERENCES public.Car ON DELETE RESTRICT ON UPDATE RESTRICT
);