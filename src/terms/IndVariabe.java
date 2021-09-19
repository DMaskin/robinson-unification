package terms;

import java.util.List;

/**
 * Класс <code>IndVariable</code> реализует индивидную переменную.
 */
public class IndVariabe extends Term {
    /**
     * Идентификатор
     */
    private String name;

    /**
     * Создаёт индивидную переменную <code>name</code>
     * @param name идентификатор
     */
    public IndVariabe(String name) {
        this.name = name;
    }

    /**
     * Возвращает идентификатор индивидной переменной
     * @return идентификатор индивидной переменной
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Проверяет, входит ли <code>term</code> в индивидную переменную
     * @param term терм, вхождение которого проверяется
     * @return всегда <code>false</code> по определению индивидной переменной
     */
    @Override
    /*public boolean occurs(Term term) {
        return false;
    }*/

    public boolean occurs(Term term) {
        if (getName().equals(term.getName()))
            return true;
        return false;
    }

    /**
     * Выполняет подстановку в иднивидной переменной: заменяет все вхождения <code>oldTerm</code> на <code>newTerm</code>
     * @param oldTerm заменяемый терм
     * @param newTerm заменяющий терм
     * @return индивидная переменная после замены всех вхождений <code>oldTerm</code> на <code>newTerm</code>
     */
    @Override
    public Term substitute(Term oldTerm, Term newTerm) {
        if (getName() != null && equals(oldTerm)) {
            return newTerm;
        }

        return this;
    }

    /**
     * Сравнивает данную индивидную переменную с указанным объектом
     * @param object объект для сравнения с данной индивидной переменной
     * @return <code>true</code> если и только если аргумент не равен <code>null</code> и является
     * индивидной переменной, представляющей данную индивидную переменную,
     * в противном случае – <code>false</code>
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        IndVariabe indVariabe = (IndVariabe) object;
        if (name == null && indVariabe.getName() == null) {
            return true;
        }

        return name.equals(indVariabe.getName());
    }

    /**
     * Возвращает хэш-код данной индивидной переменной
     * @return значение хэш-кода
     */
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    /**
     * Возвращает строковое представление индивидной переменной
     * @return строковое представление индивидной переменной
     */
    @Override
    public String toString() {
        return getName() != null ? getName() : "_";
    }

    @Override
    public int getArgumentCount() {
        return 0;
    }

    @Override
    public List<Term> getArgs() {
        throw new IllegalStateException("IndVar has no arguments");
    }

}
