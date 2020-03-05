public class Main {

    private final Object MONITOR = new Object();
    private static volatile char ch = 'A';

    public static void main(String[] args) {
        Main m = new Main();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                    m.writeA();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                    m.writeB();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                   m.writeC();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public void writeA () {
        synchronized (MONITOR) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (ch != 'A') {
                        MONITOR.wait();
                    }
                    System.out.print("A");
                    ch = 'B';
                    MONITOR.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeB () {
        synchronized (MONITOR) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (ch != 'B') {
                        MONITOR.wait();
                    }
                    System.out.print("B");
                    ch = 'C';
                    MONITOR.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeC () {
        synchronized (MONITOR) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (ch != 'C') {
                        MONITOR.wait();
                    }
                    System.out.print("C");
                    ch = 'A';
                    MONITOR.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
