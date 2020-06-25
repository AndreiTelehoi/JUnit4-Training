package ro.ase.csie.cts.g1084.assignment3.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ro.ase.csie.cts.g1084.assignment3.Product;
import ro.ase.csie.cts.g1084.assignment3.ProductValuesValidationInterface;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductInvalidMinLimitNegativeException;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductInvalidSoldItemsNoException;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductInvalidIndexException;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductNullWeeklySoldItemsNoException;
import ro.ase.csie.cts.g1084.assignment3.tests.categories.ProductImportantTestsCategory;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ProductTestReq2 {

    Product product;

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
    public void testAddWeekRight()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidSoldItemsNoException, ProductInvalidIndexException {
        ArrayList<Integer> expectedWeeklySoldItemsNo = new ArrayList<>(
                Arrays.asList(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1,
                        ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1));

        ArrayList<Integer> weeklySoldItemsNo = new ArrayList<>(
                Arrays.asList(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1));
        product.setSales(weeklySoldItemsNo);
        product.addWeek(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1);

        ArrayList<Integer> calculatedWeeklySoldItemsNo = new ArrayList<>();
        for (int i = 0; i < product.getNoWeeks(); i++) {
            calculatedWeeklySoldItemsNo.add(product.getSoldItems(i));
        }

        assertEquals(expectedWeeklySoldItemsNo, calculatedWeeklySoldItemsNo);
    }

    @Test
    public void testGetSoldItemsRight()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidIndexException {
        int week = 0;
        ArrayList<Integer> weeklySoldItems = new ArrayList<>(
                Arrays.asList(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1));
        product.setSales(weeklySoldItems);
        int expectedSoldItemsNo = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1;
        int calculatedSoldItemsNo = product.getSoldItems(week);

        assertEquals(expectedSoldItemsNo, calculatedSoldItemsNo);
    }

    @Test
    public void testGetNoWeeksAboveLimitRight()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidMinLimitNegativeException {
        int minLimit = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1;
        ArrayList<Integer> weeklySoldItems = new ArrayList<>(
                Arrays.asList(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 2));
        product.setSales(weeklySoldItems);
        int expectedNoWeeksAboveLimit = 1;

        assertEquals(expectedNoWeeksAboveLimit, product.getNoWeeksAboveLimit(minLimit));
    }

    @Test(expected = ProductInvalidSoldItemsNoException.class)
    public void testAddWeekRangeNegativeSoldItemsNo() throws ProductInvalidSoldItemsNoException {
        int soldItemsNo = -1;
        product.addWeek(soldItemsNo);
    }

    @Test(expected = ProductInvalidIndexException.class)
    public void testGetSoldItemsRangeInvalidIndex()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidIndexException {
        ArrayList<Integer> weeklySoldItems = new ArrayList<>(
                Arrays.asList(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1));
        product.setSales(weeklySoldItems);
        int week = weeklySoldItems.size() + 1;
        product.getSoldItems(week);
    }

    @Test(expected = ProductInvalidMinLimitNegativeException.class)
    public void testGetNoWeeksAboveLimitRightNegative()
            throws ProductInvalidMinLimitNegativeException {
        int minLimit = -1;
        product.getNoWeeksAboveLimit(minLimit);
    }

    @Test
    public void testAddWeekBoundaryLower()
            throws ProductInvalidSoldItemsNoException, ProductInvalidIndexException {
        product.addWeek(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO);
        int expectedSoldItemsNo = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO;
        int calculatedSoldItemsNo = product.getSoldItems(product.getWeeklySoldItemsNo().size() - 1);
        assertEquals(expectedSoldItemsNo, calculatedSoldItemsNo);
    }

    @Test
    public void testAddWeekBoundaryUpper()
            throws ProductInvalidSoldItemsNoException, ProductInvalidIndexException {
        product.addWeek(ProductValuesValidationInterface.MAX_SOLD_ITEMS_NO);
        int expectedSoldItemsNo = ProductValuesValidationInterface.MAX_SOLD_ITEMS_NO;
        int calculatedSoldItemsNo = product.getSoldItems(product.getWeeklySoldItemsNo().size() - 1);
        assertEquals(expectedSoldItemsNo, calculatedSoldItemsNo);
    }

    @Test
    public void testGetSoldItemsBoundaryLower()
            throws ProductInvalidIndexException {
        int week = ProductValuesValidationInterface.MIN_NO_WEEKS;
        int expectedSoldItemsNo = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1;
        int calculatedSoldItemsNo = product.getSoldItems(week);
        assertEquals(expectedSoldItemsNo, calculatedSoldItemsNo);
    }

    @Test
    public void testGetSoldItemsBoundaryUpper()
            throws ProductInvalidIndexException {
        int week = product.getWeeklySoldItemsNo().size() - 1;
        int expectedSoldItemsNo = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1;
        int calculatedSoldItemsNo = product.getSoldItems(week);
        assertEquals(expectedSoldItemsNo, calculatedSoldItemsNo);
    }

    @Test
    public void testGetNoWeeksAboveLimitBoundaryLower()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidMinLimitNegativeException {
        int minLimit = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO;
        ArrayList<Integer> weeklySoldItems = new ArrayList<>(
                Arrays.asList(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO));
        product.setSales(weeklySoldItems);
        int expectedNoWeeksAboveLimit = 1;
        assertEquals(expectedNoWeeksAboveLimit, product.getNoWeeksAboveLimit(minLimit));
    }

    @Test
    public void testGetNoWeeksAboveLimitBoundaryUpper()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidMinLimitNegativeException {
        int minLimit = ProductValuesValidationInterface.MAX_SOLD_ITEMS_NO;
        ArrayList<Integer> weeklySoldItems = new ArrayList<>(
                Arrays.asList(ProductValuesValidationInterface.MAX_SOLD_ITEMS_NO));
        product.setSales(weeklySoldItems);
        int expectedNoWeeksAboveLimit = 1;
        assertEquals(expectedNoWeeksAboveLimit, product.getNoWeeksAboveLimit(minLimit));
    }

    @Test
    public void testGetNoWeeksAboveLimitCardinalitySingleSale()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidMinLimitNegativeException {
        int minLimit = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO;
        int soldItemsNo = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO;
        ArrayList<Integer> weeklySoldItemsNo = new ArrayList<>();
        weeklySoldItemsNo.add(soldItemsNo);
        product.setSales(weeklySoldItemsNo);

        int expectedNoWeeksAboveLimit = 1;
        int calculatedNoWeeksAboveLimit = product.getNoWeeksAboveLimit(minLimit);

        assertEquals(expectedNoWeeksAboveLimit, calculatedNoWeeksAboveLimit);
    }

    @Test
    public void testGetNoWeeksAboveLimitCardinalityZeroSales()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidMinLimitNegativeException {
        int minLimit = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO;
        ArrayList<Integer> weeklySoldItemsNo = new ArrayList<>();
        product.setSales(weeklySoldItemsNo);

        int expectedNoWeeksAboveLimit = 0;
        int calculatedNoWeeksAboveLimit = product.getNoWeeksAboveLimit(minLimit);

        assertEquals(expectedNoWeeksAboveLimit, calculatedNoWeeksAboveLimit);
    }

    @Test
    public void testGetNoWeeksAboveLimitAscendingSales()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidMinLimitNegativeException {
        ArrayList<Integer> weeklySoldItemsNo = new ArrayList<>();
        for (int i = 0; i < ProductTestFixture.WEEKS_NO; i++) {
            weeklySoldItemsNo.add(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + i);
        }
        product.setSales(weeklySoldItemsNo);

        int minLimit = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO;

        int expectedNoWeeksAboveLimit = ProductTestFixture.WEEKS_NO;
        int calculatedNoWeeksAboveLimit = product.getNoWeeksAboveLimit(minLimit);

        assertEquals(expectedNoWeeksAboveLimit, calculatedNoWeeksAboveLimit);
    }

    @Test
    public void testGetNoWeeksAboveLimitDescendingSales()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidMinLimitNegativeException {
        ArrayList<Integer> weeklySoldItemsNo = new ArrayList<>();
        for (int i = 0; i < ProductTestFixture.WEEKS_NO; i++) {
            weeklySoldItemsNo.add(ProductValuesValidationInterface.MAX_SOLD_ITEMS_NO - i);
        }
        product.setSales(weeklySoldItemsNo);

        int minLimit = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO;

        int expectedNoWeeksAboveLimit = ProductTestFixture.WEEKS_NO;
        int calculatedNoWeeksAboveLimit = product.getNoWeeksAboveLimit(minLimit);

        assertEquals(expectedNoWeeksAboveLimit, calculatedNoWeeksAboveLimit);
    }

    @Test(timeout = 3000)
    public void testGetNoWeeksAboveLimitPerformanceUnder3s() throws ProductNullWeeklySoldItemsNoException, ProductInvalidMinLimitNegativeException {
        product.setSales(ProductTestFixture.WEEKLY_SALES_PERFORMANCE_TEST);
        int expectedNoWeeksAboveLimit = ProductTestFixture.MAX_WEEKS;
        int calculatedNoWeeksAboveLimit = product.getNoWeeksAboveLimit(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO);

        assertEquals(expectedNoWeeksAboveLimit, calculatedNoWeeksAboveLimit);
    }

    @Test(expected = ProductInvalidSoldItemsNoException.class)
    public void testAddWeekErrorConditionMinSoldItemsNo() throws ProductInvalidSoldItemsNoException {
        int soldItemsNo = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO - 1;
        product.addWeek(soldItemsNo);
    }

    @Test(expected = ProductInvalidSoldItemsNoException.class)
    public void testAddWeekErrorConditionMaxSoldItemsNo() throws ProductInvalidSoldItemsNoException {
        int soldItemsNo = ProductValuesValidationInterface.MAX_SOLD_ITEMS_NO + 1;
        product.addWeek(soldItemsNo);
    }

}