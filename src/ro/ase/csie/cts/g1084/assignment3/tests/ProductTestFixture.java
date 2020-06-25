package ro.ase.csie.cts.g1084.assignment3.tests;

import ro.ase.csie.cts.g1084.assignment3.ProductValuesValidationInterface;

import java.util.ArrayList;

public class ProductTestFixture {

    public static final String NAME;
    public static final float PRICE = ProductValuesValidationInterface.MIN_PRICE_VALUE+1;
    public static final ArrayList<Integer> WEEKLY_SOLD_ITEMS_NO = new ArrayList<>();
    public static final int WEEKS_NO = 3;

    static {
        for(int i=0;i<WEEKS_NO;i++){
            WEEKLY_SOLD_ITEMS_NO.add(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO+1);
        }
    }

    static {
        String initialName = "";
        for (int i = 0; i < ProductValuesValidationInterface.MIN_NAME_SIZE; i++) {
            initialName += "A";
        }
        NAME = initialName;
    }

    public static final int MAX_WEEKS = 1000;
    public static final ArrayList<Integer> WEEKLY_SALES_PERFORMANCE_TEST = new ArrayList<>();

    static {
        for (int i=0;i<MAX_WEEKS;i++) {
            WEEKLY_SALES_PERFORMANCE_TEST.add(ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO + 1);
        }
    }



}
