public class JantarFilosofos {

    static final int N = 5;
    static final Object[] garfos = new Object[N];

    static {
        for (int i = 0; i < N; i++) {
            garfos[i] = new Object();
        }
    }

    static class Filosofo extends Thread {
        int id;

        Filosofo(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            int garfoEsq = id;
            int garfoDir = (id + 1) % N;

            int primeiro = Math.min(garfoEsq, garfoDir);
            int segundo = Math.max(garfoEsq, garfoDir);

            for (int i = 0; i < 5; i++) {
                pensar();
                System.out.printf("Filósofo %d está com fome.%n", id);

                synchronized (garfos[primeiro]) {
                    System.out.printf("Filósofo %d pegou o garfo %d%n", id, primeiro);
                    dormir(50);

                    synchronized (garfos[segundo]) {
                        System.out.printf("Filósofo %d pegou o garfo %d%n", id, segundo);
                        comer();
                    }
                }
                System.out.printf("Filósofo %d devolveu os garfos.%n", id);
            }

            System.out.printf("Filósofo %d terminou.%n", id);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            new Filosofo(i).start();
        }
    }

    static void pensar() {
        System.out.println("Pensando...");
        dormir(100);
    }

    static void comer() {
        System.out.println("   -> Comendo...");
        dormir(100);
    }

    static void dormir(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
