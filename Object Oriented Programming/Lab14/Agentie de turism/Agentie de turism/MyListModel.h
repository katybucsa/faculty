#pragma once
#include <QAbstractListModel>
#include <vector>
#include <string>
#include "Domain.h"
#include "Repository.h"


class MyListModel :public QAbstractListModel {
private:
	std::vector<Oferta> all;
public:
	MyListModel() {
	}
	MyListModel(std::vector<Oferta> all) :all{ all }{}

	int rowCount(const QModelIndex &parent = QModelIndex()) const override {
		return all.size();
	}

	QVariant data(const QModelIndex & index, int role = Qt::DisplayRole) const override {
		if (role == Qt::DisplayRole) {
			auto sp = all[index.row()].getName() + "\t" + all[index.row()].getDestination() +"\t"+ all[index.row()].getType();
			return QString::fromStdString(sp) + "\t" + QString::number(all[index.row()].getPrice());
		}

		return QVariant{};
	}

	Oferta getOferta(const QModelIndex& index) {
		return all[index.row()];
	}
	void setOffers(const std::vector<Oferta>& ofrs) {
		this->all = ofrs;
		QModelIndex topLeft = createIndex(0, 0);
		QModelIndex bottomRight = createIndex(rowCount(), 0);
		emit dataChanged(topLeft, bottomRight);
	}
};