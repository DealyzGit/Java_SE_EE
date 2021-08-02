package org.fangsoft.testcenter.dao.prop;

import org.fangsoft.testcenter.dao.CustomerDao;
import org.fangsoft.testcenter.dao.DaoIOConfig;
import org.fangsoft.testcenter.data.CustomerData;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class CustomerPropDao implements CustomerDao {

    @Override
    public void save(Customer customer) {
        Properties ps = Property2Object.toProperties(customer);
        String path = DaoIOConfig.getCustomerFilePath();
        String fileName = DaoIOConfig.getFileName(customer);
        new File(path).mkdirs();
        try {
            ps.store(new FileWriter(path + fileName), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findByUserId(String userId) {
        String path = DaoIOConfig.getCustomerFilePath();
        File[] files = new File(path).listFiles();
        if (files == null || files.length == 0) return null;
        for (File f : files) {
            String fileName = f.getName();
            if (f.isFile() && fileName.startsWith(userId)) {
                Properties ps = new Properties();
                try {
                    ps.load(new FileReader(f));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return Property2Object.toCustomer(ps);
            }
        }
        return null;
    }

    public ArrayList<Customer> findALLUser() {
        ArrayList<Customer> customers = new ArrayList<>();
        String path = DaoIOConfig.getCustomerFilePath();
        File[] files = new File(path).listFiles();
        if (files == null || files.length == 0) return null;
        for (File f : files) {
            Properties ps = new Properties();
            try {
                ps.load(new FileReader(f));
                customers.add(Property2Object.toCustomer(ps));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return customers;

        }
        return null;
    }

    @Override
    public Customer login(String userId, String pass) {

//        ArrayList<Customer> customers = new ArrayList<Customer>(Arrays.asList(CustomerData.getCustomer()));
        ArrayList<Customer> customers = CustomerPropDao.getInstance().findALLUser();

        for (Customer customer : customers
        ) {
            Customer c = customer;
            if (c.getUserId().equals(userId) && c.getPassword().equals(pass)) {
                return customer;
            }
        }
        return null;
    }

    private static final CustomerPropDao testDao = new CustomerPropDao();

    public static final CustomerPropDao getInstance() {
        return testDao;
    }

    private CustomerPropDao() {
    }
}
