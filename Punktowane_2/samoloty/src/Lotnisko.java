import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class Lotnisko {

    private List<Samolot> samoloty;
    private final static Random random = new Random();

    public Lotnisko(int iloscSamolotow) {
        samoloty = new ArrayList<>();
        NameGenerator nameGenerator = () -> {
            String x = "abcdefghijklmnoprstuwxyz";
            StringBuilder str = new StringBuilder();
            int dlugosc = random.nextInt(1, 21);
            for (int k = 0; k < dlugosc; k++) {
                str.append(x.charAt(random.nextInt(0, x.length())));
            }
            return str.toString();
        };
        for (int i = 0; i < iloscSamolotow; i++) {
            String nazwa = nameGenerator.losujNazwe();
            int los = random.nextInt(3);
            if (los == 0) {
                samoloty.add(stworzSamolotPasazerski(nazwa));
            } else if (los == 1) {
                samoloty.add(stworzSamolotTowarowy(nazwa));
            } else {
                samoloty.add(stworzMysliwiec(nazwa));
            }
        }
    }

    private SamolotPasazerski stworzSamolotPasazerski(String nazwa) {
        int predkoscMax = random.nextInt(500, 1001);
        int maxLiczbaPasazerow = random.nextInt(100, 301);
        return new SamolotPasazerski(nazwa, predkoscMax, maxLiczbaPasazerow);
    }

    private SamolotTowarowy stworzSamolotTowarowy(String nazwa) {
        int predkoscMax = random.nextInt(300, 701);
        int maxLadunek = random.nextInt(10, 101);
        return new SamolotTowarowy(nazwa, predkoscMax, maxLadunek);
    }

    private Mysliwiec stworzMysliwiec(String nazwa) {
        int predkoscMax = random.nextInt(900, 3001);
        return new Mysliwiec(nazwa, predkoscMax);
    }

    public void odprawaSamolotow() {
        for (Samolot samolot : samoloty) {
            if (samolot instanceof SamolotPasazerski) {
                try {
                    samolot.odprawa(random.nextInt(0, 400));
                } catch (WyjatekLotniczy e) {
                    System.out.println(e.getMessage());
                }
            } else if (samolot instanceof SamolotTowarowy) {
                try {
                    samolot.odprawa(random.nextInt(0, 201));
                } catch (WyjatekLotniczy e) {
                    System.out.println(e.getMessage());
                }
            } else if (samolot instanceof Mysliwiec) {
                try {
                    samolot.odprawa(random.nextInt(0, 11));
                } catch (WyjatekLotniczy e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void wypiszWszystkie(){
        Consumer<List<Samolot>> wypisanie = (list) -> list.forEach(System.out::println);
        wypisanie.accept(samoloty);
    }

    public void dzialaniaLotniskowe() {
        Consumer<List<Samolot>> var = (list) -> {
            list.forEach(Samolot::laduj);
            list.forEach(samolot -> {
                try {
                    samolot.odprawa(random.nextInt(401));
                } catch (WyjatekLotniczy e) {
                    System.out.println(e.getMessage());
                }
            });
            list.forEach(a -> a.lec(10));
            list.forEach(samolot1 -> {
                if (samolot1 instanceof Mysliwiec) ((Mysliwiec) samolot1).atak();
            });
        };
        wypiszWszystkie();
        var.accept(samoloty);
    }


    public void sortowanieSamolotow() {
        Comparator<Samolot> comparatorPredkosc = Comparator.comparingInt(Samolot::getPredkoscMax);
        samoloty.sort(comparatorPredkosc);
        wypiszWszystkie();
        Comparator<Samolot> comparatorNazwa = Comparator.comparing(Samolot::getNazwa)
                .thenComparing(Samolot::getNazwa, Comparator.comparingInt(String::length));
        samoloty.sort(comparatorNazwa);
        wypiszWszystkie();
    }

    public void sortowanieLosowe() {
        LosoweSort wyl = () -> {
            Comparator<Samolot> com;
            int los = random.nextInt(2);
            if (los == 0) {
                com = Comparator.comparingInt(Samolot::getPredkoscMax);
            } else {
                com = Comparator.comparing(Samolot::getNazwa)
                        .thenComparing(Samolot::getNazwa, Comparator.comparingInt(String::length));
            }
            return com;
        };
        samoloty.sort(wyl.wylosujKomperator());
        wypiszWszystkie();
    }

    public List<Samolot> getSamoloty() {
        return samoloty;
    }

    private interface LosoweSort {
        public Comparator<Samolot> wylosujKomperator();
    }

    private static abstract class Samolot {
        protected String nazwa;
        protected int predkoscMax;
        protected int iloscGodzinWPowietrzu;
        protected boolean czyPoOdprawie;
        protected boolean czyWPowietrzu;

        public String getNazwa() {
            return nazwa;
        }

        public int getPredkoscMax() {
            return predkoscMax;
        }

        public Samolot(String nazwa, int predkoscMax) {
            this.nazwa = nazwa;
            this.predkoscMax = predkoscMax;
        }

        public void lec(int planowanaIloscGodzinWPowietrzu) {
            if (!czyPoOdprawie & !czyWPowietrzu) {
                System.out.println("Nie możemy wystartować");
            } else if (czyPoOdprawie & !czyWPowietrzu) {
                System.out.println("Startujemy");
                iloscGodzinWPowietrzu = planowanaIloscGodzinWPowietrzu;
            } else if (czyPoOdprawie & czyWPowietrzu) {
                System.out.println("Lecimy");
            }
        }

        public void laduj() {
            if (czyWPowietrzu) {
                System.out.println("Lądujemy");
                czyWPowietrzu = false;
            } else {
                System.out.println("I tak jesteśmy na ziemi");
            }
        }

        public abstract void odprawa(int iloscZaladunku) throws WyjatekLotniczy;

        @Override
        public String toString() {
            return " o nazwie " + nazwa +
                    ". Prędkość maksymalna " + predkoscMax +
                    ", w powietrzu spędził łącznie " + iloscGodzinWPowietrzu +
                    " godzin";
        }
    } // end Samolot

    public static class SamolotPasazerski extends Samolot {
        private int maxLiczbaPasazerow;
        private int aktualnaLiczbaPasazerow;

        public SamolotPasazerski(String nazwa, int predkoscMax, int maxLiczbaPasazerow) {
            super(nazwa, predkoscMax);
            this.maxLiczbaPasazerow = maxLiczbaPasazerow;
        }

        @Override
        public void odprawa(int iloscZaladunku) throws WyjatekLotniczy {
            aktualnaLiczbaPasazerow = iloscZaladunku;
            int roznicaIlosciPasazerow = aktualnaLiczbaPasazerow - maxLiczbaPasazerow;
            if (aktualnaLiczbaPasazerow < maxLiczbaPasazerow / 2) {
                throw new WyjatekEkonomiczny("Za mało pasażerów");
            } else if (roznicaIlosciPasazerow > 0) {
                czyPoOdprawie = true;
                throw new WyjatekPrzeladowania(String.valueOf(roznicaIlosciPasazerow) + " pasażerów");
            } else {
                czyPoOdprawie = true;
            }
        }

        @Override
        public String toString() {
            String s = "Samolot pasazerski" + super.toString() +
                    ", może zabrać na pokład " + maxLiczbaPasazerow + " pasażerów. ";
            if (czyWPowietrzu) {
                s += "Obecnie leci z " + aktualnaLiczbaPasazerow + " pasażerami na pokładzie.";
            } else {
                s += "Aktualnie uziemiony";
            }
            return s;
        }
    } // end SamolotPasazerski

    public static class SamolotTowarowy extends Samolot {

        private int maxLadunek;
        private int aktualnyLadunek;

        public SamolotTowarowy(String nazwa, int predkoscMax, int maxLadunek) {
            super(nazwa, predkoscMax);
            this.maxLadunek = maxLadunek;
        }

        @Override
        public void odprawa(int iloscZaladunku) throws WyjatekLotniczy {
            aktualnyLadunek = iloscZaladunku;
            int roznicaIlosciZaladunku = aktualnyLadunek - maxLadunek;
            if (aktualnyLadunek < maxLadunek / 2) {
                throw new WyjatekEkonomiczny("Zbyt mały ładunek");
            } else if (roznicaIlosciZaladunku > 0) {
                czyPoOdprawie = true;
                throw new WyjatekPrzeladowania(String.valueOf(roznicaIlosciZaladunku) + " ton ladunku");
            } else {
                czyPoOdprawie = true;
            }
        }

        @Override
        public String toString() {
            String s = "Samolot towarowy" + super.toString() +
                    ", może zabrać na pokład " + maxLadunek + " ton ładunku. ";
            if (czyWPowietrzu) {
                s += "Obecnie leci z " + aktualnyLadunek + " tonami ładunku na pokładzie.";
            } else {
                s += "Aktualnie uziemiony";
            }
            return s;
        }
    } // end SamolotTowarowy

    public static class Mysliwiec extends Samolot {

        private int iloscRakiet;

        public Mysliwiec(String nazwa, int predkoscMax) {
            super(nazwa, predkoscMax);
        }

        @Override
        public void odprawa(int iloscZaladunku) throws WyjatekLotniczy {
            iloscRakiet = iloscZaladunku;
        }

        public void atak() {
            if (czyWPowietrzu) {
                iloscRakiet--;
                System.out.println("Ataaaaak");
                if (iloscRakiet == 0) {
                    laduj();
                }
            }
        }

        @Override
        public String toString() {
            String s = "Myśliwiec" + super.toString() + ".";
            if (czyWPowietrzu) {
                s += "Obecnie leci, rakiet: " + iloscRakiet + ".";
            } else {
                s += "Aktualnie uziemiony";
            }
            return s;
        }
    } // end Mysliwiec

    private static abstract class WyjatekLotniczy extends Exception {
        public WyjatekLotniczy(String s) {
            super(s);
        }

    } // end WyjatekLotniczy

    private static class WyjatekEkonomiczny extends WyjatekLotniczy {
        public WyjatekEkonomiczny(String s) {
            super(s + ", nie opłaca się lecieć");
        }
    } // end WyjatekEkonomiczny

    private static class WyjatekPrzeladowania extends WyjatekLotniczy {
        public WyjatekPrzeladowania(String s) {
            super("Za dużo o " + s);
        }
    } // end WyjatekPrzeladowania

    @FunctionalInterface
    private interface NameGenerator {
        String losujNazwe();
    }


}
