package org.fangsoft.testcenter.dao.db;

import org.fangsoft.testcenter.dao.TestDao;
import org.fangsoft.db.IRS2Object;
import org.fangsoft.db.SQLUtil;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.util.DataValidator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.fangsoft.db.SQLAction;

public class TestDBDao extends SQLAction implements TestDao {

    public static final String sql_save = "insert into TEST (TT_NAME, TT_NUMQUESTION, TT_TIMELIMITMIN, TT_DESCRIPTION, TT_SCORE, TT_ID) values(?,?,?,?,?,?)";
    public static final String sql_findAllTest = "select * from TEST";
    public static final String sql_findTestByName = "select * from TEST where TT_NAME = ?";

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public TestDBDao(DataSource ds) {
        super(ds);
    }

    @Override
    public Test findTestByName(String testName) {
        try {
            return this.query2Object(getRS2Test(), sql_findTestByName, testName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Test> findAllTest() {
        try {
            return this.query2List(getRS2Test(), sql_findAllTest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Test>(0);
    }


    private Object[] test2SQLParameter(Test test, int tid) {
        Object[] p = new Object[6];
        p[0] = DataValidator.validate(test.getName());
        p[1] = test.getNumQuestion();
        p[2] = test.getTimeLimitMin();
        p[3] = DataValidator.validate(test.getDescription());
        p[4] = test.getScore();
        p[5] = tid;
        return p;
    }

    @Override
    public void save(Test test) {
        Connection conn = null;
        try {
            conn = this.getDataSource().getConnection();
            conn.setAutoCommit(false);
            int id = Sequence.getSeqValue(conn, Sequence.SEQ_TEST);
            update(conn, sql_save, this.test2SQLParameter(test, id));
            test.setId(id);
            conn.commit();
        } catch (SQLException e) {
            SQLUtil.rollback(conn);
            e.printStackTrace();
        } finally {
            SQLUtil.close(conn);
        }
    }


    private static final ResultSet2Test rs2Test = new ResultSet2Test();

    public static final ResultSet2Test getRS2Test() { return rs2Test; }

    private static class ResultSet2Test implements IRS2Object<Test> {
        public Test process(ResultSet rs) throws SQLException {
            return ResultSet2Object.rs2Test(rs);
        }
    }

    public static final String sql_findAllTestNames= "select TT_NAME from TEST";
    public  List<String> findAllTestNames() {
        Connection conn =null;
        ResultSet rs=null;
        List<String> names=new ArrayList<String>();
        try {
            conn=this.getDataSource().getConnection();
            rs=this.query2ResultSet(conn, sql_findAllTestNames);
            while(rs.next()){
                names.add(rs.getString("TT_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            SQLUtil.close(rs);
            SQLUtil.close(conn);
        }
        return names;
    }


}
