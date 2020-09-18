import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SomeApp {

    private static Map<Integer, Location> locations = new HashMap<>();
    private static Map<Integer, Location> locations1 = new HashMap<>();

    public static void main(String[] args) throws IOException {
        locations.put(1, new Location(1, "In the road"));
        locations.put(2, new Location(2, "On the mountain"));
        locations.put(3, new Location(3, "Swimming in the river"));
        locations.put(4, new Location(4, "Lost in the forest"));
        locations.put(5, new Location(5, "At home"));
//        writeByte();
        readByte();
    }

    private static void readByte() throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data.dat")))){
            Location tempLocation;
            while ((tempLocation = (Location) objectInputStream.readObject()) != null) {
                locations1.put(tempLocation.getLocationID(), tempLocation);
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (EOFException e) {
            System.out.print("");
        }finally {
            for (Location location : locations1.values()) {
                System.out.println(location.getLocationID()+ ","+ location.getDescription());
            }
        }
    }

    private static void writeByte() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data.dat")))){
            for (Location location : locations.values()) {
                objectOutputStream.writeObject(location);
            }
        }
    }

    private static void read() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("locations.txt"))){
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                String[] inputArr = input.split(",");
                int id = Integer.parseInt(inputArr[0]);
                String description = inputArr[1];
                System.out.println("ID: "+id+", Description: "+ description);
            }
        }
    }

    private static void write () throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("locations.txt"))){
            for (Location location : locations.values()) {
                bufferedWriter.write(location.getLocationID()+","+location.getDescription()+"\n");
            }
        }
    }
}
