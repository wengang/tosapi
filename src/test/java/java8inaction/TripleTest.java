package java8inaction;

import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TripleTest {
    @Test public void createTripleTest(){
        Stream<Triple> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                                      .mapToObj(b ->
                                                new Triple(a, b, Math.sqrt(a * a + b * b)))
                                .filter(Triple::isValid)
                        );
        pythagoreanTriples.limit(5)
                .forEach(System.out::println);
    }
    @Test
    public void testIterateStream(){
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }
    @Test public void creatFibNumbers(){
        Stream.iterate(new IntPair(0,1),pre -> {
           return new IntPair(pre.getTwo(),pre.sum());
        }).limit(10)
                .map(IntPair::getOne)
        .forEach(System.out::println);
    }
    @Test public void generate(){
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}