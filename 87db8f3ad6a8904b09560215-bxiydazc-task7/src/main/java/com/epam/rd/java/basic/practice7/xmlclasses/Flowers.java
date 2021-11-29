package com.epam.rd.java.basic.practice7.xmlclasses;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flowers {

    protected List<Flowers.Flower> flower;

    public List<Flowers.Flower> getFlower() {
        if (flower == null) {
            flower = new ArrayList<>();
        }
        return this.flower;
    }

    public void flowersWord(){
        //comment
    }

    public static class Flower implements Comparable<Flower>{

        protected String name;
        protected String soil;
        protected String origin;
        protected Flowers.Flower.VisualParameters visualParameters;
        protected Flowers.Flower.GrowingTips growingTips;
        protected String multiplying;

        public Flower(String name, String soil, String origin,
                      Flowers.Flower.VisualParameters visualParameters,
                      Flowers.Flower.GrowingTips growingTips,
                      String multiplying){
            this.name = name;
            this.soil = soil;
            this.origin = origin;
            this.visualParameters = visualParameters;
            this.growingTips = growingTips;
            this.multiplying = multiplying;
        }

        public Flower(){

        }

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }

        public String getSoil() {
            return soil;
        }

        public void setSoil(String value) {
            this.soil = value;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String value) {
            this.origin = value;
        }

        public Flowers.Flower.VisualParameters getVisualParameters() {
            return visualParameters;
        }

        public void setVisualParameters(Flowers.Flower.VisualParameters value) {
            this.visualParameters = value;
        }

        public Flowers.Flower.GrowingTips getGrowingTips() {
            return growingTips;
        }

        public void setGrowingTips(Flowers.Flower.GrowingTips value) {
            this.growingTips = value;
        }

        public String getMultiplying() {
            return multiplying;
        }

        public void setMultiplying(String value) {
            this.multiplying = value;
        }

        public static class GrowingTips {

            protected Flowers.Flower.GrowingTips.Tempreture tempreture;
            protected Flowers.Flower.GrowingTips.Lighting lighting;
            protected Flowers.Flower.GrowingTips.Watering watering;

            public Flowers.Flower.GrowingTips.Tempreture getTempreture() {
                return tempreture;
            }

            public void setTempreture(Flowers.Flower.GrowingTips.Tempreture value) {
                this.tempreture = value;
            }

            public Flowers.Flower.GrowingTips.Lighting getLighting() {
                return lighting;
            }

            public void setLighting(Flowers.Flower.GrowingTips.Lighting value) {
                this.lighting = value;
            }

            public Flowers.Flower.GrowingTips.Watering getWatering() {
                return watering;
            }

            public void setWatering(Flowers.Flower.GrowingTips.Watering value) {
                this.watering = value;
            }

            public static class Lighting {

                protected String lightRequiring;

                public String getLightRequiring() {
                    return lightRequiring;
                }

                public void setLightRequiring(String value) {
                    this.lightRequiring = value;
                }

            }

            public static class Tempreture {

                protected String measure;
                protected int value;

                public String getMeasure() {
                    if (measure == null) {
                        return "celcius";
                    } else {
                        return measure;
                    }
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public int getValue() {
                    return value;
                }

                public void setMeasure(String value) {
                    this.measure = value;
                }

            }

            public static class Watering {

                protected int value;
                protected String measure;

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public String getMeasure() {
                    if (measure == null) {
                        return "mlPerWeek";
                    } else {
                        return measure;
                    }
                }

                public void setMeasure(String value) {
                    this.measure = value;
                }

            }

        }

        public static class VisualParameters {

            protected String stemColour;
            protected String leafColour;
            protected Flowers.Flower.VisualParameters.AveLenFlower aveLenFlower;

            public String getStemColour() {
                return stemColour;
            }

            public void setStemColour(String value) {
                this.stemColour = value;
            }

            public String getLeafColour() {
                return leafColour;
            }

            public void setLeafColour(String value) {
                this.leafColour = value;
            }

            public Flowers.Flower.VisualParameters.AveLenFlower getAveLenFlower() {
                return aveLenFlower;
            }

            public void setAveLenFlower(Flowers.Flower.VisualParameters.AveLenFlower value) {
                this.aveLenFlower = value;
            }

            public static class AveLenFlower {

                public String getMeasure() {
                    if (measure == null) {
                        return "cm";
                    } else {
                        return measure;
                    }
                }

                public int getValue() {
                    return value;
                }

                protected int value;

                public void setValue(int value) {
                    this.value = value;
                }

                protected String measure;

                public void setMeasure(String value) {
                    this.measure = value;
                }

            }

        }

        @Override
        public int compareTo(Flower o) {
            int result = name.compareTo(o.name);
            if (result != 0) {
                return result;
            }
            result = soil.compareTo(o.soil);
            if (result != 0) {
                return result;
            }
            result = o.origin.compareTo(origin);
            if (result != 0) {
                return result;
            }
            result = o.multiplying.compareTo(multiplying);
            if (result != 0) {
                return result;
            }
            result = Integer.compare(o.growingTips.tempreture.value, growingTips.tempreture.value);
            if (result != 0) {
                return result;
            }
            result = o.growingTips.tempreture.measure.compareTo(growingTips.tempreture.measure);
            if (result != 0) {
                return result;
            }
            result = o.growingTips.lighting.lightRequiring.compareTo(growingTips.lighting.lightRequiring);
            if (result != 0) {
                return result;
            }
            result = Integer.compare(growingTips.watering.value, o.growingTips.watering.value);
            if (result != 0) {
                return result;
            }
            result = growingTips.watering.measure.compareTo(o.growingTips.watering.measure);
            if (result != 0) {
                return result;
            }
            result = visualParameters.stemColour.compareTo(o.visualParameters.stemColour);
            if (result != 0) {
                return result;
            }
            result = visualParameters.leafColour.compareTo(o.visualParameters.leafColour);
            if (result != 0) {
                return result;
            }
            result = visualParameters.aveLenFlower.measure.compareTo(o.visualParameters.aveLenFlower.measure);
            if (result != 0) {
                return result;
            }
            result = Integer.compare(o.visualParameters.aveLenFlower.value, visualParameters.aveLenFlower.value);
            if (result != 0) {
                return result;
            }
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Flower flower = (Flower) o;
            return Objects.equals(name, flower.name) && Objects.equals(soil, flower.soil) && Objects.equals(origin, flower.origin) && Objects.equals(visualParameters, flower.visualParameters) && Objects.equals(growingTips, flower.growingTips) && Objects.equals(multiplying, flower.multiplying);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, soil, origin, visualParameters, growingTips, multiplying);
        }
    }
}
