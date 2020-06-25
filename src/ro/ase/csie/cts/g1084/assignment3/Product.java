package ro.ase.csie.cts.g1084.assignment3;

import ro.ase.csie.cts.g1084.assignment3.exceptions.*;

import java.util.ArrayList;

public class Product {
    private String name;
    private float price;
    private ArrayList<Integer> weeklySoldItemsNo;

    public Product(String name, float price)
            throws ProductInvalidNameException, ProductInvalidPriceException {
        super();
        if (name.length() < ProductValuesValidationInterface.MIN_NAME_SIZE ||
                name.length() > ProductValuesValidationInterface.MAX_NAME_SIZE) {
            throw new ProductInvalidNameException();
        }
        this.name = name;
        if (price < ProductValuesValidationInterface.MIN_PRICE_VALUE ||
                price > ProductValuesValidationInterface.MAX_PRICE_VALUE) {
            throw new ProductInvalidPriceException();
        }
        this.price = price;
        this.weeklySoldItemsNo = new ArrayList<Integer>();
    }

    public Product(String name, float price, ArrayList<Integer> weeklySoldItemsNo)
            throws ProductInvalidNameException, ProductInvalidPriceException, ProductNullWeeklySoldItemsNoException {
        if (name.length() < ProductValuesValidationInterface.MIN_NAME_SIZE
                || name.length() > ProductValuesValidationInterface.MAX_NAME_SIZE) {
            throw new ProductInvalidNameException();
        }
        this.name = name;
        if (price < ProductValuesValidationInterface.MIN_PRICE_VALUE
                || price > ProductValuesValidationInterface.MAX_PRICE_VALUE) {
            throw new ProductInvalidPriceException();
        }
        this.price = price;
        if (weeklySoldItemsNo == null) {
            throw new ProductNullWeeklySoldItemsNoException();
        }
        this.weeklySoldItemsNo = new ArrayList<Integer>();
        for (Integer soldItemsNo : weeklySoldItemsNo) {
            this.weeklySoldItemsNo.add(soldItemsNo);
        }
    }

    public void setSales(ArrayList<Integer> weeklySoldItemsNo)
            throws ProductNullWeeklySoldItemsNoException {
        if (weeklySoldItemsNo == null) {
            throw new ProductNullWeeklySoldItemsNoException();
        }
        this.weeklySoldItemsNo = new ArrayList<Integer>();
        for (int soldItemsNo : weeklySoldItemsNo) {
            this.weeklySoldItemsNo.add(soldItemsNo);
        }
    }

    public String getName() {
        return this.name;
    }

    public float getPrice() {
        return this.price;
    }

    public ArrayList<Integer> getWeeklySoldItemsNo() {
        return weeklySoldItemsNo;
    }

    public void addWeek(int soldItemsNo) throws ProductInvalidSoldItemsNoException {
        if (soldItemsNo < 0) {
            throw new ProductInvalidSoldItemsNoException();
        }
        if (soldItemsNo < ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO ||
                soldItemsNo > ProductValuesValidationInterface.MAX_SOLD_ITEMS_NO) {
            throw new ProductInvalidSoldItemsNoException();
        }
        this.weeklySoldItemsNo.add(soldItemsNo);
    }

    public int getSoldItems(int week) throws ProductInvalidIndexException {
        if (week > weeklySoldItemsNo.size()) {
            throw new ProductInvalidIndexException();
        }
        if (week < ProductValuesValidationInterface.MIN_SOLD_ITEMS_NO
                || week > ProductValuesValidationInterface.MAX_SOLD_ITEMS_NO) {
            throw new ProductInvalidIndexException();
        }
        return this.weeklySoldItemsNo.get(week);
    }

    public int getNoWeeks() {
        return this.weeklySoldItemsNo.size();
    }

    public int getNoWeeksAboveLimit(int minLimit)
            throws ProductInvalidMinLimitNegativeException {
        if (minLimit < 0) {
            throw new ProductInvalidMinLimitNegativeException();
        }
        int noAboveLimitWeeks = 0;
        for (int soldItemsNo : this.weeklySoldItemsNo) {
            if (soldItemsNo >= minLimit) {
                noAboveLimitWeeks++;
            }
        }
        return noAboveLimitWeeks;
    }

    public int getPercentOfBadWeeks(int minLimit)
            throws ProductInvalidMinLimitNegativeException, ProductInvalidMinLimitTooHighException {
        if (minLimit < 0) {
            throw new ProductInvalidMinLimitNegativeException();
        }
        if (minLimit > ProductValuesValidationInterface.MAX_SOLD_ITEMS_NO) {
            throw new ProductInvalidMinLimitTooHighException();
        }
        int badWeeksNo = 0;
        for (Integer soldItemsNo : this.weeklySoldItemsNo) {
            if (soldItemsNo < minLimit) {
                badWeeksNo++;
            }
        }

        return (int) (100 * badWeeksNo / this.weeklySoldItemsNo.size());
    }

    public ArrayList<Integer> getWeeksIndexWithMaxSales() {
        ArrayList<Integer> maxSalesWeeks = new ArrayList<>();
        int maxSales = this.weeklySoldItemsNo.get(0);

        for (int i = 0; i < this.weeklySoldItemsNo.size(); i++) {
            if (this.weeklySoldItemsNo.get(i) > maxSales) {
                maxSales = this.weeklySoldItemsNo.get(i);
            }
        }

        for (int i = 0; i < this.weeklySoldItemsNo.size(); i++) {
            if (this.weeklySoldItemsNo.get(i) >= maxSales) {
                maxSalesWeeks.add(i);
            }
        }

        return maxSalesWeeks;
    }

    @Override
    public String toString() {
        String weeklySales = this.name + " weekly sales: ";
        for (Integer soldItemsNo : weeklySoldItemsNo) {
            weeklySales += soldItemsNo + " ";
        }
        return weeklySales;
    }
}


