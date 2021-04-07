
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class BarrenLand {
    private static int[][] land = new int[400][600];
    private static int[][] visited = new int[400][600];

    private static ArrayList<Integer> landAreas = new ArrayList<Integer>();

    public static String barrenLandAnalysis(String[] barrens){
        fillOutBarrens(barrens);
        calculateLands();
        Collections.sort(landAreas);
        String result = "";
        for(int i = 0 ; i < landAreas.size(); i++){
            if(i == landAreas.size() -1 ){
                result = result + String.valueOf(landAreas.get(i));
            }else{
            result = result + String.valueOf(landAreas.get(i)) + " ";}
        }
        return result;
    }

    private static void calculateLands(){
        for(int i = 0; i < land.length; i++){
            for(int j = 0; j < land[i].length;j++){
                if(visited[i][j] == 0){
                    calculateLandHelper(i,j);
                }
            }
        }
    }
    private static void calculateLandHelper(int x, int y){
        if(land[x][y] == 1){
            visited[x][y] = 1;
            return;
        }
        visited[x][y] = 1;
        int area = 0;
        Coordinate origin = new Coordinate(x,y);
        LinkedList<Coordinate> queue = new LinkedList<Coordinate>();
        queue.add(origin);
        while(queue.size() != 0){
            Coordinate c = queue.poll();
            int xPoint = c.getX();
            int yPoint = c.getY();
            if(land[xPoint][yPoint] == 0){
                area = area + 1;
                for(int i = xPoint -1; i <= xPoint + 1; i++){
                    for(int j = yPoint - 1; j <= yPoint + 1; j++){
                        if(isValidPoint(i,j) && visited[i][j] == 0){
                            if(land[i][j] == 1){
                                visited[i][j] = 1;
                            }else{
                                Coordinate newCoordinate = new Coordinate(i,j);
                                queue.add(newCoordinate);
                                visited[i][j] = 1;
                            }
                        }
                    }
                }
            }
        }
        landAreas.add(area);

    }


    public static void fillOutBarrens(String[] barrens){
        for (int i = 0 ; i < barrens.length; i++){
            fillOutBarrenHelper(barrens[i]);
        }
    }

    private static void fillOutBarrenHelper(String barren){
        String[] coordinates = barren.split(" ");
        int x1 = Integer.valueOf(coordinates[0]);
        int y1 = Integer.valueOf(coordinates[1]);
        int x2 = Integer.valueOf(coordinates[2]);
        int y2 = Integer.valueOf(coordinates[3]);

        for (int i = x1; i <= x2; i++){
            for(int j = y1; j <= y2; j++){
                land[i][j] = 1;
            }
        }
    }
    private static boolean isValidPoint(int x, int y){
        if (x < 0 || y < 0 || x >= land.length || y >= land[0].length) {
            return false;
        }
        return true;
    }
    static char countChar[] = new char[256];
    static void countChar(String str){
        for(int i = 0 ; i < str.length(); i++){
            countChar[Character.toLowerCase(str.charAt(i))]++;
        }
    }
    public static String firstNonRepeatingLetter( String str ) {
        countChar(str);
        int temp = 1;
        for(int i = 0 ; i < str.length(); i++){
            Character c = Character.toLowerCase(str.charAt(i));
            if(countChar[c] == 1){
                temp = -1;
                break;
            }
        }
        if (temp == 1) return "";
        int index = -1;
        for(int i = 0 ; i < str.length(); i++){
            if(countChar[Character.toLowerCase(str.charAt(i))] == 1){
                index = i;
                break;
            }
        }

        return String.valueOf(str.charAt(index));
    }

    public static void main(String[] args){
        String[] STDIN  = {};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter the rectangular coordinates");
            STDIN = (br.readLine()).split(",");
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        System.out.println(barrenLandAnalysis(STDIN));
    }
}
