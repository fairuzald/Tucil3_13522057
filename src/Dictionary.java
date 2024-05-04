import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Dictionary {
    private static Map<Integer, List<String>> dictionary;

    static {
        dictionary = new HashMap<>();
        loadDictionary("words2.json");
    }

    private static void loadDictionary(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);

            for (String key : jsonObject.keySet()) {
                JSONArray jsonArray = jsonObject.getJSONArray(key);
                List<String> values = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    values.add(jsonArray.getString(i));
                }
                dictionary.put(Integer.parseInt(key), values);
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
    }

    public static boolean isValidWord(String word) {
        return dictionary.get(word.length()).contains(word);
    }

    public static List<String> getDictionary(String key){
        int intKey = Integer.parseInt(key);
        return dictionary.get(intKey);
    }
}
