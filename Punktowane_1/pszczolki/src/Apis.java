import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Apis {

    List<Pszczola> ul;

    public Apis() {
        ul = new ArrayList<>();
        ul.add(new KrolowaMatka("Alicja"));
    }

    public void watkiPszczol() {
        for (Pszczola p : ul) {
            p.run();
        }
    }

    public void sortujWgSilyIImienia() {
        ul.sort(new PorownanieSily() {
            @Override
            public int compare(Pszczola p1, Pszczola p2) {
                return super.compare(p1, p2);
            }
        });


    }

    public void dodajZolnierza() {
        ul.add(new Pszczola("Helena") {
            @Override
            public String toString() {
                return "Żołnierz " + super.toString() +
                        "potrafię użądlić!";
            }

            @Override
            public void run() {
                System.out.println("Walka to moje życie!!!");
            }
        });

    }

    public void zyciePszczol(){
        Truten t1 = new Truten("Radek");
        Truten t2 = new Truten("Gucio");
        Robotnica r1 = new Robotnica("Maja");
        Robotnica r2 = new Robotnica("Basia");
        t1.run();
        t2.run();
        r1.run();
        r2.run();
        dodajPszczole(t1);
        dodajPszczole(t2);
        dodajPszczole(r1);
        dodajPszczole(r2);
    }

    @Override
    public String toString() {
        String s = "W ulu jest: " + ul.size() +
                " pszczół";
        for (Pszczola p : ul) {
            s += "\n";
            s += p.toString();
        }
        return s;
    }

    public void dodajPszczole(Pszczola p) {
        ul.add(p);
    }

    public abstract static class Pszczola implements Runnable {

        protected String imie;
        protected int silaAtaku;
        protected int wiek;
        protected Random random = new Random();

        public Pszczola(String imie) {
            this.imie = imie;
            this.silaAtaku = ustawSilaAtaku();
            this.wiek = ustawWiek();
        }

        protected int ustawSilaAtaku() {
            return random.nextInt(101);
        }

        protected int ustawWiek() {
            return random.nextInt(50);
        }

        @Override
        public String toString() {
            return imie +
                    " (atak: " + silaAtaku +
                    "), żyję " + wiek +
                    " dni i ";
        }
    } // end Pszczola

    public static class KrolowaMatka extends Pszczola {
        private int iloscJaj;

        public KrolowaMatka(String imie) {
            super(imie);
            this.iloscJaj = 0;
        }

        @Override
        public void run() {
            System.out.println("Lot godowy...");
        }

        protected int ustawSilaAtaku() {
            return 100;
        }

        private void zaplodnienie() {
            iloscJaj += 1000;
        }

        @Override
        public String toString() {
            return "Krolowa " + super.toString() +
                    "będę matką dla " + iloscJaj +
                    " młodych pszczółek";
        }
    } // end KrolowaMatka

    public class Truten extends Pszczola {

        private boolean przydatny;

        public Truten(String imie) {
            super(imie);
            przydatny = true;
        }

        @Override
        public void run() {
            int los = random.nextInt(2);
            if (los == 0) {
                zaplodnienie(znajdzKrolowa());
            } else {
                System.out.println("Jak przyjemnie nie robić nic...");
            }
        }

        private KrolowaMatka znajdzKrolowa(){
            List<KrolowaMatka> krolowe = new ArrayList<>();
            for(Pszczola p: ul){
                if(p instanceof KrolowaMatka){
                    krolowe.add((KrolowaMatka) p);
                }
            }
            return krolowe.get(random.nextInt(krolowe.size()));
        }

        protected int ustawSilaAtaku() {
            return 0;
        }

        public void zaplodnienie(KrolowaMatka krolowa) {
            przydatny = false;
            krolowa.zaplodnienie();
            System.out.println(imie + " - byłem z Królową!!! Można umierać...");
        }

        @Override
        public String toString() {
            String s = "Truten " +
                    imie +
                    " (atak: " + silaAtaku + "), ";
            if (przydatny) {
                s += "żyję " + wiek + " dni";
            } else {
                s += "spełniłem swoje zadanie :(";
            }
            return s;
        }
    } // end Truten

    public static class Robotnica extends Pszczola {

        private int iloscWyprodukowanegoMiodu;

        public Robotnica(String imie) {
            super(imie);
            iloscWyprodukowanegoMiodu = 0;
        }

        private void zbierajNektar(int x) {
            iloscWyprodukowanegoMiodu += x;
        }

        @Override
        public void run() {
            int iloscNektaru = random.nextInt(21);
            zbierajNektar(iloscNektaru);
            System.out.println(imie + " - kolejna porcja miodu do kubełka");
        }

        @Override
        public String toString() {
            return "Robotnica " +
                    super.toString() +
                    "zrobiłam " + iloscWyprodukowanegoMiodu +
                    " baryłek miodu :)";
        }
    } // end Robotnica

    private abstract class PorownanieSily implements Comparator<Pszczola> {
        @Override
        public int compare(Pszczola p1, Pszczola p2) {
            if (p1.silaAtaku > p2.silaAtaku) {
                return 1;
            } else if (p1.silaAtaku == p2.silaAtaku) {
                return p1.imie.compareTo(p2.imie);
            } else {
                return -1;
            }
        }

    } // end PorownanieSily

}
