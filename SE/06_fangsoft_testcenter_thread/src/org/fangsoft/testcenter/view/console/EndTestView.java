package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.testcenter.view.TestCenterView;

import java.util.Date;

import static org.fangsoft.util.Console_util.output;

public class EndTestView extends ConsoleView implements TestCenterView {
//    @Override
//    protected void displayView() {
//                 System.out.println("退出考试系统！，即将生成考试报告...");
//    }


    private TestResult tr;

    public TestResult getTr() {
        return tr;
    }

    public void setTr(TestResult tr) {
        this.tr = tr;
    }

    public boolean isTimeout() {
        return timeout;
    }

    public void setTimeout(boolean timeout) {
        this.timeout = timeout;
    }

    private boolean timeout=false;
    public EndTestView(TestResult tr, boolean timeout) {
        this.tr = tr;
        this.timeout = timeout;
    }
    public EndTestView(){}

    public void displayView() {
        if(tr==null){
            tr=new TestResult();
        }
        tr.setEndTime(new Date());
        output("考试结束，现在时间是：%1$tT%n",this.tr.getEndTime());
        long duration=0;
        if(timeout){
            output("时间已到，考试已由系统提交");
            duration=this.tr.getTest().getTimeLimitMin();
        }else{
            duration=(this.tr.getEndTime().getTime()-
                    this.tr.getStartTime().getTime())/(1000*60);
            if(duration==0)duration=1;
        }
        output("你花了%1$s分钟考试%n",duration);
    }


}
