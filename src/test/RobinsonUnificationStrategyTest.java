import junit.framework.TestCase;
import main.SyntaxAnalyzer.SyntaxAnalyzer;
import main.UnificationStrategy.RobinsonUnificationStrategy;
import main.UnificationStrategy.UnificationContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RobinsonUnificationStrategyTest extends TestCase {

    @Test
    public void testUnify() {
        //Тестовый пример №1: P(y,f(u,b)) && P(z,y)
        //Тестовый пример №2: P(g(x),x,f(g(y))) && P(z,a,f(z))
        //Тестовый пример №3: P(x,f(x,y)) && P(g(y),f(g(a),z))
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer();

        List<String> expectedMGU = new ArrayList<String>();
        List<String> actualMGU = new ArrayList<String>();

        UnificationContext context = new UnificationContext(new RobinsonUnificationStrategy());

        actualMGU.add(context.executeStrategy(
                syntaxAnalyzer.parse("P(y,f(u,b))"),
                syntaxAnalyzer.parse("P(z,y)")).toString());
        expectedMGU.add("{ f(u, b)/y, f(u, b)/z }");
        actualMGU.add(context.executeStrategy(
                syntaxAnalyzer.parse("P(g(x),x,f(g(y)))"),
                syntaxAnalyzer.parse("P(z,a,f(z))")).toString());
        expectedMGU.add("{ g(a)/z, a/x, a/y }");
        actualMGU.add(context.executeStrategy(
                syntaxAnalyzer.parse("P(x,f(x,y))"),
                syntaxAnalyzer.parse("P(g(y),f(g(a),z))")).toString());
        expectedMGU.add("{ g(a)/x, a/y, a/z }");

        Assert.assertEquals(expectedMGU, actualMGU);
    }
}