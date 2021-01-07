#pragma once
/*
post: returns 1 if the offer was added succesfully after validation and 0 otherwise
*/
int addOffer(ListaOferte*oferte, int id, char *type, char *destination, int date, float price, char erori[]);


/*
post:returns 1 if the offer was modified succesfully after validation amd 0 otherwise
*/
int modifyOffer(ListaOferte*oferte, int id, char *type, char *destination, int date, float price, char erori[]);


/*
oferte - vector of offers
poz - one position in the vector;poz>=0
post: returns the offer that is on position poz in vector
*/
Oferta pozOffer(ListaOferte*oferte, int poz);



int deleteOffer(ListaOferte *oferte, int id, char erori[]);

/*
test function for the functions above
*/
void testServiceOferte();
