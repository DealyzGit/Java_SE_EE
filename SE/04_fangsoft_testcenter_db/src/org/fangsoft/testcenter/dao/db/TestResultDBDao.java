package org.fangsoft.testcenter.dao.db;

import org.fangsoft.testcenter.dao.TestResultDao;
import org.fangsoft.testcenter.db.IRS2Object;
import org.fangsoft.testcenter.db.SQLAction;
import org.fangsoft.testcenter.db.SQLUtil;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.util.DataConverter;
import org.fangsoft.util.DataValidator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestResultDBDao extends SQLAction implements TestResultDao {

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    private DataSource dataSource;

    public TestResultDBDao(DataSource ds) {
        super(ds);
    }


    public static final ResultSet2TestResult getRS2TestResult() {
        return rs2TestResult;
    }

    private static final ResultSet2TestResult rs2TestResult = new ResultSet2TestResult();

    private static class ResultSet2TestResult implements IRS2Object<TestResult> {
        public TestResult process(ResultSet rs) throws SQLException {
            return ResultSet2Object.rs2TestResult(rs);
        }
    }

    public static final String sql_findTestResultByCustomerID = "select * from TESTRESULT where (CR_ID) values (?)";
    public static final String sql_findCustomerIDByName = "select CR_ID from CUSTOMER where (CR_NAME) values (?)";

    @Override
    public List<TestResult> findTestResultByCustomer(String userId) {

        try {
            List<Customer> customers = query2List(CustomerDBDao.getRS2Customer(), sql_findCustomerIDByName, userId);
            List<TestResult> testResults = new ArrayList<>();
            for (Customer c : customers
            ) {
                testResults.add(query2Object(getRS2TestResult(), sql_findTestResultByCustomerID, c.getId()));
            }
            return testResults;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final String sql_save = "insert into TESTRESULT (TL_ID, TL_STARTTIME, TL_ENDTIME, " +
            "TL_STATUS, TL_RESULT, CR_ID,TT_ID) values(?,?,?,?,?,?,?)";

    private Object[] test2SQLParameter(TestResult testResult, int tid, int CustomerID, int TestID) {
        Object[] p = new Object[7];

//        Wed Jul 21 15:20:41 CST 2021
//        Wed Jul 21 15:20:45 CST 2021
        p[0] = tid;

        p[1] = DataConverter.date2SqlDate(testResult.getStartTime());
        p[2] = DataConverter.date2SqlDate(testResult.getEndTime());

        p[3] = testResult.getStatus().getIntVal();
        p[4] = testResult.getResult().getIntVal();

        p[5] = CustomerID;
        p[6] = TestID;

        return p;
    }

    @Override
    public void save(TestResult testResult) {

        int CustomerID = testResult.getCustomer().getId();
        int TestID = testResult.getTest().getId();

        Connection conn = null;
        try {
            conn = this.getDataSource().getConnection();
            conn.setAutoCommit(false);
            int id = Sequence.getSeqValue(conn, Sequence.SEQ_TEST);

            update(conn, sql_save, this.test2SQLParameter(testResult, id, CustomerID, TestID));
            testResult.setId(id);
            conn.commit();
        } catch (SQLException e) {
            SQLUtil.rollback(conn);
            e.printStackTrace();
        } finally {
            SQLUtil.close(conn);
        }

    }
}
