package terms;

/**
 * Класс <code>TermVariablePair</code> реализует пару:
 * { {@linkplain Term Терм} / {@linkplain IndVariabe Индивидная переменная} },
 * являющуюся элементом {@linkplain Substitution подстановки}
 */
public class TermVariablePair {
    private IndVariabe indVariabe;
    private Term term;

    /**
     * Создаёт пару <code>{ {@linkplain Term Терм} / {@linkplain IndVariabe Индивидная переменная} } </code>
     * @param indVariabe индивидная переменная
     * @param term терм
     */
    public TermVariablePair(Term term, IndVariabe indVariabe) {
        this.indVariabe = indVariabe;
        this.term = term;
    }

    /**
     * Возвращает индивидную переменную
     * @return индивидная переменная
     */
    public IndVariabe getVariable() {
        return indVariabe;
    }

    /**
     * Возвращает терм
     * @return терм
     */
    public Term getTerm() {
        return term;
    }

    @Override
    public String toString() {
        return getTerm().toString() + "/" + getVariable().toString();
    }
}
