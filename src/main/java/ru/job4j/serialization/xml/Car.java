package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;

import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean isElectric;

    @XmlAttribute
    private long mileage;

    @XmlAttribute
    private String model;

    private Engine engine;
    @XmlElementWrapper(name = "serviceHistories")
    @XmlElement(name = "serviceHistory")
    private String[] serviceHistories;

    public Car() {
    }

    public Car(boolean isElectric, long mileage, String model, Engine engine, String... serviceHistories) {
        this.isElectric = isElectric;
        this.mileage = mileage;
        this.model = model;
        this.engine = engine;
        this.serviceHistories = serviceHistories;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isElectric=" + isElectric
                + ", mileage=" + mileage
                + ", model=" + model
                + ", engine" + engine
                + ", serviceHistories=" + Arrays.toString(serviceHistories)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Car car = new Car(false, 130000, "Audi",
                new ru.job4j.serialization.xml.Engine(116, "1.4 ATM"),
                new String[]{"2022 - Body repair", "2023 - Engine repair"});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

