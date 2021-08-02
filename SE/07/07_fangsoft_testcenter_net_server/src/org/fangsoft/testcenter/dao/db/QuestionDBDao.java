package org.fangsoft.testcenter.dao.db;

import org.fangsoft.testcenter.dao.QuestionDao;
import org.fangsoft.db.IRS2Object;
import org.fangsoft.db.SQLAction;
import org.fangsoft.db.SQLUtil;
import org.fangsoft.testcenter.model.ChoiceItem;
import org.fangsoft.testcenter.model.Question;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.util.DataConverter;
import org.fangsoft.util.DataValidator;
import org.fangsoft.util.NumberUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuestionDBDao extends SQLAction implements QuestionDao {
    public QuestionDBDao(DataSource ds) {
        super(ds);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    private DataSource dataSource;

    public static final String sql_save = "insert into QUESTION (QN_NAME, QN_SCORE, QN_TEST_ID, QN_ID) values(?,?,?,?)";

    protected Object[] question2SQLParam(Question q, int qid, int testId) {
        Object[] p = new Object[4];
        p[0] = DataValidator.validate(q.getName());
        p[1] = q.getScore();
        p[2] = testId;
        p[3] = qid;
        return p;
    }

    public static final String sql_choiceitem_save = "insert into CHOICEITEM (CM_NAME, CM_CORRECT,CM_QUESTION_ID, CM_ID) values(?,?,?,?)";

    protected Object[] choiceItem2SQLParam(ChoiceItem c, int cid, int qid) {
        Object[] p = new Object[4];
        p[0] = DataValidator.validate(c.getName());
        p[1] = DataConverter.boolean2Int(c.isCorrect());
        p[2] = qid;
        p[3] = cid;
        return p;
    }

    private void saveChoiceItem(Connection conn, Question q, int qid)
            throws SQLException {
        List<Integer> cids = new ArrayList<Integer>();
        List<ChoiceItem> items = q.getChoiceItem();
        if (items != null && items.size() > 0) {
            List<Object[]> ps = new ArrayList<Object[]>();
            for (ChoiceItem c : items) {
                int cid = Sequence.getSeqValue(
                        conn, Sequence.SEQ_CHOICEITEM);
                cids.add(cid);
                ps.add(this.choiceItem2SQLParam(c, cid, qid));
            }
            this.batch(conn, sql_choiceitem_save, ps);
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setId(cids.get(i));
            }
        }
    }


    @Override
    public void addQuestionToTest(Test test, Question q) {
        Connection conn = null;
        try {
            conn = this.getDataSource().getConnection();
            conn.setAutoCommit(false);
            int qid = Sequence.getSeqValue(conn, Sequence.SEQ_QUESTION);
            Object[] p = this.question2SQLParam(q, qid, test.getId());
            this.update(conn, sql_save, p);
            this.saveChoiceItem(conn, q, qid);
            conn.commit();
            q.setId(qid);
        } catch (SQLException e) {
            SQLUtil.rollback(conn);
            e.printStackTrace();
        } finally {
            SQLUtil.close(conn);
        }
    }

    private static final ResultSet2ChoiceItem rs2Item = new ResultSet2ChoiceItem();

    public static final ResultSet2ChoiceItem getRS2Item() {
        return rs2Item;
    }

    private static class ResultSet2ChoiceItem implements IRS2Object<ChoiceItem> {
        public ChoiceItem process(ResultSet rs) throws SQLException {
            return ResultSet2Object.rs2ChoiceItem(rs);
        }
    }

    public static final String sql_addChoiceItemToQuestion =
            "select * from CHOICEITEM where CM_QUESTION_ID = ?";

    private void addChoiceItemToQuestion(Question q, Connection conn)
            throws SQLException {
        List<ChoiceItem> items = this.query2List(getRS2Item(),
                sql_addChoiceItemToQuestion, q.getId());
        q.setChoiceItem(items);
    }

    public static Question rs2Question(ResultSet rs) throws SQLException {
        return ResultSet2Object.rs2Question(rs);
    }

    public static final String sql_loadQuestion =
            "select * from QUESTION  where QN_TEST_ID = ?";
    public static final String sql_loadQuestion_count =
            "select count(*) from QUESTION where QN_TEST_ID = ?";

    @Override

    public boolean loadQuestion(Test test) {
        int tid = test.getId();
        if (tid == 0) return false;
        Connection conn = null;
        ResultSet qrs = null;
        int count = 0;
        int[] indexs = null;
        int numQuestion = test.getNumQuestion();
        try {
            conn = this.getDataSource().getConnection();
            conn.setAutoCommit(false);
            ResultSet rs = this.query2ResultSet(conn, sql_loadQuestion_count, tid);
            if (rs.next()) count = rs.getInt(1);
            rs.close();
            if (count < numQuestion) {
                return false;
            }
            indexs = NumberUtil.uniRandomNumbers(0, count, numQuestion);
            PreparedStatement stmt = conn.prepareStatement(sql_loadQuestion,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, tid);
            qrs = stmt.executeQuery();
            if (!qrs.next()) return false;
            for (int i : indexs) {
                boolean ok = qrs.absolute(i + 1);
                if (ok) {
                    Question q = rs2Question(qrs);
                    this.addChoiceItemToQuestion(q, conn);
                    test.addQuestion(q);
                }
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            SQLUtil.rollback(conn);
            e.printStackTrace();
        } finally {
            SQLUtil.close(qrs);
            SQLUtil.close(conn);
        }
        return false;
    }

}

