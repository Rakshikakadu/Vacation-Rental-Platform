package Service;

import java.util.List;

import EXception.BookingException;
import Entity.Booking;

public interface BookingService {
	
	List<Booking> getAllBookings();
    Booking getBookingById(Long id) throws BookingException;
    Booking bookProperty(Long guestId, Long propertyId, Booking booking) throws BookingException;
    List<Booking> getGuestBookingHistory(Long guestId) throws BookingException;
    List<Booking> getPropertyBookingHistory(Long propertyId) throws BookingException;

}
