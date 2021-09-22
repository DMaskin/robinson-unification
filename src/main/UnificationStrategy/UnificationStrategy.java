package main.UnificationStrategy;

import main.terms.Substitution;
import main.terms.Term;

/**
 * Интерфейс предоставляет метод нахождения наиболее общего унификатора пары термов
 */
public interface UnificationStrategy {
    Substitution unify(Term s, Term t);
}
