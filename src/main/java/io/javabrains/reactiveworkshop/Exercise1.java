package io.javabrains.reactiveworkshop;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        // TODO: Write code here

        StreamSources.intNumbersStream().forEach(
                element -> System.out.println(element)
        );
        System.out.println("============");
        // Print numbers from intNumbersStream that are less than 5
        // TODO: Write code here
        StreamSources.intNumbersStream()
                .filter(x -> x < 5)
                .forEach(
                        element -> System.out.println(element)
                );
        System.out.println("============");

        // Print the second and third numbers in intNumbersStream that's greater than 5
        // TODO: Write code here
        StreamSources.intNumbersStream()
                .filter(x -> x < 5)
                .skip(1)
                .limit(2)
                .forEach(
                        element -> System.out.println(element)
                );

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        // TODO: Write code here
        StreamSources.intNumbersStream()
                .filter(x -> x > 5)
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println(-1));

        Integer value = StreamSources.intNumbersStream()
                .filter(x -> x > 5)
                .findFirst()
                .orElse(-1);
        System.out.println(value);


        // Print first names of all users in userStream
        // TODO: Write code here
        StreamSources.userStream()
                .forEach(user -> System.out.println(user.getFirstName()));
        StreamSources.userStream()
                .map(user -> user.getFirstName())
                .forEach(userName -> System.out.println(userName));
        // Print first names in userStream for users that have IDs from number stream

        StreamSources.userStream()
                .filter(user -> StreamSources.intNumbersStream().anyMatch(n -> n == user.getId()))
                .forEach(userName -> System.out.println(userName));

        StreamSources.intNumbersStream()
                .flatMap(id -> StreamSources.userStream().filter(user -> user.getId() == id))
                .map(user -> user.getFirstName())
                .forEach(userName -> System.out.println(userName));

    }

}
