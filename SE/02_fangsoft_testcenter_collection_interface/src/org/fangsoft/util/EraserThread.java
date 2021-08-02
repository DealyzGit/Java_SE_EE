package org.fangsoft.util;
import java.util.Scanner;

/**
 * Create Time: 2018-03-17 07:28
 *
 * @author sheting
 */
 class TestEraserThread {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("请输入名称：");
            String name = scanner.next();

            System.out.print("请输入密码：");
            EraserThread eraserThread = new EraserThread('#');
            eraserThread.start();
            String password = scanner.next();
            eraserThread.setActive(false);

            if ("sheting".equals(name) && "123456".equals(password)) {
                System.out.println(String.format("欢迎%s", name));
                break;
            } else {
                System.out.println("用户名或密码输入错误！请重新输入");
            }
        }
    }
}


public class EraserThread extends Thread {
    private boolean active;
    private String mask;

    public EraserThread() {
        this('*');
    }

    public EraserThread(char maskChar) {
        active = true;
        mask = "\010" + maskChar;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void run() {
        while (isActive()) {
            System.out.print(mask);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
