package be.ucll.crafsmanship.template.dataprocessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVProcessor extends DataProcessor {
    private List<String> data;

    public CSVProcessor(String inputFile, String outputFile) {
        super(inputFile, outputFile);
        this.data = new ArrayList<>();
    }

    protected void load() {
        System.out.println("Loading CSV...");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading CSV: " + e.getMessage());
        }
    }

    protected void transform() {
        System.out.println("Transforming CSV...");

        data.replaceAll(String::toUpperCase);
    }

    protected void save() {
        System.out.println("Saving CSV...");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving CSV: " + e.getMessage());
        }
    }

    protected boolean validateData() {
        System.out.println("Validating CSV...");

        if (data == null || data.isEmpty()) {
            System.out.println("CSV is empty or not loaded correctly.");
            return false;
        }
        return true;
    }
}
