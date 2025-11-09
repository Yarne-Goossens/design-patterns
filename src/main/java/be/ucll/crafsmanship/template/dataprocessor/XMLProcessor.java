package be.ucll.crafsmanship.template.dataprocessor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class XMLProcessor extends DataProcessor {
    DocumentBuilderFactory builderFactory;
    DocumentBuilder builder;
    Document doc;
    TransformerFactory transformerFactory;
    Transformer transformer;
    DOMSource source;
    FileWriter writer;
    StreamResult result;

    public XMLProcessor(String inputFile, String outputFile) {
        super(inputFile, outputFile);

        try {
            builderFactory = DocumentBuilderFactory.newInstance();
            builder = builderFactory.newDocumentBuilder();
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            System.out.println("Error loading XML: " + e.getMessage());
        }
    }

    protected void load() {
        System.out.println("Loading XML...");

        try {
            doc = builder.parse(new File(inputFile));
            doc.getDocumentElement().normalize();
        } catch (IOException | SAXException e) {
            System.out.println("Error loading XML: " + e.getMessage());
        }
    }

    protected void transform() {
        System.out.println("Transforming XML...");

        Element root = doc.getDocumentElement();
        NodeList entries = root.getChildNodes();

        for (int i = 0; i < entries.getLength(); i++) {
            if (entries.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element entry = (Element) entries.item(i);
                Element timestampElement = doc.createElement("timestamp");
                timestampElement.setTextContent(LocalDateTime.now().toString());
                entry.appendChild(timestampElement);
            }
        }
    }

    protected void save() {
        System.out.println("Saving XML...");

        try {
            source = new DOMSource(doc);
            writer = new FileWriter(outputFile);
            result = new StreamResult(writer);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        } catch (IOException | TransformerException e) {
            System.out.println("Error saving XML: " + e.getMessage());
        }
    }

    protected boolean validateData() {
        System.out.println("Validating XML...");

        if (doc == null) {
            System.out.println("XML is empty or not loaded correctly.");
            return false;
        }
        return true;
    }
}
