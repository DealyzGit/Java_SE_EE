package org.fangsoft.testcenter.dao.db;

import org.fangsoft.testcenter.model.*;
import org.fangsoft.util.DataConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.fangsoft.testcenter.dao.db.Field2Property.int2TestResult;
import static org.fangsoft.testcenter.dao.db.Field2Property.int2TestResultStatus;

public class ResultSet2Object {
    public static Test rs2Test(ResultSet rs) throws SQLException {
        Test t = new Test();
        t.setId(rs.getInt("TT_ID"));
        t.setName(rs.getString("TT_NAME"));
        t.setNumQuestion(rs.getInt("TT_NUMQUESTION"));
        t.setTimeLimitMin(rs.getInt("TT_TIMELIMITMIN"));
        t.setDescription(rs.getString("TT_DESCRIPTION"));
        t.setScore(rs.getInt("TT_SCORE"));
        return t;
    }

    public static Question rs2Question(ResultSet rs) throws SQLException {
        Question q = new Question();
        q.setId(rs.getInt("QN_ID"));
        q.setName(rs.getString("QN_NAME"));
        q.setScore(rs.getInt("QN_SCORE"));
        return q;
    }

    public static ChoiceItem rs2ChoiceItem(ResultSet rs) throws SQLException {
        ChoiceItem item = new ChoiceItem();
        item.setName(rs.getString("CM_NAME"));
        item.setCorrect(DataConverter.int2Boolean(rs.getInt("CM_CORRECT")));
        item.setId(rs.getInt("CM_ID"));
        return item;
    }

    //    TestResult，QuestionResult和Customer
    public static Customer rs2Customer(ResultSet rs) throws SQLException {
        Customer q = new Customer();
        q.setId(rs.getInt("CR_ID"));
        q.setUserId(rs.getString("CR_NAME"));
        q.setPassword(rs.getString("CR_PASSWORD"));
        q.setEmail(rs.getString("CR_EMAILS"));
        return q;
    }

    public static TestResult rs2TestResult(ResultSet rs) throws SQLException {
        TestResult q = new TestResult();
        q.setId(rs.getInt("TL_ID"));
        q.setStartTime(DataConverter.str2Date(rs.getString("TL_STARTTIME")));
        q.setEndTime(DataConverter.str2Date(rs.getString("TL_ENDTIME")));
        q.setStatus(int2TestResultStatus(rs.getInt("TL_STATUS")));
        q.setResult(int2TestResult(rs.getInt("TL_RESULT")));

        return q;
    }

    public static QuestionResult rs2QuestionResult(ResultSet rs) throws SQLException {
        QuestionResult q = new QuestionResult();
        q.setId(rs.getInt("QT_ID"));
        q.setScore(rs.getInt("QT_SCORE"));
        q.setAnswer(rs.getString("QT_ANSWER"));
        q.setResult(DataConverter.int2Boolean(rs.getInt("QT_RESULT")));
        return q;
    }
}
