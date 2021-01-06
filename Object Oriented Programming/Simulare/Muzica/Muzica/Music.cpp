#include "Music.h"

bool Music::operator==(const Music & m){
	return this->getArtist() == m.getArtist() && this->getTitlu() == m.getTitlu();
}
