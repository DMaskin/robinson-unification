package UnificationStrategy;

import terms.Substitution;
import terms.TermPair;

/**
 * Интерфейс предоставляет метод нахождения наиболее общего унификатора пары термов
 */
public interface UnificationStrategy {
    /**
     * Возвращает наиболее общий унификатор {@link TermPair пары термов}
     * в виде {@linkplain Substitution подстановки}
     * @param termPair пара термов
     * @return {@link Substitution наиболее общий унификатор пары термов}
     */
    Substitution unify(TermPair termPair);
}
