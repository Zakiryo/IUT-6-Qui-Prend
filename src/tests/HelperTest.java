package tests;

import sixQuiPrend.Helper;
import org.junit.Assert;
import org.junit.Test;

public class HelperTest {
    @Test
    public void testIsNumeric() {
        Assert.assertTrue(Helper.isNumeric("3"));
        Assert.assertFalse(Helper.isNumeric("3aab"));
        Assert.assertFalse(Helper.isNumeric("Ab"));
    }
}
