import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.IOException;
import java.lang.IllegalArgumentException;



public class Main {

    public static void main(String[] args) throws IOException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(calc(scanner.nextLine()));
        }
    }




    public static String calc(String input) throws IOException {
        checkExp(input);
        if (input.contains(String.valueOf("+"))) {
            String[] expressionMathematical = input.split("\\+");
            return calculation('+', expressionMathematical);
        }
        if (input.contains(String.valueOf("-"))) {
            String[] expressionMathematical = input.split("-");
            return calculation('-', expressionMathematical);
        }
        if (input.contains(String.valueOf("*"))) {
            String[] expressionMathematical = input.split("\\*");
            return calculation('*', expressionMathematical);
        }
        if (input.contains(String.valueOf("/"))) {
            String[] expressionMathematical = input.split("/");
            return calculation('/', expressionMathematical);
        }
        return "0";
    }





    public static String calculation(char input, String[] numbers) throws IOException {
        String[] numbersAllFirst = {"1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10 ",};
        String[] numbersAllSecond = {" 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", " 10",};
        int count = 0;
        for (int i = 0; i < numbersAllFirst.length; i++) {
            if (numbersAllFirst[i].equals(numbers[0])) {
                count++;
                break;
            }
        }
        for (int j = 0; j < numbersAllSecond.length; j++) {
            if (numbersAllSecond[j].equals(numbers[1])) {
                count++;
                break;
            }
        }
        switch (count) {
            case 2:
                if (input == '+') {
                    return ""+(Integer.parseInt(numbers[0].replace(" ", "")) + Integer.parseInt(numbers[1].replace(" ", "")));
                }
                else if (input == '-') {
                    return ""+(Integer.parseInt(numbers[0].replace(" ", "")) - Integer.parseInt(numbers[1].replace(" ", "")));
                }
                else if (input == '*') {
                    return ""+(Integer.parseInt(numbers[0].replace(" ", "")) * Integer.parseInt(numbers[1].replace(" ", "")));
                }
                else if (input == '/') {
                    return ""+(Integer.parseInt(numbers[0].replace(" ", "")) / Integer.parseInt(numbers[1].replace(" ", "")));
                }
            case 1:
                throw new IOException("Разные системы счисления или неизвестные символы");
            case 0:
                int cout = 0;
                for (String str : numbers) {
                    for (int i = 0; i < str.length(); i++) {
                        if (str.charAt(i)==' ') {
                            cout++;
                        }
                        if (str==numbers[0]&&str.charAt(numbers[0].length()-1)==' ' ){
                            numbers[0].replace(" ", "");
                        }
                        else if (str==numbers[1]&&str.charAt(0)==' ' ){
                            numbers[1].replace(" ", "");
                        }
                        else {
                            throw new IOException("Неверное местоположения символа \"пробел\"");
                        }
                    }
                }
                if (cout!=2) {
                    throw new IOException("Ошибка с колличеством пробелов");
                }
                try {
                    RomanNumerals romanNumerals1 = RomanNumerals.valueOf(numbers[0].replace(" ", ""));
                    RomanNumerals romanNumerals2 = RomanNumerals.valueOf(numbers[1].replace(" ", ""));
                    if (input == '+') {
                        return  converter(romanNumerals1.getNumber() + romanNumerals2.getNumber());
                    }
                    else if (input == '-') {
                        return converter(romanNumerals1.getNumber() - romanNumerals2.getNumber());
                    }
                    else if (input == '*') {
                        return converter(romanNumerals1.getNumber() * romanNumerals2.getNumber());
                    }
                    else if (input == '/') {
                        return converter(romanNumerals1.getNumber() / romanNumerals2.getNumber());
                    }
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Таких символов нет ни в одной системе счисления");
                }

        }
        return "0";
    }





    public static void checkExp(String input) throws IOException{
        if (input.length() > 9 || input.length() < 5) {
            throw new IOException("Невверный ввод, вышли за границы допустимой длинны строки");
        }
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '/')
                count++;
        }
        if (count != 1) {
            throw new IOException("Ведите выражение с одним  математическим оператором");
        }
    }





    public static String converter(int n) throws IOException{
        if(n<1)
        {
            throw new IOException("В римской системе счисления нет \"0\" и отричательцых значений");
        }
        RomanNumerals[] values = RomanNumerals.values();
        StringBuilder anser = new StringBuilder("");
        int ten = n/10;
        int nine = n%10/9;
        int eight = n%10%9/8;
        int seven = n%10%9%8/7;
        int six= n%10%9%8%7/6;
        int five = n%10%9%8%7%6/5;
        int four =n%10%9%8%7%6%5/4;
        int three =n%10%9%8%7%6%5%4/3;
        int two =n%10%9%8%7%6%5%4%3/2;
        int one =n%10%9%8%7%6%5%4%3%2/1;
        for(int i = 0; i < ten; i++){
            anser.append(values[9]);
        }
        for(int i = 0; i < nine; i++){
            anser.append(values[8]);
        }
        for(int i = 0; i < eight; i++){
            anser.append(values[7]);
        }
        for(int i = 0; i < seven; i++){
            anser.append(values[6]);
        }
        for(int i = 0; i < six; i++){
            anser.append(values[5]);
        }
        for(int i = 0; i < five; i++){
            anser.append(values[4]);
        }
        for(int i = 0; i < four; i++){
            anser.append(values[3]);
        }
        for(int i = 0; i < three; i++){
            anser.append(values[2]);
        }
        for(int i = 0; i < two; i++){
            anser.append(values[1]);
        }
        for(int i = 0; i < one; i++){
            anser.append(values[0]);
        }
        return new String(anser);
    }
}