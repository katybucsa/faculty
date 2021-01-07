#include "Validator.h"

void Validator::validate(const Produs &p){
	std::string err = "";
	if (p.getNume() == "")
		err += "Nume invalid!\n";
	if (p.getPret() < 1.0 || p.getPret() > 100.0)
		err += "Pret invalid!\n";
	if (!err.empty())
		throw ProdusException(err);
}
