public class CounterTest {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();
        CounterSync countersync = new CounterSync();

        int iterations = 1_000_000;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) {
                counter.increment();
		countersync.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) {
                counter.decrement();
		countersync.decrement();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Valor esperado: 0");
        System.out.println("Valor obtido em counter: " + counter.value());
        System.out.println("Valor obtido em countersync: " + countersync.value());
    }
}
