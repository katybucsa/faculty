class TElement {
private:
	TCheie c;
	TValoare v;
public:
	TElement(const TCheie& c, const TValoare& v) :c{ c }, v{ v } {}
	TElement() = default;
	friend std::ostream& operator<<(std::ostream& s, TElement e) {
		s << "Nume: " << e.c << "\t" << "Nota: " << e.v << std::endl;
		return s;
	}
	const TCheie& getCheie() const noexcept {
		return c;
	}
	const TValoare& getValoare() const noexcept {
		return v;
	}
	friend class Nod;
	friend class DictionarOrdonat;
	friend bool cmp(const TElement&, const TElement&);
};
