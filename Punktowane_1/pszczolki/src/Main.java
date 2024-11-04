import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Apis ul = new Apis();
        System.out.println(ul);
        ul.zyciePszczol();

        ul.dodajPszczole(new Apis.Robotnica("Marta"));
        ul.dodajPszczole(new Apis.KrolowaMatka("Waleria"));
        ul.dodajPszczole(ul.new Truten("Tadek"));
        System.out.println(ul);

        ul.sortujWgSilyIImienia();
        System.out.println(ul);

        ul.dodajZolnierza();

        System.out.println("\n\n");
        ul.sortujWgSilyIImienia();
        System.out.println(ul);

        ul.watkiPszczol();
        System.out.println(ul);



    }
}