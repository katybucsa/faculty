#include "Validator.h"

void Validator::validate(const Task &t){
	std::string err = "";
	if (t.getId() <= 0)
		err += "Id invalid!\n";
	if (t.getDescriere() == "")
		err += "Descriere invalida!\n";
	if (t.getProgramatori().size() == 0)
		err += "Task-ul trebuie sa aiba minim un programator!\n";
	if (t.getStare() != "open" && t.getStare() != "inprogress" && t.getStare() != "closed")
		err += "Stare task invalida!\n";
	if (!err.empty())
		throw TaskException(err);
}
