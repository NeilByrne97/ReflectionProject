package ie.gmit.sw.Controller;

/**
 * Created by Sean on 10/01/2017.
 */


import java.io.IOException;

public interface Readable {

    public ClassList init(String jarName) throws IOException, NoSuchMethodException;

}