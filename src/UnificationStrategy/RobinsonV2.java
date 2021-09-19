package UnificationStrategy;

import terms.*;

public class RobinsonV2 {

    //static Substitution sigma = new Substitution();


    static public Substitution unify(Term s, Term t) {
        if (s instanceof IndVariabe) {
            if (t.occurs(s)) {
                throw new IllegalArgumentException("not unifiable");
            } else {
                return new Substitution(new TermVariablePair(t, (IndVariabe) s));
            }
        }
        if (t instanceof IndVariabe) {
            if (s.occurs(t))
                throw new IllegalArgumentException("not unifiable");
            else
                return new Substitution(new TermVariablePair(s, (IndVariabe) t));
        }

        if (t instanceof FuncSymbWithArgs && s instanceof FuncSymbWithArgs) {

            if (!t.getName().equals(s.getName()) || t.getArgs().size() != s.getArgs().size()) {
                throw new IllegalArgumentException("not unified");
            }
        }

        if (t instanceof IndConstant || s instanceof IndConstant)
            if (t.getName() == s.getName())
                return new Substitution(); // Вернуть пустую подстановку { }
            else
                throw new IllegalArgumentException("not unified");

        Substitution sigma = new Substitution();

        for (int i = 0; i < s.getArgumentCount(); i++) {
            Substitution tau = unify(execSubstitution(s.getArgs().get(i), sigma), execSubstitution(t.getArgs().get(i), sigma));
            //Substitution tau = unify(s.getArgs().get(i), t.getArgs().get(i));
            sigma = sigma.compose(tau);
            //sigma.substitute(tau.getValue().get(0).getVariable(), tau.getValue().get(0).getTerm());
        }

        return sigma;
    }

    static Term execSubstitution(Term term, Substitution substitution) {
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
