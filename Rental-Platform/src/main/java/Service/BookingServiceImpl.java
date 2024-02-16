package Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EXception.BookingException;
import Entity.Booking;
import Entity.Guest;
import Entity.Property;
import Repository.BookingRepository;
import Repository.GuestRepository;
import Repository.PropertyRepository;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private PropertyRepository propertyRepository;

	@Override
	public List<Booking> getAllBookings() {
		 return bookingRepository.findAll();
	}

	@Override
	public Booking getBookingById(Long id) throws BookingException {
		return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingException("Booking not found with id: " + id));
	}

	@Override
	public Booking bookProperty(Long guestId, Long propertyId, Booking booking) throws BookingException {
		Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new BookingException("Guest not found with id: " + guestId));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new BookingException("Property not found with id: " + propertyId));

        // Check if property is available for booking
        List<Booking> propertyBookings = property.getBookings();
        for (Booking existingBooking : propertyBookings) {
            LocalDate checkInDate = existingBooking.getCheckInDate();
            LocalDate checkOutDate = existingBooking.getCheckOutDate();
            LocalDate newCheckInDate = booking.getCheckInDate();
            LocalDate newCheckOutDate = booking.getCheckOutDate();
            if ((newCheckInDate.isAfter(checkInDate) && newCheckInDate.isBefore(checkOutDate)) ||
                    (newCheckOutDate.isAfter(checkInDate) && newCheckOutDate.isBefore(checkOutDate)) ||
                    (newCheckInDate.isBefore(checkInDate) && newCheckOutDate.isAfter(checkOutDate))) {
                throw new BookingException("Property is not available for the selected dates");
            }
        }

        
        booking.setGuest(guest);
        booking.setProperty(property);

        return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> getGuestBookingHistory(Long guestId) throws BookingException {
		 Guest guest = guestRepository.findById(guestId)
	                .orElseThrow(() -> new BookingException("Guest not found with id: " + guestId));
	        return guest.getBookings();
	}

	@Override
	public List<Booking> getPropertyBookingHistory(Long propertyId) throws BookingException {
		Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new BookingException("Property not found with id: " + propertyId));
        return property.getBookings();
	}
    
    

}
