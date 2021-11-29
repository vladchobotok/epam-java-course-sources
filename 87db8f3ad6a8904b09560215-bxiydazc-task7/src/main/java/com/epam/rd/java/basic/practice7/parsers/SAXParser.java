package com.epam.rd.java.basic.practice7.parsers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.epam.rd.java.basic.practice7.Consts;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.helpers.DefaultHandler;
import com.epam.rd.java.basic.practice7.xmlclasses.Flowers;

public class SAXParser extends DefaultHandler {

    private String filename;

    private List<Flowers.Flower> flowers;
    private Deque<Object> elementDeque;
    private Deque<Flowers.Flower> objectDeque;
    private Deque<Flowers.Flower.VisualParameters> vpObjectDeque;
    private Deque<Flowers.Flower.GrowingTips> gtObjectDeque;

    public SAXParser(String filename) {
        flowers = new ArrayList<>(20);
        elementDeque = new ArrayDeque<>();
        objectDeque = new ArrayDeque<>();
        vpObjectDeque = new ArrayDeque<>();
        gtObjectDeque = new ArrayDeque<>();
        this.filename = filename;
    }

    public List<Flowers.Flower> getFlowers() {
        return flowers;
    }

    public void parse(boolean validate) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        } catch (ParserConfigurationException | SAXNotRecognizedException | SAXNotSupportedException e) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, Consts.ERROR, e);
        }

        if (validate) {
            factory.setValidating(true);
            try {
                factory.setFeature(Consts.FEATURE_TURN_VALIDATION_ON, true);
                factory.setFeature(Consts.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
            } catch (SAXNotRecognizedException | SAXNotSupportedException | ParserConfigurationException e) {
                Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, Consts.ERROR, e);
            }
        }
        javax.xml.parsers.SAXParser parser;
        try {
            parser = factory.newSAXParser();
            parser.parse(filename, this);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, Consts.ERROR, e);
        }
    }

    public void sortXML(){
        Collections.sort(flowers);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){

        this.elementDeque.push(qName);

        if (Consts.FLOWER.equals(qName))
        {
            Flowers.Flower flower = new Flowers.Flower();
            this.objectDeque.push(flower);
        }
        else if (Consts.VISUAL_PARAMETERS.equals(qName))
        {
            Flowers.Flower.VisualParameters visualParameters1 = new Flowers.Flower.VisualParameters();
            this.vpObjectDeque.push(visualParameters1);
        }
        else if (Consts.GROWING_TIPS.equals(qName))
        {
            Flowers.Flower.GrowingTips growingTips1 = new Flowers.Flower.GrowingTips();
            this.gtObjectDeque.push(growingTips1);
        }
        else if(Consts.AVELENFLOWER.equals(currentElement())){
            Flowers.Flower.VisualParameters visualParameters = this.vpObjectDeque.peek();
            Flowers.Flower.VisualParameters.AveLenFlower aveLenFlower1 = new Flowers.Flower.VisualParameters.AveLenFlower();
            aveLenFlower1.setMeasure(attributes.getValue(0));
            visualParameters.setAveLenFlower(aveLenFlower1);
        }
        else if(Consts.TEMPRETURE.equals(currentElement())){
            Flowers.Flower.GrowingTips growingTips = this.gtObjectDeque.peek();
            Flowers.Flower.GrowingTips.Tempreture tempreture = new Flowers.Flower.GrowingTips.Tempreture();
            tempreture.setMeasure(attributes.getValue(0));
            growingTips.setTempreture(tempreture);
        }
        else if(Consts.LIGHTING.equals(currentElement())){
            Flowers.Flower.GrowingTips growingTips = this.gtObjectDeque.peek();
            Flowers.Flower.GrowingTips.Lighting lighting = new Flowers.Flower.GrowingTips.Lighting();
            lighting.setLightRequiring(attributes.getValue(0));
            growingTips.setLighting(lighting);
        }
        else if(Consts.WATERING.equals(currentElement())){
            Flowers.Flower.GrowingTips growingTips = this.gtObjectDeque.peek();
            Flowers.Flower.GrowingTips.Watering watering = new Flowers.Flower.GrowingTips.Watering();
            watering.setMeasure(attributes.getValue(0));
            growingTips.setWatering(watering);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){

        this.elementDeque.pop();

        if (Consts.FLOWER.equals(qName))
        {
            Flowers.Flower object = this.objectDeque.pop();
            this.flowers.add(object);
        }
        else if (Consts.VISUAL_PARAMETERS.equals(qName))
        {
            Flowers.Flower flower = this.objectDeque.peek();
            Flowers.Flower.VisualParameters object = this.vpObjectDeque.pop();
            flower.setVisualParameters(object);

        }
        else if (Consts.GROWING_TIPS.equals(qName))
        {
            Flowers.Flower flower = this.objectDeque.peek();
            Flowers.Flower.GrowingTips object = this.gtObjectDeque.pop();
            flower.setGrowingTips(object);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length){
        String value = new String(ch, start, length).trim();
        if (value.length() == 0) {
            return;
        }

        Flowers.Flower flower = this.objectDeque.peek();

        if (Consts.NAME.equals(currentElement())) {
            flower.setName(value);
        }
        else if (Consts.SOIL.equals(currentElement())) {
            flower.setSoil(value);
        }
        else if (Consts.ORIGIN.equals(currentElement())){
            flower.setOrigin(value);
        }
        else if(Consts.MULTIPLYING.equals(currentElement())){
            flower.setMultiplying(value);
        }
        else if(Consts.STEM_COLOUR.equals(currentElement())){
            Flowers.Flower.VisualParameters visualParameters = this.vpObjectDeque.peek();
            visualParameters.setStemColour(value);
        }
        else if(Consts.LEAF_COLOUR.equals(currentElement())){
            Flowers.Flower.VisualParameters visualParameters = this.vpObjectDeque.peek();
            visualParameters.setLeafColour(value);
        }
        else if(Consts.AVELENFLOWER.equals(currentElement())){
            Flowers.Flower.VisualParameters visualParameters = this.vpObjectDeque.peek();
            Flowers.Flower.VisualParameters.AveLenFlower aveLenFlower1 = visualParameters.getAveLenFlower();
            aveLenFlower1.setValue(Integer.parseInt(value));
            visualParameters.setAveLenFlower(aveLenFlower1);
        }
        else if(Consts.TEMPRETURE.equals(currentElement())){
            Flowers.Flower.GrowingTips growingTips = this.gtObjectDeque.peek();
            Flowers.Flower.GrowingTips.Tempreture tempreture = growingTips.getTempreture();
            tempreture.setValue(Integer.parseInt(value));
            growingTips.setTempreture(tempreture);
        }
        else if(Consts.WATERING.equals(currentElement())){
            Flowers.Flower.GrowingTips growingTips = this.gtObjectDeque.peek();
            Flowers.Flower.GrowingTips.Watering watering = growingTips.getWatering();
            watering.setValue(Integer.parseInt(value));
            growingTips.setWatering(watering);
        }
    }

    private Object currentElement()
    {
        return this.elementDeque.peek();
    }

    public void writeXML(){
        try {
            writeSaxAndStax(flowers, Consts.SAX_RESULT);
        } catch (IOException | XMLStreamException e) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, Consts.ERROR, e);
        }
    }

    public static void writeSaxAndStax(List<Flowers.Flower> flowers, String path) throws IOException, XMLStreamException {
        final String[] flowersArray = { Consts.NAME, Consts.SOIL, Consts.ORIGIN, Consts.VISUAL_PARAMETERS, Consts.GROWING_TIPS, Consts.MULTIPLYING };
        final String[] visualParametersArray = { Consts.STEM_COLOUR, Consts.LEAF_COLOUR, Consts.AVELENFLOWER };
        final String[] growingTipsArray = { Consts.TEMPRETURE, Consts.LIGHTING, Consts.WATERING };
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter;
        try (FileWriter fileWriter = new FileWriter(path)) {
            xmlStreamWriter = outputFactory.createXMLStreamWriter(fileWriter);
            xmlStreamWriter.writeStartDocument();

            xmlStreamWriter.writeStartElement(Consts.FLOWERS);
            xmlStreamWriter.writeAttribute("xmlns:tns", "http://www.example.org/input");
            xmlStreamWriter.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            xmlStreamWriter.writeAttribute("xsi:schemaLocation", "http://www.example.org/input input.xsd");

            for (Flowers.Flower flower : flowers) {
                List<Object> objectsFlower = DOMParser.flowerToList(flower);
                xmlStreamWriter.writeStartElement(Consts.FLOWER);

                for (int i = 0; i < 3; i++) {
                    xmlStreamWriter.writeStartElement(flowersArray[i]);
                    xmlStreamWriter.writeCharacters(objectsFlower.get(i).toString());
                    xmlStreamWriter.writeEndElement();
                }

                xmlStreamWriter.writeStartElement(Consts.VISUAL_PARAMETERS);

                xmlStreamWriter.writeStartElement(visualParametersArray[0]);
                xmlStreamWriter.writeCharacters(flower.getVisualParameters().getStemColour());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement(visualParametersArray[1]);
                xmlStreamWriter.writeCharacters(flower.getVisualParameters().getLeafColour());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement(visualParametersArray[2]);
                xmlStreamWriter.writeAttribute(Consts.MEASURE, flower.getVisualParameters().getAveLenFlower().getMeasure());
                xmlStreamWriter.writeCharacters(Integer.toString(flower.getVisualParameters().getAveLenFlower().getValue()));
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement(Consts.GROWING_TIPS);

                for (int i = 0; i < growingTipsArray.length; i++) {
                    xmlStreamWriter.writeStartElement(growingTipsArray[i]);
                    if (i == 0) {
                        xmlStreamWriter.writeAttribute(Consts.MEASURE, flower.getGrowingTips().getTempreture().getMeasure());
                        xmlStreamWriter.writeCharacters(Integer.toString(flower.getGrowingTips().getTempreture().getValue()));

                    } else if (i == 1) {
                        xmlStreamWriter.writeAttribute("lightRequiring", flower.getGrowingTips().getLighting().getLightRequiring());
                    } else if (i == 2) {
                        xmlStreamWriter.writeAttribute(Consts.MEASURE, flower.getGrowingTips().getWatering().getMeasure());
                        xmlStreamWriter.writeCharacters(Integer.toString(flower.getGrowingTips().getWatering().getValue()));
                    }
                    xmlStreamWriter.writeEndElement();
                }

                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement(flowersArray[5]);
                xmlStreamWriter.writeCharacters(objectsFlower.get(5).toString());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
        }
    }
}
