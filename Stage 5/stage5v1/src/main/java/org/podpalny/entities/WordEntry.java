package org.podpalny.entities;

public class WordEntry {
    private final String word;
    private final long occurrenceCount;

    public WordEntry(String word, long occurrenceCount) {
        this.word = word;
        this.occurrenceCount = occurrenceCount;
    }

    public String getWord() {
        return word;
    }

    public long getOccurrenceCount() {
        return occurrenceCount;
    }
}
