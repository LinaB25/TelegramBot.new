import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class News {

    public static Map<String,String> readRSSFeed() throws IOException, FeedException {
        String feed = "https://lenta.ru/rss/top7";

        URL feedUrl = new URL(feed);

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed sf = input.build(new XmlReader(feedUrl));

        List entries = sf.getEntries();
        Iterator it = entries.iterator();
        Map<String,String> hashMap= new HashMap<>();
        while (it.hasNext()) {
            SyndEntry entry = (SyndEntry)it.next();
            hashMap.put(entry.getTitle(), entry.getLink());
        }
        return hashMap;
    }
}



