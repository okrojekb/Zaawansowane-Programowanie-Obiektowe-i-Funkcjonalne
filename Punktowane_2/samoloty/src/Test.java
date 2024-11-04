import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\okroj\\OneDrive\\Studia\\semestr 3\\ZPOiF\\Punktowane_2\\samoloty\\src\\tekst.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(content.split("[ \t\n.]"));

        long count = words.stream().filter(s -> s.length() > 10).count();
        System.out.println(count);

        long count2 = words.parallelStream().filter(s -> s.length() > 10).count();
        System.out.println(count2);

        Stream<BigInteger> factorial = Stream.iterate(BigInteger.ONE, n ->
                n.multiply(n.add(BigInteger.ONE)));
        //factorial.forEach(System.out::println);

        Stream<String> smallWords = words.stream().map(String::toLowerCase);

        Stream<String> firstLetters = words.stream().map(s -> s.substring(0, 1));

        smallWords.forEach(System.out::println);
        System.out.println("");
        //firstLetters.forEach(System.out::println);

        String l = "sjflkjllkkjdsjh";

        letters(l).forEach(System.out::println);

        Stream<Stream<String>> result = words.stream().map(w -> letters(w));
        result.forEach(System.out::println);

        Stream<String> flatResult = words.stream().flatMap(w -> letters(w)).limit(15);
        Stream<String> flatResult1 = words.stream().flatMap(w -> letters(w)).limit(15);

        System.out.println("random");
        flatResult.forEach(System.out::println);
        System.out.println("pokolei");
        flatResult1.forEachOrdered(System.out::println);

        Stream<Double> random = Stream.generate(Math::random).limit(100);
        Stream<String> words2 = Stream.of(content.split(" ")).skip(10);
        random.forEach(System.out::println);
        words2.forEach(System.out::println);


        Stream<BigInteger> factorial2 = Stream.iterate(BigInteger.ONE, n -> n.multiply(n.add(BigInteger.ONE))).skip(5).limit(5);
        factorial2.forEach(System.out::println);

        Stream<String> concatenated = Stream.concat(letters("Pine"), letters("apple"));
        concatenated.forEach(System.out::println);

        Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed());
        Stream<String> uniqueWords = Stream.of(" Twinkle ", " Twinkle ", " Little ", " Star ").distinct();
        longestFirst.forEach(System.out::println);
        uniqueWords.forEach(System.out::println);

        Stream<String> smallWords2 = words.stream().map(String::toLowerCase);
        smallWords2.distinct().sorted(Comparator.comparing(String::length).reversed()).limit(5).forEach(System.out::println);

        Object[] powersof2 = Stream.iterate(1.0, p -> p * 2).peek(e -> System.out.println(" Reading of " + e)).limit(20).toArray();

        Stream<String> smallWords3 = words.stream().map(String::toLowerCase);


        Optional<String> longest = smallWords3.filter(s -> s.length() > 10).max((s, t) -> s.length() - t.length());
        System.out.println(" longest : " + longest.orElse(""));

        Stream<String> smallWords4 = words.stream().map(String::toLowerCase);

        Stream<String> smallWords5 = words.stream().map(String::toLowerCase);
        Stream<String> smallWords6 = words.stream().map(String::toLowerCase);

        boolean a = smallWords4.anyMatch(s -> s.startsWith("x"));
        System.out.println(a);

        Optional<String> word = smallWords5.filter(s -> s.length() == 11).findFirst();

        System.out.println(word.orElse(""));
        Optional<String> word2 = smallWords6.filter(s -> s.endsWith(" ing ")).findAny();
        System.out.println(word2.orElse(""));

        Stream<String> smallWords7 = words.stream().map(String::toLowerCase);

        String[] result5 = smallWords7.toArray(String[]::new);
        for (String i : result5) {
            System.out.println(i);
        }
        Stream<String> smallWords8 = words.stream().map(String::toLowerCase);
        Stream<String> smallWords9 = words.stream().map(String::toLowerCase);


        List<String> list = smallWords8.collect(Collectors.toList());
        Set<String> set = smallWords9.collect(Collectors.toSet());

        Stream<String> smallWords10 = words.stream().map(String::toLowerCase);
        Stream<String> smallWords11 = words.stream().map(String::toLowerCase);

        String text = smallWords10.collect(Collectors.joining());
        System.out.println("bez sep ");
        System.out.println(text);

        String text1 = smallWords11.collect(Collectors.joining(" -  "));
        System.out.println("z sep ");
        System.out.println(text1);

        //dataList - strumien danych pomiarowych
        //dataList.filter(d -> d > 100).map(Object::toString).collect(Collectors.joining(","));

        /*DoubleSummaryStatistics summary = dataList.collect(Collectors.summarizingDouble(d - > d));
        System.out.println(" Measurements - min : "
                + summary.getMin() + " avg : "
                + summary.getAverage() + " max: "
                + summary.getMax());
*/
        /*Map<Integer, String> indexToSecondName = students.collect(Collectors.toMap(Student::getIndex, Student::getSecondName));

        Map<Integer, Student> indexToStudent = students.collect(Collectors.toMap(Student::getIndex, Function.identity()));*/

        /*Map < String , List < Student > > groupOfStudent = names . stream () . collect (Collectors . groupingBy ( Student :: getCourse ));
        groupOfStudent . get (" Java ");*/


/*        ArrayList<Dean> deans = new ArrayList<Dean>();
        2 deans.add(new Dean(" Krzysztof Kaczmarski ", TUESDAY));
        3
        4 ...
        5
        6 System.out.println(deans.stream().collect(groupingBy(l -> l.getOfficeDay(), () -> new EnumMap<>(WorkingDay.class), toSet() )));*/

        /*Map<Boolean, List<Student>> groupOfStudent = names.stream().collect(Collectors.partitioningBy(s -> s.getCourse().equals(" Java ")));
        System.out.println(groupOfStudent.get(false));*/

/*        Map<String, Long> countOfStudents;
        countOfStudents = names.stream().collect(Collectors.groupingBy(Student::getCourse, counting()));
        System.out.println(countOfStudents.entrySet());*/

/*        Map<String, Integer> sumOfPoints = names.stream().collect(Collectors.groupingBy(Student::getCourse, Collectors.summingInt(Student::getPoints)));
        System.out.println(sumOfPoints.entrySet());

        Map<String, Optional<Student>> maxOfPoints = names.stream().collect(Collectors.groupingBy(Student::getCourse, Collectors.maxBy(Comparator.comparing(Student::getPoints))));*/

        /*OptionalInt sum = names.stream().mapToInt(Student::getPoints).reduce((x, y) -> x + y);
        System.out.println(sum.getAsInt());*/



    }

    public static Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.substring(i, i + 1));
        }
        return result.stream();
    }
}
