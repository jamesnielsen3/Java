package module_1;
import java.util.Scanner;

public class Singleton_Design {

	public static void main(String[] args) 
	{
		//Create a new object of the personType class
		personType Person1 = personType.getInstance();
		
		//Run the getName method with the personType object
		//Gets and sets the first, middle, and last name of the object
		Person1.getName();

	}

}

//Create personType class 
class personType{
	static personType Person = new personType();
	private personType()
	{
		
	}
	public static personType getInstance() {
		
		if (Person == null) {
			synchronized (personType.class) {
				if (Person == null) {
					Person = new personType();
				}
			}
		}
		return Person;
	}

	public void getName() {
		
		System.out.println("Welcome to the program. We need some information to start: "); 
		
		Scanner scanner = new Scanner(System.in); 
		System.out.println("What is your First Name? ");
		String fName = scanner.nextLine();		

		System.out.println("What is your Middle Name? ");
		String mName = scanner.nextLine();	
		
		System.out.println("What is your Last Name? ");
		String lName = scanner.nextLine();	
		
		System.out.println("Your name is: " + fName +" " + mName + " " + lName); 
		
		scanner.close();
	}

}


