package main.terms;

import java.util.List;

/**
 * Класс <code>main.terms.CustomIdentsTermFactory</code> реализует фабрику создания <code>main.terms.TermFactory</code>. <br>
 * Класс <code>main.terms.CustomIdentsTermFactory</code> реализует методы создания {@link Term термов} без использования
 * {@link main.SyntaxAnalyzer синтаксического анализа}.
 */
public class CustomIdentsTermFactory implements TermFactory {
    /**
     * Возвращает индивидную переменную с идентификатором <code>name</code>
     * @param name идентификатор
     * @return индивидная переменная с идентификатором <code>name</code>
     */
    @Override
    public IndVariabe createIndVariable(String name) {
        return new IndVariabe(name);
    }

    /**
     * Возвращает индивидную константу с идентификатором <code>name</code>
     * @param name идентификатор
     * @return индивидная константа с идентификатором <code>name</code>
     */
    @Override
    public IndConstant createIndConstant(String name) throws IllegalArgumentException {
        return new IndConstant(name);
    }

    /**
     * Возвращает функциональный символ с идентификатором <code>name</code> и списком аргументов <code>args</code>
     * @param name идентификатор
     * @param args список аргументов
     * @return функциональный символ с идентификатором <code>name</code> и списком аргументов <code>args</code>
     */
    @Override
    public FuncSymbWithArgs createFSymb(String name, List<Term> args) {
        return new FuncSymbWithArgs(name, args);
    }

    /**
     * Возвращает функциональный символ с идентификатором <code>name</code> и аргументом <code>arg</code>
     * @param name идентификатор
     * @param arg аргумент
     * @return функциональный символ с идентификатором <code>name</code> и аргументом <code>arg</code>
     */
    @Override
    public FuncSymbWithArgs createFSymb(String name, Term arg) {
        return new FuncSymbWithArgs(name, arg);
    }

}
