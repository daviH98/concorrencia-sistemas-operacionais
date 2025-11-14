public class DeadlockExemplo {

    static final Object recursoA = new Object();
    static final Object recursoB = new Object();

    static class Tarefa1 extends Thread {
        @Override
        public void run() {
            synchronized (recursoA) {
                System.out.println("T1 bloqueou A, tentando B...");
                dormir(100);
                synchronized (recursoB) {
                    System.out.println("T1 conseguiu B");
                }
            }
        }
    }

    static class Tarefa2 extends Thread {
        @Override
        public void run() {
            synchronized (recursoB) {
                System.out.println("T2 bloqueou B, tentando A...");
                dormir(100);
                synchronized (recursoA) {
                    System.out.println("T2 conseguiu A");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Tarefa1().start();
        new Tarefa2().start();
    }

    static void dormir(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
