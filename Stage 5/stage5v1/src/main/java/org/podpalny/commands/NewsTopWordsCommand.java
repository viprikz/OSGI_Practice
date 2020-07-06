package org.podpalny.commands;

import org.podpalny.data.DataProvider;
import org.podpalny.entities.WordEntry;
import org.podpalny.services.WordsService;
import org.podpalny.services.impl.WordsServiceImpl;
import org.apache.felix.service.command.CommandProcessor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component(
        service = NewsTopWordsCommand.class,
        immediate = true,
        property = {
                CommandProcessor.COMMAND_SCOPE + ":String=news",
                CommandProcessor.COMMAND_FUNCTION + ":String=stats"
        }
)
public class NewsTopWordsCommand {
    private static final int TOP_WORD_COUNT = 10;
    private DataProvider dataProvider;
    private final Map<String, DataProvider> providers = new HashMap<String, DataProvider>();

    @Reference(
            service = DataProvider.class,
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            bind = "binder",
            unbind = "unbinder"
    )
    protected void binder(DataProvider dataProvider) {
        System.out.println("Bound: " + dataProvider.getName());
        providers.put(dataProvider.getName(), dataProvider);
    }

    public void unbinder(DataProvider dataProvider) {
        System.out.println("Unbound: " + dataProvider.getName());
        providers.remove(dataProvider.getName());
    }

    public void stats() {
        System.out.println("Choose source: ");
        System.out.println(String.join(", ", providers.keySet()));
    }

    public void stats(String source) {
        if (providers.containsKey(source)) {
            DataProvider provider = providers.get(source);
            WordsService topWordsService = new WordsServiceImpl(provider);
            List<WordEntry> topWords = topWordsService.getWords(TOP_WORD_COUNT);

            for (WordEntry entry : topWords) {
                System.out.println(entry.getWord() + ": " + entry.getOccurrenceCount());
            }
        } else {
            System.out.println("No such provider");
        }
    }
}

