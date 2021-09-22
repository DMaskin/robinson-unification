package main.terms;

import java.util.List;

/**
 * Абстрактный класс <code>Term</code> реализует терм. <br><br>
 *
 * Множество термов определяется индуктивно: <br>
 * 1) все {@link IndVariabe индивидные переменные} и все {@link IndConstant индивидные константы} являются термами; <br>
 * 2) если <i>t1, ... ,tN</i> – термы, а <i>f</i> является функциональным символом, количество аргументов которого равно <i>N</i>, тогда <br>
 * {@link FuncSymbWithArgs функциональный символ с термами-аргументами <i>f(t1, ... ,tN) </i>} является термом. <br><br>
 *
 * Синтаксическая спецификация терма (<code>Term</code>) в записи формы Бэкуса-Наура: <br><br>
 * Следующие символы являются терминалами: <br>
 * <code>1)</code> строчные буквы из алфавита, такие как <code>a, b, c</code> <br>
 * <code>2)</code> символы операторов, такие как <code>/</code> <br>
 * <code>3)</code> символы пунктуации, такие как запятые, скобки и т. п. <br>
 * <code>4)</code> цифры <code>0, 1, ... , 9</code><br><br>
 *
 * Следующие символы являются нетерминалами: <br>
 * <code>1)</code> слова, начинающиеся с букв из алфавита, такие как <code>A, B, C</code> и <code>a, b, c</code> и т. д. <br><br>
 *
 * <code>Term ::= Constant | Variable | FunctionalSymbol "(" Term {"," Term} ")".</code><br>
 *
 * <code>Constant ::= "a"|"b"|"c"|"d" {Word} {Number}.</code> <br>
 * <code>Variable ::= "x"|"y"|"z"|"u"|"v"|"w" {Word} {Number}.</code> <br>
 * <code>FunctionalSymbol ::= "P"|"e"|"f"|"g"|"h"|"i"|"j"|"k"|"l"|"m"|"n"|"o"|"p"|"q"|"r"|"s"|"t" {Word} {Number}.</code><br><br>
 *
 * <code>Number ::= digit {digit}. </code> <br>
 * <code>Word ::= char {char}.</code> <br>
 * <code>digit ::= "0"|"1"|"2"|"3"|"4"|"5"|"6"|"7"|"8"|"9". </code> <br>
 * <code>char ::= "a"|"b"|"c"|"d"|"e"|"f"|"g"|"h"|"i"|"j"|"k"|"l"|"m"|"n"|"o"|"p"|"q"|"r"|"s"|"t"|"u"|"v"|"w"|"x"|"y"|"z"</code>. <br>
 */
public abstract class Term {
    /**
     * Проверяет, входит ли <code>term</code> в терм
     * @param term терм, вхождение которого проверяется
     * @return <code>true</code> если и только если <code>term</code> входит в терм, в противном случае – <code>false</code>
     */
    public abstract boolean occurs(Term term);

    /**
     * Выполняет подстановку в терме: заменяет все вхождения <code>oldTerm</code> на <code>newTerm</code>
     * @param oldTerm заменяемый терм
     * @param newTerm заменяющий терм
     * @return терм после одновременной замены всех вхождений <code>oldTerm</code> на <code>newTerm</code>
     */
    public abstract Term substitute(Term oldTerm, Term newTerm);

    /**
     * Возвращает строковое представление терма
     * @return строковое представление терма
     */
    public abstract String toString();

    public abstract String getName();

    public abstract int getArgumentCount(); //Может заменить на "size()"?

    public abstract List<Term> getArgs();
}
