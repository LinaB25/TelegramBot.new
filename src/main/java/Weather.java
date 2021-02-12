import entities.WeatherEntity;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //0fe21cbbb52b3ba292ed1f283b41b940
    public static String getWeather(String message, WeatherEntity weatherEntity) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=0fe21cbbb52b3ba292ed1f283b41b940");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
        result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        weatherEntity.setName(object.getString("name"));
        JSONObject main = object.getJSONObject("main");
        weatherEntity.setTemp(main.getDouble("temp"));
        JSONObject wind = object.getJSONObject("wind");
        weatherEntity.setWind(wind.getDouble("speed"));

        return "Город: " + weatherEntity.getName() + "\n" +
                "Температура " + weatherEntity.getTemp() + " C" + "\n" +
                "Скорость ветра " + weatherEntity.getWind() + " м/с";
    }
}
