
// Program used the functionality of the Garage class 

public class GarageTest
{
	public static void main (String [] args) 
   {
		Garage g1 = new Garage();
		System.out.println("Number parked: " + g1.numberParked());
      System.out.println("Number waiting: " + g1.numberWaiting());
      System.out.println("Parking WEB445 ... " + g1.arrival("WEB445"));
      System.out.println("Parking BEA345 ... " + g1.arrival("BEA342"));
      System.out.println("Parking WEB445 ... " + g1.arrival("WEB443"));
      System.out.println("Parking BEA345 ... " + g1.arrival("BEA3421"));
      System.out.println("Parking WEB445 ... " + g1.arrival("WEB225"));
      System.out.println("Parking BEA345 ... " + g1.arrival("BEA115"));
      System.out.println("Parking WEB445 ... " + g1.arrival("WEB435"));
      // Waiting
      System.out.println("Parking BEA345 ... " + g1.arrival("BEA995"));
      System.out.println("Parking BEA345 ... " + g1.arrival("JEA995"));
      System.out.println("Parking BEA345 ... " + g1.arrival("IKA995"));
      
      System.out.println("Number parked: " + g1.numberParked());
      System.out.println("Number waiting: " + g1.numberWaiting());
      System.out.println(g1);
      System.out.println("WEB445 departs after " + g1.departure("WEB225") + " car(s) moved");
      System.out.println("Number parked: " + g1.numberParked());
      System.out.println("Number waiting: " + g1.numberWaiting());
      System.out.println(g1);
      System.out.println("WEB445 departs after " + g1.departure("BEA995") + " car(s) moved");
      System.out.println("Number parked: " + g1.numberParked());
      System.out.println("Number waiting: " + g1.numberWaiting());
      System.out.println(g1);
      
      System.out.println("\n\nFile: \n\n\n");
      
		g1 = new Garage("parking.txt");
      System.out.println("Number parked: " + g1.numberParked());
      System.out.println("Number waiting: " + g1.numberWaiting());
      System.out.println("Parking WEB445 ... " + g1.arrival("WEB445"));
      System.out.println("Parking BEA345 ... " + g1.arrival("BEA345"));
      System.out.println(g1);
      System.out.println("Z23YTU departs after " + g1.departure("Z23YTU") + " car(s) moved");
      System.out.println(g1);

	}
}