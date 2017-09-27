package java8inaction;


import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MenuManagerTest {
    @Test
    public void getThreeHighCaloricDishNames() throws Exception {
        MenuManager mm = new MenuManager();
        List<String> menus = mm.getThreeHighCaloricDishNames();
        System.out.println(menus);
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
//        s.forEach(System.out::println);
    }
    @Test
    public void getVegetarianDishes() {
        MenuManager mm = new MenuManager();
        List<Dish> menus = mm.getVegetarianDishes();
        System.out.println(menus);
    }
    @Test
    public  void distinctTest(){
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        HashSet<Integer> numberSets = new HashSet<>(numbers);
        numbers.stream()
                .filter(i -> i % 2 == 1)
                .distinct()
                .forEach(System.out::println);
    }
    @Test
    public void haseSetStreamTest() {
        List<Integer> numbers = Arrays.asList(1,4,6,3, 3, 2, 4);
        HashSet<Integer> numberSets = new HashSet<>(numbers);
        numberSets.stream()
                .limit(4)
                .forEach(System.out::println);
    }
    @Test public void flatMapTest(){
        List<String> words = Arrays.asList("Hello","World");
        words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::print);
    }
    @Test public void numberPairsTest(){
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
//        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .filter(j -> (i + j) % 2 == 0)
                                .map(j -> java.lang.String.join(",",Arrays.asList(i,j).toString()))
                        )
                        .forEach(System.out::println);
    }
    @Test public void reduceTest(){
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int sum = numbers.stream().reduce(0,(a,b) -> a + b);
        System.out.println(sum);
        int product = numbers.stream().reduce(1,(a,b) -> a * b);
        System.out.println(product);
        Optional<Integer> result =numbers.stream().reduce(Integer::max);
        result.ifPresent(System.out::println);
    }
    @Test public void rangedStreamTest() {
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());
        evenNumbers = IntStream.range(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());
    }

    @Test
    public void getStatistics() throws Exception {
        MenuManager mm = new MenuManager();
        IntSummaryStatistics menuStatistics = mm.getStatistics();
        System.out.println(menuStatistics);
        double avgCalories= mm.getAvgCalories();
        System.out.println(avgCalories);
        mm.getMaxCalorieDish().ifPresent(dish -> System.out.println(dish));

    }
}