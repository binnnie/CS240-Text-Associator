public class MyClient {
    public static void main(String[] args){
        TextAssociator tester = new TextAssociator();
        tester.addNewWord("hi");
        tester.addNewWord("sill");
        tester.addNewWord("floo");
        tester.addNewWord("reee");
        tester.addAssociation("hi", "go away");
        tester.addAssociation("sill", "aaaa");
        tester.prettyPrint();
    }
}
