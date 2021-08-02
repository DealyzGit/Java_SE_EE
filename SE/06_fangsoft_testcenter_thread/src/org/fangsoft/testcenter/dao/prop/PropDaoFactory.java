package org.fangsoft.testcenter.dao.prop;

import org.fangsoft.testcenter.dao.*;

public class PropDaoFactory implements DaoFactory{
    private static final PropDaoFactory daoFactory=new PropDaoFactory();
    public static final DaoFactory getInstance(){
        return daoFactory;
    }
    private PropDaoFactory(){}
    public CustomerDao getCustomerDao(){
        return CustomerPropDao.getInstance();
    }
    public TestDao getTestDao(){
        return TestPropDao.getInstance();
    }
    public QuestionPropDao getQuestionDao(){
        return  QuestionPropDao.getInstance();
    }
    public TestResultDao getTestResultDao(){
        return TestResultPropDao.getInstance();
    }
}