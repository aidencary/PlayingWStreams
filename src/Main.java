import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        /* Stream Intermediate Operations (works on the data):
        * They are "lazy" because they do no working until a terminal operation is called
        * .filter() is a Predicate<T> that returns a boolean. It keeps only the elements that match the condition in the ()
        * .map() is a Function<T, R> that transforms each element such as turning each String into uppercase using String::toUpperCase
        * .flatMap() is a Function<T, Stream<R>> (returns a Stream) which flattens nested streams. Used when working with lists of lists
        * .limit() is a long n (n being the number of elements) that truncates (limits) the stream to the first n elements
        * .skip() is a long n (n being the number of elements) that skips the first n elements of whatever is being put through the stream
        * .peek() is a Consumer<T> that performs an action (used for debugging) without modifying the stream
        */

        /* Stream Terminal Operations (trigger the computation and close the stream):
        * .count() returns the number of elements of whatever is put through the stream
        * min is a Comparator<T> that finds the minimum element
        * max is a Comparator<T> that finds the maximum element
        * findFirst() returns the first element (wrapped in Optional)
        * findAny() returns any element (used in parallel streams)
        * anyMatch is a Predicate<T> that returns true if any element matches
        * allMatch is a Predicate<T> that returns true if all elements match
        * noneMatch is a Predicate<T> that returns true if no elements match
        * forEach is a Consumer<T> that processes each element. Could be used to print each element: stream.forEach(System.out::println)
        * collect() is a Collector<T, A, R> that gathers the results into a collection or structure
        */

        /* Using collect with examples:
        * To List: .collect(Collectors.toList())
        * To set: .collect(Collectors.toSet())
        * To Map: .collect(Collectors.toMap(k -> ..., v -> ...))
        * Grouping: collect(Collectors.groupingBy(...))
        * To Array (does not use collect): String[] array = stream.toArray(String::new)
        */

        // Example 1:
        // Write a Stream pipeline that removes all words with more than 7 letters, removes all words that contain the letter 'e',
        // changes the remaining words to the first four characters in the word, and then groups them according to their last character.
        // The output should be a Map<Character, List<String>>

        // Could have just used a List of Strings
        List<Words> wordsList = new ArrayList<>();
        wordsList.add(new Words("Hello"));
        wordsList.add(new Words("World"));
        wordsList.add(new Words("Carolina"));
        wordsList.add(new Words("Aiden"));
        wordsList.add(new Words("Bobbery"));
        wordsList.add(new Words("Rhett and Link"));
        wordsList.add(new Words("Pikachu"));
        wordsList.add(new Words("Fart"));
        wordsList.add(new Words("Joshua Fielder"));
        wordsList.add(new Words("John Doe"));
        wordsList.add(new Words("UCABears"));
