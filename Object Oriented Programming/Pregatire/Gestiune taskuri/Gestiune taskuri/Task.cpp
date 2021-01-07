#include "Task.h"

bool Task::operator==(const Task &t) {
	return this->id == t.id;
}
