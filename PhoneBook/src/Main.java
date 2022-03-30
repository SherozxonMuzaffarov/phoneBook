import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Main {
	public static void main(String[] args) throws IOException {

		Properties ht = new Properties();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String name, number;
		FileInputStream fin = null;
		boolean changed = false;

		// try to open phonebook.dat file
		try {
			fin = new FileInputStream("phonebook.dat");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if phonebook file already exist,
		// load existing phone number
		if (fin != null) {
			try {
				ht.load(fin);
				fin.close();
			} catch (IOException e) {
				System.out.println("error reading file.");
			}
		}

		// let user enter new names and numbers
		do {
			System.out.println("Enter new name" + "('quit' to stop): ");
			name = br.readLine();
			if (name.equals("quit"))
				continue;

			System.out.println("enter number: ");
			number = br.readLine();

			ht.setProperty(name, number);
			changed = true;
		} while (!name.equals("quit"));
		
		//if phonebook data has changed, save it
		if(changed) {
			FileOutputStream fout = new FileOutputStream("phonebook.dat");
			ht.store(fout, "telephone book");
			fout.close();
		}
		
		//look up number given a name
		do {
		System.out.println("Enter name to find" + "('quit' to stop): ");
		name = br.readLine();
		if (name.equals("quit"))
			continue;
		number = (String) ht.get(name);
		System.out.println(number);
		
		}while (!name.equals("quit"));
	}
}
