import java.util.Scanner;
import java.util.Iterator;

public class HumanPlayer implements Player {
    protected char player;
    
    public HumanPlayer(char m) {
	this.player = m; 
    }

    /** Returns this player's symbol */
    public char getSymbol() {return player;}
    
    public Player play(GameTree node, Player opponent) {

	// Print the current board state
	System.out.println(node.getBoard());
	
	// Scenario analysis: win, loss, or neither?
	if (node.getBoard().win(this.player)) {
	    // The game is won, notify the other player and celebrate!
	    System.out.println("You have won!");
	    return this;
	} else if (node.getChildren().isEmpty()) {
	    // The game is lost, admit defeat
	    System.out.println("You have lost");
	    return opponent;
	} else {
            // Present all possible moves
            Iterator iter = node.getBoard().moves(player).iterator();
            int j = 0;
	    while (iter.hasNext())
	    {
		System.out.println(j+". "+iter.next());
		j++;
	    }

            // Listen for a selection
            Scanner scan = new Scanner(System.in);
	    int i = scan.nextInt();
	    
	    while (true) {
		if(i >= 0 && i < node.getChildren().size()){
		    node = node.getChildren().get(i);
		    break;
		} else {
		    System.out.println("Nope! Try again.");
                    i = scan.nextInt();
		}
	    }

	    // Print out the new board
	    System.out.println(node.getBoard());
	    return opponent.play(node, this);
	}
    }	
}
