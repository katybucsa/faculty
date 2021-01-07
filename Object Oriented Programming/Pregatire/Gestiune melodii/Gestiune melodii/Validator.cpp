#include "Validator.h"

void Validator::validate(const Music &m){
	std::string err = "";
	if (m.getId() <= 0)
		err += "Id invalid\n";
	if (m.getTitlu() == "")
		err += "Titlu invalid!\n";
	if (m.getArtist() == "")
		err += "Artist invalid!\n";
	if (m.getGen() != "pop" && m.getGen()!="rock" && m.getGen()!="folk" && m.getGen()!="disco")
		err += "Gen invalid!\n";
	if (!err.empty())
		throw MusicException(err);
}
