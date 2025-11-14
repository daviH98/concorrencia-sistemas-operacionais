import java.util.concurrent.Semaphore;

public class CorridaComSemaforo {

    static int contador = 0;
    static Semaphore semaforo = new Semaphore(1, true);

    static class Tarefa extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                try {
                    semaforo.acquire();
                    contador++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaforo.release();
                }
            }
        }
    }

    public static void main(String[] args) {
        Tarefa t1 = new Tarefa();
        Tarefa t2 = new Tarefa();

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final do contador: " + contador);
    }
}
