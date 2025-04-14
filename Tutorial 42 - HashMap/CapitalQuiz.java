package advanced.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CapitalQuiz {
    public static void main(String[] args) {
        java.util.Map<String, String> capitals = new HashMap<>();
        capitals.put("Frankreich", "Paris");
        capitals.put("Dänemark", "Kopenhagen");
        capitals.put("Schweden", "Stockholm");
        capitals.put("Finnland ", "Helsinki");
        capitals.put("USA", "Washington");
        capitals.put("China", "Peking");
        capitals.put("Japan", "Tokio");
        capitals.put("Russland", "Moskau");
        capitals.put("Deutschland", "Berlin");
        capitals.put("Polen", "Warschau");
        capitals.put("Andorra", "Andorra La Vella");
        capitals.put("Uruguay", "Montevideo");
        capitals.put("Island", "Reykjavik");
        capitals.put("Taiwan", "Taipeh");
        capitals.put("Pakistan", "Islamabad");

        List<String> countries = new ArrayList<>();
        countries.addAll(capitals.keySet());

        Random random = new Random();
        Scanner scan = new Scanner(System.in);

        while (true) {
            int index = random.nextInt(countries.size());
            String country = countries.get(index);
            System.out.println("Was ist die Hauptstadt von " + country);
            if (scan.next().equals(capitals.get(country))) {
                System.out.println("Richtig!");
            } else {
                System.out.println("Leider Falsch!");
                System.out.println("Richtig wäre: " + capitals.get(country));
            }
        }
    }
}
