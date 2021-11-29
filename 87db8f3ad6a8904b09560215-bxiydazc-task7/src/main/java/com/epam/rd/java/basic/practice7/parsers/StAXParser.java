package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.Consts;
import com.epam.rd.java.basic.practice7.xmlclasses.Flowers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;


public class StAXParser {

    private String filename;

    private final List<Flowers.Flower> flowers;
    Flowers.Flower flower;
    Flowers.Flower.VisualParameters visualParameters;
    Flowers.Flower.GrowingTips growingTips;

    public StAXParser(String filename) {
        flowers = new ArrayList<>();
        this.filename = filename;
    }

    public List<Flowers.Flower> getFlowers() {
        return flowers;
    }


    public void parse() {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        try {
            XMLEventReader eventReader = factory.createXMLEventReader(new StreamSource(filename));
            while (eventReader.hasNext()) {
                XMLEvent xmlEvent = eventReader.nextEvent();
                readInput(xmlEvent,eventReader);
            }
        } catch (XMLStreamException e) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, Consts.ERROR, e);
        }
    }

    public void sortXML(){
        Collections.sort(flowers);
    }

    private void readInput(XMLEvent xmlEvent, XMLEventReader eventReader) throws XMLStreamException {

        if (xmlEvent.isStartElement()) {
            StartElement startElement = xmlEvent.asStartElement();
            if (startElement.getName().getLocalPart().equals(Consts.FLOWER)) {
                flower = new Flowers.Flower();
            }
            else if (startElement.getName().getLocalPart().equals(Consts.NAME)) {
                xmlEvent = eventReader.nextEvent();
                flower.setName(xmlEvent.asCharacters().getData());
            }
            else if (startElement.getName().getLocalPart().equals(Consts.SOIL)) {
                xmlEvent = eventReader.nextEvent();
                flower.setSoil(xmlEvent.asCharacters().getData());
            }
            else if (startElement.getName().getLocalPart().equals(Consts.ORIGIN)) {
                xmlEvent = eventReader.nextEvent();
                flower.setOrigin(xmlEvent.asCharacters().getData());
            }
            else if (startElement.getName().getLocalPart().equals(Consts.VISUAL_PARAMETERS)) {
                visualParameters = new Flowers.Flower.VisualParameters();
                setVisualParameters(eventReader);
            }
            else if (startElement.getName().getLocalPart().equals(Consts.GROWING_TIPS)) {
                growingTips = new Flowers.Flower.GrowingTips();
                setGrowingTips(eventReader);
            }
            else if (startElement.getName().getLocalPart().equals(Consts.MULTIPLYING)) {
                xmlEvent = eventReader.nextEvent();
                flower.setMultiplying(xmlEvent.asCharacters().getData());
            }
        } else if (xmlEvent.isEndElement()) {
            EndElement endElement = xmlEvent.asEndElement();
            if (endElement.getName().getLocalPart().equals(Consts.FLOWER)) {
                flowers.add(flower);
            } else if (endElement.getName().getLocalPart().equals(Consts.VISUAL_PARAMETERS)) {
                flower.setVisualParameters(visualParameters);
            } else if (endElement.getName().getLocalPart().equals(Consts.GROWING_TIPS)) {
                flower.setGrowingTips(growingTips);
            }
        }
    }

    private void setVisualParameters(XMLEventReader reader) {
        XMLEvent event;
        StartElement element;
        Characters character;
        for (int i = 0; i < 8; i++) {
            try {
                event = reader.nextEvent();
                if (event.isStartElement()) {
                    element = event.asStartElement();
                    if (element.getName().getLocalPart().equals(Consts.STEM_COLOUR)) {
                        event = reader.nextEvent();
                        visualParameters.setStemColour(event.asCharacters().getData());
                    }
                    else if (element.getName().getLocalPart().equals(Consts.LEAF_COLOUR)) {
                        event = reader.nextEvent();
                        visualParameters.setLeafColour(event.asCharacters().getData());
                    }
                    else if (element.getName().getLocalPart().equals(Consts.AVELENFLOWER)) {
                        Flowers.Flower.VisualParameters.AveLenFlower aveLenFlower = new Flowers.Flower.VisualParameters.AveLenFlower();
                        event = reader.nextEvent();
                        character = event.asCharacters();
                        aveLenFlower.setMeasure("cm");
                        aveLenFlower.setValue(Integer.parseInt(character.getData()));
                        visualParameters.setAveLenFlower(aveLenFlower);
                    }
                }
            } catch (XMLStreamException e) {
                Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, Consts.ERROR, e);
            }
        }
    }

    private void setGrowingTips(XMLEventReader reader) {
        XMLEvent event;
        StartElement element;
        Characters character;
        for (int i = 0; i < 8; i++) {
            try {
                event = reader.nextEvent();
                if (event.isStartElement()) {
                    element = event.asStartElement();
                    if (element.getName().getLocalPart().equals(Consts.TEMPRETURE)) {
                        Flowers.Flower.GrowingTips.Tempreture tempreture = new Flowers.Flower.GrowingTips.Tempreture();
                        event = reader.nextEvent();
                        character = event.asCharacters();
                        tempreture.setMeasure("celcius");
                        tempreture.setValue(Integer.parseInt(character.getData()));
                        growingTips.setTempreture(tempreture);
                    }
                    else if (element.getName().getLocalPart().equals(Consts.LIGHTING)) {
                        Flowers.Flower.GrowingTips.Lighting lighting = new Flowers.Flower.GrowingTips.Lighting();
                        lighting.setLightRequiring("yes");
                        growingTips.setLighting(lighting);
                    }
                    else if (element.getName().getLocalPart().equals(Consts.WATERING)) {
                        Flowers.Flower.GrowingTips.Watering watering = new Flowers.Flower.GrowingTips.Watering();
                        event = reader.nextEvent();
                        character = event.asCharacters();
                        watering.setMeasure("mlPerWeek");
                        watering.setValue(Integer.parseInt(character.getData()));
                        growingTips.setWatering(watering);
                    }
                }
            } catch (XMLStreamException e) {
                Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, Consts.ERROR, e);
            }
        }
    }

    public void writeXML() {
        try {
            SAXParser.writeSaxAndStax(flowers, Consts.STAX_RESULT);
        } catch (IOException | XMLStreamException e) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, Consts.ERROR, e);
        }
    }
}