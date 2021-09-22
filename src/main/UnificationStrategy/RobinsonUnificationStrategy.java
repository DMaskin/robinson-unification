package main.UnificationStrategy;

import main.terms.*;


/**
 * Класс <code>main.UnificationStrategy.main.UnificationStrategy.RobinsonUnificationStrategy</code> реализует алгоритм унификации (аогоритм Робинсона) для нахождения {@link Substitution наиболее общего унификатора}
 * указанной { пары термов} <br><br>
 * <p>
 * Алгоритм унификации  возвращает наиболее общий унификатор в виде {@link Substitution подстановки (Substitution},
 * если {пара термов} унифицируема, иначе – <code>"не унифицируемо</code> <br><br>
 */
public class RobinsonUnificationStrategy implements UnificationStrategy {

    /**
     * Возвращает НОУ  { пары термов} в виде подстановки
     *
     * @param s терм s
     * @param t терм t
     * @return НОУ в виде списка пар термов
     * @throws IllegalArgumentException если термы не унифицируемы
     */
    @Override
    public Substitution unify(Term s, Term t) {
        if (s instanceof IndVariabe) {
            if (t.occurs(s)) {
                throw new IllegalArgumentException("Not unifiable: " + t.getName() + " occurs " + s.getName());
            } else {
                return new Substitution(new TermVariablePair(t, (IndVariabe) s));
            }
        }
        if (t instanceof IndVariabe) {
            if (s.occurs(t))
                throw new IllegalArgumentException("Not unifiable: " + s.getName() + " occurs " + t.getName());
            else
                return new Substitution(new TermVariablePair(s, (IndVariabe) t));
        }

        if (t instanceof FuncSymbWithArgs && s instanceof FuncSymbWithArgs) {

            if (!t.getName().equals(s.getName()) || t.getArgs().size() != s.getArgs().size()) {
                throw new IllegalArgumentException("Not unified: Substitution can't be executed because functional symbols "
                        + "'" + t.getName() + "''" + " and " + "'" + s.getName() + "'" + " are different "
                        + "or have a different number of arguments");
            }
        }

        if (t instanceof IndConstant || s instanceof IndConstant)
            if (t.getName() == s.getName())
                return new Substitution();
            else
                throw new IllegalArgumentException("Not unified: individual constants " + "'" + t.getName() +
                        "'" + " and " + "'" + s.getName() + "'" + " are different");

        Substitution sigma = new Substitution();

        for (int i = 0; i < s.getArgumentCount(); i++) {
            Substitution tau = unify(applySubst(s.getArgs().get(i), sigma),
                    applySubst(t.getArgs().get(i), sigma));
            //Substitution tau = unify(s.getArgs().get(i), t.getArgs().get(i));
            sigma = sigma.compose(tau);
        }

        return sigma;
    }

    Term applySubst(Term term, Substitution substitution) {
        for (TermVariablePair pair : substitution.getValue()) {
            if (term.occurs(pair.getVariable())) {
                //System.out.println("Замена: " + term + "на { " + pair.getVariable() + "/" + pair.getTerm() + " }");
                term = term.substitute(pair.getVariable(), pair.getTerm());
                //System.out.println("Терм после замены: " + term);
            }
        }
        return term;
    }
}
