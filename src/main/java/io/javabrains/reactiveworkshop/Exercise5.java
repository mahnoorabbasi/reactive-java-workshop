package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberFlux() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumbersFlux()
                .subscribe(x -> System.out.println(x),
                        err -> System.out.println(err.getCause()),
                        () -> System.out.println("Complete"));
        // Subscribe to a flux using an implementation of BaseSubscriber

        ReactiveSources.intNumbersFlux().subscribe(new MySubscsriber<>());


        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscsriber<T> extends BaseSubscriber<T> {

    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("subscription happened");
        request(1);
    }

    public void hookOnNext(T value) {
        System.out.println(value.toString() + " recieved");
        request(1);//need to ask in this model
    }

}