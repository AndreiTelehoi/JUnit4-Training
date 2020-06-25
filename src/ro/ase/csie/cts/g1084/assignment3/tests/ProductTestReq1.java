package ro.ase.csie.cts.g1084.assignment3.tests;

import org.junit.*;
import org.junit.experimental.categories.Category;
import ro.ase.csie.cts.g1084.assignment3.Product;
import ro.ase.csie.cts.g1084.assignment3.ProductValuesValidationInterface;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductInvalidIndexException;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductInvalidNameException;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductInvalidPriceException;
import ro.ase.csie.cts.g1084.assignment3.exceptions.ProductNullWeeklySoldItemsNoException;
import ro.ase.csie.cts.g1084.assignment3.tests.categories.ProductImportantTestsCategory;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProductTestReq1 {

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

    @Test
    public void testConstructorNoArrayRight()
            throws ProductInvalidNameException, ProductInvalidPriceException {
        Product product = new Product(ProductTestFixture.NAME, ProductTestFixture.PRICE);
        assertEquals("Name is not correctly initialized",
                ProductTestFixture.NAME, product.getName());
        assertEquals("Price is not correctly initialized",
                ProductTestFixture.PRICE, product.getPrice(), 0);
    }

    @Test
    public void testCompleteConstructorRight()
            throws ProductInvalidPriceException, ProductInvalidNameException, ProductNullWeeklySoldItemsNoException, ProductInvalidIndexException {
        Product product = new Product(ProductTestFixture.NAME,
                ProductTestFixture.PRICE, ProductTestFixture.WEEKLY_SOLD_ITEMS_NO);
        assertEquals("Name is not correctly initialized",
                ProductTestFixture.NAME, product.getName());
        assertEquals("Price is not correctly initialized",
                ProductTestFixture.PRICE, product.getPrice(), 0);
        for (int i = 0; i < ProductTestFixture.WEEKLY_SOLD_ITEMS_NO.size(); i++) {
            assertEquals("The weekly sold items number is not correctly initialized",
                    (int) ProductTestFixture.WEEKLY_SOLD_ITEMS_NO.get(i), product.getSoldItems(i));
        }
    }

    @Test(expected = ProductInvalidNameException.class)
    public void testConstructorNoArrayErrorConditionName() throws ProductInvalidNameException, ProductInvalidPriceException {
        String name = "X";
        Product product = new Product(name, ProductTestFixture.PRICE);
    }

    @Test(expected = ProductInvalidPriceException.class)
    public void testConstructorNoArrayErrorConditionPrice() throws ProductInvalidPriceException, ProductInvalidNameException {
        float price = ProductValuesValidationInterface.MIN_PRICE_VALUE - 1;
        Product product = new Product(ProductTestFixture.NAME, price);
    }

    @Test(expected = ProductInvalidNameException.class)
    public void testCompleteConstructorErrorConditionName()
            throws ProductInvalidPriceException, ProductInvalidNameException, ProductNullWeeklySoldItemsNoException {
        String name = "X";
        Product product = new Product(name, ProductTestFixture.PRICE, ProductTestFixture.WEEKLY_SOLD_ITEMS_NO);
    }

    @Test(expected = ProductInvalidPriceException.class)
    public void testCompleteConstructorErrorConditionPrice()
            throws ProductInvalidPriceException, ProductInvalidNameException, ProductNullWeeklySoldItemsNoException {
        float price = ProductValuesValidationInterface.MIN_PRICE_VALUE - 1;
        Product product = new Product(ProductTestFixture.NAME, price, ProductTestFixture.WEEKLY_SOLD_ITEMS_NO);
    }

    @Category(ProductImportantTestsCategory.class)
    @Test
    public void testCompleteConstructorArrayListReference()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidPriceException, ProductInvalidNameException, ProductInvalidIndexException {
        ArrayList<Integer> weeklySoldItemsNo = new ArrayList<>();

        int soldItemsNo1 = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1;
        int soldItemsNo2 = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 2;
        weeklySoldItemsNo.add(soldItemsNo1);
        weeklySoldItemsNo.add(soldItemsNo2);

        product = new Product(ProductTestFixture.NAME, ProductTestFixture.PRICE, weeklySoldItemsNo);

        ArrayList<Integer> weeklySoldItemsNoProduct = new ArrayList<>();
        for (int i = 0; i < product.getNoWeeks(); i++)
            weeklySoldItemsNoProduct.add(product.getSoldItems(i));

        weeklySoldItemsNo.set(0, soldItemsNo1 + 1);
        weeklySoldItemsNo.set(1, soldItemsNo2 + 2);

        ArrayList<Integer> weeklySoldItemsNoProductNew = new ArrayList<>();
        for (int i = 0; i < product.getNoWeeks(); i++)
            weeklySoldItemsNoProductNew.add(product.getSoldItems(i));

        assertArrayEquals(weeklySoldItemsNoProduct.toArray(),
                weeklySoldItemsNoProductNew.toArray());
    }

    @Test(expected = ProductNullWeeklySoldItemsNoException.class)
    public void testCompleteConstructorExistenceWeeklySoldItemsNoNull()
            throws ProductInvalidPriceException, ProductInvalidNameException, ProductNullWeeklySoldItemsNoException {
        ArrayList<Integer> weeklySoldItemsNoNull = null;
        product = new Product(ProductTestFixture.NAME, ProductTestFixture.PRICE, weeklySoldItemsNoNull);

    }

    @Test
    public void testSetSalesReference()
            throws ProductNullWeeklySoldItemsNoException, ProductInvalidIndexException {
        ArrayList<Integer> weeklySoldItemsNo = new ArrayList<>();

        int soldItemsNo1 = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1;
        int soldItemsNo2 = ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 2;
        weeklySoldItemsNo.add(soldItemsNo1);
        weeklySoldItemsNo.add(soldItemsNo2);

        product.setSales(weeklySoldItemsNo);

        ArrayList<Integer> weeklySoldItemsNoProduct = new ArrayList<>();
        for (int i = 0; i < product.getNoWeeks(); i++)
            weeklySoldItemsNoProduct.add(product.getSoldItems(i));

        weeklySoldItemsNo.set(0, soldItemsNo1 + 1);
        weeklySoldItemsNo.set(1, soldItemsNo2 + 2);

        ArrayList<Integer> weeklySoldItemsNoProductNew = new ArrayList<>();
        for (int i = 0; i < product.getNoWeeks(); i++)
            weeklySoldItemsNoProductNew.add(product.getSoldItems(i));

        assertArrayEquals(weeklySoldItemsNoProduct.toArray(),
                weeklySoldItemsNoProductNew.toArray());
    }

    @Test(expected = ProductNullWeeklySoldItemsNoException.class)
    public void testSetSalesExistenceNull() throws ProductNullWeeklySoldItemsNoException {
        ArrayList<Integer> weeklySoldItemsNoNull = null;
        product.setSales(weeklySoldItemsNoNull);
    }


}