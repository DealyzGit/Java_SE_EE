package org.fangsoft.testcenter.controller;

public class TestTimeoutGuard implements Runnable {
    public long interval = 10 * 1000;//10s
    private boolean testFinished = false;
    private TestCenterController controller;

    public TestTimeoutGuard(TestCenterController controller) {
        this.controller = controller;
        Thread t = new Thread(this);
        t.setPriority(Thread.NORM_PRIORITY);
        t.setDaemon(true);
        t.start();
    }

    public void run() {
        while (!isTestFinished()) {
            if (this.controller.isTestTimeout()) {
                this.controller.handleTimeOut();
                break;
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized boolean isTestFinished() {
        return testFinished;
    }

    public synchronized void setTestFinished(boolean testFinished) {
        this.testFinished = testFinished;
    }
}
