package org.podpalny.services.impl;

import org.podpalny.data.DataProvider;
import org.podpalny.entities.WordEntry;
import org.podpalny.services.WordsService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordsServiceImpl implements WordsService {
    DataProvider dataProvider;

    public WordsServiceImpl(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public List<WordEntry> getWords(int count) {
        List<String> titles = dataProvider.getTitles();

        Map<String, Long> words = titles.stream()
                .flatMap(string -> Stream.of(string.split(" ")))
                .map(String::toLowerCase)
                .filter(s -> s.length() > 2)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return words.keySet().stream()
                .sorted(Comparator.comparing(words::get, Comparator.reverseOrder()))
                .limit(count)
                .map(s -> new WordEntry(s, words.get(s)))
                .collect(Collectors.toList());
    }
}
