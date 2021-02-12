import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;

import java.util.List;

import static io.restassured.RestAssured.given;

public class News {

    public static String readRSSFeed() {
        String s = given()
                .accept(ContentType.XML)
                .when()
                .get("https://lenta.ru/rss/top7")
                .thenReturn()
                .asString();
        XmlPath xmlPath = new XmlPath(s);
        List<String> list = xmlPath.getList("rss.channel.item.title");
        System.out.println(list.size());
        /*for (String str : list) {
            System.out.println();;
        }*/
        return s;
    }



  /*public static String readRSSFeed() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
      String title = null;
      try {
          DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
          domFactory.setNamespaceAware(true);
          DocumentBuilder builder = domFactory.newDocumentBuilder();
          URL url = new URL("https://lenta.ru/rss/top7");
          InputStream inputStream = url.openStream();
          Reader reader = new InputStreamReader(inputStream, "UTF-8");
          InputSource inputSource = new InputSource(reader);
          Document doc = builder.parse(inputSource);
          XPath xpath = XPathFactory.newInstance().newXPath();
          XPathExpression expr = xpath.compile("//rss/channel/item/title/text()");
          NodeList nodes = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
         for (int i = 0; i < nodes.getLength(); i++)
          {
              title = nodes.item(i).getNodeValue();
          }
      }
      catch (Exception exception) {
          exception.printStackTrace();
      }

      return title;
  }*/
        /*public static String readRSSFeed(String urlAddress){ //работающий код
        try{
            URL rssUrl = new URL (urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String sourceCode = "";
            String line;
            while ((line = in.readLine()) != null) {
                int titleEndIndex = 0;
                int titleStartIndex = 0;
                while (titleStartIndex >= 0) {
                    titleStartIndex = line.indexOf("<title>", titleEndIndex);
                    if (titleStartIndex >= 0) {
                        titleEndIndex = line.indexOf("</title>", titleStartIndex);
                        sourceCode += line.substring(titleStartIndex + "<title>".length(), titleEndIndex) + "\n";
                    }
                }
            }
            in.close();
            return sourceCode;
        } catch (MalformedURLException ue){
            System.out.println("Malformed URL");
        } catch (IOException ioe){
            System.out.println("Something went wrong reading the contents");
        }
        return null;
    }*/
}