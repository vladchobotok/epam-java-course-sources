package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.xmlclasses.Flowers;
import com.epam.rd.java.basic.practice7.Consts;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DOMParser {

    private String filename;

    private final List<Flowers.Flower> flowers;

    public DOMParser(String filename) {
        flowers = new ArrayList<>();
        this.filename = filename;
        Flowers flowersF = new Flowers();
        flowersF.flowersWord();
    }

    public List<Flowers.Flower> getFlowers() {
        return flowers;
    }


    public void parseXML(boolean validate){

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            dbf.setNamespaceAware(true);
            if (validate) {
                        dbf.setFeature(Consts.FEATURE_TURN_VALIDATION_ON, true);
                        dbf.setFeature(Consts.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
            }

            Document doc = db.parse(new File(filename));

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName(Consts.FLOWER);

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);
                Flowers.Flower flower;

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String name = element.getElementsByTagName(Consts.NAME).item(0).getTextContent();
                    String soil = element.getElementsByTagName(Consts.SOIL).item(0).getTextContent();
                    String origin = element.getElementsByTagName(Consts.ORIGIN).item(0).getTextContent();

                    NodeList visualParameters = element.getElementsByTagName(Consts.VISUAL_PARAMETERS);
                    Flowers.Flower.VisualParameters visualParameters1 = getVisualParameters(visualParameters);

                    NodeList growingTips = element.getElementsByTagName(Consts.GROWING_TIPS);
                    Flowers.Flower.GrowingTips growingTips1 = getGrowingTips(growingTips);

                    String multiplying = element.getElementsByTagName(Consts.MULTIPLYING).item(0).getTextContent();

                    flower = new Flowers.Flower(name, soil, origin, visualParameters1, growingTips1, multiplying);
                    flowers.add(flower);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, Consts.ERROR, e);
        }

    }

    private Flowers.Flower.VisualParameters getVisualParameters(NodeList visualParameters){
        Flowers.Flower.VisualParameters visualParameters1 = new Flowers.Flower.VisualParameters();
        for (int tmp = 0; tmp < visualParameters.getLength(); tmp++) {

            Node node1 = visualParameters.item(tmp);
            Flowers.Flower.VisualParameters.AveLenFlower aveLenFlower1 = new Flowers.Flower.VisualParameters.AveLenFlower();
            if (node1.getNodeType() == Node.ELEMENT_NODE) {

                Element element1 = (Element) node1;
                visualParameters1.setStemColour(element1.getElementsByTagName(Consts.STEM_COLOUR).item(0).getTextContent());
                visualParameters1.setLeafColour(element1.getElementsByTagName(Consts.LEAF_COLOUR).item(0).getTextContent());

                aveLenFlower1.setValue(Integer.parseInt(element1.getElementsByTagName(Consts.AVELENFLOWER).item(0).getTextContent()));
                aveLenFlower1.setMeasure(element1.getElementsByTagName(Consts.AVELENFLOWER).item(0).getAttributes().getNamedItem(Consts.MEASURE).getTextContent());
                visualParameters1.setAveLenFlower(aveLenFlower1);
            }
        }
        return visualParameters1;
    }

    private Flowers.Flower.GrowingTips getGrowingTips(NodeList growingTips){
        Flowers.Flower.GrowingTips growingTips1 = new Flowers.Flower.GrowingTips();

        for (int tmp = 0; tmp < growingTips.getLength(); tmp++) {

            Node node1 = growingTips.item(tmp);
            Flowers.Flower.GrowingTips.Tempreture tempreture1 = new Flowers.Flower.GrowingTips.Tempreture();
            Flowers.Flower.GrowingTips.Lighting lighting1 = new Flowers.Flower.GrowingTips.Lighting();
            Flowers.Flower.GrowingTips.Watering watering1 = new Flowers.Flower.GrowingTips.Watering();

            if (node1.getNodeType() == Node.ELEMENT_NODE) {

                Element element1 = (Element) node1;
                tempreture1.setValue(Integer.parseInt(element1.getElementsByTagName(Consts.TEMPRETURE).item(0).getTextContent()));
                watering1.setValue(Integer.parseInt(element1.getElementsByTagName(Consts.WATERING).item(0).getTextContent()));

                tempreture1.setMeasure(element1.getElementsByTagName(Consts.TEMPRETURE).item(0).getAttributes().getNamedItem(Consts.MEASURE).getTextContent());
                lighting1.setLightRequiring(element1.getElementsByTagName(Consts.LIGHTING).item(0).getAttributes().getNamedItem("lightRequiring").getTextContent());
                watering1.setMeasure(element1.getElementsByTagName(Consts.WATERING).item(0).getAttributes().getNamedItem(Consts.MEASURE).getTextContent());
                growingTips1.setTempreture(tempreture1);
                growingTips1.setLighting(lighting1);
                growingTips1.setWatering(watering1);
            }
        }
        return growingTips1;
    }

    public void sortXML(){
        Collections.sort(flowers);
    }

    private Document createDocument() throws ParserConfigurationException {
        final String[] flowersArray = { Consts.NAME, Consts.SOIL, Consts.ORIGIN, Consts.VISUAL_PARAMETERS, Consts.GROWING_TIPS, Consts.MULTIPLYING };
        final String[] visualParametersArray = { Consts.STEM_COLOUR, Consts.LEAF_COLOUR, Consts.AVELENFLOWER };
        final String[] growingTipsArray = { Consts.TEMPRETURE, Consts.LIGHTING, Consts.WATERING };
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setAttribute(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        dbf.setXIncludeAware(false);
        dbf.setExpandEntityReferences(false);
        dbf.setNamespaceAware(true);
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement(Consts.FLOWERS);
        document.appendChild(root);
        Element element;
        for (Flowers.Flower flower : flowers) {
            Element flowerElement = document.createElement(Consts.FLOWER);
            List<Object> listedFlowers = flowerToList(flower);

            for (int i = 0; i < 3; i++) {
                element = document.createElement(flowersArray[i]);
                element.setTextContent(listedFlowers.get(i).toString());
                flowerElement.appendChild(element);
            }

            Element visualParametersElement = document.createElement(Consts.VISUAL_PARAMETERS);

            Element childElement;

            childElement = document.createElement(visualParametersArray[0]);
            childElement.setTextContent(flower.getVisualParameters().getStemColour());
            visualParametersElement.appendChild(childElement);

            childElement = document.createElement(visualParametersArray[1]);
            childElement.setTextContent(flower.getVisualParameters().getLeafColour());
            visualParametersElement.appendChild(childElement);

            childElement = document.createElement(visualParametersArray[2]);
            childElement.setTextContent(Integer.toString(flower.getVisualParameters().getAveLenFlower().getValue()));
            childElement.setAttribute(Consts.MEASURE, flower.getVisualParameters().getAveLenFlower().getMeasure());
            visualParametersElement.appendChild(childElement);


            flowerElement.appendChild(visualParametersElement);

            Element growingTipsElement = document.createElement(Consts.GROWING_TIPS);

            for (int i = 0; i < growingTipsArray.length; i++) {
                childElement = document.createElement(growingTipsArray[i]);
                if(i == 0){
                    childElement.setTextContent(Integer.toString(flower.getGrowingTips().getTempreture().getValue()));
                    childElement.setAttribute(Consts.MEASURE, flower.getGrowingTips().getTempreture().getMeasure());
                }else if(i == 1){
                    childElement.setAttribute("lightRequiring", flower.getGrowingTips().getLighting().getLightRequiring());
                }else if(i == 2){
                    childElement.setTextContent(Integer.toString(flower.getGrowingTips().getWatering().getValue()));
                    childElement.setAttribute(Consts.MEASURE, flower.getGrowingTips().getWatering().getMeasure());
                }
                growingTipsElement.appendChild(childElement);
            }

            flowerElement.appendChild(growingTipsElement);

            element = document.createElement(flowersArray[5]);
            element.setTextContent(listedFlowers.get(5).toString());
            flowerElement.appendChild(element);

            root.appendChild(flowerElement);
        }
        return document;

    }

    public void saveToXML(){

        StreamResult result = new StreamResult(new File(Consts.DOM_RESULT));

        try {
            TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
            tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            javax.xml.transform.Transformer t = null;
            t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(createDocument()), result);
        } catch (ParserConfigurationException | TransformerException e) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, Consts.ERROR, e);
        }

    }

    public static List<Object> flowerToList(Flowers.Flower flower) {
        List<Object> resultList = new ArrayList<>();
        resultList.add(flower.getName());
        resultList.add(flower.getSoil());
        resultList.add(flower.getOrigin());
        resultList.add(flower.getVisualParameters());
        resultList.add(flower.getGrowingTips());
        resultList.add(flower.getMultiplying());
        return resultList;
    }
}