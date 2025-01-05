package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {

    private static final String TEST_DATA_PATH = "src/test/resources/TestData/";


    //TODO: reading data from jason file

    public static String getJsonData(String fileName, String field) throws FileNotFoundException {
        try {
            // Define object from fileReader
            FileReader fileReader = new FileReader(TEST_DATA_PATH + fileName + ".json");

            //parse the json file directly into json webElement
            JsonElement jsonElement = JsonParser.parseReader(fileReader);
            return jsonElement.getAsJsonObject().get(field).getAsString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
    // TODO: reading data from properties file

    public static final String getEnvironmentValue(String fileName, String key) throws IOException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(TEST_DATA_PATH + fileName + ".properties"));
            return properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
