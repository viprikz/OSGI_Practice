package org.podpalny.data.impl.lenta;

import org.podpalny.data.DataProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.osgi.service.component.annotations.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component(
        service = DataProvider.class,
        immediate = true
)
public class LentaDataProvider implements DataProvider {
    private static final String url = "https://api.lenta.ru/lists/latest";

    @Override
    public List<String> getTitles() {
        ArrayList<String> titles = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new URL(url).openConnection().getInputStream(),
                            StandardCharsets.UTF_8
                    )
            );

            JSONTokener tokener = new JSONTokener(reader);
            JSONArray headlines = new JSONObject(tokener).getJSONArray("headlines");

            for (Object entry : headlines) {
                JSONObject jsonObject = (JSONObject) entry;

                if (jsonObject.get("type").equals("news")) {
                    titles.add((String) jsonObject.getJSONObject("info").get("title"));
                }
            }
        } catch (IOException e) {
            System.out.println("lenta error");
            e.printStackTrace();
        }

        return titles;
    }

    @Override
    public String getName() {
        return "lenta";
    }
}
