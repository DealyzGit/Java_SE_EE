package test.org.fangsoft.testcenter.dao; 

import org.fangsoft.testcenter.dao.prop.TestPropDao;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* TestDaoTest Tester. 
* 
* @author <Authors name> 
* @since <pre>7ÔÂ 21, 2021</pre> 
* @version 1.0 
*/ 
public class TestDaoTestTest {
    @Test
    public void test(){
        TestPropDao dao= TestPropDao.getInstance();
        this.testAll(dao);
    }

    private void testAll(TestPropDao dao) {

    }


    @Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: testAll(TestDao tdao) 
*
*/
@Test
public void testTestAll() throws Exception {
//TODO: Test goes here... 
} 


/** 
* 
* Method: toString(Test test) 
* 
*/ 
@Test
public void testToString() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = TestDaoTest.getClass().getMethod("toString", Test.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
