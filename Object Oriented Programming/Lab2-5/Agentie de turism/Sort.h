#pragma once
#include "Domain.h"
#include "DynamicVector.h"

typedef int(*CmpFunction)(Oferta* o1, Oferta* o2);

void sort(DynamicVector*, CmpFunction,int);