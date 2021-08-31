package org.fangsoft.testcenter.dao.memory;

import org.fangsoft.testcenter.dao.*;

public class MemDaoFactory implements DaoFactory {
    private CustomerDao customerDao;
    private TestDao testDao;
    private QuestionDao questionDao;
    private TestResultDao testReusltDao;
    private TestReservationDao testResDao;

    public  CustomerDao getCustomerDao(){
        if(this.customerDao==null){
            this.customerDao=new CustomerMemDao();
        }
        return this.customerDao;
    }
    public  TestDao getTestDao(){
        if(this.testDao==null){
            this.testDao=new TestMemDao();
        }
        return this.testDao;
    }
    public  QuestionDao getQuestionDao(){
        if(this.questionDao==null){
            this.questionDao=new QuestionMemDao();
        }
        return this.questionDao;
    }
    public  TestResultDao getTestResultDao(){
        if(this.testReusltDao==null){
            this.testReusltDao=new TestResultMemDao();
        }
        return this.testReusltDao;
    }
    public  TestReservationDao getTestReservationDao(){
        if(this.testResDao==null){
            this.testResDao=new TestReservationMemDao();
        }
        return this.testResDao;
    }
}
