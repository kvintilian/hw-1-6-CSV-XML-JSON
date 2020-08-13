package task3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commons.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MainParseJson {

    public static void main(String[] args) {
        String json = readString("data.json");
        List<Employee> list = jsonToList(json);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> result = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        JSONParser jParser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) jParser.parse(json);
            for (Object o : jsonArray) {
                result.add(gson.fromJson(((JSONObject) o).toJSONString(), Employee.class));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String readString(String fileName) {
        StringBuilder result = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
