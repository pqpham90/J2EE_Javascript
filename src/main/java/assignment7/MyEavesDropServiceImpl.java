package assignment7;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyEavesDropServiceImpl implements MyEavesDropService {

	public List<String> getMeetings() {
		String url = "http://eavesdrop.openstack.org/meetings/";

		List<String> meetings = new ArrayList<String>();

		try {
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.select("body a");

			ListIterator<Element> iter = links.listIterator();
			while(iter.hasNext()) {
				Element e = iter.next();
				String s = e.html();

				String check = s.substring(s.length() - 1);

				if (check.equals("/")) {
					meetings.add(s);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return  meetings;
	}

	/*
 * Return a map where the contents of map is a single entry:
 * <this.url, List-of-parsed-entries>
 */
	public Map<String, List<String>> getYears(List<String> meetings) {
		String url = "http://eavesdrop.openstack.org/meetings/";

		Map<String, List<String>> data = new LinkedHashMap<String, List<String>>();
		List<String> years;

		for (String meeting : meetings) {
			years = new ArrayList<String>();
			try {
				Document doc = Jsoup.connect(url + meeting).get();
				Elements links = doc.select("body a");

				ListIterator<Element> iter = links.listIterator();
				while(iter.hasNext()) {
					Element e = iter.next();
					String s = e.html();

					Pattern p = Pattern.compile("^\\d{4}/?");
					Matcher m = p.matcher(s);

					if(m.matches()) {
						years.add(s);
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}

			data.put(meeting, years);
		}


		return data;
	}

	// obtains the appropriate log file
	public int getLog(String team, String year) {
		String url = "http://eavesdrop.openstack.org/meetings/" + team + year;
		int count = 0;
		try {
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.select("body a");

			String match = "";

			ListIterator<Element> iter = links.listIterator();
			while(iter.hasNext()) {
				Element e = iter.next();
				String original = e.html();


				// making sure we're dealing with a log link
				team = team.replace("/", "");
				if(original.contains(team)) {
					String s = original.replace(team+".", "");

					String log[] = s.split("\\.");

					// adds when is on same meeting and index where necessary
					if((log[0] + "." + log[1]).equals(match)) {
					}
					else {
						if (original.contains("html") && !original.contains("log.html")) {
							count++;
						} else if (original.contains("log.html")) {
							count++;
						} else if (original.contains("log.txt")) {
							count++;
						} else if (original.contains("txt") && !original.contains("log.txt")) {
							count++;
						}

						match = log[0] + "." + log[1];
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}
}
