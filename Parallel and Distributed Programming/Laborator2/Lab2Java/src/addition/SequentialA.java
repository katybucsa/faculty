package addition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on: 26-Oct-19, 13:21
 *
 * @author: Katy Bucșa
 */

public class SequentialA {
    public static List<Integer> sequentialAdd(List<Integer> number1, List<Integer> number2) {
        int carry = 0;
        if (number1.size() < number2.size()) {
            List<Integer> l = number1;
            number1 = number2;
            number2 = l;
        }

        List<Integer> result = new ArrayList<>(Collections.nCopies(number1.size(), 0));
        Collections.reverse(number1);
        Collections.reverse(number2);

        for (int i = 0, j = 0; i < number1.size(); i++, j++) {
            int s = 0;
            if (j >= number2.size()) {
                s = number1.get(i) + carry;
            } else {
                s = number1.get(i) + number2.get(j) + carry;
            }
            result.set(i, s % 10);
            carry = s / 10;
        }
        if (carry != 0) {
            result.add(carry);
        }
        Collections.reverse(number1);
        Collections.reverse(number2);
        Collections.reverse(result);
        return result;
    }
}
