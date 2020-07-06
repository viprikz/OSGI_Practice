package org.podpalny.data.impl.yandex;

import org.podpalny.data.DataProvider;
import org.osgi.service.component.annotations.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(
        service = DataProvider.class,
        immediate = true
)
public class YandexDataProvider implements DataProvider {
    private static final String url = "https://news.yandex.ru/computers.rss";

    @Override
    public List<String> getTitles() {
        List<String> titles = new ArrayList<>();
        Pattern titlePattern = Pattern.compile("<title>(.*?)</title>");

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new URL(url).openConnection().getInputStream(),
                            StandardCharsets.UTF_8
                    )
            );

            String line;
            boolean itemsStarted = false;

            while ((line = reader.readLine()) != null) {
                if (!itemsStarted) {
                    if (line.equals("<item>")) {
                        itemsStarted = true;
                    }

                    continue;
                }

                Matcher matcher = titlePattern.matcher(line);
                while (matcher.find()) {
                    String s = matcher.group();
                    titles.add(s.substring(7, s.length() - 8));
                }
            }
        } catch (Exception e) {
            System.out.println("yandex error");
            e.printStackTrace();
        }

        return titles;
    }

    @Override
    public String getName() {
        return "yandex";
    }
}
