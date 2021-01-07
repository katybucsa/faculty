#pragma once
#include "Domain.h"
#include "Repository.h"

class UndoAction {
public:
	virtual void doUndo() = 0;
	virtual ~UndoAction() = default;
};

class AddUndo :public UndoAction {
private:
	Oferta oAdded;
	Repository& repo;
public:
	AddUndo(Repository&repo, const Oferta& o) :repo{ repo }, oAdded{ o } {}
	void doUndo() override {
		repo.remove(oAdded);
	}
};

class RemoveUndo :public UndoAction {
private:
	Oferta oDel;
	Repository& repo;
public:
	RemoveUndo(Repository&repo, const Oferta&o) :repo{ repo }, oDel{ o } {}
	void doUndo() override {
		repo.add(oDel);
	}
};

class ModifyUndo :public UndoAction {
private:
	Oferta oMod;
	Repository& repo;
public:
	ModifyUndo(Repository&repo, const Oferta&o) :repo{ repo }, oMod{ o } {}
	void doUndo() override {
		repo.modify(oMod);
	}
};


class UndoException {
private:
	std::string message;
public:
	UndoException(std::string msg) :message{ msg } {}
	const std::string& getMessage() const noexcept {
		return message;
	}
};
