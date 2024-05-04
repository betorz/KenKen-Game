package test;

import main.domain.classes.Region;
import main.domain.classes.Equal;
import main.domain.classes.Addition;
import main.domain.classes.Subtraction;
import main.domain.classes.Multiplication;
import main.domain.classes.Division;
import main.domain.classes.Module;
import main.domain.classes.Power;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.Arrays;

public class RegionTest {

    @Test 
    public void equalTest() {
        Region equal = new Equal(6, 1);
        assertTrue("Equal should be correct", equal.checkResult(Arrays.asList(6)));
        assertFalse("Equal should be incorrect", equal.checkResult(Arrays.asList(3)));
    }
    
    @Test 
    public void additionTest() {
        Region addition = new Addition(10, 3);
        assertTrue("Addition should be correct", addition.checkResult(Arrays.asList(3, 4, 3)));
        assertFalse("Addition should be incorrect", addition.checkResult(Arrays.asList(1, 4, 2)));
    }

    @Test 
    public void subtractionTest() {
        Region subtraction = new Subtraction(2, 2);
        assertTrue("Subtraction should be correct", subtraction.checkResult(Arrays.asList(3, 1)));
        assertFalse("Subtraction should be incorrect", subtraction.checkResult(Arrays.asList(1, 2)));
    }

    @Test 
    public void multiplicationTest() {
        Region multiplication = new Multiplication(6, 2);
        assertTrue("Multiplication should be correct", multiplication.checkResult(Arrays.asList(3, 2)));
        assertFalse("Multiplication should be incorrect", multiplication.checkResult(Arrays.asList(4, 2)));
    }

    @Test 
    public void divisionTest() {
        Region division = new Division(2, 2);
        assertTrue("Division should be correct", division.checkResult(Arrays.asList(2, 1)));
        assertFalse("Division should be incorrect", division.checkResult(Arrays.asList(3, 2)));
    }

    @Test 
    public void moduleTest() {
        Region module = new Module(2, 1);
        assertTrue("Module should be correct", module.checkResult(Arrays.asList(3, 2)));
        assertFalse("Module should be incorrect", module.checkResult(Arrays.asList(8, 4)));
    }

    @Test 
    public void powerTest() {
        Region power = new Power(8, 2);
        assertTrue("Power should be correct", power.checkResult(Arrays.asList(2, 3)));
        assertFalse("Power should be incorrect", power.checkResult(Arrays.asList(3, 4)));
    }
}
