package assignment7;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MyEavesDropService {
	List<String> getMeetings();
	Map<String, List<String>> getYears(List<String> meetings);
	int getLog(String team, String year);
}
