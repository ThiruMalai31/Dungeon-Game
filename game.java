import java.util.Scanner;

public class DungeonGame {
    static int min=Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Row of Dungeon");
        int row=scanner.nextInt();

        System.out.println("Enter Column of Dungeoon");
        int column=scanner.nextInt();

        char [][] dungeon= new char[row][column];

        System.out.println("Enter row for the position of Adventure ");
        int advRow=scanner.nextInt();

        System.out.println("Enter column for the position of Adventure ");
        int advCol=scanner.nextInt();

        dungeon[advRow-1][advCol-1]='A';

        System.out.println("Enter row for the position of Gold ");
        int goldRow=scanner.nextInt();

        System.out.println("Enter column for the position of Gold ");
        int goldCol=scanner.nextInt();

        System.out.println("Enter row for the Monster Position");
        int monsterRow= scanner.nextInt();

        System.out.println("Enter column for the Monster Position");
        int monsterCol= scanner.nextInt();

        dungeon[goldRow-1][goldCol-1]='G';

        boolean[][] visited=new boolean[row][column];
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                visited[i][j]=false;
            }
        }
        findMinPath(dungeon,advRow-1,advCol-1,goldRow-1,goldCol-1,0,visited);

        if( (goldRow<monsterRow && monsterRow<advRow) || (goldCol>monsterCol && monsterCol>advCol) ){
            System.out.println("Not Possible");
        }
        else {
            System.out.println(min);
        }
    }

    public static boolean findMinPath(char[][] dungeon, int advRow, int advCol, int goldRow, int goldCol, int count, boolean[][] visited){

//        System.out.println(advRow+" "+advCol);
        if(advRow==-1 || advRow==dungeon.length || advCol==-1 || advCol==dungeon[0].length){
            return false;
        }

        if(advRow==goldRow && advCol==goldCol){
            if(count<min){
                min=count;
            }
            return true;
        }

        if(advCol< dungeon.length && !visited[advRow][advCol]) {
            if (findMinPath(dungeon, advRow , advCol+1, goldRow, goldCol, count + 1,visited)) {
                visited[advRow][advCol]=true;
                return true;
            }
            visited[advRow][advCol]=false;
        }
        if(advRow>=0 && !visited[advRow][advCol]){
            if (findMinPath(dungeon, advRow - 1, advCol, goldRow, goldCol, count + 1,visited)) {
                visited[advRow][advCol]=true;
                return true;
            }
        }

        if(advCol>=0 && !visited[advRow][advCol]){
            if (findMinPath(dungeon, advRow , advCol-1, goldRow, goldCol, count + 1,visited)) {
                visited[advRow][advCol]=true;
                return true;
            }
            visited[advRow][advCol]=false;
        }

        if(advRow< dungeon.length && !visited[advRow][advCol]) {
            if (findMinPath(dungeon, advRow + 1, advCol, goldRow, goldCol, count + 1,visited)) {
                visited[advRow][advCol]=true;
                return true;
            }
            visited[advRow][advCol]=false;
        }


        return false;
    }
}
