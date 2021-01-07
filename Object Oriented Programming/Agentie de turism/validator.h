#pragma once
#include "domainOferta.h"
/*
off - offfer 
erori - stores the errors that can appear in the reading of the offer elements
post:errno is activated if there is any error
*/
void validate(Oferta*off, char erori[]);

/*
test function for validate
*/
void testValidate();