import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Refleksje {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        Lotnisko e = new Lotnisko(5);
        Class cl = e.getClass();
        System.out.println(cl.getName());
        System.out.println(e.getClass().getName());
        System.out.println(e == null);

        Class cl1 = Random.class;
        Class cl2 = int.class;
        Class cl3 = Double[].class;
        Class cl4 = Lotnisko.class;
        System.out.println(cl1 + "   " + cl2 + "    " + cl3 + "     " + cl4);
        System.out.println(e.getClass() == cl4);

        Class cl5 = Class.forName("java.util.Date");
        Object date = cl5.newInstance();  // nowy obiekt z domyślnym konstruktorem
        System.out.println(date);

        System.out.println(cl4.getMethod("odprawaSamolotow").getReturnType());
        System.out.println(Modifier.isPublic(cl4.getMethod("odprawaSamolotow").getModifiers()));
        System.out.println(Modifier.toString(cl4.getMethod("odprawaSamolotow").getModifiers()) +
                " " + cl4.getMethod("odprawaSamolotow").getName());

        Lotnisko.Mysliwiec m = new Lotnisko.Mysliwiec("a", 100);
        /*m.getClass().getDeclaredMethods().stream().forEach(x -> System.out.println(x));//.forEach(m -> System.out.println(Modifier.toString(m.getModifiers()) +
               // " " + m.getName() + "; isPrivate :" + Modifier.isPrivate(m.getModifiers())));
*/
        Arrays.stream(m.getClass().getDeclaredMethods()).forEach(
                x -> System.out.println(Modifier.toString(x.getModifiers()) + " "
                        + x.getName() + "; isPrivate :" + Modifier.isPrivate(x.getModifiers())));


        Arrays.stream(m.getClass().getFields()).forEach(x -> {
            System.out.println("test 1");
            x.setAccessible(true);
            System.out.println(x);
            try {
                System.out.println(x + "    " + x.get(m));
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
        System.out.println("Konstruktory");

        //Wypisz listę konstruktorów wraz z parzmetrami klasy Barrel korzystając z informacji
        //możliwych do uzyskania z obiektu klasy.

        Arrays.stream(m.getClass().getDeclaredConstructors()).forEach(x -> {
            System.out.println("Konstruktor to: " + x + "\nParametry to: ");
        Arrays.stream(x.getParameters()).forEach(y -> System.out.println("Typ: " + y.getType().getSimpleName() +" Nazwa: " + y.getName()));
        });




        //Ustal, czy klasa Chest posiada konstruktor przyjmujący dwa parametry: boolean i
        //String o modyfikatorze protected.
        boolean w = false;

        Arrays.stream(Lotnisko.class.getDeclaredConstructors()).forEach(x -> {
            if (Modifier.isProtected(x.getModifiers())){
                if (x.getParameterCount() == 2){
                    System.out.println("istnieje");
                    return;
                    }
            }
        });



    }
}
