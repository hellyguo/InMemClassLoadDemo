package demo.inmem.load;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/10/24 2:48 PM
 */
public interface InMemClassLoader {
    Class<?> loadClass(String className, byte[] classBytes);
}
