import entities.CurrencyEntity;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class Currency {
    public static String getCurrency(String message, CurrencyEntity currencyEntity) throws IOException {
        URL url = new URL("https://www.cbr-xml-daily.ru/daily_json.js");
       // CurrencyEntity currencyEntity = new CurrencyEntity();
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        JSONObject main = object.getJSONObject("Valute");
        currencyEntity.setName(main.getJSONObject(message).getString("Name"));
        currencyEntity.setValue(main.getJSONObject(message).getDouble("Value"));

        return "Валюта: " + currencyEntity.getName() + "\n" +
                "Значение: " + currencyEntity.getValue() ;
    }
}
