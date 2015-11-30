package clientServer;

public class Key  { //File name 
	
	//Attributes
	protected static int key;
	
	// Constructor 
	public Key() {
	}
	
	// Constructor to set key 
	public Key(int s) {
		key = s;
	}
	
	// Setter/Getter
	public void setKey(int k) {
		key = k;
	}
	
	public int getKey() {
		return key;
	}
}
