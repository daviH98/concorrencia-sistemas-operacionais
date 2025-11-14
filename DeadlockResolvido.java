public class DeadlockResolvido {

    static final Object recursoA = new Object();
    static final Object recursoB = new Object();

    static class Tarefa extends Thread {
        int id;

        Tarefa(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            Object primeiro = recursoA;
            Object segundo = recursoB;

            // ordem: sempre A depois B
            synchronized (primeiro) {
                System.out.printf("T%d bloqueou A%n", id);
                dormir(100);

                synchronized (segundo) {
                    System.out.printf("T%d bloqueou B%n", id);
                    System.out.printf("T%d executando sem deadlock%n", id);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Tarefa(1).start();
        new Tarefa(2).start();
    }

    static void dormir(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
