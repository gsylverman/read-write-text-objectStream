import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SomeApp {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) {
        locations.put(1, new Location(1, "At home"));
        locations.put(2, new Location(2, "Lost in the forest"));
        locations.put(3, new Location(3, "Climbing the mountain"));
        locations.put(4, new Location(4, "Swimming in the river"));
        read();
    }

    //READ AND WRITE WITH NIO
    private static void read() {
        Path filePath = FileSystems.getDefault().getPath("data.dat");
        boolean endOfFile = false;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(filePath)))) {
            while (!endOfFile) {
                Location tempLocation = (Location) objectInputStream.readObject();
                System.out.println(tempLocation.getDescription());
            }
        } catch (EOFException e) {
            endOfFile = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void write() {
        Path filePath = FileSystems.getDefault().getPath("data.dat");

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(filePath)))) {
            for (Location location : locations.values()) {
                objectOutputStream.writeObject(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}