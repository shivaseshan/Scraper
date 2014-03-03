import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		scraperOuter();
	}

	public static void scraperOuter()
	{
		String snopes = "http://www.snopes.com/humor/";
		try {
			Document doc = Jsoup.connect(snopes + "humor.asp").get();
			//Element table = doc.select("table[align=center]").first();
			Elements tables = doc.getElementsByTag("TABLE");
			Element table = tables.get(2);

			Elements links = table.getElementsByTag("a");

			for (Element link : links) {
				String linkHref = link.attr("href");
				scraperInner(snopes+linkHref);
				//System.out.println("\n---END----");
				break;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void scraperInner(String url)
	{
		String urlInner =  url.substring(0, url.lastIndexOf("/")+1);
		try {
			Document doc = Jsoup.connect(url).get();
			Elements tables = doc.getElementsByTag("TABLE");
			Element table = tables.get(3);
			Elements links = table.getElementsByTag("a");

			for (Element link : links) {
				String linkHref = link.attr("href");
				if (!linkHref.equals(""))
				{
					DataScraper(urlInner+linkHref);
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void  DataScraper(String url)
	{
		try {
			Document doc = Jsoup.connect(url).get();
			Element content = doc.getElementById("main-content");
			Elements fonts = content.getElementsByTag("FONT");
			System.out.println(fonts);
			for (Element font : fonts)
			{
				Element attr = font.attr("face", "Arial");
				//System.out.println(attr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
