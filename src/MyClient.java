import java.util.Scanner;
import java.util.Set;

/*
 * Brandon Dixon, TJ Dillon, Saywere Hendricks
 * 05/15/2018
 * MyClient.java
 *
 * A word association game using the text associator object. The program tries to imitate a game
 * some people play where you have to respond back with the first word that comes to mind as fast as
 * possible.
 */

public class MyClient {
    public static void main(String[] args){
        System.out.println("Welcome to my word association game!\n" +
                "Give me any word (well one of 20 (: ) and I'll tell\n" +
                "you what the first word is that comes to my mind!");
        TextAssociator knownWords = new TextAssociator();
        populateAssociations(knownWords);
        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("These are the words I know: (type \"q\" to quit)");
            knownWords.printWords();
            System.out.print("\nWhich word are you picking? ");
            String word = input.next();
            if (word.equals("q")) {
                running = false;
            }
            Set<String> associations = knownWords.getAssociations(word);
            if (associations != null) {
                System.out.println(associations);
            } else {
                System.out.println("don't know that word :'(");
            }
        }

    }

    //Takes a TextAssociator object and populates it with 20 words and an association for each word.
    private static void populateAssociations(TextAssociator knownWords) {
        knownWords.addNewWord("apple");knownWords.addAssociation("apple", "banana");
        knownWords.addNewWord("table");knownWords.addAssociation("table", "cadet");
        knownWords.addNewWord("controller");knownWords.addAssociation("controller", "pen");
        knownWords.addNewWord("denture");knownWords.addAssociation("denture", "pie");
        knownWords.addNewWord("double-u");knownWords.addAssociation("double-u", "brace");
        knownWords.addNewWord("blanket");knownWords.addAssociation("blanket", "speaker");
        knownWords.addNewWord("speaker");knownWords.addAssociation("glasses", "envelope");
        knownWords.addNewWord("desk-mat");knownWords.addAssociation("desk-mat", "bag");
        knownWords.addNewWord("management");knownWords.addAssociation("management", "requirement");
        knownWords.addNewWord("battery");knownWords.addAssociation("battery", "comedy");
        knownWords.addNewWord("news");knownWords.addAssociation("news", "mole");
        knownWords.addNewWord("leader");knownWords.addAssociation("leader", "rescue");
        knownWords.addNewWord("medal");knownWords.addAssociation("medal", "strong");
        knownWords.addNewWord("fist");knownWords.addAssociation("fist", "punch");
        knownWords.addNewWord("instrument");knownWords.addAssociation("instrument", "trumpet");
        knownWords.addNewWord("tool");knownWords.addAssociation("tool", "wrench");
        knownWords.addNewWord("judge");knownWords.addAssociation("judge", "law");
        knownWords.addNewWord("eyes");knownWords.addAssociation("eyes", "blind");
        knownWords.addNewWord("phone");knownWords.addAssociation("phone", "screen");
        knownWords.addNewWord("image");knownWords.addAssociation("image", "frame");
    }
}