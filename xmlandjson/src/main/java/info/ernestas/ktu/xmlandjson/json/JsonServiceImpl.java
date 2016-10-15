package info.ernestas.ktu.xmlandjson.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.ernestas.ktu.xmlandjson.ConvertingService;

import java.io.File;
import java.io.IOException;

public class JsonServiceImpl<T> implements ConvertingService<T> {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Class<T> clazz;

    public JsonServiceImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T stringToObject(String objectString) throws IOException {
        return objectMapper.readValue(objectString, clazz);
    }

    @Override
    public T fileToObject(String pathToFile) throws IOException {
        return objectMapper.readValue(new File(pathToFile), clazz);
    }

    @Override
    public String objectToString(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @Override
    public void objectToFile(String pathToFile, T object) throws IOException {
        objectMapper.writeValue(new File(pathToFile), object);
    }

}
