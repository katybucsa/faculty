#pragma once
#include "Service.h"
#include <assert.h>
void test() {
	Repo repo;
	Service srv{ repo };
	Music m = Music("asd", "ads", 0);
	srv.add("asd", "ads", 0);
	try {
		srv.add("asd", "ads", 0);
	}
	catch (RepoException) { assert(true); }

	srv.like(m);
	//assert(m.getLikes() == 1);

}