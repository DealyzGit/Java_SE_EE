package org.fangsoft.testcenter.dao.db;

import org.fangsoft.testcenter.dao.CustomerDao;
import org.fangsoft.testcenter.data.CustomerData;
import org.fangsoft.testcenter.db.IRS2Object;
import org.fangsoft.testcenter.db.SQLAction;
import org.fangsoft.testcenter.db.SQLUtil;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.util.DataValidator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerDBDao extends SQLAction implements CustomerDao {

    public static final String sql_save = "insert into CUSTOMER (CR_ID, CR_NAME, CR_PASSWORD, CR_EMAILS) values(?,?,?,?)";

    private Object[] test2SQLParameter(Customer customer, int tid) {

        Object[] p = new Object[4];
        p[0] = tid;
        p[1] = customer.getUserId();
        p[2] = customer.getPassword();
        p[3] = DataValidator.validate(customer.getEmail());

        return p;
    }

    @Override
    public void save(Customer customer) {
        Connection conn = null;
        try {
            conn = this.getDataSource().getConnection();
            conn.setAutoCommit(false);
            int id = Sequence.getSeqValue(conn, Sequence.SEQ_TEST);
            customer.setId(id);
            update(conn, sql_save, this.test2SQLParameter(customer, id));

            conn.commit();
        } catch (SQLException e) {
            SQLUtil.rollback(conn);
            e.printStackTrace();
        } finally {
            SQLUtil.close(conn);
        }
    }

    public CustomerDBDao(DataSource ds) {
        super(ds);
    }

    private static final ResultSet2Customer rs2Test = new ResultSet2Customer();

    public static final ResultSet2Customer getRS2Customer() {
        return rs2Test;
    }

    private static class ResultSet2Customer implements IRS2Object<Customer> {
        public Customer process(ResultSet rs) throws SQLException {
            return ResultSet2Object.rs2Customer(rs);
        }
    }

    //  userId -> CR_ID ? CR_NAME
    public static final String sql_findByUserId = "select * from CUSTOMER where (CR_NAME) values (?)";

    @Override
    public Customer findByUserId(String userId) {
        try {
            return this.query2Object(getRS2Customer(), sql_findByUserId, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final String sql_findALLByUserId = "select * from CUSTOMER";

    @Override
    public Customer login(String userId, String pass) {
        try {
            List<Customer> customers = this.query2List(getRS2Customer(), sql_findALLByUserId);
            for (Customer customer : customers
            ) {
                Customer c = customer;
                if (c.getUserId().equals(userId) && c.getPassword().equals(pass)) {
                    return customer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
