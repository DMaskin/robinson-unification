package main.SyntaxAnalyzer;

import main.terms.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Класс <code>main.SyntaxAnalyzer.main.SyntaxAnalyzer</code> реализует синтаксический анализ {@link Term термов} в строковом представлении. <br><br>
 *
 * Создаёт экземпляры {@linkplain Term термов} из {@link String входной последовательности символов}.
 */
public class SyntaxAnalyzer {

    //private Term term = null;

    /**
     * Возвращает массив лексем после разбиения входной строки
     * @param str входная строка
     * @return массив лексем в строковом представлении
     */
    private String[] splitIntoSymbols(String str) {
        return str.split("[,()]");
    }

    /**
     * Возвращает {@link Term терм}, построенный из лексемы в строковом представлении
     * @param str лексема в строковом представлении
     * @return {@link Term терм}
     * @throws IllegalArgumentException если некорректно расставлены скобки или обнаружен неизвестный символ
     */
    public Term parse(String str) {
        str = str.replaceAll(" ", "");

        String[] parsedLexem = splitIntoSymbols(str);

        Term term = null;
        // if CONSTANT or VARIABLE
        if (parsedLexem.length == 1) {
            if (ParsingUtils.defineTermPattern(parsedLexem[0]) == ParsingUtils.TermPattern.INDIVIDUAL_VARIABLE) {
                term = new IndVariabe(parsedLexem[0]);
            } else if (ParsingUtils.defineTermPattern(parsedLexem[0]) == ParsingUtils.TermPattern.INDIVIDUAL_CONSTANT) {
                term = new IndConstant(parsedLexem[0]);
            } else {
                throw new IllegalArgumentException("Single-character lexeme is not a Variable or Constant: " +  ParsingUtils.defineTermPattern(parsedLexem[0]));
            }

        }
        // FuncSymb or NULL
        else {
            // if first symbol not a funcSymb
            if (ParsingUtils.defineTermPattern(parsedLexem[0]) != ParsingUtils.TermPattern.FUNCTION_SYMBOL_WITH_ARGS) {
                System.out.println("defineTermPattern(parsedLexem[0]) != TermPattern.FUNCTION_SYMBOL_WITH_ARGS");
                throw new IllegalArgumentException("First symbol not a Functional Symbol: " +  "'" + parsedLexem[0] + "'");
            }
            // if paranthes not match
            else if (!isParenthesisMatch(str)) {
                throw new IllegalArgumentException("Paranthes not match: " + "'" + str + "'");
            } else {
                for (String symbol : parsedLexem) {
                    if ((!symbol.isEmpty())
                            && (ParsingUtils.defineTermPattern(symbol) != ParsingUtils.TermPattern.INDIVIDUAL_VARIABLE
                            && ParsingUtils.defineTermPattern(symbol) != ParsingUtils.TermPattern.INDIVIDUAL_CONSTANT
                            && ParsingUtils.defineTermPattern(symbol) != ParsingUtils.TermPattern.FUNCTION_SYMBOL_WITH_ARGS)) {
                        throw new IllegalArgumentException("Unknown symbol: " + "'" + str + "'");
                    }
                }
                term = fromStringToFunc(str);

            }

        }

        if (term == null)
            throw new IllegalArgumentException("Invalid input string : " + "'" + str + "'");

        return term;
    }

    /**
     * Проверяет корректность расстановки скобок <code>(</code> и <code>)</code> во входной строке
     * @param str входная строка
     * @return <code>true</code> если и только если скобки в строке расставлены правильно, иначе – <code>false</code>
     */
    private boolean isParenthesisMatch(String str) {

        Stack<Character> stack = new Stack<Character>();

        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);

            if (c == '(') {
                stack.push(c);
            }

            else if (c == ')') {
                if (stack.empty())
                    return false;
                else if (stack.peek() == '(')
                    stack.pop();
                else
                    return false;
            }
        }
        return stack.empty();
    }

    // convert from string to func

    /**
     * Возвращает {@link Term терм}, построенный из лексемы в строковом представлении
     * @param str входная строка
     * @return функциональный символ с термами-аргументами если лексема не является
     * {@link IndVariabe индивидной переменной} и не явлется {@link IndConstant индивидной константой}.
     */
    private Term fromStringToFunc(String str) {

        FuncSymbWithArgs funcSymbWithArgs = null;

        if (!str.contains("(")) {

            if (ParsingUtils.defineTermPattern(str) == ParsingUtils.TermPattern.INDIVIDUAL_VARIABLE) {
                return new IndVariabe(str);
            } else if (ParsingUtils.defineTermPattern(str) == ParsingUtils.TermPattern.INDIVIDUAL_CONSTANT) {
                return new IndConstant(str);
            }

        } else {
            String name = str.substring(0, str.indexOf("("));
            String funcBody = str.substring(str.indexOf("(") + 1,
                    str.lastIndexOf(")"));

            int paranthesesCounter = 0;
            String arg = "";
            List<Term> args = new ArrayList<Term>();

            for (int i = 0; i < funcBody.length(); ++i) {
                char curr = funcBody.charAt(i);
                if (curr == ',') {
                    if (paranthesesCounter == 0) {
                        args.add(fromStringToFunc(arg));
                        arg = "";
                        continue;
                    }
                } else if (curr == ')') {
                    paranthesesCounter--;

                } else if (curr == '(') {
                    paranthesesCounter++;
                }
                arg += curr;

            }
            args.add(fromStringToFunc(arg));

            funcSymbWithArgs = new FuncSymbWithArgs(name, args);
        }
        return funcSymbWithArgs;
    }

}
