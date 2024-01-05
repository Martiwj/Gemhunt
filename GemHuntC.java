class GemHuntC {
    private GemHuntModell modell;
    private GemHuntGui gui;
    private int antallPoeng = 0;

    public boolean erFerdig = false;

    public GemHuntC(int rad, int kol) {
        modell = new GemHuntModell(rad, kol);
        modell.lagRutenett();
        gui = new GemHuntGui(this, rad, kol);
    }

    public int[][] hentRutenett() {
        return modell.hentRutenett();
    }

    public int hentBomber() {
        return modell.hentBomber();
    }

    public void leggTilPoeng(int poeng) {
        antallPoeng += poeng;
        gui.oppdaterPoeng();
    }

    public int hentAntallPoeng() {
        return antallPoeng;
    }

}