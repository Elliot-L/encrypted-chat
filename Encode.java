package clientServer;

public class Encode extends Key { // File name

	// Attributes
	// Upper case alphabet array
	protected static final char[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H','I','J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 
			'T', 'U', 'V','W', 'X', 'Y', 'Z' };

	// Lower case alphabet array 
	protected static final char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i','j','k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w','x', 'y', 'z' };

	// Array to hold normal numbers 
	protected static char[] numbers = { '0', '1', '2', '3', '4', '5', '6',
		    '7','8', '9' }; 

	// Array for upper case encrypt and decrypt
	protected static char[] ENCRYPT = new char[26];
	protected static char[] DECRYPT = new char[26];

	// Array for lower case encrypt and decrypt
	protected static char[] encrypt = new char[26];
	protected static char[] decrypt = new char[26];

	// Array for encrypt and decrypt of numbers
	protected static char[] encryptedNumbers = new char[10];
	protected static char[] decryptedNumbers = new char[10];


	// Key object
	protected static Key key;

	// No-arguments Constructor
	public Encode() {
		key = new Key(); // C

		// Sets first letter to variable 'k'
		key.setKey(8975);

		// For loop to set the ENCRYPT and DECRYPT char arrays
		for (int i = 0; i < 26; i++)
			// Adds the key to the index i and uses modulus 26 so it wont go out of bounds
			ENCRYPT[i] = ALPHABET[(i + key.getKey()) % 26];
		for (int i = 0; i < 26; i++)
			DECRYPT[ENCRYPT[i] - 'A'] = ALPHABET[i];

		// For loop to set the encrypt and decrypt char arrays
		for (int i = 0; i < 26; i++)
			// Adds the key to the index i and uses modulus 26 so it wont go out of bounds
			encrypt[i] = alphabet[(i + key.getKey()) % 26];
		for (int i = 0; i < 26; i++)
			decrypt[encrypt[i] - 'a'] = alphabet[i];

		// For loop to set the encrypt and decrypt numbers
		for (int i = 0; i < 10; i++)
			// Adds the key to the index i and uses modulus 10 so it wont go out of bounds
			encryptedNumbers[i] = numbers[(i + key.getKey()) % 10];
		for (int i = 0; i < 10; i++)
			decryptedNumbers[Character.getNumericValue(encryptedNumbers[i])] = numbers[i];
	}
	
	// constructor with key setter
	public Encode(String s) {
		key = new Key(s.charAt(0));
	}

	// Encryption method
	public String encrypt(String message) {
        
		// Creates new char array to store message
		char[] secret = new char[message.length()];
		
		// Stores every character of message to secret array
		for (int i = 0; i < message.length(); i++)
			secret[i] = (char) message.charAt(i);
    	
    	// For loop to change secret array and encrypt it
    	for(int i = 0; i < secret.length; i ++) { 
    		
    		//If letter is capital
    		if(Character.isUpperCase(secret[i]))
    			// Stores secret at [i] from ENCRYPT char array
    			secret[i] = ENCRYPT[secret[i] - 'A'];
    		
    		// If letter is lower case
    		else if(Character.isLowerCase(secret[i])) 
    			//Stores secret at [i] from encrypt char array
    			secret[i] = encrypt[secret[i] - 97];
    		
    		//If the char is a number
    		else if(Character.isDigit(secret[i]) == true) 
    			// Stores secret at [i] from encrypted number array
    			secret[i] = encryptedNumbers[Character.getNumericValue(secret[i])];
    		
    		// If the char is punctuation
    		 else if(!Character.isSpaceChar(secret[i])) {
    			// Sets user punctuation to array
    			secret[i]  = secret[i]; // Sets secret at [i] to same punctuation 
    		}	
    		
    		else // If index is a space
    			secret[i] = ' '; //Sets secret array at [i] to a space
    	}
    	//Creates a new string to return
    	String result = new String(secret);
	
    	return result; //Returns the encrypted message
    }

	// Decryption method
	public String decrypt(String message) {
        
		// Creates new char array to store message
		char[] secret = new char[message.length()];
		
		// Stores every character of message to secret array
		for (int i = 0; i < message.length(); i++)
			secret[i] = (char) message.charAt(i);

		// For loop to change secret array
		for (int i = 0; i < secret.length; i++) {

			// If letter is capital
			if (Character.isUpperCase(secret[i]))
				// Stores secret at [i] from ENCRYPT char array
				secret[i] = DECRYPT[secret[i] - 'A'];

			// If letter is lower case
			else if (Character.isLowerCase(secret[i]))
				// Stores secret at [i] from encrypt char array
				secret[i] = decrypt[secret[i] - 97];

			// If the char is a number
			else if (Character.isDigit(secret[i]))
				// Sets secret at [i] to 
				secret[i] = decryptedNumbers[Character
						.getNumericValue(secret[i])];

			// If the char is punctuation
			else if (!Character.isSpaceChar(secret[i]))
				// Puts user punctuation back in
				secret[i] = secret[i];

			else
				// If index is a space
				secret[i] = ' '; // Sets to space
		}

		// Creates a new string to return
		String result = new String(secret);

		return result; // Returns the decrypted message
	}

}
