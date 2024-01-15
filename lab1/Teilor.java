package pp_lab_1;

import java.math.*;
import java.util.*;


public class Teilor {
    static double calculateNormal(double x, int epsParam) {
        double eps = 1;
        for(int i = 0; i < epsParam; ++i)
        {
            eps /= 10;
        }
        double result = 1;
        double tempResult = result;
        int formulaNumber = 1;

        while(Math.abs(tempResult) > Math.abs(eps)) {
            formulaNumber += 2;
            tempResult = -1 * tempResult*x*x/(formulaNumber*(formulanumber-1)*(formulaNumber-2));
            result += tempResult;
        }

        return result;
    }

    static float calculateBig(double x, int epsParam) {
        BigDecimal eps = new BigDecimal(1);
        for(int i = 0; i < epsParam; ++i)
        {
            eps = eps.divide(new BigDecimal(10));
        }
        BigDecimal result = new BigDecimal(x);
        BigDecimal tempResult = new BigDecimal(x);
        double formulaNumber = 1;
        BigDecimal bigX = new BigDecimal(x);
        System.out.println(tempResult);
        System.out.println(tempResult.abs().doubleValue() > eps.abs().doubleValue());
        while(tempResult.abs().doubleValue() > eps.abs().doubleValue()) {
            formulaNumber += 2;
            tempResult = tempResult.multiply(bigX);
            tempResult = tempResult.multiply(bigX);
            tempResult = tempResult.multiply(new BigDecimal(-1));
            tempResult = tempResult.multiply(new BigDecimal(formulaNumber - 2));
            tempResult = tempResult.divide(new BigDecimal(formulaNumber), 7, RoundingMode.HALF_UP);
            result = result.add(tempResult);
        }

        return result.floatValue();
    }
}
