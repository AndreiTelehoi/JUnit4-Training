package ro.ase.csie.cts.g1084.assignment3.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ro.ase.csie.cts.g1084.assignment3.tests.categories.ProductImportantTestsCategory;

@RunWith(Categories.class)
@Suite.SuiteClasses({ProductTestReq1.class, ProductTestReq2.class, ProductTestReq3.class})
@Categories.IncludeCategory({ProductImportantTestsCategory.class})
public class ProductTestImportantOnly {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}