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
Oferta* create(int id, char *type, char *destination, int date, float price);
/*
offer - offer
post: the memory allocated for offer is deallocated
*/
void destruct(Oferta *offer);


void distruge(Oferta off);


/*
test function for the functions above
*/
void testDomainOferte();