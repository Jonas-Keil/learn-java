import java.awt.Point;
import java.util.Scanner;

public class Snake {
    public static void main(String[] args) {
        //Objekte und Spielfeld erstellen
        Point spielerPosition = new Point(19, 4);
        Point schlangePosition = new Point(3, 8);
        Point goldPosition = new Point(25, 8);
        Point tuerPosition = new Point(34, 6);
        boolean weiter = true;
        boolean goldEingesammelt = false;

        while(weiter) {
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 40; x++) {
                    Point p = new Point(x, y);
                    if (p.equals(spielerPosition))
                        System.out.print("P");
                    else if (p.equals(schlangePosition))
                        System.out.print("S");
                    else if (p.equals(goldPosition))
                        System.out.print("G");
                    else if (p.equals(tuerPosition))
                        System.out.print("T");
                    else System.out.print(".");

                }
                System.out.println();
            }
            //Spiellogik
            if (schlangePosition.equals(spielerPosition)) {
                weiter = false;
                System.out.println("Zzz... Die Schlange hat dich.");
            }
            if (spielerPosition.equals(goldPosition)) {
                goldEingesammelt = true;
                goldPosition = new Point(-1, -1);
            }
            if (spielerPosition.equals(tuerPosition) && goldEingesammelt) {
                weiter = false;
                System.out.println("Gewonnen!");
            }


            bewegeSpieler(spielerPosition);
            bewegeSchlange(schlangePosition, spielerPosition);
        }
    }

    private static void bewegeSchlange(Point schlangePosition, Point spielerPosition) {
        if (spielerPosition.x < schlangePosition.x)
            schlangePosition.x--;
        else if (spielerPosition.x > schlangePosition.x)
            schlangePosition.x++;
        if (spielerPosition.y < schlangePosition.y)
            schlangePosition.y--;
        else if (spielerPosition.y > schlangePosition.y)
            schlangePosition.y++;
    }

    private static void bewegeSpieler(Point spielerPosition) {
        Scanner scan = new Scanner(System.in);
        String input = scan.next();

        //w,a,s,d
        if (input.equals("w")) {
            if (spielerPosition.y > 0)
                spielerPosition.y--;
        } else if (input.equals("s")) {
            if (spielerPosition.y < 9)
                spielerPosition.y++;
        } else if (input.equals("a")) {
            if (spielerPosition.x > 0)
                spielerPosition.x--;
        } else if (input.equals("d")) {
            if (spielerPosition.x < 39 )
                spielerPosition.x++;
        }
    }
}
