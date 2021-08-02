package org.fangsoft.testcenter.dao.db;

import org.fangsoft.testcenter.dao.*;

import javax.sql.DataSource;

public class DBDaoFactory implements DaoFactory {
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    private DataSource dataSource;
    private CustomerDao cdao;
    private TestDao tdao;
    private QuestionDao qdao;
    private TestResultDao trdao;
    public DBDaoFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public  CustomerDao getCustomerDao(){
        if(this.cdao==null){
            this.cdao=new CustomerDBDao(this.dataSource);
        }
        return this.cdao;
    }
    public  TestDao getTestDao(){
        if(this.tdao==null){
            this.tdao=new TestDBDao(this.dataSource);
        }
        return this.tdao;
    }
    public  QuestionDao getQuestionDao(){
        if(this.qdao==null){
            this.qdao=new QuestionDBDao(this.dataSource);
        }
        return this.qdao;
    }
    public  TestResultDao getTestResultDao(){
        if(this.trdao==null){
            this.trdao=new TestResultDBDao(this.dataSource);
        }
        return this.trdao;
    }
}
