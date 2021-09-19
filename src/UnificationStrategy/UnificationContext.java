package UnificationStrategy;

import terms.Substitution;
import terms.TermPair;

/**
 * Класс <code>UnificationContext</code> реализует контекст унификации в соотвествии с паттерном
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
     * Возвращает наиболее общий унификатор {@link TermPair пары термов}
     * @param termPair пара термов
     * @return {@link Substitution наиболее общий унификатор пары термов}
     */
    public Substitution executeStrategy(TermPair termPair) {
        return strategy.unify(termPair);
    }
}
