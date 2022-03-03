import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.LinkedList;


/**
 * The class represents a garage with a capacity of seven cars and a waiting line with a capacity of five cars.
 * Incoming cars are parked in the garage; the last one to arrive is kept near the entrance and the first car 
 * is kept at the last; Waiting cars enter the garage in a first-come, first-serve basis. The class also reads 
 * from a file with arrival and departure information followed by the liscence number.
 */
public class Garage {

   // Stack representing a garage
   private StackInt<String> s;
   // Queue representing a waiting line
   private Queue<String> q;


   /**
    * Constructor to initialize a garage and a waiting line
    */
   public Garage() {
   
      s = new LinkedStack<>();
      q = new LinkedList<> ();
      
   }
   
   
    
   /**	
    * A constructor to initialize a garage and process information from a file; it adds 
    * the liscence number from the file to a stack representing a garage and a  
    * queue of waiting cars, removes them after departure, and does execption handling.
	 * @param fileName The file containing arrival and departure information followed by 
    *                 the license Number. 
    */
   public Garage(String fileName) {
      
      s= new LinkedStack<>();
      q = new LinkedList<> ();
      String status="";
      String liscence="";
      
      
      // Exceptional handling if file is not found
      try {
      
         // Read from the file
         Scanner input = new Scanner(new File(fileName));
         
         
         // Find index of space separating thr first letter and license No.
         while(input.hasNextLine()) {
         
            String text = input.nextLine();
            int space = text.indexOf(" ") + 1;
            
            
            // Get the first letter 
            for (int i = 0; i < space - 1; i++) {
            
               status += text.charAt(i);  
            }
            
            
            // Get the license number
            while (space < text.length()) {
            
               liscence += text.charAt(space);
               space++; 
            }
            
            // Call arrival method if first letter is 'a'
            if (status.equals("a")) {
            
              arrival(liscence); 
            }
            
            
            // Call departure method if first letter is 'd'
            else {
            
               departure(liscence);
            }
            
            
            // Clear the variables
            status="";
            liscence="";
         }   
      }
      
      
      // Catch exception if file is not found
      catch(FileNotFoundException ex) {
      
         s = new LinkedStack<>();
      }  
   }
   
   
   
   
   /**	
	 * Adds up to 7 cars in a garage; other incoming cars enter a waiting room with a 
    * capacity of 5 cars in a first-come-first-serve basis and only allows cars with 
    * unique license number to enter.
	 * @param license The license number of the incoming cars.   
	 * @return true if the car was added successfully; 
    *         false if car was not added 
    */
   public boolean arrival(String license) {
   
      boolean arrived = false;
      
      
      // Proceed only if license number is unique
      if (!checkDub(license)) {
      
      
         // Add liscence no. to the garage if there is space
         if(numberParked() < 7) {
         
            s.push(license);
            arrived = true;
         }
         
         
         // Add other cars to a waiting line if it has less than 5 cars
         else if (numberWaiting() < 5) {
         
            q.add(license);
            arrived = true;
         }  
      }
      
      return arrived;
   }
   
   
   
   
   /**	
	 * Moves cars blocking the path out, removes the specified car, move other cars back
    * in, and allows cars from the waiting line to enter if space is available.
	 * @param license The license of the car to be removed.
	 * @return The number of cars that had to be moved; 
    *         -1 if liscense number was not found in garage.
    */
   public int departure(String license) {
   
      StackInt<String> temp = new LinkedStack<>();
      boolean found = false;
      int count=0;
      
      
      // Look through the garage to find the specified liscence number
      while(!s.empty() && !found) {
      
         String plateNo = s.peek();
            
         if (plateNo.equals(license)) {
         
            s.pop();
            found = true;
         }
         
         else {
            temp.push(s.pop());
            count++;
         }
      }  
      
      // Retrieve the stack back to its original order
      while(!temp.empty()) {
      
         s.push(temp.pop());
      }
      
      
      // Move cars from waiting line to garage if space is available
      while(numberParked() < 7 && !q.isEmpty()) {
      
         s.push(q.remove());
      }
      
      // Return the number of cars that had to be moved
      if (found) {
         return count;
      }
      
      // Return -1 if no car was found
      else {
         return -1;
      }                    
   }
   
   
   
   /**	
	 * Checks for dublication of license number in garage and waiting line. 
    * @param license The license plate number of the vehicle to be checked.
    * @return true if license number is identical;
    *         false if license number is not identical.
    */
   private boolean checkDub(String license) {
   
   
      boolean identical = false;
      // Temporary stack
      StackInt<String> temp = new LinkedStack<>();
      // Temporary Queue
      Queue<String> tempQ = new LinkedList<>();
      
      
      while(!s.empty()) {
      
         String plateNo = s.peek();
         temp.push(s.pop());
         
         if (plateNo.equals(license)) {
         
            identical = true;
         }
      }  
      
      // Retrieve stack back to its original order
      while(!temp.empty()) {
      
         s.push(temp.pop());
      }
      

      while(!q.isEmpty()) {
      
         String plateNo = q.peek();
         tempQ.add(q.remove());
         
         if (plateNo.equals(license)) {
         
            identical = true;
         }
      }  
      
      
      // Retrieve queue back to its original order
      while(!tempQ.isEmpty()) {
      
         q.add(tempQ.remove());
      }
      
      return identical;
   }
   
   
   
   /**	
	 * Gets the number of cars parked in the garage.
    * @return the number of cars parked.
    */
   public int numberParked() {
   
      // Temorary stack
      StackInt<String> temp = new LinkedStack<>();
      int parked = 0;
      
      
      while (!s.empty()) {
      
         temp.push(s.pop());
         parked++;
      }
      
      
      while(!temp.empty()) {
      
         s.push(temp.pop());
      }
      
      return parked;  
   }
   
   
   
   /**	
	 * Gets the number of cars waiting in line.
    * @return the number of cars waiting.
    */
   public int numberWaiting() {
   
      // Temporary stack
      Queue<String> temp = new LinkedList<>();
      int count=0;
      
      while(!q.isEmpty()) {
      
         temp.add(q.remove());
         count++;
      }
      
      
      while(!temp.isEmpty()) {
      
         q.add(temp.remove());
      }
      
      return count;
   }
   
   
   
   /** 
    * Returns a formatted string containing all cars in the garage and the waiting line.
    * @return A String Value of cars in the garage and waiting line.
    */
   @Override
   public String toString() {
   
   
      // Temporary stack
      StackInt<String> temp = new LinkedStack<>();
      // Temporary queue
      Queue<String> temp2 = new LinkedList<>();
      
      String garage="";
      String waiting="";
      
      
         // Get all cars in the garage
          while (!s.empty()) {
          
              String t = temp.push(s.pop());
              garage += t + "\t";
          }   
       
       
         // Retrieve the stack to its original form
          while (!temp.empty()) {
          
               s.push(temp.pop());   
          }  
          
          
          // Get all cars waiting in the line
          while(!q.isEmpty()) {
          
            temp2.add(q.peek());
            waiting += q.remove() + "\t";
      }
      
      
      // Retrieve the queue back to its original form
      while(!temp2.isEmpty()) {
      
         q.add(temp2.remove());
      }
       
      return String.format("\nCars Parked In The Garage: %-1s %-28s %-1s", garage, 
                            "\n\nCars Waiting In The Line: ", waiting);
   }
   
}