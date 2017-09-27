package java8inaction;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class TransactionManagerTest {
    final List<Transaction> transactions = TransactionManager.transactions;

    @Test
    public void find2011YearTransAndSortedByAmount() {
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList())
                .forEach(System.out::println);
    }
    @Test public void findTranderWorkedCities(){
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }
    @Test public void findTranderFromCambridgeAndSorted(){
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .forEach(System.out::println);
    }
    @Test public void findAllTranderNameConnectedAndSorted(){
        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .collect(joining(","));
        System.out.println(traderStr);
    }
    @Test public  void matchTranderWorkedAtMilan(){
        boolean milanBased =
                transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                .getCity()
                                .equals("Milan"));
        System.out.println(milanBased);
    }
    @Test public  void printLiveInCambridgeTraderTransValue(){
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }
    @Test public void printHighestTransValue(){
        Optional<Integer> highestValue =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::max);
        highestValue.ifPresent(System.out::println);
        transactions.stream()
                .max(comparing(Transaction::getValue))
                .ifPresent(System.out::println);
    }

}