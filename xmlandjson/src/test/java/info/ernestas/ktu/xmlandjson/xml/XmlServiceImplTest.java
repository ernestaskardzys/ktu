package info.ernestas.ktu.xmlandjson.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import info.ernestas.ktu.xmlandjson.ConvertingService;
import info.ernestas.ktu.xmlandjson.model.Customer;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XmlServiceImplTest {

    private ConvertingService<Customer> xmlService;

    @Before
    public void setUp() {
        xmlService = new XmlServiceImpl<>(Customer.class);
    }

    @Test
    public void testStringToObject() throws IOException {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<customer id=\"123\">\n" +
                "    <age>29</age>\n" +
                "    <name>Ernestas Kardzys</name>\n" +
                "</customer>\n";

        Customer customer = xmlService.stringToObject(xmlString);

        assertEquals(123, customer.getId());
        assertEquals(29, customer.getAge());
        assertEquals("Ernestas Kardzys", customer.getName());

    }

    @Test
    public void testFileToObject() throws IOException {
        String pathToFile = "src/test/resources/file.xml";

        Customer customer = xmlService.fileToObject(pathToFile);

        assertEquals(123, customer.getId());
        assertEquals(29, customer.getAge());
        assertEquals("Ernestas Kardzys", customer.getName());
    }

    @Test
    public void testObjectToString() throws IOException {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Ernestas Kardzys");
        customer.setAge(29);

        String xml = xmlService.objectToString(customer);

        String resultingString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<customer id=\"1\">\n" +
                "    <age>29</age>\n" +
                "    <name>Ernestas Kardzys</name>\n" +
                "</customer>\n";

        assertEquals(resultingString, xml);
    }

    @Test
    public void testObjectToFile() throws IOException {
        final String pathToFile = "xmlFile.xml";

        Customer customer = new Customer();
        customer.setId(10);
        customer.setName("John Smith");
        customer.setAge(35);

        xmlService.objectToFile(pathToFile, customer);

        assertTrue(new File(pathToFile).exists());
    }

}