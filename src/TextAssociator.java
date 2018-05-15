import javax.swing.plaf.SeparatorUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/* CS 240 Starter Code
 * @Author Kevin Quinn
 * 
 * TextAssociator represents a collection of associations between words.
 * See write-up for implementation details and hints
 * 
 */

/*
 * Brandon Dixon, TJ Dillon, Sawyere Hendricks
 * 05/15/2018
 * TextAssociator.java
 *
 * A data structure which represents a hash table of WordInfo objects. Word Info objects store a
 * a word and associations to that word so the object can be used for things like thesauruses.
 * The hash table resolves collisions through separate chaining and resizes to prime numbers based
 * on an internal class constant.
 */

public class TextAssociator {
	public static final int[] PRIME_LIST = {13, 29, 61, 127, 257, 509, 1019, 2039, 4079, 8161,
			16183, 32381, 64793, 128347, 256129, 512009, 624829, 1260751};

	private WordInfoSeparateChain[] table;
	private int size;
	private int sizeStep;
	
	/* INNER CLASS
	 * Represents a separate chain in your implementation of your hashing
	 * A WordInfoSeparateChain is a list of WordInfo objects that have all
	 * been hashed to the same index of the TextAssociator
	 */
	private class WordInfoSeparateChain {
		private List<WordInfo> chain;
		
		/* Creates an empty WordInfoSeparateChain without any WordInfo
		 */
		public WordInfoSeparateChain() {
			this.chain = new ArrayList<WordInfo>();
		}
		
		/* Adds a WordInfo object to the SeparateCahin
		 * Returns true if the WordInfo was successfully added, false otherwise
		 */
		public boolean add(WordInfo wi) {
			if (chain.contains(wi)) {
				return false;
			} else {
				chain.add(wi);
				return true;
			}
		}
		
		/* Removes the given WordInfo object from the separate chain
		 * Returns true if the WordInfo was successfully removed, false otherwise
		 */
		public boolean remove(WordInfo wi) {
			if (chain.contains(wi)) {
				chain.remove(wi);
				return true;
			} else {
				return false;
			}
		}
		
		// Returns the size of this separate chain
		public int size() {
			return chain.size();
		}
		
		// Returns the String representation of this separate chain
		public String toString() {
			return chain.toString();
		}
		
		// Returns the list of WordInfo objects in this chain
		public List<WordInfo> getElements() {
			return chain;
		}
	}
	
	
	/* Creates a new TextAssociator without any associations 
	 */
	public TextAssociator() {
		size = 0;
		sizeStep = 0;
		table = new WordInfoSeparateChain[PRIME_LIST[sizeStep]];
	}
	
	
	/* Adds a word with no associations to the TextAssociator 
	 * Returns False if this word is already contained in your TextAssociator ,
	 * Returns True if this word is successfully added
	 */
	public boolean addNewWord(String word) {
		WordInfo hold = new WordInfo(word);
		int spot = hold.hashCode() % table.length;
		if (table[spot] == null) {
			table[spot] = new WordInfoSeparateChain();
			boolean added = table[spot].add(hold);
			if (added) {
				size++;
				if (size == table.length) {
					resize();
				}
			}
			return added;
		} else {
			boolean added = table[spot].add(hold);
			if (added) {
				size++;
				if (size == table.length) {
					resize();
				}
			}
			return added;
		}
	}

	//Resizes the hash table to the next prime number in the PRIME_LIST.
	private void resize() {
		sizeStep++;
		WordInfoSeparateChain[] holdTable = new WordInfoSeparateChain[PRIME_LIST[sizeStep]];
		for (WordInfoSeparateChain i : table) {
			if (i != null) {
				List<WordInfo> iChain = i.getElements();
				for (WordInfo n : iChain) {
					int spot = n.hashCode() % holdTable.length;
					if (holdTable[spot] == null) {
						holdTable[spot] = new WordInfoSeparateChain();
						holdTable[spot].add(n);
					} else {
						holdTable[spot].add(n);
					}
				}
			}
		}
	}
	
	/* Adds an association between the given words. Returns true if association correctly added, 
	 * returns false if first parameter does not already exist in the SpellChecker or if 
	 * the association between the two words already exists
	 */
	public boolean addAssociation(String word, String association) {
		WordInfo hold = new WordInfo(word);
		if (table[hold.hashCode() % table.length] !=null) {
			List<WordInfo> wordChain = table[hold.hashCode() % table.length].getElements();
			if (wordChain.contains(hold) && !(wordChain.get(wordChain.indexOf(hold)).getAssociations()
					.contains(association))) {
				wordChain.get(wordChain.indexOf(hold)).addAssociation(association);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	/* Remove the given word from the TextAssociator, returns false if word 
	 * was not contained, returns true if the word was successfully removed.
	 * Note that only a source word can be removed by this method, not an association.
	 */
	public boolean remove(String word) {
		WordInfo hold = new WordInfo(word);
		boolean removed = table[hold.hashCode() % table.length].remove(hold);
		if (removed) {
			size--;
		}
		return removed;
	}
	
	
	/* Returns a set of all the words associated with the given String  
	 * Returns null if the given String does not exist in the TextAssociator
	 */
	public Set<String> getAssociations(String word) {
	    WordInfo hold = new WordInfo(word);
	    if (table[hold.hashCode() % table.length] != null) {
			List<WordInfo> wordChain = table[hold.hashCode() % table.length].getElements();
			if (wordChain.contains(hold)) {
				return wordChain.get(wordChain.indexOf(hold)).getAssociations();
			} else {
				return null;
			}
		} else {
	    	return null;
		}
	}
	
	
	/* Prints the current associations between words being stored
	 * to System.out
	 */
	public void prettyPrint() {
		System.out.println("Current number of elements : " + size);
		System.out.println("Current table size: " + table.length);
		
		//Walk through every possible index in the table
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				WordInfoSeparateChain bucket = table[i];
				
				//For each separate chain, grab each individual WordInfo
				for (WordInfo curr : bucket.getElements()) {
					System.out.println("\tin table index, " + i + ": " + curr);
				}
			}
		}
		System.out.println();
	}

	//Prints every word in the hash table excluding associations.
	public void printWords() {
		for (WordInfoSeparateChain i : table) {
			if (i != null) {
				List<WordInfo> chain = i.getElements();
				for (WordInfo n : chain) {
					System.out.print(n.getWord() + ", "); // aware of the signposting issue.
				}
			}
		}
	}
}
