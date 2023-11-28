import java.util.Scanner;
import java.util.HashMap;
class Main {
    public static String calc(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 3){
            return "";
        }
        boolean flag = true;
        int num1 = 0, num2 = 0;
        if (isRomanNumeral(tokens[0])){
            num1 = romanToArabic(tokens[0]);
            if (isRomanNumeral(tokens[2])){
                num2 = romanToArabic(tokens[2]);
            }
            else{
                return "";
            }
            flag = false;
        }
        else{
            num1 = Integer.parseInt(tokens[0]);
            num2 = Integer.parseInt(tokens[2]);
            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10){
                return "";
            }
        }
        String operator = tokens[1];
        int result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    return "";
                }
                break;
            default:
                return "";
        }
        if (flag){
            return Integer.toString(result);
        }
        else{
            if (result <= 0){
                return "";
            }
            return intToRoman(result);
        }

    }

    public static String intToRoman(int num) {
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (num > 0) {
            if (num - values[i] >= 0) {
                sb.append(symbols[i]);
                num -= values[i];
            } else {
                i++;
            }
        }
        return sb.toString();
    }
    private static boolean isRomanNumeral(String input) {
        return input.matches("[IVXLCDM]+");
    }

    public static int romanToArabic(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && map.get(s.charAt(i)) > map.get(s.charAt(i - 1))) {
                result += map.get(s.charAt(i)) - 2 * map.get(s.charAt(i - 1));
            } else {
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String result = calc(input);
        if (result == ""){
            System.out.println("throws Exception");
        }
        else{
            System.out.println(result);
        }
    }
}