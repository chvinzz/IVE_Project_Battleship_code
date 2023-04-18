
import java.util.Scanner;

public class Battleship {

    //create 2D array
    static char[][] battlefield = new char[10][10];

    //class var
    static int hit;

    //class Launched
    static int Launched;

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                battlefield[i][j] = '.';
            }
        }
        showBattleField(false);
        hit = 0;
        Launched = 0;
        setAShip();

        while (true) {
            System.out.print("Set your missile [XY](x to exit) :");
            String ui = kb.nextLine();
            if (ui.equals("x")) {
                System.out.println("Bye!");
                break;
            } else if (ui.equals("c")) {
                //cheat 
                showBattleField(true);
            } else {
                char cx = ui.charAt(1);
                char cy = ui.charAt(0);
                //ascii code , 0 = 48
                int x = cx - 48;
                int y = cy - 48;
                bomb(x, y);
                showBattleField(false);
                if (hit == 9) {
                    //congr....  print out 
                    System.out.println("You have hit all battleship!");
                    break;
                }

            }
        }

    }

    static void showBattleField(boolean isCheating) {
        System.out.println("    0 1 2 3 4 5 6 7 8 9");
        System.out.println("--+--------------------");
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + i + "| ");
            for (int j = 0; j < 10; j++) {
                if (battlefield[i][j] == 'S' && !isCheating) {
                    System.out.print('.' + " ");
                } else {
                    System.out.print(battlefield[i][j] + " ");
                }

            }
            System.out.println();
        }
    }

    // create 3 ships
    static void setAShip() {
        int placedShips = 0;

        while (placedShips < 3) {
            int a = (int) (Math.random() * 8);
            int b = (int) (Math.random() * 10);
            double r = Math.random();

            if (r > 0.5) {
                // Check if the ship can be placed vertically
                if (a + 2 < 10 && battlefield[a][b] != 'S' && battlefield[a + 1][b] != 'S' && battlefield[a + 2][b] != 'S') {
                    battlefield[a][b] = 'S';
                    battlefield[a + 1][b] = 'S';
                    battlefield[a + 2][b] = 'S';
                    placedShips++;
                }
            } else {
                // Check if the ship can be placed horizontally
                if (b + 2 < 10 && battlefield[b][a] != 'S' && battlefield[b][a + 1] != 'S' && battlefield[b][a + 2] != 'S') {
                    battlefield[b][a] = 'S';
                    battlefield[b][a + 1] = 'S';
                    battlefield[b][a + 2] = 'S';
                    placedShips++;
                }
            }
        }
    }

    static void bomb(int x, int y) {

        if (battlefield[x][y] == 'S') {

            //hit, 9 = win
            battlefield[x][y] = '#';
            hit++;
            System.out.println("It's hit!!");
        } else {
            //missed
            battlefield[x][y] = 'o';
            System.out.println("Missed.");
        }
        Launched++;
        System.out.println("Launched:" + Launched + ", Hit:" + hit);

    }

}
