package org.fangsoft.testcenter.dao.array;

import javafx.scene.shape.ClosePathBuilder;
import org.fangsoft.testcenter.dao.CustomerDao;
import org.fangsoft.testcenter.dao.TestDao;
import org.fangsoft.testcenter.data.CustomerData;
import org.fangsoft.testcenter.model.Customer;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomerArrayDao implements CustomerDao {

    private static final CustomerArrayDao tdao = new CustomerArrayDao();

    public static final CustomerArrayDao getInstance() {
        return tdao;

    }

    private CustomerArrayDao() {
    }

    @Override
    public Customer login(String userId, String password) {
        ArrayList<Customer> customers = new ArrayList<Customer>(Arrays.asList(CustomerData.getCustomer()));
        for (Customer customer : customers
        ) {
            Customer c = customer;
            if (c.getUserId().equals(userId) && c.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }
}
