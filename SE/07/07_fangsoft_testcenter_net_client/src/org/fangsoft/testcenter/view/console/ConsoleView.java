package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.model.QuestionResult;
import org.fangsoft.testcenter.view.TestCenterView;
import org.fangsoft.util.Console_util;

import java.util.Observable;

public abstract class ConsoleView  extends Observable implements TestCenterView {
    public final void display(){

        Console_util.output("===========fangsoft testcenter==========");
        displayView();
        Console_util.output("==copyright Fangsoft Inc,all rights reserved.==");
        Console_util.output("");
        setChanged();
        notifyObservers();
    }
    protected abstract void displayView();


}
