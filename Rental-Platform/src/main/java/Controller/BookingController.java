package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import EXception.BookingException;
import Entity.Booking;
import Service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
    private BookingService bookingService;

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) throws BookingException {
        Booking booking = bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PostMapping("/guest/{guestId}/property/{propertyId}")
    public ResponseEntity<Booking> bookProperty(@PathVariable Long guestId,
                                                 @PathVariable Long propertyId,
                                                 @RequestBody Booking booking) throws BookingException {
        Booking newBooking = bookingService.bookProperty(guestId, propertyId, booking);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @GetMapping("/guest/{guestId}/history")
    public ResponseEntity<List<Booking>> getGuestBookingHistory(@PathVariable Long guestId) throws BookingException {
        List<Booking> bookingHistory = bookingService.getGuestBookingHistory(guestId);
        return new ResponseEntity<>(bookingHistory, HttpStatus.OK);
    }

    @GetMapping("/property/{propertyId}/history")
    public ResponseEntity<List<Booking>> getPropertyBookingHistory(@PathVariable Long propertyId) throws BookingException {
        List<Booking> bookingHistory = bookingService.getPropertyBookingHistory(propertyId);
        return new ResponseEntity<>(bookingHistory, HttpStatus.OK);
    }

}
