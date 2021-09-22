package main.SyntaxAnalyzer;

import main.terms.FuncSymbWithArgs;
import main.terms.IndConstant;
import main.terms.IndVariabe;

import java.util.Arrays;
import java.util.List;

/**
 * Класс <code>main.SyntaxAnalyzer.ParsingUtils</code> реализует утилиты { синтаксического анализа}
 * и хранит константы-идентификаторы {@link main.terms.Term термов}
 */
public class ParsingUtils {
    /**
     * Перечисление типов терма – список именованных констант
     */
    public enum TermPattern {
        INDIVIDUAL_VARIABLE, INDIVIDUAL_CONSTANT, FUNCTION_SYMBOL_WITH_ARGS
    }

    /**
     * Список индентификаторов индивидных констант {@link IndConstant}
     */
    private static final List<String> IND_CONSTANTS = Arrays.
            asList("a", "b", "c", "d");

    /**
     * Список индентификаторов индивидных переменных {@link IndVariabe}
     */
    private static final List<String> IND_VARIABLES = Arrays
            .asList("x", "y", "z", "u", "v", "w");

    /**
     * Список идентификаторов функциональных символов {@link FuncSymbWithArgs}
     */
    private static final List<String> FUNC_SYMBS = Arrays.
            asList("P", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t");

    /**
     * Возвращает имя типа терма по его идентификатору
     * @param lexem идентификатор терма
     * @return имя тип терма
     * @throws IllegalArgumentException – если указан неизвестный идентификатор терма
     */
    public static TermPattern defineTermPattern(String lexem) {
        lexem = lexem.substring(0, 1);

        if (IND_VARIABLES.contains(lexem)) {
            return TermPattern.INDIVIDUAL_VARIABLE;
        } else if (IND_CONSTANTS.contains(lexem)) {
            return TermPattern.INDIVIDUAL_CONSTANT;
        } else if (FUNC_SYMBS.contains(lexem)) {
            return TermPattern.FUNCTION_SYMBOL_WITH_ARGS;
        } else {
            throw new IllegalArgumentException("Unknown identifier of term");
        }
    }

}
