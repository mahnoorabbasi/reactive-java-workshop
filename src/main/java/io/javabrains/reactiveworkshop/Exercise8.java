package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.io.IOException;

public class Exercise8 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens
        ReactiveSources.intNumbersFluxWithException()
                .subscribe(x -> System.out.println(x),
                        err -> System.out.println("error")); //error no bubble up

        ReactiveSources.intNumbersFluxWithException()
                .doOnError(err -> System.out.println(err.getMessage()))
                .subscribe(x -> System.out.println(x));    //error will bubble up

        // Print values from intNumbersFluxWithException and continue on errors
        ReactiveSources.intNumbersFluxWithException()
                .onErrorContinue((err, item) -> System.out.println(err.getMessage()))
                .subscribe(x -> System.out.println(x));
        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        ReactiveSources.intNumbersFluxWithException()
                .onErrorResume((err -> Flux.just(-1, -2)))
                .doFinally(signal -> {
                    if (signal == SignalType.ON_COMPLETE)
                        System.out.println("fin");
                    else System.out.println("error?");
                })
                .subscribe(x -> System.out.println(x));

        System.out.println("Press a key to end");
        System.in.read();
    }

}
