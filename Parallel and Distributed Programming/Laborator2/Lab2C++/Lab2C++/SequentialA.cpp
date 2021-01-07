#include <vector>
#include <iostream>

static class SequentialA {
public:
	static std::vector<int> sequentialAdd(std::vector<int>& number1, std::vector<int>& number2) {
		int carry = 0;
		std::vector<int> result;

		for (int i = number1.size() - 1, j = number2.size() - 1; i >= 0; i--, j--) {
			int s = 0;
			if (j < 0) {
				s = number1.at(i) + carry;
			}
			else {
				s = number1.at(i) + number2.at(j) + carry;
			}
			result.insert(result.begin(), s % 10);
			carry = s / 10;
		}
		if (carry != 0) {
			result.insert(result.begin(), carry);
		}
		return result;
	}
};
