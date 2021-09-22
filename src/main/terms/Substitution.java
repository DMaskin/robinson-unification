package main.terms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Класс <code>Substitution</code> реализует подстановку в виде списка пар
 * <code>{терм/переменная}</code> элементов подстановки. <br><br>
 * Подстановка <code>θ</code>, такая что <code>Dom(θ)⊆ {x1 … xN}</code> и <code>θ(x1) = t1 … θ(xN) = tN</code>, <br>
 * представляется в виде множества <code>{t1/x1, … ,tN/xN}</code>, где <code>t1 … tN</code> – термы, а <code>x1 … xN</code> – индивидные переменные. <br><br>
 *
 * Синтаксическая спецификация подстановки (<code>Substitution</code>) в записи формы Бэкуса-Наура: <br><br>
 * Следующие символы являются терминалами: <br>
 * <code>1)</code> строчные буквы из алфавита, такие как <code>a, b, c</code> <br>
 * <code>2)</code> символы операторов, такие как <code>/</code> <br>
 * <code>3)</code> символы пунктуации, такие как запятые, скобки и т. п. <br>
 * <code>4)</code> цифры <code>0, 1, ... , 9</code><br><br>
 *
 * Следующие символы являются нетерминалами: <br>
 * <code>1)</code> слова, начинающиеся с букв из алфавита, такие как <code>A, B, C</code> и <code>a, b, c</code> и т. д. <br><br>
 *
 * <code>Substitution ::= "{" "}" | "{" Term "/" Variable {"," Term "/" Variable} "}"</code> <br><br>
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
public class Substitution {
    //private List<TermPair> value;
    private List<TermVariablePair> value;

    public Substitution() {
        this.value = new ArrayList<>();
    }

    /**
     * Создаёт подстановку со списком {@link TermVariablePair пар: {терм/переменная} }
     * @param termVariablePairs список пар <code>{терм/переменная}</code>
     */
    public Substitution(List<TermVariablePair> termVariablePairs) {
        this.value = termVariablePairs;
    }

    /**
     * Создаёт подстановку с {@link TermVariablePair парой {терм/переменная} }
     * @param termVariablePair пара {терм/переменная}
     */
    public Substitution(TermVariablePair termVariablePair) {
        this(Arrays.asList(termVariablePair));
    }

    /**
     * Добавляет пару <code>{терм/переменная}</code> в подстановку
     * @param termVariablePair пара <code>{терм/переменная}</code>
     * @return подстановка после добавления пары <code>{терм/переменная}</code>
     */
    public Substitution addTermPair(TermVariablePair termVariablePair) {
        this.value.add(termVariablePair);
        return this;
    }

    /**
     * Получает список пар <code>{терм/переменная}</code> элементов подстановки
     * @return список пар <code>{терм/переменная}</code> элементов подстановки
     */
    public List<TermVariablePair> getValue() {
        return value;
    }

    public TermVariablePair getValueOneElementSubs() {
        return value.get(0);
    }

    /**
     * Заменяет в подстановке все вхождения переменной <code>variable</code> на терм <code>term</code>
     * @param variable переменная
     * @param term терм
     */
    public Substitution substitute(IndVariabe variable, Term term) {
        List<TermVariablePair> value = this.getValue();
        for (int i = 0; i < value.size(); i++) {
            value.set(i, new TermVariablePair(
                    value.get(i).getTerm().substitute(variable, term),
                    (IndVariabe) value.get(i).getVariable().substitute(variable, term)));
        }
        return new Substitution(value);
    }

    public Substitution compose(Substitution tau) {
        tau.getValue().forEach(e -> this.getValue().add(e));

        List<TermVariablePair> delta = new ArrayList<>();

        for (int i = 0; i < this.getValue().size(); i++) {
            Term sigmaTerm = this.getValue().get(i).getTerm();
            IndVariabe sigmaVar = this.getValue().get(i).getVariable();

            Term tauTerm = tau.getValue().get(0).getTerm();
            IndVariabe tauVar = tau.getValue().get(0).getVariable();

            delta.add(new TermVariablePair(sigmaTerm.substitute(tauVar, tauTerm), sigmaVar));
        }

        return new Substitution(delta);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{ ");
        Iterator<TermVariablePair> termVariablePairIterator = this.getValue().iterator();
        while (termVariablePairIterator.hasNext()) {
            str.append(termVariablePairIterator.next().toString());

            if (termVariablePairIterator.hasNext()) {
                str.append(", ");
            }
        }

        str.append(" }");
        return str.toString();
    }
}
