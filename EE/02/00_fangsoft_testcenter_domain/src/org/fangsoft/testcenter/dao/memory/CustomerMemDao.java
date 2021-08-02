package org.fangsoft.testcenter.dao.memory;

import org.fangsoft.testcenter.dao.CustomerDao;
import org.fangsoft.testcenter.model.Customer;

public class CustomerMemDao implements CustomerDao {
    @Override
    public void save(Customer customer) {

    }

    public Customer findByUserId(String userId) {
        return DataRepository.customerMap.get(userId);
    }
    public Customer login(String userId, String pass) {
        Customer customer=DataRepository.customerMap.get(userId);
        if(customer!=null && customer.getPassword().equals(pass)){
            return customer;
        }
        return null;
    }

}
