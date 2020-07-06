package org.podpalny.services;

import org.podpalny.entities.WordEntry;

import java.util.List;

public interface WordsService {
    List<WordEntry> getWords(int count);
}
