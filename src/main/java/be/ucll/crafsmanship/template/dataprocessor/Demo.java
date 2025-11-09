package be.ucll.crafsmanship.template.dataprocessor;

public class Demo {
    public static void main(String[] args) {
        String inputFileCSV = "CSVInput.csv";
        String outputFileCSV = "CSVOutput.csv";
        String inputFileJSON = "JSONInput.json";
        String outputFileJSON = "JSONOutput.json";
        String inputFileXML = "XMLInput.xml";
        String outputFileXML = "XMLOutput.xml";

        DataProcessor csvProcessor = new CSVProcessor(inputFileCSV, outputFileCSV);
        DataProcessor jsonProcessor = new JSONProcessor(inputFileJSON, outputFileJSON);
        DataProcessor xmlProcessor = new XMLProcessor(inputFileXML, outputFileXML);

        System.out.println("\nProcessing CSV Data...");
        csvProcessor.process();

        System.out.println("\nProcessing JSON Data...");
        jsonProcessor.process();

        System.out.println("\nProcessing XML Data...");
        xmlProcessor.process();
    }
}
