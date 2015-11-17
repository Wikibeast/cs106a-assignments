
import acm.program.*;
import java.util.*;

public class NameCounts extends ConsoleProgram {
	
	public void run() {
		Map<String, Integer> nameCount = new HashMap<String, Integer>();
		enterNames(nameCount);
		printNameCount(nameCount);
	};

	
	
	private void enterNames(Map<String, Integer> map) {
		while (true) {
			String input = readLine("Enter name: ");
			if (input.equals("")) break;
			if (!map.containsKey(input)) {
				map.put(input, 1);
			} else {
				map.put(input, map.get(input) + 1);
			};
		};
	};
	
	
	
	private void printNameCount(Map<String, Integer> map) {
		for (String name: map.keySet()) {
			println("Entry " + name + " has count " + map.get(name));
		};
		
	};

};
