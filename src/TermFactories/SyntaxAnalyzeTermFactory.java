package TermFactories;

import terms.FuncSymbWithArgs;
import terms.IndConstant;
import terms.IndVariabe;
import terms.Term;
import utils.ParsingUtils;

import java.util.List;

/**
 * Класс <code>SyntaxAnalyzeTermFactory</code> реализует фабрику создания <code>TermFactory</code>. <br>
 * Класс <code>SyntaxAnalyzeTermFactory</code> реализует методы создания {@link Term термов} с использованием
 * {@link SyntaxAnalyzer.SyntaxAnalyzer синтаксического анализа}.
 */
public class SyntaxAnalyzeTermFactory implements TermFactory {
    /**
     * Возвращает индивидную переменную с идентификатором <code>name</code>
     * @param name идентификатор
     * @return индивидная переменная с идентификатором <code>name</code>
     * @throws IllegalArgumentException если указан неизвестный идентификатор индивидной переменной
     */
    @Override
    public IndVariabe createIndVariable(String name) {
        IndVariabe term = null;

        if (ParsingUtils.defineTermPattern(name) == ParsingUtils.TermPattern.INDIVIDUAL_VARIABLE) {
            term = new IndVariabe(name);
        }

        if (term == null) {
            throw new IllegalArgumentException("Unknown identifier of individual variable");
        }

        return term;
    }

    /**
     * Возвращает индивидную константу с идентификатором <code>name</code>
     * @param name идентификатор
     * @return индивидная константа с идентификатором <code>name</code>
     * @throws IllegalArgumentException если указан неизвестный идентификатор индивидной константы
     */
    @Override
    public IndConstant createIndConstant(String name) {
        IndConstant term = null;

        if (ParsingUtils.defineTermPattern(name) == ParsingUtils.TermPattern.INDIVIDUAL_CONSTANT) {
            term = new IndConstant(name);
        }

        if (term == null) {
            throw new IllegalArgumentException("Unknown identifier of individual constant");
        }

        return term;
    }

    /**
     * Возвращает функциональный символ с идентификатором <code>name</code> и списком-аргументов <code>arg</code>
     * @param name идентификатор
     * @param args список аргументов
     * @return функциональный символ с идентификатором <code>name</code> и списком-аргументов <code>arg</code>
     * @throws IllegalArgumentException если указан неизвестный идентификатор функционального символа
     */
    @Override
    public FuncSymbWithArgs createFSymb(String name, List<Term> args) {
        FuncSymbWithArgs term = null;

        switch (ParsingUtils.defineTermPattern(name)) {
            case FUNCTION_SYMBOL_WITH_ARGS:
                term = new FuncSymbWithArgs(name, args);
                break;
        }

        if (term == null) {
            throw new IllegalArgumentException("Unknown identifier of functional symbol");
        }

        return term;
    }

    /**
     * Возвращает функциональный символ с идентификатором <code>name</code> и аргументом <code>arg</code>
     * @param name идентификатор
     * @param arg аргумент
     * @return функциональный символ с идентификатором <code>name</code> и аргументом <code>arg</code>
     * @throws IllegalArgumentException если указан неизвестный идентификатор функционального символа
     */
    @Override
    public FuncSymbWithArgs createFSymb(String name, Term arg) {
        FuncSymbWithArgs term = null;

        if (ParsingUtils.defineTermPattern(name) == ParsingUtils.TermPattern.FUNCTION_SYMBOL_WITH_ARGS) {
            term = new FuncSymbWithArgs(name, arg);
        }

        if (term == null) {
            throw new IllegalArgumentException("Unknown identifier of functional symbol");
        }

        return term;
    }

}
