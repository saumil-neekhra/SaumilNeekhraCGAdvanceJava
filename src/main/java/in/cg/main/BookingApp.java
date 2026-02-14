package in.cg.main;

import org.hibernate.cfg.Configuration;

import in.cg.entities.Booking;
import in.cg.entities.RoomType;
import in.cg.entities.BookingService;
import java.util.List;

import org.hibernate.*;

public class BookingApp {
	public static void main(String[] args) {
		Configuration c = new Configuration();
		c.configure("/in/cg/config/hibernate.cfg.xml");

		SessionFactory sf = c.buildSessionFactory();

		Session s = sf.openSession();

		try {
			// Create
			Transaction t1 = s.beginTransaction();
			Booking b1 = new Booking("Saumil Neekhra", RoomType.DELUXE, "2025-12-15", "2025-12-30");
			Booking b2 = new Booking("Rahul Sharma", RoomType.STANDARD, "2025-12-10", "2025-12-12");
			Booking b3 = new Booking("Anita Verma", RoomType.SUITE, "2025-12-01", "2025-12-05");

			s.persist(b1);
			s.persist(b2);
			s.persist(b3);

			t1.commit();
			System.out.println("Create Done");

			// Read - All
			List<Booking> list = s.createQuery("from Booking", Booking.class).list();
			System.out.println("\nIntial Booking Before Update:");
			for (Booking x : list)
				System.out.println(x + "\n");

			// Update
			Transaction t2 = s.beginTransaction();
			Booking updateBooking = s.get(Booking.class, 3);

			if (updateBooking != null) {
				updateBooking.setCustomerName("Updated_Vikash");
				updateBooking.setRoomType(RoomType.DELUXE);

				updateBooking.setTotalAmount(BookingService.calculateTotal(updateBooking.getRoomType(),
						updateBooking.getCheckInDate(), updateBooking.getCheckOutDate()));
				System.out.println("UPDATE DONE");
			}

			t2.commit();
			
			// Delete
			Transaction t3 = s.beginTransaction();
			Booking deleteBooking = s.get(Booking.class, 2);
            if (deleteBooking != null) {
                s.remove(deleteBooking);
                System.out.println("DELETE DONE");
            }

            t3.commit();
            
            // Final Booking
            List<Booking> finalList = s.createQuery("from Booking", Booking.class).list();

            System.out.println("\nFINAL BOOKINGS:");
            finalList.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
			sf.close();
		}

	}
}
