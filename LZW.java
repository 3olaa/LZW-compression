import java.util.Scanner;
import java.util.Vector;

public class LZW {
    Vector<String> dictionary = new Vector<> ();

    public void Compression (String string){
        Vector Index = new Vector ();
        String prev = "";
        for (int  i = 0 ; i <128 ; i++){
            dictionary.add ((char)i+"");  //add all characters in the vector
        }
        for (int  i = 0 ; i < string.length (); i++){
            String current = String.valueOf (string.charAt (i));
            if(dictionary.contains (prev + current)){
                prev = prev + string.charAt (i);
            }
            else {
                Index.add (dictionary.indexOf (prev));
                dictionary.add (prev+string.charAt (i));
                prev = current;
            }
        }
        System.out.println (Index);
    }

    public void Decompression (String input){
        String [] text = input.split (" ");
        String decompText = "";
        String str = "";
        for (int  i = 0 ; i <128 ; i++){
            dictionary.add ((char)i+"");
        }
        for (int i = 0 ; i <text.length ; i++){
            int myString = Integer.parseInt (text[i]); //convert string into integer
            if( i > 0){
                int prevStr = Integer.parseInt (text[i-1]);
                if(myString >= dictionary.size ()){ //condition to handle special case
                    dictionary.add(dictionary.get (prevStr) + dictionary.get (prevStr).charAt (0));
                }
                else {
                    dictionary.add(dictionary.get (prevStr) + dictionary.get (myString).charAt (0));
                }
            }

            if(dictionary.get (myString) !=null){
                str = dictionary.get (myString);
                decompText += str;
            }
        }
        System.out.println (decompText);
    }

    public void printDictionary(){
        for (int i = 0; i< dictionary.size() ; i++){

            System.out.println (i + "  "+ dictionary.get(i));
        }
    }

    public static void main(String args[]){
        Scanner in = new Scanner (System.in);
        String input = in.nextLine ();
        LZW l = new LZW ();
        l.Compression (input);
        l.printDictionary ();
    }
}
