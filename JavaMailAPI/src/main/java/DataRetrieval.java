import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class DataRetrieval {
    private static final String path = "src\\main\\resources\\data.json";;

    public static String getJsonObject(String title) {
        try {
            return ((JSONObject) (new JSONParser()).parse(new FileReader(path))).get(title).toString();

        } catch (IOException | ParseException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return null;
        }
    }
}
