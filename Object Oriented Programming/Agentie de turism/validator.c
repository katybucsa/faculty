#include <stdio.h>
#include "domainOferta.h"
#include <errno.h>
#include <string.h>
#include <assert.h>

void validate(Oferta *off, char erori[]) {
	char idErr[] = "Id invalid!\n", typeErr[] = "Tip invalid!\n", priceErr[] = "Pret invalid!\n", dateErr[] = "Data invalida!\n";
	errno = 0;
	if (off->id < 0)
	{
		strcat(erori,idErr);
		errno = EINVAL;
	}
	if (strcmp(off->type, "munte") != 0 && strcmp(off->type, "mare") != 0 && strcmp(off->type, "citiy break") != 0)
	{
		strcat(erori, typeErr);
		errno = EINVAL;
	}
	if (off->price < 0)
	{
		strcat(erori, priceErr);
		errno = EINVAL;
	}
	if (off->date < 1 || off->date>31)
	{
		strcat(erori, dateErr);
		errno = EINVAL;
	}
}


void testValidate() {
	char erori[200] = "";
	Oferta* oferta1 = create(-1, "munte", "Himalaya", -18, 123);
	validate(oferta1, erori);
	assert(strcmp(erori, "Id invalid!\nData invalida!\n")==0);
	Oferta* oferta2 = create(1, "munte", "Himalaya", 18, 134);
	strcpy(erori, "");
	validate(oferta2, erori);
	assert(strlen(erori) == 0);
	Oferta* oferta3 = create(1, "dfds", "Himalaya", 18, -23);
	validate(oferta3, erori);
	assert(strcmp(erori, "Tip invalid!\nPret invalid!\n") == 0);
	destruct(oferta1);
	destruct(oferta2);
	destruct(oferta3);
}