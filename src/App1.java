import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class App1 {
    /*
        Przygotujemy klasę Person, której obiekty przechowamy w kolekcji implementującej interfejs Set.
        Ustalamy, że dwa obiekty klasy Person uznajemy za równe, kiedy mają identyczne wartości
        pól name oraz age.
    */

    static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        /*
            Dodaj metody equals oraz hashCode do klasy Person.
            Możesz je wygenerować korzystając ze środowiska Intellij.
            Wybierz skrót alt + insert i szukaj opcji generowania metod
            equals oraz hashCode
        */

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (age != person.age) return false;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + age;
            return result;
        }

        @Override
        public String toString() {
            return "%s is %d".formatted(name, age);
        }
    }

    /*
        Żeby uniknąć zbędnego generowania kodu możesz zastosować rekord, który ma już "pod spodem"
        wygenerowane metody takie jak equals czy hashCode
    */
    record Worker(String name, BigDecimal salary) {}

    public static void main(String[] args) {
        /*
            Przygotujemy przykładową kolekcję HashSet, którą zainicjalizujemy listą obiektów klasy Person
        */
        var people = new HashSet<>(List.of(
                new Person("A", 10),
                new Person("A", 10),
                new Person("B", 20),
                new Person("B", 20),
                new Person("C", 30),
                new Person("C", 30)
        ));

        /*
            Po wypisaniu elementów kolekcji widzimy, że w kolekcji HashSet znajdują się duplikaty.
            Obecność duplikatów wynika z braku przesłoniętych metod equals oraz hashCode w klasie Person.
            Te metody są wykorzystywane podczas usuwania duplikatów przez kolekcje implementujące interfejs
            Set.

            Kiedy ponownie uruchomisz kod w kolekcji nie ma już duplikatów.
        */
        people.forEach(System.out::println);

        var workers = new HashSet<>(List.of(
                new Worker("A", new BigDecimal("100")),
                new Worker("A", new BigDecimal("100")),
                new Worker("B", new BigDecimal("200")),
                new Worker("B", new BigDecimal("200"))
        ));
        workers.forEach(System.out::println);
    }
}
