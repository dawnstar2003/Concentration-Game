package MatchUpGame;

import java.util.*;

public class MatchTwoCards 
{

	  public static void printBoard(char board[][])
	  {
		  System.out.println("      0    1    2    3    4    5    6    7    8    9");
			System.out.print("   ---------------------------------------------------");
			System.out.println();
			for(int i = 0; i <  10; i++)
			{
				for(int j = 0; j < 10; j++)
				{
					if(j == 0)
						System.out.print(" "+i+" |");
					
						System.out.print("  "+board[i][j]+" |");
				}
				System.out.println();
				System.out.print("   ---------------------------------------------------");
				System.out.println();
			}
	  }
	  
	public static void placeElement(char element,char[][] board)
	{
		Random random = new Random();
		int row = random.nextInt(10);
		int col = random.nextInt(10);	
		
	    while(board[row][col] != '\u0000')
	    {
	    	row = random.nextInt(10);
			col = random.nextInt(10);
	    }
	    
	    board[row][col] = element;
	}
	
	public static void createBoard(char board[][])
	{
		char element = '0';
		int filled = 0;
		
		while(filled < 50)
		{
		    placeElement(element, board);
		    placeElement(element, board);
		    element++;
		    if(element == ':') element += 2;
		    else if(element == '^') element = '!';
		    filled++;
		}		
	}
	
	 public static boolean checkIfOpen(List<List<Integer>> opened,int i,int j)
	  {
		  for (List<Integer> innerList : opened)
		  {
	        if(i == innerList.get(0) && j == innerList.get(1))
	        	return true;
	      }
		  return false;
	  }
	 
	  public static void printOpenedCell(char board[][], List<List<Integer>> opened, List<List<Integer>> openedButWrong)
	  {
		  System.out.println("      0    1    2    3    4    5    6    7    8    9");
			System.out.print("   ---------------------------------------------------");
			System.out.println();
			for(int i = 0; i <  10; i++)
			{
				for(int j = 0; j < 10; j++)
				{
					if(j == 0)
						System.out.print(" "+i+" |");
					
					boolean isOpened = checkIfOpen(opened,i,j);
					boolean isInotherList = checkIfOpen(openedButWrong, i, j);
					
					if(isOpened || isInotherList) 
							System.out.print("  " + board[i][j] + " |");
					
					else
						System.out.print("    |");	
				}
				System.out.println();
				System.out.print("   ---------------------------------------------------");
				System.out.println();
			}
	  }
	  
	  public static String displayMessage()
	  {
		  Random random = new Random();
		  
		  int num = random.nextInt(7);
		  String msg = "";
		  
		  switch(num)
		  {
		    case 0:
		    	msg += "Congratulations! You found a match! 🎉";
		    	break;
		    case 1:
		    	msg += "Well done! You've matched a pair! 👍";
		    	break;
		    case 2:
		    	msg += "Great job! You've successfully paired two cards! 🌟";
		    	break;
		    case 3:
		    	msg += "Excellent! You've made a match! 🥳";
		    	break;
		    case 4:
		    	msg += "Hooray! You've found a matching pair! 🎊";
		    	break;
		    case 5:
		    	msg += "Nicely done! Match found! 🙌";
		    	break;
		    case 6:
		    	msg += "You're on fire! Another pair matched! 🔥";
		    	break;		    	
		  }
		  return msg;
	  }
	  
	  public static void playGame(char[][] board)
	  {
		  Scanner sc = new Scanner(System.in);
		  int moves = 0;
		  int cntOfMoves = 0;
		 // int wrongMoveLimit = 50;
		  
		  List<List<Integer>> openedCell = new ArrayList<>();
		  List<List<Integer>> openedButWrong = new ArrayList<>();
		  
		  while(moves < 50)
		  {
			  System.out.println("Enter the first card Row : ");
			  int fRow = sc.nextInt();
			  System.out.println("Enter the first card Col : ");
			  int fCol = sc.nextInt();
			  
			  boolean isOpened = checkIfOpen(openedCell, fRow, fCol);
			 
			  
			  while(isOpened || fRow < 0 || fRow > 5 || fCol < 0 || fCol > 5)
			  {
				  System.out.println("Already opened, try again ");
				  System.out.println("Enter the first card Row");
				  fRow = sc.nextInt();
				  System.out.println("Enter the first card Col : ");
				  fCol = sc.nextInt();
				  
				  isOpened = checkIfOpen(openedCell, fRow, fCol);
			  }
			  openedButWrong.add(List.of(fRow, fCol));
			  printOpenedCell(board, openedCell, openedButWrong);
			  
			  System.out.println("Enter the similar card Row : ");
			  int sRow = sc.nextInt();
			  System.out.println("Enter the similar card Col : ");
			  int sCol = sc.nextInt();
			  
              isOpened = checkIfOpen(openedCell, sRow, sCol);
			  
			  while(isOpened || sRow < 0 || sRow > 5 || sCol < 0 || sCol > 5)
			  {
				  System.out.println("Already opened, try again ");
				  System.out.println("Enter the Similar card Row");
				  sRow = sc.nextInt();
				  System.out.println("Enter the Similar card Col : ");
				  sCol = sc.nextInt();
				  
				  isOpened = checkIfOpen(openedCell, sRow, sCol);
			  }
			  openedButWrong.add(List.of(sRow, sCol));
			  printOpenedCell(board, openedCell, openedButWrong);
			  
			  if(board[fRow][fCol] == board[sRow][sCol])
			  {
				  openedCell.add(List.of(fRow, fCol));
				  openedCell.add(List.of(sRow, sCol));
				  moves++;
				  System.out.println(displayMessage());	 
			  }
			  cntOfMoves++;
			  openedButWrong.clear();  
		  }
		 
		  System.out.println("Congratulations! you cracked it in "+cntOfMoves);
	  }
	  
	public static void main(String args[])
	{
		
		char board[][] = new char[10][10];
		createBoard(board);
		System.out.println("Welcome to Memory Match !");
		System.out.println("Ready to test your memory? Flip cards, find Pairs and Win !");
		printBoard(board);
	    playGame(board);
	}

}
