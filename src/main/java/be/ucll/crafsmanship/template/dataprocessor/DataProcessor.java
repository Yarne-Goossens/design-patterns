package be.ucll.crafsmanship.template.dataprocessor;

public abstract class DataProcessor {
    protected final String inputFile;
    protected final String outputFile;

    public DataProcessor(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public final void process() {
        load();
        if (validateData()) {
            transform();
            save();
        } else {
            System.err.println("Data validation failed. Processing aborted.");
        }
    }

    abstract void save();

    abstract void transform();

    abstract void load();

    boolean validateData() {
        return true;
    }
}
