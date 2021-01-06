#pragma once
#include <QAbstractTableModel>
#include <vector>
#include "Service.h"

class MyTableModel : public QAbstractTableModel {
private:
	std::vector<Music> all;
	//Service serv;
public:
	MyTableModel() {}
	MyTableModel(std::vector<Music>& all) :all{ all } {}

	int rowCount(const QModelIndex & parent = QModelIndex()) const override {
		return all.size();
	}

	int columnCount(const QModelIndex & parent = QModelIndex()) const override {
		return 6;
	}

	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override {
		Music m = all[index.row()];
		if (role == Qt::DisplayRole) {
			if (index.column() == 0) {
				return QString::number(m.getId());
			}

			if (index.column() == 1) {
				return QString::fromStdString(m.getTitlu());
			}

			if (index.column() == 2) {
				return QString::fromStdString(m.getArtist());
			}

			if(index.column() == 3) {
				return QString::fromStdString(m.getGen());
			}
		}
		return QVariant{};
	}

	Music getMusic(const QModelIndex& index) {
		return all[index.row()];
	}

	void setMusics(const std::vector<Music>& all, Service& serv) {
		this->all = all;
		QModelIndex topLeft = createIndex(0, 0);
		QModelIndex bottomRight = createIndex(rowCount(), columnCount());
		emit dataChanged(topLeft, bottomRight);
	}
};