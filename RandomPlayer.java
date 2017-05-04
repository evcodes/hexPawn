public class RandomPlayer implements Player {
    protected char player;
    
    public RandomPlayer(char m) {
	this.player = m; 
    }

    public char getSymbol() {
	return player;
    }
    
    public Player play(GameTree node, Player opponent) {
	
	// Is this a losing scenario?
	if (node.getBoard().win(this.player)) {
	    // The game is won, notify the other player and celebrate!
	    System.out.println("The computer has won!");
	    return this;
	} else if (node.getChildren().isEmpty()) {
	    // The game is lost, admit defeat
	    System.out.println("You have lost");
	    return opponent;
	} else {
	    // Make a move
	    node = node.makeAMove();
	    return opponent.play(node, this);
	}
    }
}