package multiplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on: 29-Oct-19, 00:13
 *
 * @author: Katy Bucșa
 */

public class DigitMultiplication {
    public static List<Integer> multiplyVector(List<Integer> vector, int number) {
        List<Integer> result = new ArrayList<>();
        int forA = 0, s;
        for (int i = vector.size() - 1; i >= 0; i--) {
            s = vector.get(i) * number + forA;
            result.add(0,s % 10);
            forA = s / 10;
        }
        if (forA != 0) {
            result.add(0,forA);
        }
        return result;
    }
}