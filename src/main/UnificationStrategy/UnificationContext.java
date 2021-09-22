package main.UnificationStrategy;

import main.terms.Substitution;
import main.terms.Term;

/**
 * Класс <code>main.UnificationStrategy.main.UnificationStrategy.UnificationContext</code> реализует контекст унификации в соотвествии с паттерном
 * <code>{@linkplain UnificationStrategy Strategy}</code>
 */
public class UnificationContext {
    private UnificationStrategy strategy;

    /**
     * Создаёт контекст с указанной стратегией унификации
     * @param strategy стратегия унификации
     */
    public UnificationContext(UnificationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Задаёт указанную стратегию унификации
     * @param strategy стратегия унификации
     */
    public void setStrategy(UnificationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Возвращает наиболее общий унификатор {пары термов}
     * @param
     * @return {@link Substitution наиболее общий унификатор пары термов}
     */
    public Substitution executeStrategy(Term s, Term t) {
        return strategy.unify(s, t);
    }
}