/*
        Map<Character, List<String>> grouped = wordsList.stream()
                .map(Words::getWord)                             // Step 1: extract String
                .filter(word -> word.length() <= 7)              // Step 2: remove words > 7 letters
                .filter(word -> !word.toLowerCase().contains("e")) // Step 3: remove words with 'e'
                .map(word -> word.length() >= 4 ? word.substring(0, 4) : word) // Step 4: keep first 4 chars
                .collect(Collectors.groupingBy(
                        word -> word.charAt(word.length() - 1)  // Step 5: group by last char
                ));

 */

        List<String> wordsList2 = new ArrayList<>();
        wordsList2.add("Hello");
        wordsList2.add("World");
        wordsList2.add("Carolina");
        wordsList2.add("Aiden");
        wordsList2.add("Bobbery");
        wordsList2.add("Rhett and Link");
        wordsList2.add("Pikachu");
        wordsList2.add("Fart");
        wordsList2.add("Joshua Fielder");
        wordsList2.add("John Doe");
        wordsList2.add("UCABears");

        // Example 1.2:
        // Write a Stream pipeline that removes all words with more than 7 letters, removes all words that contain the letter 'e',
        // changes the remaining words to the first four characters in the word, and then groups them according to their last character.
        // The output should be a Map<Character, List<String>>

        Map<Character, List<String>> shortWords = wordsList2.stream()
                .filter(w -> w.length() < 7)
                .filter(w -> !w.contains("e"))
                .map(w -> w.length() >= 4 ? w.substring(0, 4) : w)
                .collect(Collectors.groupingBy(w -> w.charAt(w.length() - 1)));




        // Uncomment this to see the output
        // System.out.println(grouped);

        // Example 2:
        // Question: Given a list of integers, write a Stream pipeline that filters out all even numbers,
        // squares the remaining numbers, and then collects them into a list.
        // Expected Output: List

        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        ints.add(6);
        ints.add(7);
        ints.add(8);
        ints.add(9);
        ints.add(10);


        List<Integer> newInts = ints.stream()
                .filter(n -> n % 2 != 0) // Checks if n is even or equal to zero. If either, filter out.
                .map(n -> n * n) // Squares each remaining number
                .collect(Collectors.toList()); // Collects into a list


        // Uncomment to see result
        //System.out.println(newInts);

        // Example 3:
        // Given a list of strings, write a Stream pipeline that removes all strings with less than 5 characters,
        // converts the remaining strings to uppercase, and then collects them into a set.
        // Expected Output: Set
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Bob");
        names.add("Rhett");
        names.add("Pikachu");
        names.add("Aiden");
        names.add("John");


        Set<String> newNames = names.stream()
                .filter(n -> n.length() < 5)
                .map(n -> n.toUpperCase())
                .collect(Collectors.toSet());

        // Uncomment to see result
        //System.out.println(newNames);

        // Example 3:
        // Given a list of employees (with fields: name, age, salary),
        // write a Stream pipeline that filters out employees younger than 30, maps the remaining employees to their names,
        // and then collects the names into a list.
        // Expected Output: List
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Carolina", 19, 13.33));
        employees.add(new Employee("Carol", 31, 13.34));
        employees.add(new Employee("Caroline", 30, 13.35));
        employees.add(new Employee("Aiden", 2, 13.36));

        List<String> oldEmployees = employees.stream()
                .filter(e -> e.getAge() >= 30)
                .map(Employee::getName) // Collects the names (String) from the Employees
                .collect(Collectors.toList());

        // Uncomment to see result
        // System.out.println(oldEmployees);

        // Example 4:
        // Given a list of products (with fields: name, price), write a Stream pipeline that filters out products with a price greater than 100,
        // maps the remaining products to their names, and then collects them into a list.
        // Expected Output: List

        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products("Soap", 1.25));
        productsList.add(new Products("Fart", 200.25));
        productsList.add(new Products("Pikachu", 100.25));
        productsList.add(new Products("Cookie", 4.45));

        List<String> cheapProducts = productsList.stream() // Remember that it wants their names so it should be a list of Strings
                .filter(p -> p.getProductPrice() < 100) // Filter out products that cost more than 100 dollars
                .map(Products::getProductName)
                .collect(Collectors.toList());

        // Uncomment to see results
        // System.out.println(cheapProducts);

        // Example 5:
        // Given a list of integers, write a Stream pipeline that skips the first 5 elements, limits the stream to the next 10 elements,
        // and then finds the maximum value.
        // Expected Output: Optional

        List<Integer> lottaInts = new ArrayList<>();
        lottaInts.add(200);
        lottaInts.add(2);
        lottaInts.add(3);
        lottaInts.add(4);
        lottaInts.add(5);
        lottaInts.add(6);
        lottaInts.add(7);
        lottaInts.add(8);
        lottaInts.add(9);
        lottaInts.add(10);
        lottaInts.add(11);
        lottaInts.add(12);
        lottaInts.add(13);
        lottaInts.add(14);
        lottaInts.add(15);


        Optional<Integer> max = lottaInts.stream()
                .skip(5)
                .limit(10)
                .max(Integer::compareTo); // max is a terminal operation




        // Uncomment to see result
        // System.out.println(max);

        // Example 6:
        // Given a list of strings, write a Stream pipeline that filters out all strings that do not start with the letter 'A',
        // maps the remaining strings to their lengths, and then finds the average length.
        // Expected Output: OptionalDouble

        List<String> coolNames = new ArrayList<>();
        coolNames.add("Pikachu");
        coolNames.add("Aiden");
        coolNames.add("John");
        coolNames.add("Bob");
        coolNames.add("Rhett");
        coolNames.add("Adrian");

        OptionalDouble averageLength = coolNames.stream()
                .filter(n -> !n.startsWith("A"))
                .mapToInt(String::length)
                .average();

        // Uncomment to see results
        // System.out.println(averageLength);

        // Example 7:
        // Given a list of transactions (with fields: id, amount, type),
        // write a Stream pipeline that filters out transactions of type "DEBIT", maps the remaining transactions to their amounts,
        // and then calculates the total sum.
        // Expected Output: Double

        List<Transactions> transes = new ArrayList<>();
        transes.add(new Transactions("1", 20.23, "Debit"));
        transes.add(new Transactions("2", 21.83, "Credit"));
        transes.add(new Transactions("3", 30.23, "Debit"));
        transes.add(new Transactions("4", 71.83, "Credit"));

        Double sum = transes.stream()
                .filter(t -> !t.getType().equalsIgnoreCase("Debit"))
                .mapToDouble(Transactions::getAmount) // When maping to a double or int, use mapToInt or mapToDouble to get that value.
                .sum();

        // Uncomment to see result
        // System.out.println(sum);

        // Example 8:
        // Given a list of words, write a Stream pipeline that removes all words containing the letter 'a',
        // converts the remaining words to lowercase,
        // and then collects them into a map where the key is the word length and the value is a list of words of that length.
        // Expected Output: Map<Integer, List>

        Map<Integer, List<String>> nameMap = coolNames.stream()
                .filter(n -> !n.toLowerCase().contains("a")) // !n.toLowerCase().contains("a") returns false is a String DOES contain "a".
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(String::length));

        //Uncomment to see result
        //System.out.println(nameMap);

        // Example 9:
        // Given a list of books (with fields: title, author, price), write a Stream pipeline that filters out books with a price less than 20,
        // maps the remaining books to their titles, and then groups them by the first letter of the title.
        // Expected Output: Map<Character, List>

        List<Book> books = new ArrayList<>();
        books.add(new Book("Epic Book", "Aiden", 19.99));
        books.add(new Book("Cool Book", "John", 49.99));
        books.add(new Book("Awesome Book", "Bob", 1.99));
        books.add(new Book("Sick Book", "Rhett", 99.99));

        Map<Character, List<String>> expensiveBooks = books.stream()
                .filter(b -> b.getPrice() >= 20)
                .map(Book::getTitle)
                .collect(Collectors.groupingBy(title -> title.charAt(0)));

        System.out.println(expensiveBooks);















    }
}
