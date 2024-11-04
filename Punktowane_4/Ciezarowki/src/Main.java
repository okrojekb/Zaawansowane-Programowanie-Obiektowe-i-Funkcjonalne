import Ciezarowka.FuelTank;
import Ciezarowka.KoloOdCiezarowki;
import Ciezarowka.SpareWheel;
import Ciezarowka.Truck;
import Ladunek.BagOfPotatoes;
import Ladunek.Barrel;
import Ladunek.Cargo;
import Ladunek.Chest;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {

        /*1. Wypisz listę konstruktorów wraz z parzmetrami klasy Barrel korzystając z informacji
        możliwych do uzyskania z obiektu klasy.*/

        System.out.println("Moja wersja: task 1");

        Arrays.stream(Barrel.class.getDeclaredConstructors()).forEach(x -> {
            System.out.println("Konstruktor to: " + x + "\nParametry to: ");
            Arrays.stream(x.getParameters()).forEach(y -> System.out.println("Typ: " + y.getType().getSimpleName() + " Nazwa: " + y.getName()));
        });

        System.out.println("--------------------1--------------------");
        Class<?> class1 = Class.forName("Ladunek.Barrel");
        Arrays.stream(class1.getDeclaredConstructors()).toList().forEach(System.out::println);

        /*2. Ustal, czy klasa Chest posiada konstruktor przyjmujący dwa parametry: boolean i
        String o modyfikatorze protected.*/

        System.out.println("\nMoja wersja: task 2");

        Class[] cl = {boolean.class, String.class};

        Arrays.stream(Chest.class.getDeclaredConstructors()).forEach(x -> {
            //if (Modifier.isProtected(x.getModifiers())){
            if (x.getParameterCount() == 2) {
                System.out.println("istnieje");
            }
            //}
            else {
                System.out.println("nie istnieje");
            }
        });

        System.out.println("--------------------2--------------------");
        Class<?> class2 = Class.forName("Ladunek.Chest");
        Class<?>[] parameterTypes = {String.class, boolean.class};
        try {
            System.out.println("Jest: " + class2.getDeclaredConstructor(parameterTypes));
        } catch (NoSuchMethodException e) {
            System.out.println("Brak");
        }

        /*3. Uzyskaj informaje o nazwie pakietu klasy Cargo.*/
        System.out.println("\nMoja wersja: task 3");

        System.out.println(Cargo.class.getPackageName());


        System.out.println("--------------------3--------------------");
        Class<?> class3 = Class.forName("Ladunek.Cargo");
        System.out.println(class3.getPackage());


        /*4. Wylistuj prywatne metody klasy BagOfPotatoes wraz z parametrami.*/
        System.out.println("\nMoja wersja: task 4");

        Arrays.stream(BagOfPotatoes.class.getDeclaredMethods()).forEach(x -> {
            if (Modifier.isPrivate(x.getModifiers())) {
                System.out.println(x);
            }
        });

        System.out.println("--------------------4--------------------");
        Class<?> class4 = Class.forName("Ladunek.BagOfPotatoes");
        Arrays.stream(class4.getDeclaredMethods()).filter(s -> Modifier.isPrivate(s.getModifiers())).toList().forEach(System.out::println);

        /*5. Ustal wartość pola sticker znajdującego się w klasie KoloOdCiezarowki i wypisz
        na konsoli.*/

        System.out.println("\nMoja wersja: task 5");

        /*KoloOdCiezarowki k = KoloOdCiezarowki.class.newInstance();
        k.getClass().getDeclaredField("sticker").setAccessible(true);
        k.setSticker("a");

        System.out.println(KoloOdCiezarowki.class.getField("sticker").get(k));
*/

        System.out.println("--------------------5--------------------");
        Class<?> class5 = Class.forName("Ciezarowka.KoloOdCiezarowki");
        try {
            Field field = class5.getDeclaredField("sticker");
            field.setAccessible(true);
            KoloOdCiezarowki kolo = (KoloOdCiezarowki) class5.getDeclaredConstructor().newInstance();
            field.set(kolo, "Nalypka");
            System.out.println(field.get(kolo));

        } catch (NoSuchFieldException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        /*6. Wypisz nazwę nadklasy klasy Barrel.*/
        System.out.println("\nMoja wersja: task 6");

        System.out.println(Barrel.class.getSuperclass());

        System.out.println("--------------------6--------------------");
        Class<?> class6 = Class.forName("Ladunek.Barrel");
        System.out.println(class6.getSuperclass());

        /*7. Wylistuj interfejsy implementowane przez klasę Cargo. Sprawdź, czy któryś z nich
        pochodzi z tego samego pakietu, co ta klasa.*/
        System.out.println("\nMoja wersja: task 7");

        Arrays.stream(Cargo.class.getInterfaces()).forEach(x -> {
            if (x.getPackage() == Cargo.class.getPackage()) {
                System.out.println(x + "ten interfejs pochodzi z tego samego pakietu co klasa");
            } else {
                System.out.println(x);
            }
        });

        System.out.println("--------------------7--------------------");
        Class<?> class7 = Class.forName("Ladunek.Cargo");
        Arrays.stream(class7.getInterfaces()).forEach(s -> {
            if (s.getPackage() == class7.getPackage()) {
                System.out.println(s + ", " + true);
            } else {
                System.out.println(s + ", " + false);
            }
        });

        /*8. Stwórz obiekt klasy KoloOdCiezarowki i ustal wartość pola tireSize na tym
        obiekcie.*/
        System.out.println("\nMoja wersja: task 8");

        //KoloOdCiezarowki k1 = KoloOdCiezarowki.class.getDeclaredConstructor(int.class).newInstance(5);
        KoloOdCiezarowki k1 = KoloOdCiezarowki.class.newInstance();
        k1.setTiresize(10);
        System.out.println(k1.getTiresize());


        System.out.println("--------------------8--------------------");
        Class<?> class8 = Class.forName("Ciezarowka.KoloOdCiezarowki");
        try {
            KoloOdCiezarowki koloo = (KoloOdCiezarowki) class5.getDeclaredConstructor().newInstance();
            Field field = class8.getDeclaredField("tiresize");
            field.setAccessible(true);
            field.set(koloo, 15);
            System.out.println(field.get(koloo));
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 NoSuchFieldException e) {
            e.printStackTrace();
        }
        /*9. Utwórz kolekcję beczek, skrzyń i worków z ziemniakami używając każdego z
        konstruktorów w tych klasach.*/
        System.out.println("\nMoja wersja: task 9");

        List<Cargo> l = new ArrayList<>();

        List<Class> c = new ArrayList<>();
        c.add(Barrel.class);
        c.add(Chest.class);
        c.add(BagOfPotatoes.class);

        /*c.stream().forEach(x -> {
            Arrays.stream(x.getDeclaredConstructors()).forEach(y -> {
                try {
                    l.add((Cargo) y.newInstance());
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
        });*/

        /*System.out.println("--------------------9--------------------");

        List<Class<?>> subclasses = List.of(Barrel.class, Chest.class, BagOfPotatoes.class);

        List<Object> cargos = subclasses.stream().flatMap(class9 -> Arrays.stream(class9.getConstructors())).map(Main::createObject).toList();
        cargos.forEach(System.out::println);
*/
        /*10. Utwórz obiekt klasy Truck.*/
        System.out.println("\nMoja wersja: task 10");

        Truck t = Truck.class.newInstance();
        System.out.println(t);


        System.out.println("--------------------10--------------------");
        Class<?> class10 = Class.forName("Ciezarowka.Truck");
        Truck truck = null;
        try {
            truck = (Truck) class10.getConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(truck);

        /*11. Uzyskaj dostęp do pola loadingBody i wywołaj na nim metodę addCargo, dodając
        obiekty utworzone w punkcie 9-tym.*/
        System.out.println("\nMoja wersja: task 11");

    /*    t.getClass().getDeclaredField("loadingBody").setAccessible(true);
        t.getClass().getDeclaredField("loadingBody").get(t);*/

        Field f = t.getClass().getDeclaredField("loadingBody");
        f.setAccessible(true);
        f.get(t);
        System.out.println(f.get(t));

        /*System.out.println("--------------------11--------------------");
        Class<?> class11 = Class.forName("Ciezarowka.Truck");
        try {
            Field field = class11.getDeclaredField("loadingBody");
            field.setAccessible(true);
            Method method = class11.getDeclaredMethod("addCargos", List.class);
            method.invoke(truck, cargos);
        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        cargos.forEach(System.out::println);*/

        /*12. Przypisz polom (instancji klasy Truck) spareWeel i fuelTank instancje stworzonych
        obiektów klas SpareWeel i FuelTank(napełniony). Informację o typach zaczerpnij
        z klas tych pól.*/
        System.out.println("\nMoja wersja: task 12");

        Field f2 = Truck.class.getDeclaredField("spareWheel");
        f2.setAccessible(true);
        f2.set(t, SpareWheel.class.newInstance());
        /*Truck.class.getDeclaredField("spareWheel").setAccessible(true);
        Truck.class.getDeclaredField("spareWheel").set(t, SpareWheel.class.newInstance());*/
        System.out.println(f2.get(t));
        System.out.println(t);

        System.out.println("--------------------12--------------------");
        Class<?> class12 = Class.forName("Ciezarowka.SpareWheel");
        Class<?> class12a = Class.forName("Ciezarowka.FuelTank");
        try {
            SpareWheel spareWheel = (SpareWheel) class12.getConstructor().newInstance();
            FuelTank fueltank = (FuelTank) class12a.getConstructor().newInstance();
            spareWheel.setTiresize(25);
            fueltank.setNapelniony(true);
            Field fuel = class10.getDeclaredField("fueltank");
            Field spare = class10.getDeclaredField("spareWheel");
            fuel.setAccessible(true);
            spare.setAccessible(true);
            fuel.set(truck, fueltank);
            spare.set(truck, spareWheel);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println(truck);


        /*13. Uzyskaj dostęp do pola: driversCabin i sprawdź czy elementy tego pola mają wartość
        null. Jeśli tak, to wstaw tam utworzone obiekty odpowiedniego typu. Informację o typach
        zaczerpnij z klas tych pól.*/

        /*14. Wywołaj metodę drive zdefiniowaną dla kierowcy.*/

        /*15. Stwórz adnotację TireCompany działającą podczas wykonania programu zawierającą
        dwie informacje będące ciągami znaków: nazwa producenta oraz rozmiar opony.
                "Ozdób" nią klasę spareWeel. Wstaw dowolne wartości.*/

    }

    private static Object createObject(Constructor<?> constructor) {
        try {
            constructor.setAccessible(true);
            int parameterCount = constructor.getParameterCount();
            Object[] arguments = new Object[parameterCount];

            for (int i = 0; i < parameterCount; i++) {

                arguments[i] = new Random().nextInt(15) + 1;
            }
            return constructor.newInstance(arguments);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }

    }
}