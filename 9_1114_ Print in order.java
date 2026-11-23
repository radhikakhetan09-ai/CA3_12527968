class Foo {
    private CountDownLatch waitForFirst;
    private CountDownLatch waitForSecond;

    public Foo() {
        waitForFirst = new CountDownLatch(1);
        waitForSecond = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        waitForFirst.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        waitForFirst.await();
        printSecond.run();
        waitForSecond.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        waitForSecond.await();
        printThird.run();
    }
}
