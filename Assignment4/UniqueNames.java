
import acm.program.*;
import java.util.*;

public class UniqueNames extends ConsoleProgram {
	
	
	
	
	public void run() {
		enterNames();
		printNames();
	};

	
	
	
	private void enterNames() {
		while (true) {
			String input = readLine("Enter name: ");
			if (input.equals("")) break;
			if (!nameList.contains(input)) {
				nameList.add(input);
			};
		};
	};

	
	
	
	private void printNames() {
		println("Unique name list contains:");
		for (int i = 0; i < nameList.size(); i++) {
			println(nameList.get(i));
		};
		
	};
	
	
	
	
	/** Instance Variables */
	private ArrayList<String> nameList = new ArrayList<String>();
	
	
	
	
};
