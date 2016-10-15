package info.ernestas.ktu.xmlandjson;

import java.io.IOException;

public interface ConvertingService<T> {

    T stringToObject(String objectString) throws IOException;

    T fileToObject(String pathToFile) throws IOException;

    String objectToString(T object) throws IOException;

    void objectToFile(String pathToFile, T object) throws IOException;

}
