import java.util.Scanner;

public class HumanPlayer implements Player {
    protected char player;
    
    public HumanPlayer(char m) {
	this.player = m; 
    }

    public char getSymbol() {
	return player;
    }
    
    public Player play(GameTree node, Player opponent) {
	
	// Is this a losing scenario?
	if (node.getBoard().win(this.player)) {
	    // The game is won, notify the other player and celebrate!
	    System.out.println("You have won!");
	    return this;
	} else if (node.getChildren().isEmpty()) {
	    // The game is lost, admit defeat
	    System.out.println("You have lost");
	    return opponent;
	} else {
	    // Make a move
	    Scanner scan = new Scanner(System.in);   
	    int counter = 1;
	    
	    for (GameTree gt : node.getChildren()) {
		System.out.println(counter + ". " + gt.getBoard());
		++counter;
	    }
	    
	    int i = scan.nextInt();
	    
	    while (true) {
		if(i > 0 && i <= node.getChildren().size()){
		    node = node.getChildren().get(i-1);
		    break;
		} else {
		    System.out.println("Nope! Try again.");
		}
	    }
	    
	    return opponent.play(node, this);
	}
    }	
}