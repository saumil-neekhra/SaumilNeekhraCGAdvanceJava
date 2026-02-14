package in.cg.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingService {
	public static double calculateTotal(RoomType roomType, String checkIn, String checkOut) {

		LocalDate in = LocalDate.parse(checkIn);
	    LocalDate out = LocalDate.parse(checkOut);

	    long days = ChronoUnit.DAYS.between(in, out);

		double price = 0;
		switch (roomType) {
			case STANDARD : price = 2000;
			case DELUXE : price = 3500;
			case SUITE : price = 5000;
		};

		return days * price;
	}
}
