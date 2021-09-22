package main;

import main.SyntaxAnalyzer.SyntaxAnalyzer;
import main.UnificationStrategy.RobinsonUnificationStrategy;
import main.UnificationStrategy.UnificationContext;
import main.terms.*;

import java.util.Scanner;

public class Main {
    //Тестовый пример №1: P(y,f(u,b)) && P(z,y)
    //Тестовый пример №2: P(g(x),x,f(g(y))) && P(z,a,f(z))
    //Тестовый пример №3: P(x,f(x,y)) && P(g(y),f(g(a),z))

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        byte ifTryAgain = 0;

        while (true) {
            System.out.println();

            System.out.println("Unification of a pair of terms: ");

            System.out.println("Type first term: ");
            String firstInputString = scanner.nextLine();
            System.out.println("Type second term: ");
            String secondInputString = scanner.nextLine();

            SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer();

            Term s = syntaxAnalyzer.parse(firstInputString);
            Term t = syntaxAnalyzer.parse(secondInputString);

            //Если входные строки, представляющие термы, введены корректно
            if (s != null && t != null) {

                System.out.println("Find the unifier of " +
                        s + " and " + t);

                UnificationContext context = new UnificationContext(new RobinsonUnificationStrategy());
                Substitution MGU = context.executeStrategy(s, t);

                //если НОУ не найден
                if (MGU == null) {
                    System.out.println("Terms can't be unified");
                } else {
                    System.out.print("Most General Unifier = " + MGU.toString());
                    System.out.println();
                }
                //Входная строка введена некорректно
            } else {
                System.out.println("Incorrect input string. Try again.");
            }

            System.out.println("\nType '1' – Try again");
            System.out.println("Type '0' – Exit\n");

            ifTryAgain = scanner.nextByte();

            if (ifTryAgain == 0) {
                break;
            }

        }
    }
}