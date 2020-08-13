package task1;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import commons.Employee;
import commons.JsonHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MainCsvToJson {

    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = JsonHelper.listToJson(list);
        JsonHelper.writeString("data.json", json);
    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName));) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader).withMappingStrategy(strategy).
                    build();
            List<Employee> list = csv.parse();
            list.forEach(System.out::println);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}

