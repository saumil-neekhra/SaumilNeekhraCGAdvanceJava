package in.cg.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking_details")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;

	@Column(name = "customer_name", nullable = false)
	private String customerName;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoomType roomType;

	private String checkInDate;
	private String checkOutDate;

	@Column(nullable = false)
	private double totalAmount;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(double totalAmount) {
	    this.totalAmount = totalAmount;
	}


	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", customerName=" + customerName + ", roomType=" + roomType
				+ ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", totalAmount=" + totalAmount
				+ "]";
	}

	public Booking() {
	}

	public Booking(String customerName, RoomType roomType, String checkInDate, String checkOutDate) {
		this.customerName = customerName;
		this.roomType = roomType;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalAmount = BookingService.calculateTotal(roomType, checkInDate, checkOutDate);
	}

}



