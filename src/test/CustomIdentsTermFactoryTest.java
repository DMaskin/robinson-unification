import main.terms.CustomIdentsTermFactory;
import main.terms.Term;
import main.terms.TermFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CustomIdentsTermFactoryTest {
    @Test
    public void testCreateCustomTerm() {
        TermFactory customTermFactory = new CustomIdentsTermFactory();
        Term customTerm = customTermFactory.createFSymb("f2021",
                customTermFactory.createFSymb("g0000",
                        Arrays.asList(customTermFactory.createIndVariable("xXx"), customTermFactory.createIndVariable("yYy"))));

        Assert.assertEquals("f2021(g0000(xXx, yYy))", customTerm.toString());
    }
}