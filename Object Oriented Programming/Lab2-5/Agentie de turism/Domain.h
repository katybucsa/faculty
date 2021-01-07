#pragma once

typedef struct {
	int id;
	char *type;
	char *destination;
	int date;
	float price;
}Oferta;

/*
creates a new tourism offer
id - offer id
type - type of offer; can be one of(munte,mare,city break)
destination - destination of the tourism offer
date - date of the tourism offer; date>0 and date<=31
price - price of the tourism offer; price>=0
*/
Oferta* createOferta(int, char*, char*, int, float);
/*
returns the id of the current offer
*/
int getId(Oferta* o);
/*
loads in type parameter the type of the current offer
*/
void getType(Oferta* o, char *type);

/*
loads in destination parameter the destination of the current offer
*/
void getDestination(Oferta* o, char *destination);

/*
returns the date of the current offer
*/
int getDate(Oferta* o);

/*
returns the price of the current offer
*/
int getPrice(Oferta* o);

/*
function to compare two offers; 2 offers are equal if their id is the same
*/
int ofertaCmp(Oferta *, Oferta*);

/*
post:returns a new offer with the same atributes as the initial offer
*/
Oferta* copyOferta(Oferta*);

/*
destroy function for an offer
*/
void destr(Oferta *);

