package BaseBuilder;

import java.util.Scanner;

public class Base {

    public static void main(String[] args) {
        int gems = 0;
        int gold = 10000;
        int holz = 1000;
        Scanner scan = new Scanner(System.in);
        Holzhütte[] holzhütten = new Holzhütte[100];
        int holzhüttenIndex = 0;
        Goldmine[] goldminen = new Goldmine[100];
        int goldMinenIndex = 0;

        while (true) {
            System.out.println("Gold: " + gold + "| Holz: " + holz + "| Gems: " + gems);
            if (gems >= 2000) {
                System.out.println("Du hast das Spiel durchgespielt! Glückwunsch");
                break;
            }
            else{
                System.out.println("Möchten Sie eine Holzhütte(1) oder Golmine(2) kaufen? Möchten sie Edelsteine erwerben?(3). Oder das Programm beeenden? (4)");
                int input = scan.nextInt();
                if (input == 1) {
                    if (holzhüttenIndex < 100) {
                        System.out.println("Eine Holzhütte kostet Sie pro Level 500 Holz und 2000 Gold");
                        System.out.println("Bitte geben Sie ihr gewünschtes Level ein");
                        int level = scan.nextInt();
                        if (level > 0 && level < 10) {
                            holzhütten[holzhüttenIndex] = new Holzhütte(level);
                            holzhüttenIndex++;
                            holz -= 500 * level;
                            gold -= 2000 * level;
                        }
                    }else{
                        System.out.println("Maximale Anzahl an Hütten erreicht");
                    }
                }
                if (input == 2) {
                    if (goldMinenIndex < 100) {
                        System.out.println("Eine Goldmine kostet Sie pro Level 600 Holz und 800 Gold");
                        System.out.println("Bitte geben Sie ihr gewünschtes Level ein");
                        int level = scan.nextInt();
                        if (level > 0 && level < 10) {
                            goldminen[goldMinenIndex] = new Goldmine(level);
                            goldMinenIndex++;
                            holz -= 600 * level;
                            gold -= 800 * level;
                        }
                    }else{
                        System.out.println("Maximale Anzahl an Hütten erreicht");
                    }
                }
                if (input == 3) {
                    System.out.println("Ein Juwel kostet 100 Gold und 10 Holz");
                    System.out.println("Wie viele Juwelen möchten Sie eintauschen?");
                    int anzahl = scan.nextInt();
                    if (anzahl * 100 <= gold && anzahl * 10 <= holz) {
                        gems += anzahl;
                        gold -= anzahl * 100;
                        holz -= anzahl * 10;
                    }
                }
                if (input == 4) {
                    break;
                }
                gold += erhalteGoldVonMinen(goldminen, goldMinenIndex);
                holz += erhalteHolzVonHütten(holzhütten, holzhüttenIndex);
            }
        }
    }

    private static int erhalteGoldVonMinen(Goldmine[] goldminen, int goldMinenIndex) {
        int goldSum = 0;
        for (int i = 0; i < goldMinenIndex; i++) {
            goldminen[i].produziereGold();
            goldSum += goldminen[i].gebeGold();
            goldminen[i].leereMine();
        }
        return goldSum;
    }

    private static int erhalteHolzVonHütten(Holzhütte[] holzhütten, int holzhüttenindex) {
        int holzSum = 0;
        for (int i = 0; i < holzhüttenindex; i++) {
            holzhütten[i].produziereHolz();
            holzSum += holzhütten[i].gebeHolz();
            holzhütten[i].leereHütte();
        }
        return holzSum;
    }
}
