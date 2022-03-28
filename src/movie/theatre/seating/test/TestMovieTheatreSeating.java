package movie.theatre.seating.test;

import java.util.ArrayList;
import java.util.List;

import movie.theatre.seating.service.SeatManagement;

public class TestMovieTheatreSeating {
	
	SeatManagement testTheater;

	public void testSeatManagement(SeatManagement movieTheater) {
		System.out.println("\n********** TEST SUITE FOR SEAT MANAGEMENT ***********");
		testTheater = movieTheater;
		checkReservationWithZeroTickets();
		checkFirstCustomerSeat();
		checkConsecutiveSeats();
		checkInsufficientSeats();
		checkGroupUnableToAccomodateInRow();
		checkAccomodattionInPartialFilledRow();
	}

	private void checkReservationWithZeroTickets(){
		if(testTheater.bookSeat("R001", 0)== 1){
			System.out.println("Test 1 Passed : No seat reserved for Reservation Id R001 with requirement of zero seats.");
		}else{
			System.out.println("Test 1 Failed : Reservation made for R001 with zero requirement of seats.");
		}
	}
	private void checkFirstCustomerSeat() {
		// testTheater.printLayout();
		testTheater.bookSeat("R002", 16);
		List<String> list = new ArrayList<>();
		list.add("A1");
		list.add("A2");
		list.add("A3");
		list.add("A4");
		list.add("A5");
		list.add("A6");
		list.add("A7");
		list.add("A8");
		list.add("A9");
		list.add("A10");
		list.add("A11");
		list.add("A12");
		list.add("A13");
		list.add("A14");
		list.add("A15");
		list.add("A16");
		if (testTheater.getServedRequest().get("R002").equals(list)) {
			System.out
					.println("Test 2 Passed : 5 Seats successfully reserved for first customer at the first row.");
		} else {
			System.out
					.println("Test 2 Failed: Failed to reserve seats for first customer at the first row.");
		}
	}

	public void checkConsecutiveSeats() {
		List<String> list = new ArrayList<>();
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		list.add("R002");
		if (testTheater.getList(0, 0, 15).equals(list)) {
			System.out
					.println("Test 3 Passed : 16 Consecutive seats successfully reserved for first customer row A.");

		} else {
			System.out
					.println("Test 3 Failed : Failed to reserve consecutive seats.");
		}

	}

	public void checkInsufficientSeats() {
		testTheater.bookSeat("R003",220);
		if (testTheater.getNumberOfSeats() > 0) {
			System.out
					.println("Test 4 Passed : Failed to allocate seats when the request was greater than the available seats.");
		} else {
			System.out
					.println(" Test 4 Failed : Allocated as many seta as possible.");
		}
	}

	public void checkGroupUnableToAccomodateInRow() {
		int result = testTheater.bookSeat("R004",24);
		if (result == 0) {
			System.out
					.println("Test 5 Passed : Successfully allocated seats to a large group that could not be accomodated in a row.");
		} else {
			System.out
					.println("Test 5 Failed : Failed to allocate seats to a large group.");
		}
	}
	
	public void checkAccomodattionInPartialFilledRow() {
		int result = testTheater.bookSeat("R005",1);
		List<String> list = new ArrayList<>();
		list.add("A20");
		if (testTheater.getServedRequest().get("R005").equals(list)) {
			System.out
					.println("Test 6 Passed : 1 seats successfully booked in remaining Seats in Row A.");

		} else {
			System.out
					.println("Test 6 Failed : Failed to booked in remaining Seats in Row A");
		}
	}
	

}
