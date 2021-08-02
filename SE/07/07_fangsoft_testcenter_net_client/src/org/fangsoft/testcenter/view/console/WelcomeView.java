package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.config.Configuration;
import org.fangsoft.testcenter.view.TestCenterView;

import java.util.Date;

import static org.fangsoft.util.Console_util.output;

public class WelcomeView extends ConsoleView implements TestCenterView {
    public void displayView() {
        output("███████╗ █████╗ ███╗   ██╗ ██████╗ ███████╗ ██████╗ ███████╗████████╗    ████████╗███████╗███████╗████████╗ ██████╗███████╗███╗   ██╗████████╗███████╗██████╗ \n" +
                "██╔════╝██╔══██╗████╗  ██║██╔════╝ ██╔════╝██╔═══██╗██╔════╝╚══██╔══╝    ╚══██╔══╝██╔════╝██╔════╝╚══██╔══╝██╔════╝██╔════╝████╗  ██║╚══██╔══╝██╔════╝██╔══██╗\n" +
                "█████╗  ███████║██╔██╗ ██║██║  ███╗███████╗██║   ██║█████╗     ██║          ██║   █████╗  ███████╗   ██║   ██║     █████╗  ██╔██╗ ██║   ██║   █████╗  ██████╔╝\n" +
                "██╔══╝  ██╔══██║██║╚██╗██║██║   ██║╚════██║██║   ██║██╔══╝     ██║          ██║   ██╔══╝  ╚════██║   ██║   ██║     ██╔══╝  ██║╚██╗██║   ██║   ██╔══╝  ██╔══██╗\n" +
                "██║     ██║  ██║██║ ╚████║╚██████╔╝███████║╚██████╔╝██║        ██║          ██║   ███████╗███████║   ██║   ╚██████╗███████╗██║ ╚████║   ██║   ███████╗██║  ██║\n" +
                "╚═╝     ╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚══════╝ ╚═════╝ ╚═╝        ╚═╝          ╚═╝   ╚══════╝╚══════╝   ╚═╝    ╚═════╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝╚═╝  ╚═╝\n" +
                "                                                                                                                                                              ");
        output("今天是：" +
                Configuration.getDateFormat().format(new Date()));
        output("你的操作系统是：" + System.getProperty("os.name"));
    }

}
