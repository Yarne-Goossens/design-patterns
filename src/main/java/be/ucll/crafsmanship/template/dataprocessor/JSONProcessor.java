package be.ucll.crafsmanship.template.dataprocessor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONProcessor extends DataProcessor {
    private List<Map<String, Object>> data;
    private ObjectMapper objectMapper;

    public JSONProcessor(String inputFile, String outputFile) {
        super(inputFile, outputFile);
        this.data = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    protected void load() {
        System.out.println("Loading JSON...");

        try {
            File file = new File(inputFile);
            data = objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("Error loading JSON: " + e.getMessage());
        }
    }

    protected void transform() {
        System.out.println("Transforming JSON...");

        for (Map<String, Object> dataObject : data) {
            String timestamp = LocalDateTime.now().toString();
            dataObject.put("timestamp", timestamp);
        }
    }

    protected void save() {
        System.out.println("Saving JSON...");

        try {
            objectMapper.writeValue(new File(outputFile), data);
        } catch (IOException e) {
            System.out.println("Error saving JSON: " + e.getMessage());
        }
    }

    protected boolean validateData() {
        System.out.println("Validating JSON...");

        if (data == null || data.isEmpty()) {
            System.out.println("JSON is empty or not loaded correctly.");
            return false;
        }
        return true;
    }
}
