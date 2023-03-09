CREATE INDEX client_information ON public.Clients(name, familyname);
CREATE UNIQUE INDEX phone ON public.Clients(phonenumber);