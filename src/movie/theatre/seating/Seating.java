package movie.theatre.seating;

import java.util.Map;
import java.util.*;
import movie.theatre.seating.service.SeatManagement;
import movie.theatre.seating.test.TestMovieTheatreSeating;
import movie.theatre.seating.util.FileProcessingUtil;



public class Seating {

	public static void main(String[] args) {
		if (args.length==0) {
			System.out.println("Please command Line a File with the seats requests.");
		}
		else {

			FileProcessingUtil fileProcessor = new FileProcessingUtil(args[0]);
			SeatManagement seatManagement = new SeatManagement(10,20);

			try {
				/* Read and process the file using FileUtilsMethod */
				Map<String,Integer> req = fileProcessor.readFile();
				for (Map.Entry<String,Integer> newEntry : req.entrySet()) {
					int output = seatManagement.bookSeat(newEntry.getKey(),newEntry.getValue());
					if (output == -2) {
						System.out.println("Invalid number of Seats");
					}
					if (output == -1) {
						System.out.println("Sorry, cannot process request for "+newEntry.getKey()+" with seats "+newEntry.getValue()+" due to insufficient seats");
					}
					fileProcessor.createTheatreMap(seatManagement.getSeats(),newEntry.getKey());
				}
				System.out.println("");

				
				/* get the Output in Map and write to File Output.txt */
				fileProcessor.writeToFile(seatManagement.getServedRequest());
				//fileProcessor.createTheatreMap(seatManagement.getSeats());

				/* Running the Test Cases */
								TestMovieTheatreSeating test = new TestMovieTheatreSeating();
								SeatManagement testObject = new SeatManagement(10,20);
								test.testSeatManagement(testObject);

			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
