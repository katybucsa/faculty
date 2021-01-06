#pragma once

class Base {
public:
	virtual int multiply(int value, int factor = 2) = 0;
};

class Derived : public Base {
public:
	int multiply(int value, int factor = 10) final;
};

/*class Derive : public Derived {
public:
	int multiply(int value, int factor = 10);
};*/
