package test.org.fangsoft.testcenter.dao.prop;

import org.fangsoft.testcenter.dao.prop.TestPropDao;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * TestPropDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>7�� 20, 2021</pre>
 */
public class TestPropDaoTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: findTestByName(String testName)
     */
    @Test
    public void testFindTestByName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findAllTest()
     */
    @Test
    public void testFindAllTest() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: save(Test test)
     */
    @Test
    public void testSave() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
//TODO: Test goes here... 
    }

    @Test
    public void test() {
        TestPropDao dao = TestPropDao.getInstance();
        this.testAll(dao);
    }

    private void testAll(TestPropDao dao) {
    }

} 
