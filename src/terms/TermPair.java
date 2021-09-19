package terms;

/**
 * Класс <code>TermPair</code> реализует пару {@link Term термов}
 */
public class TermPair {
    private Term term1;

    private Term term2;

    /**
     * Создаёт пару термов <code>term1, term2</code>
     * @param term1 первый терм
     * @param term2 второй терм
     */
    public TermPair(Term term1, Term term2) {
        this.term1 = term1;
        this.term2 = term2;
    }

    /**
     * Возвращает первый терм пары термов
     * @return первый терм пары термов
     */
    public Term getFirsTerm() {
        return term1;
    }

    /**
     * Возвращает второй терм пары термов
     * @return второй терм пары термов
     */
    public Term getSecondTerm() {
        return term2;
    }

    /**
     * Возвращает строковое представление пары термов
     * @return строковое представление пары термов
     */
    @Override
    public String toString() {
        return String.format("TermsPair(term1 = '%s', term2 = '%s')", term1, term2);
    }

}
