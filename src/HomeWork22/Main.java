package HomeWork22;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        countMinors(persons);
        conscripts(persons);
        ableToWorkPeople(persons);
    }

    public static void countMinors(Collection<Person> persons) {
        Stream<Integer> countMinors = persons.stream()
                .map(Person::getAge)
                .filter(x -> x < 18);
        System.out.println("Количество несовершеннолетних: " + countMinors.count());
    }

    public static void conscripts(Collection<Person> persons) {
        Stream<String> conscripts = persons.stream()
                .filter(p -> p.getAge() < 27 && p.getAge() > 18)
                .map(Person::getFamily);
        System.out.println("Cписок фамилий призывников: " + conscripts.collect(Collectors.toList()));
    }

    public static void ableToWorkPeople(Collection<Person> persons) {
        Stream<Person> ableToWorkPeople = persons.stream()
                .filter((p) -> {
                    if (p.getSex() == Sex.WOMEN) {
                        return p.getAge() < 60 && p.getAge() > 18 && p.getEducation() == Education.HIGHER;
                    } else if (p.getSex() == Sex.MAN) {
                        return p.getAge() < 65 && p.getAge() > 18 && p.getEducation() == Education.HIGHER;
                    }
                    return true;
                })
                .sorted(Comparator.comparing(p -> p.getFamily()));
        System.out.println("Cписок потенциально работоспособных людей: " + ableToWorkPeople.collect(Collectors.toList()));
    }
}