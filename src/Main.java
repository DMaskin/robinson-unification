import SyntaxAnalyzer.SyntaxAnalyzer;
import TermFactories.CustomIdentsTermFactory;
import TermFactories.SyntaxAnalyzeTermFactory;
import TermFactories.TermFactory;
import UnificationStrategy.RobinsonUnificationStrategy;
import UnificationStrategy.RobinsonV2;
import UnificationStrategy.UnificationContext;
import terms.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Класс реализует точку входа в программу
 */
public class Main {
    /**
     * Сканнер для ввода данных с клавиатуры
     */
    private static Scanner scanner = new Scanner(System.in);

    //Тестовый пример №1: P(y,f(u,b)) && P(z,y)
    //Тестовый пример №2: P(g(x),x,f(g(y))) && P(z,a,f(z))
    //Тестовый пример №3: P(x,f(x,y)) && P(g(y),f(g(a),z))

    public static void main(String[] args) {
        //Переменная для выбора повторной унификации термов
        byte ifTryAgain = 0;

        //Бесконечный цикл чтобы не запускать программу каждый раз
        while (true) {
            //region Syntax Analyze TF
            TermFactory strictTermFactory = new SyntaxAnalyzeTermFactory();
            Term firstTerm = strictTermFactory.createFSymb("f1",
                    strictTermFactory.createFSymb("g1",
                            Arrays.asList(strictTermFactory.createIndVariable("x1"), strictTermFactory.createIndVariable("y1"))));
            System.out.println("Term 'f1(g1(x1, y1)' = " + firstTerm.toString());
            //endregion

            //region Custom Idents TF
            TermFactory customTermFactory = new CustomIdentsTermFactory();
            Term customTerm = customTermFactory.createFSymb("f2021",
                    customTermFactory.createFSymb("g0000",
                            Arrays.asList(customTermFactory.createIndVariable("xXx"), customTermFactory.createIndVariable("yYy"))));
            System.out.println("Term 'f2021(g0000(xXx, yYy)' = " + customTerm.toString());
            System.out.println();
            //endregion

            System.out.println("Unification of a pair of terms: ");
            System.out.println("\n");

            /*System.out.println("Type first term: ");
            //Первая входная строка - введённая строка лексем
            String firstInputString = scanner.nextLine();

            System.out.println("Type second term: ");
            //Вторая входная строка - введённая строка лексем
            String secondInputString = scanner.nextLine();*/

            String firstInputString = "P(g(x),x,f(g(y)))";
            String secondInputString = "P(z,a,f(z))";
            //Тестовый пример №3: P(x,f(x,y)) && P(g(y),f(g(a),z))

            //Тестовый пример №2: P(g(x),x,f(g(y))) && P(z,a,f(z))

            //Экземпляр для парсинга входных строк
            SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer();

            Term term1 = syntaxAnalyzer.parse(firstInputString);
            Term term2 = syntaxAnalyzer.parse(secondInputString);

            System.out.println();

            //Если входные строки, представляющие термы, введены корректно
            if (term1 != null && term2 != null) {
                TermPair termPair = new TermPair(term1, term2);
                System.out.println("Find the unifier of " + termPair.getFirsTerm() + " and "
                        + termPair.getSecondTerm());

                //Контекст для выбора алгоритма унификации
                UnificationContext context = new UnificationContext(new RobinsonUnificationStrategy());

                //Алгоритм Робинсона возвращает подстановку – НОУ пары термов
                //Substitution generalUnifier = context.executeStrategy(termPair);
                Substitution generalUnifier = RobinsonV2.unify((FuncSymbWithArgs) term1, (FuncSymbWithArgs) term2);


                //если НОУ не найден
                if (generalUnifier == null) {
                    System.out.println("Terms can't be unified");
                    //если НОУ найден, вывод на экран
                } else {
                    System.out.print("General unifier: ");
                    StringBuffer generalUnifierStr = new StringBuffer("{ ");
                    Iterator<TermVariablePair> termVariablePairIterator = generalUnifier.getValue().iterator();
                    while (termVariablePairIterator.hasNext()) {
                        generalUnifierStr.append(termVariablePairIterator.next().toString());

                        if (termVariablePairIterator.hasNext()) {
                            generalUnifierStr.append(", ");
                        }
                    }

                    generalUnifierStr.append(" }");
                    System.out.print(generalUnifierStr.toString());
                    System.out.println();
                }
                //Входная строка введена некорректно
            } else {
                System.out.println("Incorrect lexem' syntax. Try again.");
            }

            System.out.println("\nType '1' – Try again");
            System.out.println("Type '0' – Exit\n");

            ifTryAgain = scanner.nextByte();

            //если введён '0' – завершение программы
            if (ifTryAgain == 0) {
                break;
            }

        }
    }
}