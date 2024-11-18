
package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class Main {
    public static double myFunction(double x, double e){
        double result = 0;
        double term = x;
        int k = 2;
        while(Math.abs(term) > e) {
            result += term;
            term = -term * x / k;
            k++;
        }
        return result;
    }

    public static double lnOnePlusX(double x) {
        return Math.log(1 + x);
    }

    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            System.out.println("Введите x ∈ (-1,+1): ");
            String line = br.readLine();
            BigDecimal ourNumber = new BigDecimal(line);
            double e = 0.0001; // Задаем точность вычислений
            System.out.println("Результат через Math.log(1+x): ");
            double result = lnOnePlusX(ourNumber.doubleValue());
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(3);
            System.out.println(formatter.format(result));
            System.out.println("Результат через формулу: ");
            double myResult = myFunction(ourNumber.doubleValue(), e);
            System.out.println(formatter.format(myResult));
        } catch (NumberFormatException e) {
            System.out.println("Не число");
        } catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры");
        }
    }
}