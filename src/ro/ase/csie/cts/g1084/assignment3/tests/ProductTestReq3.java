package ro.ase.csie.cts.g1084.assignment3.tests;

import org.junit.*;
import org.junit.experimental.categories.Category;
import ro.ase.csie.cts.g1084.assignment3.Product;
import ro.ase.csie.cts.g1084.assignment3.ProductValuesValidationInterface;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductInvalidIndexException;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductInvalidMinLimitNegativeException;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductInvalidMinLimitTooHighException;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductNullWeeklySoldItemsNoException;
import ro.ase.csie.cts.g1084.assignment3.tests.categories.ProductImportantTestsCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class ProductTestReq3 {

    Product product;

    @BeforeClass
    public static void beforeClass() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        product = new Product(ProductTestFixture.NAME, ProductTestFixture.PRICE,
                ProductTestFixture.WEEKLY_SOLD_ITEMS_NO);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Category(ProductImportantTestsCategory.class)
    @Test
    public void testGetPercentOfBadWeeksRight()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidMinLimitNegativeException, ProductInvalidMinLimitTooHighException {
        ArrayList<Integer> weeklySoldItemsNo = new ArrayList<>(Arrays.asList(
                ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 2, ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO));
        product.setSales(weeklySoldItemsNo);
        int minLimit = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1;

        int expectedPercentage = 50;
        int calculatedPercentage = product.getPercentOfBadWeeks(minLimit);

        assertEquals(expectedPercentage, calculatedPercentage);
    }

    @Category(ProductImportantTestsCategory.class)
    @Test
    public void testgetWeeksIndexWithMaxSalesRight()
            throws ProductNullWeeklySoldItemsNoException {
        ArrayList<Integer> expectedWeeksIndex = new ArrayList<>();
        for (int i = 0; i < ProductTestFixture.WEEKS_NO; i++) {
            expectedWeeksIndex.add(i);
        }
        ArrayList<Integer> calculatedWeeksIndex = product.getWeeksIndexWithMaxSales();

        assertEquals(expectedWeeksIndex, calculatedWeeksIndex);
    }

    @Test
    public void testGetWeeksIndexWithMaxSalesCrossCheck()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidIndexException {
        ArrayList<Integer> weeklySoldItemsNo = new ArrayList<>(Arrays.asList(
                ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 2, ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO));
        product.setSales(weeklySoldItemsNo);

        ArrayList<Integer> expectedWeeksIndex = new ArrayList<>();
        for (int i = 0; i < product.getNoWeeks(); i++) {
            if (product.getSoldItems(i) >= Collections.max(product.getWeeklySoldItemsNo())) {
                expectedWeeksIndex.add(i);
            }
        }

        ArrayList<Integer> calculatedWeeksIndex = product.getWeeksIndexWithMaxSales();

        assertEquals(expectedWeeksIndex, calculatedWeeksIndex);
    }

    @Test
    public void testgetPercentOfBadWeeksInverse()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidMinLimitNegativeException, ProductInvalidMinLimitTooHighException {
        ArrayList<Integer> weeklySoldItemsNo = new ArrayList<>(Arrays.asList(
                ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 2, ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO));
        product.setSales(weeklySoldItemsNo);
        int minLimit = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1;

        int goodWeeksNo = 0;
        for (Integer soldItemsNo : product.getWeeklySoldItemsNo()) {
            if (soldItemsNo >= minLimit) {
                goodWeeksNo++;
            }
        }

        int percentOfGoodWeeks = (int) (100 * goodWeeksNo / product.getWeeklySoldItemsNo().size());

        int expectedPercentOfBadWeeks = 100 - percentOfGoodWeeks;
        int calculatedPercentOfBadWeeks = product.getPercentOfBadWeeks(minLimit);

        assertEquals(expectedPercentOfBadWeeks, calculatedPercentOfBadWeeks);
    }

    @Test(expected = ProductInvalidMinLimitNegativeException.class)
    public void testGetPercentOfBadWeeksErrorConditionMinLimitNegative()
            throws ProductInvalidMinLimitNegativeException, ProductInvalidMinLimitTooHighException {
        int minLimit = -1;
        product.getPercentOfBadWeeks(minLimit);
    }

    @Test(expected = ProductInvalidMinLimitTooHighException.class)
    public void testGetPercentOfBadWeeksErrorConditionMinLimitTooHigh()
            throws ProductInvalidMinLimitNegativeException, ProductInvalidMinLimitTooHighException {
        int minLimit = ProductValuesValidationInterface.MAX_SOLD_ITEMS_NO + 1;
        product.getPercentOfBadWeeks(minLimit);
    }
}