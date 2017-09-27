package java8inaction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class MenuManager {
    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );
    public List<String> getThreeHighCaloricDishNames(){
        return menu.stream().filter(d -> {
            System.out.println("filtering" + d.getName());
            return d.getCalories()>300;  })
                .map(d -> {
                    System.out.println("mapping" + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
    }
    public List<Dish> getVegetarianDishes() {
        return menu.stream().filter(Dish::isVegetarian)
                .collect(Collectors.toList());
    }
    public int sumCalories(){
        return menu.stream().mapToInt(Dish::getCalories)
                .sum();
    }
    public long countMenu() {
        return menu.stream().collect(Collectors.counting());
    }
    public Optional<Dish> getMaxCalorieDish() {
        Optional<Dish> mostCalorieDish =
                menu.stream()
                        .collect(maxBy(comparingInt(Dish::getCalories)));
        return mostCalorieDish;
    }
    public int getSumCalories(){
        return menu.stream().collect(summingInt(Dish::getCalories));
    }
    public double getAvgCalories() {
        return menu.stream().collect(averagingInt(Dish::getCalories));

    }
    public IntSummaryStatistics getStatistics() {
        IntSummaryStatistics menuStatistics =
                menu.stream().collect(summarizingInt(Dish::getCalories));
        return menuStatistics;
    }
    public int getMaxCaloriesByReduce() {
        return menu.stream().collect(reducing(0,Dish::getCalories,(i,j) -> i > j ? i : j));
    }
    public void multiLevelGroup(){
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                } )
                        )
                );
    }

        public enum CaloricLevel { DIET, NORMAL, FAT }
}
