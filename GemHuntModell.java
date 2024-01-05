import java.util.Random;

//Spill logikk

class GemHuntModell {
    private int rad, kol;
    private int[][] rutenett;
    private int bomber = 0;

    public GemHuntModell(int rad, int kol) {
        this.rad = rad;
        this.kol = kol;
        rutenett = new int[rad][kol];
    }

    public int[][] hentRutenett() {
        return rutenett;
    }

    public int hentBomber() {
        return bomber;
    }

    public void lagRutenett() {
        Random rand = new Random();
        for (int rx = 0; rx < rad; rx++) {
            for (int kx = 0; kx < kol; kx++) {
                int verdi = rand.nextInt(9);
                rutenett[rx][kx] = verdi;

                if (verdi == 0)
                    bomber++;
            }
        }

    }

}