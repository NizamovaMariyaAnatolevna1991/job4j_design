package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAttribute;

class Engine {

    @XmlAttribute
    private int horsepower;

    @XmlAttribute
    private String type;

    public Engine() {
    }

    public Engine(int horsepower, String type) {
        this.horsepower = horsepower;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "horsepower='" + horsepower + '\''
                + ", type='" + type + '\''
                + '}';
    }
}
