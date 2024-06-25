package com.zimji.auth.utils;

import java.time.LocalTime;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CommonUtils {

    public static double round(double number, int decimals) {
        double multiplier = Math.pow(10, decimals);
        return Math.round(number * multiplier) / multiplier;
    }

    public static String convertToCamelCase(String input, char symbol) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = false;

        for (char c : input.toCharArray()) {
            if (c == symbol) {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }

        return result.toString();
    }

    public static String convertToSnakeCase(String input, char symbol) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                if (i > 0) {
                    result.append(symbol);
                }
                result.append(Character.toLowerCase(currentChar));
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    public static boolean areSetEqual(Set<Long> set1, Set<Long> set2) {
        if (set1.size() != set2.size()) {
            return false;
        }
        return set1.equals(set2);
    }

    public static <T, R> Set<R> extractSetValues(List<T> list, Function<T, R> mapper) {
        return list.stream()
                .map(mapper)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public static <T, R> List<R> extractListValues(List<T> list, Function<T, R> mapper) {
        return list.stream()
                .map(mapper)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static <H, B, Z extends Collection<B>> Z extractDataFlatMap(Collection<H> inputCollection,
                                                                       Function<H, List<B>> extractor,
                                                                       Supplier<Z> collectionFactory) {
        return inputCollection.stream()
                .flatMap(item -> extractor.apply(item).stream())
                .collect(Collectors.toCollection(collectionFactory));
    }

    public static <T> List<T> filterBy(List<T> list, Predicate<T> predicate) {
        return list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static <H, B extends Collection<H>> B filterBy(Collection<H> inputCollection,
                                                          Predicate<H> predicate,
                                                          Supplier<B> collectionFactory) {
        return inputCollection.stream()
                .filter(predicate)
                .collect(Collectors.toCollection(collectionFactory));
    }

    public static <T> T findFirst(List<T> list, Predicate<T> predicate) {
        return list.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public static <H, B> Map<B, List<H>> filterGroupingBy(Collection<H> inputCollection,
                                                          Predicate<H> predicate,
                                                          Function<H, B> mapper) {
        return inputCollection
                .stream()
                .filter(predicate)
                .collect(Collectors.groupingBy(mapper));
    }

    public static <T, K> Map<K, List<T>> groupingBy(List<T> list, Function<T, K> mapper) {
        return list.stream().collect(Collectors.groupingBy(mapper));
    }

    public static <T, K, V> Map<K, V> collectToMap(List<T> items,
                                                   Function<T, K> keyMapper,
                                                   Function<T, V> valueMapper,
                                                   Comparator<V> comparator) {
        return items.stream()
                .collect(Collectors.toMap(
                        keyMapper,
                        valueMapper,
                        BinaryOperator.maxBy(comparator)
                ));
    }

    public static <T, U, C extends Collection<U>> C extractCollectionFromField(Collection<T> inputCollection,
                                                                               Predicate<T> filterPredicate,
                                                                               Function<T, U> idExtractor,
                                                                               Supplier<C> collectionFactory) {
        return inputCollection.stream()
                .filter(filterPredicate)
                .map(idExtractor)
                .collect(Collectors.toCollection(collectionFactory));
    }

    public static <T, R extends Collection<T>> R limitAndCollect(Collection<T> inputCollection, Supplier<R> collectionFactory, int limit) {
        return inputCollection.stream()
                .limit(limit)
                .collect(Collectors.toCollection(collectionFactory));
    }

    public static <H> long findCount(Collection<H> collection) {
        return collection.size();
    }

    public static <H> long findCount(Collection<H> collection, Predicate<H> predicate) {
        return collection.stream().filter(predicate).count();
    }

    public static <T extends Number, R> double sumOfList(Collection<T> collection, Function<T, R> mapper) {
        return collection.stream()
                .filter(Objects::nonNull)
                .mapToDouble(item -> Double.parseDouble(mapper.apply(item).toString()))
                .sum();
    }

    public static <H> double sumByDouble(Collection<H> inputCollection,
                                         Predicate<H> filterPredicate,
                                         Function<H, Double> extractor) {
        return inputCollection
                .stream()
                .filter(filterPredicate)
                .mapToDouble(extractor::apply)
                .sum();
    }

    public static <H, B extends Comparable<B>> LocalTime findExtremeLocalTime(
            Collection<H> inputCollection,
            Predicate<H> filterPredicate,
            Function<H, B> mapper,
            BinaryOperator<B> minMaxFunction
    ) {
        return inputCollection.stream()
                .filter(filterPredicate)
                .map(mapper)
                .reduce(minMaxFunction)
                .map(LocalTime.class::cast)
                .orElse(null);
    }

}
