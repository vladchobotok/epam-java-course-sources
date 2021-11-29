package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.parsers.DOMParser;
import com.epam.rd.java.basic.practice7.parsers.SAXParser;
import com.epam.rd.java.basic.practice7.parsers.StAXParser;

public final class Main {

    public static void main(final String[] args) {

        DOMParser domParser = new DOMParser(args[0]);
        domParser.parseXML(true);
        domParser.sortXML();
        domParser.saveToXML();

        SAXParser saxParser = new SAXParser(args[0]);
        saxParser.parse(true);
        saxParser.sortXML();
        saxParser.writeXML();

        StAXParser stAXParser = new StAXParser(args[0]);
        stAXParser.parse();
        stAXParser.sortXML();
        stAXParser.writeXML();
    }
}
