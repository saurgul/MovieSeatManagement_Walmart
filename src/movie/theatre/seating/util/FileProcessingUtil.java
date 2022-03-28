package movie.theatre.seating.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class FileProcessingUtil {
	private String filename;

	public FileProcessingUtil(String input) {
		this.filename = input;
	}

	public LinkedHashMap<String, Integer> readFile() {
		LinkedHashMap<String,Integer> req = new LinkedHashMap<String,Integer>();
		try {
			File file = new File(this.filename);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();	
			while (line != null) {
				String[] input = line.split(" ");
				String rno = input[0];
				int count = Integer.parseInt(input[1]);
				req.put(rno,count);
				line = bufferedReader.readLine();
	
			}
			
		} catch (FileNotFoundException ex) {
			System.err.println("Input file not Found.");
			ex.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return req;
	}
	


	public void writeToFile(LinkedHashMap<String, ArrayList<String>> servedReq) {
		BufferedWriter wr = null;
		try {
			wr = new BufferedWriter(new FileWriter("output.txt"));
			Iterator<Entry<String, ArrayList<String>>> itr = servedReq.entrySet()
					.iterator();
			while (itr.hasNext()) {
				Entry<String, ArrayList<String>> pairs = itr.next();
				String str = pairs.getKey() + " " + pairs.getValue();
				System.out.print(str + "\n");
				wr.write(str + "\n");
			}
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
