import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;
import java.lang.reflect.Field;
import java.util.Date;


public class Adnotacje {
    public static void main(String[] args) throws Exception {

        /*Object[] elements = new Object[1000];
        // inicjacja tabeli obiektami proxy
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{
                    Comparable.class}, handler);
            elements[i] = proxy;
        }
        // wyszukiwanie losowego elementu
        Integer key = new Random().nextInt(elements.length) + 1;
        int result = Arrays.binarySearch(elements, key);
        if (result >= 0) {
            System.out.println(elements[result]);
        }*//*

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface BugCount {
            int value();
        }
        *//*@BugCount(1)
        private static void showToDo (Method m){
            // Do something
        }
*//*
        public static void main (String[]args ){
            int bugs =
                    Arrays.stream(AnnotationsDemo.class.getDeclaredMethods()).mapToInt(AnnotationsDemo::calculateBugs).sum();
            System.out.println(" Bugs in project : " + bugs);
        }
        private static int calculateBugs (Method m){
            return m.isAnnotationPresent(BugCount.class) ?
                    m.getAnnotation(BugCount.class).value() : 0;
        }

        *//*@Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface ToDo {
            PRIORITY priority() default PRIORITY.LOW;
            String[] description();
            Class<?> source() default AnnotationsDemo.class;
        }*/


        System.out.println("Java Custom Annotation Example");
        System.out.println();

        User usr = new User();
        usr.setEmail("john.doe@example.com");
        usr.setName("John Doe");
        usr.setId(112);
        usr.setCreated(new Date());

        for (Field field : usr.getClass().getDeclaredFields()) {
            DBField dbField = field.getAnnotation(DBField.class);
            System.out.println("field name: " + dbField.name());

            // changed the access to public
            field.setAccessible(true);
            Object value = field.get(usr);
            System.out.println("field value: " + value);

            System.out.println("field type: " + dbField.type());
            System.out.println("is primary: " + dbField.isPrimaryKey());
            System.out.println();
        }


        /*@interface EmptyStringFields {
            // No attributes needed
        }

        @EmptyStringFields
        class A {
            String name;
            String address;
            int age;
            // Other fields and methods
        }

        public void emptyStringFields(A a) {
            // Get the class object of A
            Class<?> cls = a.getClass();
            // Get the annotation object of @EmptyStringFields
            Annotation annotation = cls.getAnnotation(EmptyStringFields.class);
            // Check if the class A is annotated with @EmptyStringFields
            if (annotation != null) {
                // Get all the declared fields of the class A
                Field[] fields = cls.getDeclaredFields();
                // Loop through the fields
                for (Field field : fields) {
                    // Check if the field is of type String
                    if (field.getType().equals(String.class)) {
                        // Make the field accessible
                        field.setAccessible(true);
                        try {
                            // Set the field value to ""
                            field.set(a, "");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }*/

        /*public void truncateStringField(Object obj) {
            // Get the class object of the object
            Class<?> cls = obj.getClass();
            // Get the field object of the field
            Field field = cls.getDeclaredField("word");
            // Make the field accessible
            field.setAccessible(true);
            // Check if the field is annotated with @TruncateString
            if (field.isAnnotationPresent(TruncateString.class)) {
                // Get the value of the field
                String value = (String) field.get(obj);
                // Truncate the value to the first 3 letters and append "___"
                String newValue = value.substring(0, 3) + "___";
                // Set the new value to the field
                field.set(obj, newValue);
            }
        }*/
        /*public @interface WywolajWieleRazy {
            int liczbaWywolan(); // Metoda adnotacji
        }

        public void wywolajMetode(Object obj) {
            // Pobierz obiekt klasy obiektu
            Class<?> cls = obj.getClass();
            // Pobierz obiekt metody metody
            Method method = cls.getDeclaredMethod("powiedzCzesc");
            // Sprawdź, czy metoda jest adnotowana z @WywolajWieleRazy
            if (method.isAnnotationPresent(WywolajWieleRazy.class)) {
                // Pobierz obiekt adnotacji
                WywolajWieleRazy annotation = method.getAnnotation(WywolajWieleRazy.class);
                // Pobierz liczbę wywołań
                int liczbaWywolan = annotation.liczbaWywolan();
                // Wywołaj metodę odpowiednią liczbę razy
                for (int i = 0; i < liczbaWywolan; i++) {
                    method.invoke(obj); // Wywołaj metodę na obiekcie
                }
            }
        }*/


    }
}
