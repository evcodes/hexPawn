public class ComputerPlayer implements Player {

    protected char player;
    
    public ComputerPlayer(char m) {
	this.player = m; 
    }

    public char getSymbol() {
	return player;
    }
    
    public Player play(GameTree node, Player opponent) {
	
	// Scenario analysis: win, loss, or neither?
	if (node.getBoard().win(this.player)) {
	    // The game is won, notify the other player and celebrate!
	    System.out.println("The computer has won!");
	    return this;
	} else if (node.getChildren().isEmpty()) {
	    // The game is lost, admit defeat
	    node.removeParent();
	    System.out.println("You have lost");
	    return opponent;
	} else {
	    // Make a move
	    node = node.makeAMove();
	    return opponent.play(node, this);
	}
    }
}
