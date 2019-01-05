package checkers;

import java.util.Scanner;

public class main {
	private static int[][] checkerboard = new int[8][8];
	private static int turn = 1;
	private static boolean player2loss = true;
	private static boolean player1loss = true;
	private static boolean win = false;
	public static int errorCheck(int min,int max){
		
		int choice = 0;
		boolean passed = true;
		Scanner input = new Scanner(System.in);
		do
		{
			passed = true;
			try
			{
				choice = input.nextInt();
			}
			catch(Exception error)
			{
				System.out.println("Invalid input, select a value between " + min + " and " + max);
				input.nextLine();
				passed = false;
			}
			if(passed == true && (choice<min || choice>max))
			{
				System.out.println("Please enter a number within the range");
				passed = false;
			}
		}while(passed == false);
		return choice;
	}
	public static void populateArray(){
		for(int x = 0; x<8; x++){
			if(x != 3 || x!= 4)
			{
				if(x%2 == 0 && x!=3 && x!= 4)
				{
					for(int y = 0; y<8; y++)
					{
						if(y%2 == 1)
						{
							if(x<3)
								checkerboard[x][y] = 1;		
							else
								checkerboard[x][y] = 2;
						}
					}
				}
				else if(x != 3 && x!= 4){
					for(int y=0; y<8; y++)
					{
						if(y%2 == 0)
						{
							if(x<3)
								checkerboard[x][y] = 1;
							else
								checkerboard[x][y] = 2;
						}
					}
				}
			}
		}
	}
	public static void displayArray(){
		for(int x = 0; x < 8; x++)
		{		
			System.out.println(" =================================");
			for(int y = 0; y < 8; y++)
				System.out.print(" | " + checkerboard[x][y]);
			System.out.println(" |");
		}
		System.out.println(" =================================");
	}
	public static void space(){
		for(int x =0; x<80; x++)
			System.out.println();
	}
	public static void playerTurn(){
		int rowCurrent, columnCurrent, rowNew, columnNew, piece1, piece2, piece3, piece4;
		System.out.println(turn);
		if(turn%2== 1)
		{
			piece1 = 1;
			piece2 = 3;
			piece3 = 2;
			piece4 = 4;
		}
		else
		{
			piece1 = 2;
			piece2 = 4;
			piece3 = 1;
			piece4 = 3;
		}
		boolean goodMove = false;
		boolean tryAgain = true;
		boolean jumpAvailable = false;
		if(turn%2 == 0)
			System.out.println("Player 2's turn");
		else
			System.out.println("Player 1's turn");
		for(int x = 0; x<8; x++)
		{
			for(int y = 0; y<8; y++)
			{
				if(checkerboard[x][y] == piece1 || checkerboard[x][y] == piece2)
					if(x+2<8 && y+2<8 && checkerboard[x][y] == piece2 && turn%2==0||x+2<8 && y+2 < 8 && turn%2==1)
					{
						if(checkerboard[x+2][y+2] == 0 && checkerboard[x+1][y+1] == piece3 || checkerboard[x+1][y+1] == piece4)
						{
							jumpAvailable = true;						
						}

					}
					if(x+2<8 && y-2 > -1 && checkerboard[x][y] == piece2 && turn%2==0||x+2<8 && y-2 > -1 && turn%2==1)
					{
						if(checkerboard[x+2][y-2] == 0 && checkerboard[x+1][y-1] == piece3 || checkerboard[x+1][y-1] == piece4)
						{
							jumpAvailable = true;						
						}
					}					
					if(x-2 > -1 && y+2<8 && checkerboard[x][y] == piece2 && turn%2 == 1 ||x-2 > -1 && y+2<8 && turn%2==0)
					{
						if(checkerboard[x-2][y+2] == 0 && checkerboard[x-1][y+1] == piece3 || checkerboard[x-1][y+1] == piece4)
						{
							jumpAvailable = true;							
						}
					}					
					if(x-2 > -1 && y-2 > -1 && checkerboard[x][y] == piece2 && turn%2 == 1 ||x-2 > -1 && y-2 >-1 && turn%2==0)
					{
						if(checkerboard[x-2][y-2] == 0 && checkerboard[x-1][y-1] == piece3 || checkerboard[x-1][y-1] == piece4)
						{
							jumpAvailable = true;						
						}
					}
			}
		}
		do{
			System.out.println("Choose piece you wish to move");
			System.out.println("Row:");
			rowCurrent = errorCheck(1,8)-1; 
			System.out.println("Column:");
			columnCurrent = errorCheck(1,8)-1;
			if(turn%2 == 0 && checkerboard[rowCurrent][columnCurrent] != 2 && checkerboard[rowCurrent][columnCurrent] != 4)
				System.out.println("Please choose a piece that is yours");
			else if(turn%2 == 1 && checkerboard[rowCurrent][columnCurrent] != 1 && checkerboard[rowCurrent][columnCurrent] != 3)
				System.out.println("Please choose a piece that is yours");
			else if(jumpAvailable == true )
			{
				if(rowCurrent+2<8 && columnCurrent+2<8 && checkerboard[rowCurrent][columnCurrent] == piece2 && turn%2==0||rowCurrent+2<8 && columnCurrent+2 < 8 && turn%2==1)
				{
					if(checkerboard[rowCurrent+2][columnCurrent+2] == 0 && checkerboard[rowCurrent+1][columnCurrent+1] == piece3 || checkerboard[rowCurrent+1][columnCurrent+1] == piece4)
						tryAgain = false;
				}
				if(rowCurrent+2<8 && columnCurrent-2 > -1 && checkerboard[rowCurrent][columnCurrent] == piece2 && turn%2==0||rowCurrent+2<8 && columnCurrent-2 > -1 && turn%2==1)
				{
					if(checkerboard[rowCurrent+2][columnCurrent-2] == 0 && checkerboard[rowCurrent+1][columnCurrent-1] == piece3 || checkerboard[rowCurrent+1][columnCurrent-1] == piece4)
						tryAgain = false;
				}					
				if(rowCurrent-2>-1 && columnCurrent+2<8 && checkerboard[rowCurrent][columnCurrent] == piece2 && turn%2==1||rowCurrent-2>-1 && columnCurrent+2 < 8 && turn%2==0)
				{
					if(checkerboard[rowCurrent-2][columnCurrent+2] == 0 && checkerboard[rowCurrent-1][columnCurrent+1] == piece3 || checkerboard[rowCurrent-1][columnCurrent+1] == piece4)
						tryAgain = false;
				}					
				if(rowCurrent-2>-1 && columnCurrent-2>-1 && checkerboard[rowCurrent][columnCurrent] == piece2 && turn%2==1||rowCurrent-2>-1 && columnCurrent-2 >-1 && turn%2==0)
				{
					if(checkerboard[rowCurrent-2][columnCurrent-2] == 0 && checkerboard[rowCurrent-1][columnCurrent-1] == piece3 || checkerboard[rowCurrent-1][columnCurrent-1] == piece4)
						tryAgain = false;							
				}
				if(tryAgain == true)
					System.out.println("A jump is available for one of your pieces, it must be taken");
			}
			else
				tryAgain = false;
		}while(tryAgain == true);
		do{
			int numberHops = 0;
			System.out.println("Where would you like to move this piece? (" +(rowCurrent+1)+ "," +(columnCurrent+1) + ")");
			System.out.println("Row:");
			rowNew = errorCheck(1,8)-1;
			System.out.println("Column:");
			columnNew = errorCheck(1,8)-1;
			if(Math.abs(rowCurrent-rowNew) == 2 && Math.abs(columnCurrent-columnNew) == 2)
			{
				if(checkerboard[rowNew][columnNew] == 0 && checkerboard[(rowCurrent+rowNew)/2][(columnCurrent+columnNew)/2] == piece3 || checkerboard[(rowCurrent+rowNew)/2][(columnCurrent+columnNew)/2] == piece4)
				{
					boolean moveAgain = false;
					checkerboard[(rowCurrent+rowNew)/2][(columnCurrent+columnNew)/2] = 0;
					checkerboard[rowNew][columnNew] = checkerboard[rowCurrent][columnCurrent];
					checkerboard[rowCurrent][columnCurrent] = 0;
					space();
					displayArray();
					if(rowCurrent+2<8 && columnCurrent+2<8 && checkerboard[rowCurrent][columnCurrent] == piece2 && turn%2==0||rowCurrent+2<8 && columnCurrent+2 < 8 && turn%2==1)
					{
						if(checkerboard[rowCurrent+2][columnCurrent+2] == 0 && checkerboard[rowCurrent+1][columnCurrent+1] == piece3 || checkerboard[rowCurrent+1][columnCurrent+1] == piece4)
							moveAgain = true;
					}
					if(rowCurrent+2<8 && columnCurrent-2 > -1 && checkerboard[rowCurrent][columnCurrent] == piece2 && turn%2==0||rowCurrent+2<8 && columnCurrent-2 > -1 && turn%2==1)
					{
						if(checkerboard[rowCurrent+2][columnCurrent-2] == 0 && checkerboard[rowCurrent+1][columnCurrent-1] == piece3 || checkerboard[rowCurrent+1][columnCurrent-1] == piece4)
							moveAgain = true;
					}					
					if(rowCurrent-2>-1 && columnCurrent+2<8 && checkerboard[rowCurrent][columnCurrent] == piece2 && turn%2==1||rowCurrent-2>-1 && columnCurrent+2 < 8 && turn%2==0)
					{
						if(checkerboard[rowCurrent-2][columnCurrent+2] == 0 && checkerboard[rowCurrent-1][columnCurrent+1] == piece3 || checkerboard[rowCurrent-1][columnCurrent+1] == piece4)
							moveAgain = true;
					}					
					if(rowCurrent-2>-1 && columnCurrent-2>-1 && checkerboard[rowCurrent][columnCurrent] == piece2 && turn%2==1||rowCurrent-2>-1 && columnCurrent-2 >-1 && turn%2==0)
					{
						if(checkerboard[rowCurrent-2][columnCurrent-2] == 0 && checkerboard[rowCurrent-1][columnCurrent-1] == piece3 || checkerboard[rowCurrent-1][columnCurrent-1] == piece4)
							moveAgain = true;							
					}
					if(moveAgain == true)
					{
						System.out.println("Hop again");
							rowCurrent = rowNew;
							columnCurrent = columnNew;
					}
					else
						goodMove = true;
				}
				else
				{
					space();
					displayArray();
					System.out.println("You cannot move there");					
				}
			}
			else if(rowNew == rowCurrent || columnNew == columnCurrent || checkerboard[rowNew][columnNew] != 0 || Math.abs(rowNew-rowCurrent) != 1 || Math.abs(columnNew-columnCurrent) != 1)
			{	
				space();
				displayArray();
				if(checkerboard[rowNew][columnNew] != 0)
					System.out.println("There is already a piece there");
				else
					System.out.println("You cannot move here");
			}
			else if(turn%2 == 1 && rowNew < rowCurrent && checkerboard[rowCurrent][columnCurrent] == 1 || turn%2 == 0 && rowNew > rowCurrent && checkerboard[rowCurrent][columnCurrent] == 2)
			{
				space();
				displayArray();
				System.out.println("This piece cannot move back");
			}	
			else if(numberHops == 0)
			{
				System.out.println(checkerboard[rowCurrent][columnCurrent]);
				checkerboard[rowNew][columnNew] = checkerboard[rowCurrent][columnCurrent];
				checkerboard[rowCurrent][columnCurrent] = 0;
				goodMove = true;
				if(rowNew == 7 && checkerboard[rowNew][columnNew] == 1)
					checkerboard[rowNew][columnNew] = 3;
				if(rowNew == 0 && checkerboard[rowNew][columnNew] == 2)
					checkerboard[rowNew][columnNew] = 4;
				space();
				displayArray();
				for(int x = 0; x <8; x++)
				{
					for(int y = 0; y<8; y++)
					{
						if(checkerboard[x][y] == 1||checkerboard[x][y] ==3)
							player1loss=false;
						if(checkerboard[x][y] == 2 || checkerboard[x][y] == 4)
							player2loss=false;
					}
				}
				if(player2loss == true || player1loss == true)
					win = true;
					
			}
			else
				System.out.println("You can only continue if you can hop again");
		}while(goodMove == false);
		turn++;
	}
	public static void rules(){
		populateArray();
		displayArray();
		System.out.println("This is the checkerboard, rows are read from top to bottom going from 1-8, columns are read from left to right");
		System.out.println("In checkers, pieces may only move diagonally");
		System.out.println("Pieces may only move forwards unless they are kings");
		System.out.println("You kill other pieces by jumping over them diagonally, if you can jump, you are forced to jump");
		System.out.println("There are combo jumps where pieces jump multiple times in one turn and these combo jumps must be taken if available");
		System.out.println("Once a piece reaches the other side, it becomes a king and can move backwards");
		System.out.println("You win once all the enemies pieces are dead");
		
	}
	public static void main(String[] args) {
		
		boolean playAgain = true;
		int player1win = 0, player2win = 0;
		do{
			System.out.println();
			System.out.println("Welcome to Checkers!");
			System.out.println("===================");
			System.out.println("1. Rules");
			System.out.println("2. Play Game");
			System.out.println("0. Exit Game");
			System.out.println("===================");	
			int menuChoice = errorCheck(0,3);
			space();
			if(menuChoice == 1){
				rules();
			}
			if(menuChoice ==2){
				populateArray();
				displayArray();
				
				System.out.println("Player 1 has the pieces numbered 1 and 3, 3 being a piece that can move backwards");
				System.out.println("Player 2 has the pieces numbered 2 and 4, 4 being a piece that can move backwards");
				System.out.println("The coordinates of the piece you wish to move rows ascending from 1-8 from up to down, columns read from left to right.");
				do{
					playerTurn();	
				}while(win == false);
				space();
				if(player1loss == true)
				{
					System.out.println("Player 2 wins!");	
					player2win++;
				}
				else
				{
					System.out.println("Player 1 wins!");
					player1win++;
				}
				System.out.println("======================");
				System.out.println("Player 1 wins: " +player1win);
				System.out.println("Player 2 wins: " +player2win);
				System.out.println("======================");
				System.out.println("Play again?");
				System.out.println("1. Yes");
				System.out.println("0. Exit");
				int playAgainChoice = errorCheck(0,1);
				if(playAgainChoice == 0)
					playAgain = false;
			}
		}while(playAgain == true);
	}
}
