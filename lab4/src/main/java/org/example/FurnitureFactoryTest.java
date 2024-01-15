package org.example;


import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

class FurnitureFactoryTest {
    FurnitureFactoryTest() {
    }

    @Test
    void txtInputTest() {
        Reader reader = new StringReader("table steel 500\r\nsofa wood 600");
        FurnitureFactory testFactory = new FurnitureFactory();
        testFactory.txtInput(reader);
        Assertions.assertEquals("type: table material: steel price: 500\ntype: sofa material: wood price: 600\n", testFactory.toString());
    }

    @Test
    void jsonInputTest() {
        Reader reader = new StringReader("[{\"material\":\"steel\",\"price\":500,\"type\":\"table\"},{\"material\":\"wood\",\"price\":600,\"type\":\"sofa\"}]");
        FurnitureFactory testFactory = new FurnitureFactory();
        testFactory.jsonInput(reader);
        Assertions.assertEquals("type: table material: steel price: 500\ntype: sofa material: wood price: 600\n", testFactory.toString());
    }

    @Test
    void toJSONTest() {
        FurnitureFactory testFactory = new FurnitureFactory();
        List<Furniture> furnitureList = new ArrayList();

        try {
            furnitureList.add(new Furniture("table", "steel", 500));
            furnitureList.add(new Furniture("sofa", "wood", 600));
        } catch (Exception var4) {
            System.out.println(var4.getMessage());
        }

        testFactory.setFurniture(furnitureList);
        Assertions.assertEquals("[{\"material\":\"steel\",\"price\":500,\"type\":\"table\"},{\"material\":\"wood\",\"price\":600,\"type\":\"sofa\"}]", testFactory.toJSON());
    }

    @Test
    void toXMLTest() {
        FurnitureFactory testFactory = new FurnitureFactory();
        List<Furniture> furnitureList = new ArrayList();
        String result = "";

        try {
            furnitureList.add(new Furniture("table", "steel", 500));
            furnitureList.add(new Furniture("sofa", "wood", 600));
            testFactory.setFurniture(furnitureList);
            DOMSource domSource = testFactory.toXML();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            result = sw.toString();
        } catch (Exception var8) {
            System.out.println(var8.getMessage());
        }

        Assertions.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><furnitureList><furniture><type>table</type><material>steel</material><price>500</price></furniture><furniture><type>sofa</type><material>wood</material><price>600</price></furniture></furnitureList>", result);
    }

    @Test
    void processXML() {
        FurnitureFactory testFurnitureFactory = new FurnitureFactory();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><furnitureList><furniture><type>table</type><material>steel</material><price>500</price></furniture><furniture><type>sofa</type><material>wood</material><price>600</price></furniture></furnitureList>";

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlString)));
            testFurnitureFactory.processXML(document);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        Assertions.assertEquals("type: table material: steel price: 500\ntype: sofa material: wood price: 600\n", testFurnitureFactory.toString());
    }
}
