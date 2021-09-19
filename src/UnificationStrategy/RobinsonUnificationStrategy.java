package UnificationStrategy;

import terms.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Класс <code>RobinsonUnificationStrategy</code> реализует алгоритм унификации (аогоритм Робинсона) для нахождения {@link Substitution наиболее общего унификатора}
 * указанной {@link TermPair пары термов} <br><br>
 * <p>
 * Алгоритм унификации  возвращает наиболее общий унификатор в виде {@link Substitution подстановки (Substitution},
 * если {@link TermPair пара термов} унифицируема, иначе – <code>"не унифицируемо</code> <br><br>
 */
public class RobinsonUnificationStrategy implements UnificationStrategy {

    Substitution unifier = new Substitution();

    List<TermPair> workingSet = new ArrayList<>();

    /**
     * Возвращает НОУ  {@linkplain TermPair пары термов} в виде подстановки
     *
     * @param termsToUnify пара термов для унификации
     * @return НОУ в виде списка пар термов
     * @throws IllegalArgumentException если термы не унифицируемы
     */
    @Override
    public Substitution unify(TermPair termsToUnify) {

        /*//List<TermPair> workingSet = new ArrayList<TermPair>();*/
        this.workingSet.add(termsToUnify);

        /*List<TermPair> unifier = new ArrayList<TermPair>();*/

        for (int i = 0; i < workingSet.size(); ++i) {

            TermPair currentPair = workingSet.get(i);
            Term lhs = currentPair.getFirsTerm();
            Term rhs = currentPair.getSecondTerm();

            if (!unify(lhs, rhs, workingSet, unifier)) {
                throw new IllegalArgumentException("Terms cannot be unified");
            }
        }

        return unifier;
    }

    /**
     * Метод, проверяющий возможность унификации двух термов
     *
     * @param lhs        первый терм
     * @param rhs        второй терм
     * @param workingSet список (пар термов) промежуточных результатов унификации
     * @param unifier    унификатор (подстановка)
     * @return <i>false</i> если не термы не унифицируемы, иначе – дальнейшая проверка по типам
     */
    private boolean unify(Term lhs, Term rhs, List<TermPair> workingSet,
                          Substitution unifier) {

        if (lhs instanceof IndConstant) {
            return unify((IndConstant) lhs, rhs, workingSet, unifier);
        } else if (lhs instanceof IndVariabe) {
            return unify((IndVariabe) lhs, rhs, workingSet, unifier);
        } else if (lhs instanceof FuncSymbWithArgs) {
            return unify((FuncSymbWithArgs) lhs, rhs, workingSet, unifier);
        }

        return false;
    }

    /**
     * Метод, проверяющий возможность унификации двух термов: индивидной константы и терма
     *
     * @param constant   индивидная константа
     * @param term       терм
     * @param workingSet список (пар термов) промежуточных результатов унификации
     * @param unifier    унификатор (подстановка)
     * @return <i>false</i> если терм является функциональным символом, иначе – дальнешая проверка по типам
     */
    private boolean unify(IndConstant constant, Term term,
                          List<TermPair> workingSet, Substitution unifier) {

        if (term instanceof IndVariabe) {
            return unify((IndVariabe) term, constant, workingSet, unifier);
        }

        if (constant.equals(term)) {
            return true;
        }

        return false;
    }

    /**
     * Метод, проверяющий возможность унификации двух термов: индивидной переменной и терма
     *
     * @param indVariabe индивидная переменная
     * @param term       терм
     * @param workingSet список (пар термов) промежуточных результатов унификации
     * @param unifier    унификатор (подстановка)
     * @return <i>false</i> если терм содержит индивидную переменную, иначе – <i>true</i>
     */
    private boolean unify(IndVariabe indVariabe, Term term,
                          List<TermPair> workingSet, Substitution unifier) {

        if (indVariabe.equals(term)) {
            return true;
        }

        if (term.occurs(indVariabe)) {
            System.out.println("Substitution can't be executed: " + indVariabe.toString() + " in " + term.toString());
            return false;
        }

        workingSet = substitute(workingSet, indVariabe, term);
        unifier.substitute(indVariabe, term);

        unifier.addTermPair(new TermVariablePair(term, indVariabe));

        return true;
    }

    /**
     * Метод, проверяющий возможность унификации двух термов: функционального символа
     * с термами-аргументами и терма
     *
     * @param funcSymbWithArgs функциональный символ с термами-аргументами
     * @param term             терм
     * @param workingSet       список (пар термов) промежуточных результатов унификации
     * @param unifier          унификатор (подстановка)
     * @return <i>false</i> если терм является иным функциональным символом с термами аргументами
     * и количество аргументов обоих функциональных символов не совпадает. Иначе <i>true</i>
     */
    private boolean unify(FuncSymbWithArgs funcSymbWithArgs, Term term,
                          List<TermPair> workingSet, Substitution unifier) {
        if (term instanceof IndVariabe) {

            return unify((IndVariabe) term, funcSymbWithArgs, workingSet, unifier);

        } else if (term instanceof FuncSymbWithArgs) {
            FuncSymbWithArgs rhsFuncSymb = (FuncSymbWithArgs) term;

            if (!funcSymbWithArgs.getName().equals(rhsFuncSymb.getName())
                    || funcSymbWithArgs.getArgs().size() != rhsFuncSymb.getArgs()
                    .size()) {
                System.out.println("Substitution can't be executed because functional symbols are different " +
                        "or have a different number of arguments");
                return false;
            }

            for (Iterator<Term> lhsArgs = funcSymbWithArgs.getArgs().iterator(), rhsArgs = rhsFuncSymb
                    .getArgs().iterator(); lhsArgs.hasNext()
                         && rhsArgs.hasNext(); ) {

                workingSet.add(new TermPair(lhsArgs.next(), rhsArgs.next()));
            }

            return true;
        }

        return false;
    }

    /**
     * Метод, выполняющий подстановку терма <b>s</b> на терм <b>t</b>
     *
     * @param variable    заменяемый терм (переменная)
     * @param replacement заменяющий терм
     * @param termPairs   пары термов, в которых необходимо выполнить подстановку
     */
    private List<TermPair> substitute(List<TermPair> termPairs, Term variable, Term replacement) {
        for (int i = 0; i < termPairs.size(); i++) {

            TermPair currentPair = termPairs.get(i);
            Term lhs = currentPair.getFirsTerm();
            Term rhs = currentPair.getSecondTerm();

            termPairs.set(i, new TermPair(lhs.substitute(variable, replacement),
                    rhs.substitute(variable, replacement)));
        }
        return termPairs;
    }
}
