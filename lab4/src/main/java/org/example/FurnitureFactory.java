package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FurnitureFactory {
    private List<Furniture> furniture = new ArrayList();

    FurnitureFactory() {
    }

    public void setFurniture(List<Furniture> temp) {
        this.furniture = temp;
    }

    public void txtInput(Reader fileIn) {
        try {
            Scanner scan = new Scanner(fileIn);

            String tempString;
            while((tempString = scan.nextLine()) != null) {
                String[] tokens = tempString.split(" ");
                this.furniture.add(new Furniture(tokens[0], tokens[1], Integer.parseInt(tokens[2])));
            }

            scan.close();
        } catch (Exception var5) {
            System.out.println(var5.getMessage());
        }

    }

    public void txtOutput(FileWriter file) {
        try {
            file.write(this.toString());
            file.flush();
        } catch (Exception var3) {
            System.out.println(var3.getMessage());
        }

    }

    public void jsonInput(Reader reader) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            JSONArray furnitureList = (JSONArray)obj;
            furnitureList.forEach((elem) -> {
                this.furniture.add(new Furniture((JSONObject)elem));
            });
        } catch (IOException | ParseException var5) {
            var5.printStackTrace();
        }

    }

    public void jsonOutput(FileWriter file) {
        try {
            file.write(this.toJSON());
            file.flush();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public void xmlInput(String fileName) {
        try {
            File xmlFile = new File(fileName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            this.processXML(document);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public void xmlOutput(String fileName) {
        try {
            FileOutputStream output = new FileOutputStream(fileName);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(this.toXML(), new StreamResult(output));
        } catch (TransformerException | ParserConfigurationException | IOException var5) {
            var5.printStackTrace();
        }

    }

    public String toString() {
        String result = "";

        for(ListIterator<Furniture> it = this.furniture.listIterator(); it.hasNext(); result = result + ((Furniture)it.next()).toString() + "\n") {
        }

        return result;
    }

    public String toJSON() {
        JSONArray furnitureList = new JSONArray();
        Iterator var3 = this.furniture.iterator();

        while(var3.hasNext()) {
            Furniture elem = (Furniture)var3.next();
            JSONObject tempFurniture = new JSONObject();
            tempFurniture.put("type", elem.getType());
            tempFurniture.put("material", elem.getMaterial());
            tempFurniture.put("price", elem.getPrice());
            furnitureList.add(tempFurniture);
        }

        return furnitureList.toString();
    }

    public void processXML(Document document) {
        try {
            document.getDocumentElement().normalize();
            NodeList furnitureNodes = document.getElementsByTagName("furniture");

            for(int temp = 0; temp < furnitureNodes.getLength(); ++temp) {
                Node node = furnitureNodes.item(temp);
                if (node.getNodeType() == 1) {
                    Element element = (Element)node;
                    String type = element.getElementsByTagName("type").item(0).getTextContent();
                    String material = element.getElementsByTagName("material").item(0).getTextContent();
                    Integer price = Integer.parseInt(element.getElementsByTagName("price").item(0).getTextContent());
                    this.furniture.add(new Furniture(type, material, price));
                }
            }
        } catch (Exception var9) {
            System.out.println(var9.getMessage());
        }

    }

    public DOMSource toXML() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element rootElement = document.createElement("furnitureList");
        document.appendChild(rootElement);
        Iterator var6 = this.furniture.iterator();

        while(var6.hasNext()) {
            Furniture elem = (Furniture)var6.next();
            Element furnitureElement = document.createElement("furniture");
            Element type = document.createElement("type");
            type.setTextContent(elem.getType());
            furnitureElement.appendChild(type);
            Element material = document.createElement("material");
            material.setTextContent(elem.getMaterial());
            furnitureElement.appendChild(material);
            Element price = document.createElement("price");
            price.setTextContent(elem.getPrice().toString());
            furnitureElement.appendChild(price);
            rootElement.appendChild(furnitureElement);
        }

        return new DOMSource(document);
    }

    public void sort() {
        Collections.sort(this.furniture, new FurnitureComparator());
    }
}
