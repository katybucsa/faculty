#include <string.h>
#include "Validator.h"
#include "Domain.h"

int validateOferta(Oferta* o) {
	char type[20];
	int val = 1;
	if (getId(o) <= 0)
		val *= 2;
	getType(o, type);
	if (strcmp(type, "munte") != 0 && strcmp(type, "mare") != 0 && strcmp(type, "city break") != 0)
		val *= 3;
	if (getDate(o) < 1 || getDate(o) > 31)
		val *= 5;
	if (getPrice(o) < 0)
		val *= 7;
	if(val!=1)
		destr(o);
	return val;
}

