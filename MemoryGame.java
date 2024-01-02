package MatchUpGame;

import java.util.*;

public class MemoryGame 
{	
	//place all card in the cell
	public static void placeElement(char element,char[][] board)
	{
		Random random = new Random();
		int row = random.nextInt(6);
		int col = random.nextInt(6);	
		
	    while(board[row][col] != '\u0000')
	    {
	    	row = random.nextInt(6);
			col = random.nextInt(6);
	    }
	    
	    board[row][col] = element;
	}

	//Initially creating the board
	public static void createBoard(char board[][])
	{
		char element = '!';
		int filled = 0;
		
		while(filled < 18)
		{
		    placeElement(element, board);
		    placeElement(element, board);
		    element++;
		    if(element == '\'') element = '<';
		    else if(element == 'A') element = 'O';
		    else if(element == 'R') element = 'Z';
		    else if(element ==  '^') element = '{';
		    filled++;
		}		
	}
	
	// check whether the player already opened the card
	public static boolean checkIfOpen(List<List<Integer>> opened,int i,int j)
	  {
		  for (List<Integer> innerList : opened)
		  {
	        if(i == innerList.get(0) && j == innerList.get(1))
	        	return true;
	      }
		  return false;
	  }

	//print the board 
	  public static void printOpenedCell(char board[][], List<List<Integer>> opened, List<List<Integer>> openedButWrong)
	  {
		  System.out.println("      0    1    2    3    4    5");
			System.out.print("   -------------------------------");
			System.out.println();
			for(int i = 0; i <  6; i++)
			{
				for(int j = 0; j < 6; j++)
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
				System.out.print("   -------------------------------");
				System.out.println();
			}
	  }

	//display message on successful flips
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

	//Maintain the flow of the game, such as getting input, checking conditions for similarities 
	  public static void playGame(char[][] board)
	  {
		  Scanner sc = new Scanner(System.in);
		  int moves = 0;
		  int cntOfMoves = 0;
		 // int wrongMoveLimit = 50;
		  
		  List<List<Integer>> openedCell = new ArrayList<>();
		  List<List<Integer>> openedButWrong = new ArrayList<>();
		  
		  printOpenedCell(board, openedCell, openedButWrong);
		  
		  while(moves < 18)
		  {
			  System.out.print("Enter the first card Row : ");
			  int fRow = sc.nextInt();
			  System.out.print("Enter the first card Col : ");
			  int fCol = sc.nextInt();
			  
			  boolean isOpened = checkIfOpen(openedCell, fRow, fCol);
			 
			  
			  while(isOpened || fRow < 0 || fRow > 5 || fCol < 0 || fCol > 5)
			  {
				  System.out.println("Wrong Move Already Opened Cell , Try again ");
				  System.out.print("Enter the first card Row");
				  fRow = sc.nextInt();
				  System.out.print("Enter the first card Col : ");
				  fCol = sc.nextInt();
				  
				  isOpened = checkIfOpen(openedCell, fRow, fCol);
			  }
			  openedButWrong.add(List.of(fRow, fCol));
			  System.out.println();
			  printOpenedCell(board, openedCell, openedButWrong);
			  
			  System.out.print("Enter the similar card Row : ");
			  int sRow = sc.nextInt();
			  System.out.print("Enter the similar card Col : ");
			  int sCol = sc.nextInt();
			  
              isOpened = checkIfOpen(openedCell, sRow, sCol);
			  
			  while(isOpened || sRow < 0 || sRow > 5 || sCol < 0 || sCol > 5 || fRow == sRow && fCol == sCol)
			  {
				  System.out.println("Wrong Move or Already Opened Cell , Try again ");
				  System.out.print("Enter the Similar card Row");
				  sRow = sc.nextInt();
				  System.out.print("Enter the Similar card Col : ");
				  sCol = sc.nextInt();
				  
				  isOpened = checkIfOpen(openedCell, sRow, sCol);
			  }
			  openedButWrong.add(List.of(sRow, sCol));
			  
			  System.out.println();
			  printOpenedCell(board, openedCell, openedButWrong);
			  
			  if(board[fRow][fCol] == board[sRow][sCol])
			  {
				  openedCell.add(List.of(fRow, fCol));
				  openedCell.add(List.of(sRow, sCol));
				  moves++;
				  System.out.println();
				  System.out.println(displayMessage());	 
				  System.out.println();
			  }
			  cntOfMoves++;
			  openedButWrong.clear();  
		  }
		 
		  System.out.println("Congratulations! you cracked it in "+cntOfMoves+" moves...");
	  }

	//starting point of the game
	public static void main(String args[])
	{
		
		char board[][] = new char[10][10];
		createBoard(board);
		System.out.println("Welcome to Memory Match !");
		System.out.println("Ready to test your memory? Flip cards, find Pairs and Win !");
	    playGame(board);
	}

}
