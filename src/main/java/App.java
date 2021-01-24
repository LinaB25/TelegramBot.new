import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class App {

        public static void main(String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new Bot());
        }catch (TelegramApiException e){
            e.printStackTrace();
        }

    }
}
