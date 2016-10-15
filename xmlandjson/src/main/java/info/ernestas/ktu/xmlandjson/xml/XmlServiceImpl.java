package info.ernestas.ktu.xmlandjson.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import info.ernestas.ktu.xmlandjson.ConvertingService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlServiceImpl<T> implements ConvertingService<T> {

    private Class<T> clazz;

    public XmlServiceImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T stringToObject(String objectString) throws IOException {
        try {
            Unmarshaller jaxbUnmarshaller = getUnmarshaller();
            return (T) jaxbUnmarshaller.unmarshal(new StringReader(objectString));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public T fileToObject(String pathToFile) throws IOException {
        try {
            Unmarshaller jaxbUnmarshaller = getUnmarshaller();
            return (T) jaxbUnmarshaller.unmarshal(new File(pathToFile));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String objectToString(T object) throws JsonProcessingException {
        try {
            Marshaller jaxbMarshaller = getMarshaller();
            StringWriter stringWriter = new StringWriter();
            jaxbMarshaller.marshal(object, stringWriter);

            return stringWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void objectToFile(String pathToFile, T object) throws IOException {
        try {
            Marshaller jaxbMarshaller = getMarshaller();
            jaxbMarshaller.marshal(object, new File(pathToFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Marshaller getMarshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return jaxbMarshaller;
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        return jaxbContext.createUnmarshaller();
    }

}
