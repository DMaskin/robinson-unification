package TermFactories;

import terms.FuncSymbWithArgs;
import terms.IndConstant;
import terms.IndVariabe;
import terms.Term;
import java.util.List;

/**
 * Интерфейс <code>TermFactoty</code> предоставляет методы создания {@link Term термов}: <br>
 * {@link IndVariabe}, {@link IndConstant}, {@link FuncSymbWithArgs}
 */
public interface TermFactory {
    /**
     * Возвращает индивидную переменную с идентификатором <code>name</code>
     * @param name идентификатор
     * @return индивидная переменная с идентификатором <code>name</code>
     */
    IndVariabe createIndVariable(String name);

    /**
     * Возвращает иднивидную константу с идентификатором <code>name</code>
     * @param name идентификатор
     * @return индивидная константа с идентификатором <code>name</code>
     */
    IndConstant createIndConstant(String name);

    /**
     * Возвращает функциональный символ с идентификатором <code>name</code> и списком аргументов <code>args</code>
     * @param name идентификатор
     * @param args список аргументов
     * @return функциональный символ с идентификатором <code>name</code> и списком аргументов <code>args</code>
     */
    FuncSymbWithArgs createFSymb(String name, List<Term> args);

    /**
     * Возвращает функциональный символ с идентификатором <code>name</code> и аргументом <code>arg</code>
     * @param name идентификатор
     * @param arg аргумент
     * @return функциональный символ с идентификатором <code>name</code> и аргументом <code>arg</code>
     */
    FuncSymbWithArgs createFSymb(String name, Term arg);
}
