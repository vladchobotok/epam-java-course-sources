
package com.epam.rd.java.basic.practice7.xmlclasses;


import javax.xml.bind.annotation.XmlRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the XMLClasses package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: XMLClasses
     * 
     */
    public ObjectFactory() {
        Logger.getLogger(ObjectFactory.class.getName()).log(Level.SEVERE, "Error");
    }

    /**
     * Create an instance of {@link Flowers }
     * 
     */
    public Flowers createFlowers() {
        return new Flowers();
    }

    /**
     * Create an instance of {@link Flowers.Flower }
     * 
     */
    public Flowers.Flower createFlowersFlower() {
        return new Flowers.Flower();
    }

    /**
     * Create an instance of {@link Flowers.Flower.GrowingTips }
     * 
     */
    public Flowers.Flower.GrowingTips createFlowersFlowerGrowingTips() {
        return new Flowers.Flower.GrowingTips();
    }

    /**
     * Create an instance of {@link Flowers.Flower.VisualParameters }
     * 
     */
    public Flowers.Flower.VisualParameters createFlowersFlowerVisualParameters() {
        return new Flowers.Flower.VisualParameters();
    }

    /**
     * Create an instance of {@link Flowers.Flower.GrowingTips.Tempreture }
     * 
     */
    public Flowers.Flower.GrowingTips.Tempreture createFlowersFlowerGrowingTipsTempreture() {
        return new Flowers.Flower.GrowingTips.Tempreture();
    }

    /**
     * Create an instance of {@link Flowers.Flower.GrowingTips.Lighting }
     * 
     */
    public Flowers.Flower.GrowingTips.Lighting createFlowersFlowerGrowingTipsLighting() {
        return new Flowers.Flower.GrowingTips.Lighting();
    }

    /**
     * Create an instance of {@link Flowers.Flower.GrowingTips.Watering }
     * 
     */
    public Flowers.Flower.GrowingTips.Watering createFlowersFlowerGrowingTipsWatering() {
        return new Flowers.Flower.GrowingTips.Watering();
    }

    /**
     * Create an instance of {@link Flowers.Flower.VisualParameters.AveLenFlower }
     * 
     */
    public Flowers.Flower.VisualParameters.AveLenFlower createFlowersFlowerVisualParametersAveLenFlower() {
        return new Flowers.Flower.VisualParameters.AveLenFlower();
    }

}
