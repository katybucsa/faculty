#include <vector>

static class SequentialD {
public:
	static std::vector<int> sequentialDiff(std::vector<int>& number1, std::vector<int>& number2) {
		int loan = 0;
		std::vector<int> result;

		for (int i = number1.size() - 1, j = number2.size() - 1; i >= 0; i--, j--) {
			int d, c1 = number1.at(i), c2;
			if (j < 0) {
				if (c1 - loan < 0) {
					d = c1 + 10 - loan;
					loan = 1;
				}
				else {
					d = c1 - loan;
					loan = 0;
				}
			}
			else {
				c2 = number2.at(j);
				if (c1 - c2 - loan < 0) {
					d = c1 + 10 - c2 - loan;
					loan = 1;
				}
				else {
					d = c1 - c2 - loan;
					loan = 0;
				}
			}
			result.insert(result.begin(), d);
		}

		while (result.size() > 1 && result.at(0) == 0) {
			result.erase(result.begin());
		}
		return result;
	}
};