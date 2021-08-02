package org.fangsoft.testcenter;

import org.fangsoft.testcenter.config.Configuration;
import org.fangsoft.testcenter.dao.CustomerDao;
import org.fangsoft.testcenter.dao.TestDao;
import org.fangsoft.testcenter.dao.array.CustomerArrayDao;
import org.fangsoft.testcenter.dao.array.QuestionArrayDao;
import org.fangsoft.testcenter.dao.array.TestArrayDao;
import org.fangsoft.testcenter.model.*;
import org.fangsoft.util.Console_util;

import java.io.Console;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.fangsoft.util.Console_util.*;
import java.util.*;

public class TestCenter {
    public static void welcome() {
        output("今天是：" + Configuration.getDateFormat().format(new Date()));
        output("你的操作系统是：" + System.getProperty("os.name"));
    }

//    public static Customer findCustomer(String userId, String password) {
//        Customer[] customers = CustomerData.getCustomer();
//        for (int i = 0; i < customers.length; i++) {
//            Customer c = customers[i];
//            if (c.getUserId().equals(userId) &&
//                    c.getPassword().equals(password)) {
//                return customers[i];
//            }
//        }
//        return null;
//    }

    public static Customer login() {
        output("参加考试前请先登录,输入完成后按enter确认:");
        for (int i = 0; i < Configuration.MAX_LOGIN; i++) {
            String userId = prompt("输入用户名称：");
            String password = prompt("输入用户密码：");

//            Customer customer = findCustomer(userId, password);
            CustomerDao cdao= CustomerArrayDao.getInstance();
            Customer customer=cdao.login(userId, password);

            if (customer != null) {
                output("欢迎" + customer.getUserId() +"光临fangsoft考试中心!");
                return customer;
            }
            output("用户名或密码错误不能登录,重新登录." +
                    "登录" + Configuration.MAX_LOGIN + "次不成功，系统将退出." +
                    "这是" + (i + 1) + "次");
        }
        return null;
    }

    private static void exit(Object msg) {
        output(msg);
        System.exit(1);
    }

    private static TestResult newTestResult(Customer c, Test test) {
        TestResult tr = new TestResult();
        tr.setCustomer(c);
        tr.setTest(test);
        tr.setStartTime(new Date());
        tr.setQuestionResult(new ArrayList<QuestionResult>(test.getQuestion().size()));
        int count = 0;
        for (Question q : test.getQuestion()) {
            if (q!=null) {
                q.assignLabel(Configuration.CHOICE_LABEL);
                QuestionResult qr = new QuestionResult();
                qr.setQuestion(q);
                tr.getQuestionResult().add(qr);
            }
        }
        return tr;
    }

    public static String presentQuestion(int pos, Question q) {
        Console_util.output("%1$s. %2$s%n", pos, q.getName());
        for (ChoiceItem item : q.getChoiceItem()) {
            Console_util.output(" %1$s. %2$s%n", item.getLabel(), item.getName());
        }
        Console_util.output("输入答案：");
        return Console_util.read();
    }

    public static TestResult takeTest(Test test, Customer customer) {
        output("==========开始考试===========");
        output("考试名称：%1$5s%n" +
                        "考试简述：%2$5s%n" +
                        "考试问题：%3$5s%n" +
                        "考试时间：%4$5s分钟%n",
                test.getName(),
                test.getDescription(),
                test.getNumQuestion(),
                test.getTimeLimitMin());
        long start = System.currentTimeMillis();
        output("注意你有%1$s分钟答题，现在时间是：%2$tT%n", test.getTimeLimitMin(), start);

        test=QuestionArrayDao.getInstance().loadQuestion(test);
        TestResult tr = newTestResult(customer, test);

        int count = 0;
        for (QuestionResult qr : tr.getQuestionResult()) {
            String answer = presentQuestion(++count, qr.getQuestion());
            qr.setAnswer(answer);
        }
        long end = System.currentTimeMillis();
        tr.setEndTime(new Date(end));
        tr.commitTest();
        output("考试结束，现在时间是：%1$tT%n", end);
        return tr;
    }

    public static void reportTestResult(TestResult tr) {
        output("==========考试报告===========");
        long duration = (tr.getEndTime().getTime() - tr.getStartTime().getTime()) / (1000 * 60);
        output("你花了%1$s分钟考试%n", duration);
        output("%1$-6s%2$-6s%3$-6s%4$-6s%n", "题号", "你的答案", "正确答案", "对错");
        int count = 0;
        for (QuestionResult qr : tr.getQuestionResult()) {
            output("%1$-8s%2$-8s%3$-8s%4$-8s%n",
                    ++count,
                    qr.getAnswer(),
                    qr.getQuestion().getAnswer(),
                    qr.isResult() ? "right" : "wrong");
        }
        boolean pass = tr.getPass() > 0 ? true : false;
        String displayPass = "";
        if (pass) displayPass = "恭喜，你通过了考试";
        else displayPass = "很遗憾，你没有通过考试";
        output("你考试的得分是：" + tr.getScore() + "," + displayPass);
    }

    public static Test selectTest(){
        output("fangsoft考试中心提供的所有考试：");

        TestDao tdao= TestArrayDao.getInstance();
        List<Test> allTest=tdao.findAllTest();

        int count=0;
        for(Test t:allTest){
            output((++count)+". "+t.getName()+"考试"+", 输入："+t.getName());
        }
        while(true){
            String testName= Console_util.prompt("请选择考试名称：");
            for(Test t:allTest){
                if(t.getName().equals(testName))
                    return t;
            }
        }
    }

    public static void main(String[] args) {

        welcome();
        Customer customer = login();
        if (customer == null) {
            exit("用户名或口令错，不能登录，系统退出");
        }
        boolean response = promptYesNo("确认参加考试吗？",
                "y", "是：y", "否,退出：n");
        if (!response) exit("系统退出");
//        Test test = TestData.produceTest();
        Test test = selectTest();
        TestResult tr = takeTest(test, customer);
        reportTestResult(tr);
    }

}


