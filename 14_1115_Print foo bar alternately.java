class FooBar {
    private int n;
    private Semaphore fooCanRun;
    private Semaphore barCanRun;

    public FooBar(int n) {
        this.n = n;
        this.fooCanRun = new Semaphore(1);
        this.barCanRun = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            fooCanRun.acquire();
            printFoo.run();
            barCanRun.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            barCanRun.acquire();
            printBar.run();
            fooCanRun.release();
        }
    }
}
