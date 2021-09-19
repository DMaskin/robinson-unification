package terms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Класс <code>FuncSymbWithArgs</code> реализует функциональный символ с {@link Term термами-аргументами}.
 */
public class FuncSymbWithArgs extends Term {

    /**
     * Идентификатор
     */
    private String name;

    /**
     * Список аргументов
     */
    private List<Term> args;

    /**
     * Создаёт функциональный символ <code>name</code> с аргументом <code>arg</code>
     * @param name идентификатор
     * @param arg аргумент
     */
    public FuncSymbWithArgs(String name, Term arg) {
        this(name, Arrays.asList(arg));
    }

    /**
     * Создаёт функциональный символ с идентификатором <code>name</code> и списком аргументов <code>args</code>
     * @param name идентификатор
     * @param args список аргументов
     */
    public FuncSymbWithArgs(String name, List<Term> args) {
        this.name = name;
        this.args = args;
    }

    /**
     * Возвращает идентификатор функционального символа
     * @return идентификатор функционального символа
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getArgumentCount() {
        return this.args.size();
    }

    /**
     * Возвращает список аргументов функционального символа
     * @return список аргументов функционального символа
     */
    @Override
    public List<Term> getArgs() {
        return Collections.unmodifiableList(args);
    }

    /**
     * Проверяет, входит ли <code>term</code> в функциональный символ
     * @param term терм, вхождение которого проверяется
     * @return <code>true</code> если и только если <code>term</code> входит в функциональный символ, в противном случае – <code>false</code>
     */
    @Override
    public boolean occurs(Term term) {
        for (Term arg : args) {
            if (arg.equals(term)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Выполняет подстановку в функциональном символе: все вхождения <code>oldTerm</code> заменяются на <code>newTerm</code>
     * @param oldTerm заменяемый терм
     * @param newTerm заменяющий терм
     * @return функциональный символ после одновременной замены всех вхождений <code>oldTerm</code> на <code>newTerm</code>
     */
    @Override
    public Term substitute(Term oldTerm, Term newTerm) {

        List<Term> substitutedArgs = new ArrayList<Term>(args.size());
        for (Term arg : args) {
            substitutedArgs.add(arg.substitute(oldTerm, newTerm));
        }

        return new FuncSymbWithArgs(name, substitutedArgs);
    }

    /**
     * Сравнивает данный функциональный символ с указанным объектом
     * @param object объект для сравнения с данным функциональным символом
     * @return <code>true</code> если и только если аргумент не равен <code>null</code> и является
     * функциональным символом, представляющим данный функциональный символ,
     * в противном случае – <code>false</code>
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        FuncSymbWithArgs funcSymbWithArgs = (FuncSymbWithArgs) object;

        if (!getArgs().equals(funcSymbWithArgs.getArgs()))
            return false;
        if (!getName().equals(funcSymbWithArgs.getName()))
            return false;

        return true;
    }

    /**
     * Возвращает хэш-код данного функционального символа
     * @return значение хэш-кода
     */
    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getArgs().hashCode();
        return result;
    }

    /**
     * Возвращает строковое представление функционального символа
     * @return строковое представление функционального символа
     */
    @Override
    public String toString() {
        StringBuffer funcSymbWithArgsSB = new StringBuffer(getName()).append("(");

        Iterator<Term> args = getArgs().iterator();
        while (args.hasNext()) {
            funcSymbWithArgsSB.append(args.next());

            if (args.hasNext()) {
                funcSymbWithArgsSB.append(", ");
            }
        }

        funcSymbWithArgsSB.append(")");

        return funcSymbWithArgsSB.toString();
    }

}
