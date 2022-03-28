package movie.theatre.seating.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SeatManagement {

	private int rows;
	private int columns;
	private int numberOfSeats;
	private int oddNumberOfSeats;
	private LinkedHashMap<String, ArrayList<String>> servedReq = new LinkedHashMap<>();
	private String[][] seats;
	private int remainingSeats[];
	private int oddSeats[];

	public SeatManagement(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.numberOfSeats = this.oddNumberOfSeats = (this.rows*this.columns)/2;
		this.remainingSeats = new int[this.columns];
		this.oddSeats = new int[this.columns];
		this.seats = new String[this.rows][this.columns];
		for(int i=0;i<this.columns;i++) {
			if(i%2==0) {
				this.remainingSeats[i]=this.columns;
			}
			else {
				this.oddSeats[i]=this.columns;
			}
		}
	}

	public int bookSeat(String request,int seatCount) {
		String rno = request;
		int group = seatCount;
		int output;
		if (seatCount <=0) {
			return -2;
		}
		if (numberOfSeats >= seatCount) {
			if (group > 20) {
				while (group > 20) {
					output = assignSeats(rno, 20,oddSeats);
					group -= 20;
				}
				output = assignSeats(rno, group,oddSeats);
			} else {
				output = assignSeats(rno, group,oddSeats);
			}		
			return output;		
		}

		else if(oddNumberOfSeats>=seatCount) {

			if (group > 20) {
				while (group > 20) {
					output = assignSeats(rno, 20,remainingSeats);
					group -= 20;
				}
				output = assignSeats(rno, group,remainingSeats);
			} else {
				output = assignSeats(rno, group,remainingSeats);

			}
			return output;

		}
		return -1;


	}

	private int assignSeats(String rno, int seatsToBook,int remainingSeats[]) {
		int r=rows-1,c = 0;
		while (r >= 0 && r < rows && seatsToBook >0) {
			if (remainingSeats[r] >= seatsToBook) {
				for (; c < 20 && seatsToBook > 0; c++) {
					if (seats[r][c] == null) {
						seats[r][c] = rno;
						if (servedReq.containsKey(rno)) {
							servedReq.get(rno).add(
									(char) (r + 65) + Integer.toString(c + 1));
						} else {
							ArrayList<String> list = new ArrayList<>();
							list.add((char) (r + 65) + Integer.toString(c + 1));
							servedReq.put(rno, list);
						}
						remainingSeats[r]--;
						if(r%2==0) {
							oddNumberOfSeats--;
						}
						else {
							numberOfSeats--;
						}
						seatsToBook--;
					}
				}

			}
			if (seatsToBook == 0) {
				if (c+3<20) {
					for(int i = c;i<=c+2;i++) {
						seats[r][i] = "COVID";

						remainingSeats[r]--;
						if(r%2==0) {
							oddNumberOfSeats--;
						}
						else {
							numberOfSeats--;
						}
					}
				}
				else {
					for(int i=19;i>=c;i--) {
						seats[r][i] = "COVID";
						remainingSeats[r]--;
						if(r%2==0) {
							oddNumberOfSeats--;
						}
						else {
							numberOfSeats--;
						}
					}
				}
				return 0;
			} 
			r-=1;

		}
		int i = rows-1;
		while (i >= 0 && i < rows) {
			if (remainingSeats[i] > 0) {
				for (int j = 19; seats[i][j] == null; j--) {
					seats[i][j] = rno;
					if (servedReq.containsKey(rno)) {
						servedReq.get(rno).add(
								(char) (i + 65) + Integer.toString(j + 1));
					} else {
						ArrayList<String> list = new ArrayList<>();
						list.add((char) (i + 65) + Integer.toString(j + 1));
						servedReq.put(rno, list);
					}
					seatsToBook--;
					if(i%2==0) {
						oddNumberOfSeats--;
					}
					else {
						numberOfSeats--;
					}
					remainingSeats[i]--;

					if (seatsToBook == 0) {
						return 0;
					}

				}
			}
			i-=1;
		}
		return 0;

	}

	public ArrayList<String> getList(int row, int columnStart, int columnEnd) {
		ArrayList<String> list = new ArrayList<>();
		for (int c = columnStart; c <= columnEnd; c++) {
			list.add(seats[row][c]);
		}
		return list;
	}

	public LinkedHashMap<String, ArrayList<String>> getServedRequest() {
		return servedReq;
	}

	public int getNumberOfSeats() {
		return numberOfSeats+oddNumberOfSeats;
	}

	public String[][] getSeats(){
		return seats;
	}

}
