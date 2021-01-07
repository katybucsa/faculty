#include <vector>

static class DigitMultiplication {
public:
	static std::vector<int> multiplyVector(std::vector<int>& vector, int number) {
		std::vector<int> result;
		int forA = 0, s;
		for (int i = vector.size() - 1; i >= 0; i--) {
			s = vector.at(i) * number + forA;
			result.insert(result.begin(), s % 10);
			forA = s / 10;
		}
		if (forA != 0) {
			result.insert(result.begin(), forA);
		}
		return result;
	}
};