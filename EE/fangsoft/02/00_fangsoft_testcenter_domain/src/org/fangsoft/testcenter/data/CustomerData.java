package org.fangsoft.testcenter.data;

import org.fangsoft.testcenter.model.Customer;

public class CustomerData {
    public static String[][] CUSTOMER_DATA = {
            {
                    "fangsoft.org@gmail.com",//userId
                    "fangsoft",//password
                    "fangsoft.org@gmail.com"//email
            },
            {
                    "123",
                    "123",
                    "123@gmail.com"
            },
            {
                    "tong",
                    "123",
                    "tong@gmail.com"

            }
    };
    private static final Customer[] customers;

    static {
        int size = CUSTOMER_DATA.length;
        customers = new Customer[size];
        for (int i = 0; i < size; i++) {
            String[] cust = CUSTOMER_DATA[i];
            Customer c = new Customer();
            c.setUserId(cust[0]);
            c.setPassword(cust[1]);
            c.setEmail(cust[2]);
            customers[i] = c;
        }
    }

    public static Customer[] getCustomer() {
        return customers;
    }

}
