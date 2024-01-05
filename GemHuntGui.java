import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

class GemHuntGui {

    private GemHuntC kontroll;
    private JFrame vindu;
    private JPanel panelRutenett;

    private JLabel tekstPoeng;
    public JPanel panelInfo;

    private int rad, kol;

    // ------------- Visuelt -----------------------------

    ImageIcon icon = new ImageIcon("images/bomb.png");
    Image originalImage = icon.getImage();
    Image nyttIcon = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon tnt = new ImageIcon(nyttIcon);

    ImageIcon stein = new ImageIcon("images/stein.png");
    Image originalStein = stein.getImage();
    Image nyStein = originalStein.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon pixelStein = new ImageIcon(nyStein);

    ImageIcon gem = new ImageIcon("images/greenGem.png");
    Image originaGem = gem.getImage();
    Image nyGem = originaGem.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon gemm = new ImageIcon(nyGem);

    ImageIcon ruby = new ImageIcon("images/ruby.png");
    Image ogRuby = ruby.getImage();
    Image nyRuby = ogRuby.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon rubyUse = new ImageIcon(nyRuby);

    ImageIcon blueGem = new ImageIcon("images/blueGem.png");
    Image ogBlueGem = blueGem.getImage();
    Image nyBlueGem = ogBlueGem.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon blueGemUse = new ImageIcon(nyBlueGem);

    ImageIcon dimond = new ImageIcon("images/dimond.png");
    Image ogDimond = dimond.getImage();
    Image nyDimond = ogDimond.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon dimondUse = new ImageIcon(nyDimond);

    Font font;

    // ------------------- Farger -------------------------------

    Color graa = new Color(73, 80, 87);
    Color gul = new Color(252, 163, 17);

    public GemHuntGui(GemHuntC kontroll, int rad, int kol) {
        this.kontroll = kontroll;
        this.rad = rad;
        this.kol = kol;

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(0);
        }
        // --------------- Font -----------------
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("PressStart2P-Regular.ttf"));
            font = font.deriveFont(Font.PLAIN, 20);
        } catch (IOException | FontFormatException e) {

        }

        vindu = new JFrame("Mine Sweeper");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindu.setLayout(new BorderLayout());

        leggTilInfo();
        leggTilKnapper();

        vindu.setSize(1920, 1080);
        vindu.setVisible(true);
        vindu.setLocationRelativeTo(null);

    }

    public void oppdaterPoeng() {
        tekstPoeng.setText("Poeng: " + kontroll.hentAntallPoeng());

    }

    public void leggTilInfo() {
        panelInfo = new JPanel();
        panelInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JLabel testBomber = new JLabel("Bomber: " + kontroll.hentBomber());
        testBomber.setFont(font);

        panelInfo.add(testBomber);

        tekstPoeng = new JLabel("Poeng: " + kontroll.hentAntallPoeng());
        tekstPoeng.setFont(font);
        panelInfo.add(tekstPoeng);

        panelInfo.setBackground(graa);
        testBomber.setForeground(gul);

        tekstPoeng.setForeground(gul);
        vindu.add(panelInfo, BorderLayout.NORTH);

    }

    public void leggTilKnapper() {
        panelRutenett = new JPanel(new GridLayout(rad, kol));
        panelRutenett.setBackground(graa);

        int[][] rutenett = kontroll.hentRutenett();

        class MineSweeperKnapp extends JButton {
            private int verdi;

            public MineSweeperKnapp(int verdi) {
                super("");
                this.verdi = verdi;
                super.addActionListener(new KnappBehandler());
            }

            class KnappBehandler implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if (kontroll.erFerdig)
                        return;
                    kontroll.leggTilPoeng(verdi);

                    if (verdi == 0) {
                        setIcon(tnt);
                        // setForeground(Color.black);
                        kontroll.erFerdig = true;

                    } else {
                        if (verdi == 1 || verdi == 2) {
                            setIcon(rubyUse);
                        }

                        if (verdi == 3 || verdi == 4) {
                            setIcon(gemm);
                        }

                        if (verdi == 5 || verdi == 6) {
                            setIcon(blueGemUse);
                        }

                        if (verdi == 7 || verdi == 8 || verdi == 9) {
                            setIcon(dimondUse);
                        }

            
                    }

                }

            }

        }
        
        for (int rx = 0; rx < rutenett.length; rx++) {
            for (int kx = 0; kx < rutenett[rx].length; kx++) {
                int verdi = rutenett[rx][kx];
                MineSweeperKnapp ruteKnapp = new MineSweeperKnapp(verdi);
                ruteKnapp.setBackground(graa);
                // ruteKnapp.setForeground(gul);
                ruteKnapp.setIcon(pixelStein);
                ruteKnapp.setPreferredSize(new Dimension(30, 30));
                ruteKnapp.setHorizontalAlignment(JLabel.CENTER);
                ruteKnapp.setVerticalAlignment(JLabel.CENTER);
                ruteKnapp.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                panelRutenett.add(ruteKnapp);

            }
        }
        vindu.add(panelRutenett, BorderLayout.CENTER);
    }

}