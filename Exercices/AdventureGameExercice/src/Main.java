import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();
    private static Map<String, String> vocabulary = new HashMap<String, String>();

    public static void main(String[] args) {
        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java"));
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building"));
        locations.put(2, new Location(2, "You are at the top of a hill"));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
        locations.put(4, new Location(4, "You are in a valley beside a stream"));
        locations.put(5, new Location(5, "You are in the forest"));

        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        command();
    }

    public static void command(){
        Scanner scanner = new Scanner(System.in);
        int loc = 1;
        while(true) {
            Location location = locations.get(loc);
            String description = location.getDescription();
            Map<String, Integer> exits = location.getExits();
            System.out.println(description);
            System.out.print("Available exits are ");
            for(String key : exits.keySet()){
                System.out.print(key + ", ");
            }
            Integer locId = null;
            String input = scanner.nextLine().toUpperCase();
            if(input.length() == 1){
                locId = exits.get(input);
            } else if(input.length() > 1) {
                String[] words = input.split(" ");
                for(String word : words){
                    for(String key : vocabulary.keySet()){
                        if(word.equals(vocabulary.get(key))){
                            input = key;
                            locId = exits.get(input);
                            break;
                        }
                    }
                    if(input.length() == 1){
                        break;
                    }
                }
            }
            if(locId == null){
                System.out.println("You cannot go in that direction");
            } else {
                loc = locId;
                if(input.equals("Q")){
                    location = locations.get(loc);
                    description = location.getDescription();
                    System.out.println(description);
                    break;
                }
            }
        }
    }
}
