import main.terms.SyntaxAnalyzeTermFactory;
import main.terms.Term;
import main.terms.TermFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SyntaxAnalyzeTermFactoryTest {

    @Test
    public void testCreateTermWithSyntaxAnalyze() {
        TermFactory strictTermFactory = new SyntaxAnalyzeTermFactory();
        Term firstTerm = strictTermFactory.createFSymb("f1",
                strictTermFactory.createFSymb("g1",
                        Arrays.asList(strictTermFactory.createIndVariable("x1"), strictTermFactory.createIndVariable("y1"))));

        Assert.assertEquals("f1(g1(x1, y1))", firstTerm.toString());
    }

}