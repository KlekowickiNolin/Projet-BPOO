package hearthstone.commun;

public class HearthstoneException extends RuntimeException {
  String message;

public HearthstoneException(String message) {
	super();
	this.message = message;
}

@Override
public String toString() {
	return message;
}
  

  
}
