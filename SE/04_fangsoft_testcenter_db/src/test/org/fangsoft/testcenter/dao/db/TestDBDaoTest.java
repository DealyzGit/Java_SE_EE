package test.org.fangsoft.testcenter.dao.db; 

import org.fangsoft.testcenter.config.Configuration;
import org.fangsoft.testcenter.dao.TestDao;
import org.fangsoft.testcenter.dao.TestDaoTest;
import org.fangsoft.testcenter.dao.db.TestDBDao;
import org.fangsoft.testcenter.db.SQLAction;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.sql.SQLException;

/** 
* TestDBDao Tester. 
* 
* @author <Authors name> 
* @since <pre>7ÔÂ 21, 2021</pre> 
* @version 1.0 
*/
public class TestDBDaoTest extends TestDaoTest {
    @Test
    public void test(){
        this.deleteAll();
        TestDao tdao=new TestDBDao(Configuration.getDataSource());
        this.testAll(tdao);
        this.deleteAll();
    }
    private void deleteAll(){
        SQLAction sqlAction=new SQLAction(Configuration.getDataSource());
        try {
            sqlAction.update("delete from TEST");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

