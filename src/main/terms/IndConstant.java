package main.terms;

import java.util.List;

/**
 * Класс <code>IndConst</code> реализует индивидную константу.
 */
public class IndConstant extends Term {
    /**
     * Идентификатор
     */
    private String name;

    /**
     * Создаёт индивидную константу <code>name</code>
     * @param name идентификатор
     */
    public IndConstant(String name) {
        this.name = name;
    }

    /**
     * Возвращает идентификатор индивидной константы
     * @return идентификатор индивидной константы
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Проверяет, входит ли <code>term</code> в индивидную константу
     * @param term терм, вхождение которого проверяется
     * @return всегда <code>false</code> по определению индивидной константы
     */
    @Override
    public boolean occurs(Term term) {
        return false;
    }

    /**
     * Выполняет подстановку в иднивидной константе: заменяет все вхождения <code>oldTerm</code> на <code>newTerm</code>
     * @param oldTerm заменяемый терм
     * @param newTerm заменяющий терм
     * @return индивидная константа после замены всех вхождений <code>oldTerm</code> на <code>newTerm</code>
     */
    @Override
    public Term substitute(Term oldTerm, Term newTerm) {
        return this;
    }

    /**
     * Сравнивает данную индивидную константу с указанным объектом
     * @param object объект для сравнения с данной индивидной константой
     * @return <code>true</code> если и только если аргумент не равен <code>null</code> и является
     * индивидной константой, представляющей данную индивидную константу,
     * в противном случае – <code>false</code>
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        IndConstant indConstant = (IndConstant) object;
        return name.equals(indConstant.getName());
    }

    /**
     * Возвращает хэш-код данной индивидной константы
     * @return значение хэш-кода
     */
    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    /**
     * Возвращает строковое представление индивидной константы
     * @return строковое представление индивидной константы
     */
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int getArgumentCount() {
        return 0;
    }

    @Override
    public List<Term> getArgs() {
        throw new IllegalStateException("IndConst has no arguments");
    }
}
