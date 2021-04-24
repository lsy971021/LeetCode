public class Test {
    static int a;
    private static final ThreadLocal<Integer> thread = new ThreadLocal<Integer>();
    public static void main(String[] args) throws InterruptedException {

        /*a=100000;
        for (int i = 0; i < 10000; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 10; i1++) {
                        System.out.println(--a);
                    }
                }
            }.start();
        }
        Thread.sleep(4000);
        System.out.println(a+"=============================================================");*/
        a=100000;
        for (int i = 0; i < 1000; i++) {
            new Thread() {
                @Override
                public void run() {
                    thread.set(a);
                    Integer integer = thread.get();
                    for (int i1 = 0; i1 < 1000; i1++) {
                        System.out.println(--integer);
                    }
                }
            }.start();
        }
        Thread.sleep(4000);
        System.out.println(a+"=============================================================");
    }
}
