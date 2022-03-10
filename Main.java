package ru.corruptzero;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    /*                      
            Создать поток случайных целых чисел от 0 до 100
            Взять все значения потока, пока не появится 50
            Преобразовать x^2/10-x-100
            Отобрать только положительные
            Преобразовать числа по модулю 42
            Вывести максимальное, минимальное значение, медиану и математическое ожидание
    */
    public static void main(String[] args) {
        int[] arr = new Random().ints(0, 100)
                .takeWhile(n -> n != 50)
                .map(n -> (int) Math.pow(n, 2) / 10 - 100)
                .filter(n -> n > 0)
                .map(n -> n % 42)
                .toArray();

        System.out.println("Min: " + (IntStream.of(arr).min().isPresent() ? IntStream.of(arr).min().getAsInt() : "undefined"));
        System.out.println("Max: " + (IntStream.of(arr).max().isPresent() ? IntStream.of(arr).max().getAsInt() : "undefined"));
        System.out.println("Mean: " + (IntStream.of(arr).average().isPresent() ? IntStream.of(arr).average().getAsDouble() : "undefined"));

        List<Integer> lst = IntStream.of(arr).boxed().toList(); // all values
        HashMap<Integer, Integer> vals = new HashMap<>();       // value:numberOfOccurrences

        IntStream.of(arr).distinct().forEach(n -> vals.put(n, Collections.frequency(lst, n)));
        System.out.println(vals);
        int entrySum = vals.values().stream().reduce(0, Integer::sum);

        double expectedValue = 0;
        for (Map.Entry<Integer, Integer> entry : vals.entrySet()) {
            expectedValue += (double) entry.getKey() * ((double) entry.getValue() / (double) entrySum);
        }
        System.out.println("Expected Value: " + expectedValue);

    }
}
