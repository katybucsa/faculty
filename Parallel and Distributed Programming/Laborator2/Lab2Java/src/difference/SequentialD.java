package difference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on: 26-Oct-19, 13:55
 *
 * @author: Katy Bucșa
 */

public class SequentialD {
    public static List<Integer> sequentialDiff(List<Integer> number1, List<Integer> number2) {
        int loan = 0;
        if (number1.size() < number2.size()) {
            List<Integer> l = number1;
            number1 = number2;
            number2 = l;
        }
        Collections.reverse(number1);
        Collections.reverse(number2);
        List<Integer> result = new ArrayList<>(Collections.nCopies(number1.size(), 0));

        for (int i = 0, j = 0; i < number1.size(); i++, j++) {
            int d, c1 = number1.get(i), c2;
            if (j >= number2.size()) {
                if (c1 - loan < 0) {
                    d = c1 + 10 - loan;
                    loan = 1;
                } else {
                    d = c1 - loan;
                    loan = 0;
                }
            } else {
                c2 = number2.get(j);
                if (c1 - c2 - loan < 0) {
                    d = c1 + 10 - c2 - loan;
                    loan = 1;
                } else {
                    d = c1 - c2 - loan;
                    loan = 0;
                }
            }
            result.set(i, d);
        }
//        if (loan != 0) {
//            result.add(loan);
//        }
        while (result.size() > 1 && result.get(result.size() - 1).equals(0)) {
            result.remove(result.size() - 1);
        }
        Collections.reverse(number1);
        Collections.reverse(number2);
        Collections.reverse(result);
        return result;
    }
}
