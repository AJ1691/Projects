import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
/**
 * Name Ajay Sharma
 * Student no. 10526282
 * asharma5@go.olemiss.edu
 * 
 * HONOR CODE:I hereby State that I have followed the honor code of 
 * The University of Mississippi
 * and not used any inappropriate assistance in this programming assignment
 * Ajay Sharma
 * 
 /* 
 * java version 7 update67
 * The program implements the pseudo code in the assignment.
 * 
 * This project has 4 classes,1 interface and also uses arraylist in java API and 
 * Stack in java API.
 * 
 * An Arraylist is used to Store the dictionary and the used words.
 * 
 * A linked queue of linearNodes is used to Blow the words into every possible 
 * chain.
 * 
 * A new generated word is added to the used array list so that it is not 
 * added to the queue again. 
 * 
 * when a new word is generated with the nested for loops(one for varying 
 * the position and other for varying the letter from a - z), it is stored as the 
 * element in the linear node and is set to point to the previous word generated 
 * named here as the current node.
 * 
 * while generating the word tree a new node generated is always checked if it has the final 
 * word and if it has then the TrackNode is set to point to it.This way the program backtracks 
 * the word chain.
 * 
**/
public class Driver {

	public static void main(String[] args) throws IOException {

		String Word = null;
		String cont;
		
		
		ArrayList<String> Dic = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader("four.txt"));

		while ((Word = br.readLine()) != null) {
			Dic.add(Word);
		}
		br.close();
		
         System.out.println("Program for word chain generator");
		do {
			String newWord = "0000";
			String currentWord = null;
			int count= 0;
			boolean Found=false;
			boolean same=false;
			System.out.println("Enter the Starting Word :");
			BufferedReader SW = new BufferedReader(new InputStreamReader(
					System.in));
			String s1 = SW.readLine();
			System.out.println("Enter the Ending Word :");
			String s2 = SW.readLine();
			
			if(s1.equals(s2)){
				System.out.println("Starting and ending words are equal");
				System.out.println("Length of the chain is : 0");
				same=true;
			}

			if (Dic.contains(s1) && Dic.contains(s2)) {
				
				LinearNode<String> StartWord = new LinearNode<String>(s1);
				ArrayList<String> Used = new ArrayList<String>();
				LinkedQueue<LinearNode<String>> Open = new LinkedQueue<LinearNode<String>>();

				Used.add(StartWord.getElement());
				Open.enqueue(StartWord);
				LinearNode<String> CurrentNode = new LinearNode<String>();
				LinearNode<String> TrackNode = new LinearNode<String>();
				LinearNode<String> newNode = new LinearNode<String>();

				while (Open.size() != 0 && !newWord.equals(s2)) {
				    CurrentNode = Open.dequeue();
					currentWord = CurrentNode.getElement();
					char[] ChStr;
					

					for (int i = 0; i < currentWord.length(); i++) {
						ChStr = currentWord.toCharArray();
						for (char Ch = 'a'; Ch <= 'z'; Ch++) {
							
							ChStr[i]=Ch;
							newWord = String.valueOf(ChStr);

							if (Dic.contains(newWord)
									&& !Used.contains(newWord)) {
								Used.add(newWord);
								newNode = new LinearNode<String>(newWord);
								newNode.setNext(CurrentNode);
								Open.enqueue(newNode);
								if (newWord.equals(s2)) {
									TrackNode = newNode;
									Found=true;
									break;
								}
							}
						}// 1st for over
						if(Found==true){
							break;
						}
					}// 2nd for over
				}
				    if (Found==true) {
				    	Stack<String> Stck=new Stack<String>();  
					   while (TrackNode != null) {
						Stck.push(TrackNode.getElement());
						TrackNode = TrackNode.getNext();
						count++;
					   }
					   count--;
					   while(!Stck.isEmpty()){
					   System.out.println(Stck.pop());
					   }
					   System.out.println("Length of chain is :" + count );
				    }// while over
			} 
			else {
				System.out.println("Word not in Dictionary");
			}
            if (Found==false && same==false) {
              System.out.println("No word chain was found");
             }
			System.out.println("Do you want to find out another word chain? Say Y/N");
			cont = SW.readLine();
		} while (cont.equalsIgnoreCase("Y"));
	}

}
