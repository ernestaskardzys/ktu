package info.ernestas.ktu.xmlandjson.json;

import info.ernestas.ktu.xmlandjson.ConvertingService;
import info.ernestas.ktu.xmlandjson.model.Customer;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonServiceImplTest {

    private ConvertingService<Customer> convertingService;

    @Before
    public void setUp() {
        convertingService = new JsonServiceImpl<>(Customer.class);
    }

    @Test
    public void testStringToObject() throws IOException {
        String jsonString = "{\"name\":\"Ernestas Kardzys\",\"age\":29,\"id\":123}";

        Customer customer = convertingService.stringToObject(jsonString);

        assertEquals("Ernestas Kardzys", customer.getName());
        assertEquals(29, customer.getAge());
        assertEquals(123, customer.getId());
    }

    @Test
    public void testFileToObject() throws IOException {
        Customer customer = convertingService.fileToObject("src/test/resources/testFile.json");

        assertEquals("John Doe", customer.getName());
        assertEquals(40, customer.getAge());
        assertEquals(159, customer.getId());
    }

    @Test
    public void testObjectToString() throws IOException {
        Customer customer = new Customer();
        customer.setId(456);
        customer.setName("John Smith");
        customer.setAge(29);

        String jsonString = convertingService.objectToString(customer);

        assertEquals("{\"id\":456,\"name\":\"John Smith\",\"age\":29}", jsonString);
    }

    @Test
    public void testObjectToFile() throws IOException {
        final String pathToFile = "src/test/resources/janeFile.json";

        Customer customer = new Customer();
        customer.setId(789);
        customer.setAge(35);
        customer.setName("Jane Doe");

        convertingService.objectToFile(pathToFile, customer);

        assertTrue(new File(pathToFile).exists());
    }

}