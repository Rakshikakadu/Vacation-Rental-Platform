package Service;

import java.util.List;

import EXception.GuestException;
import Entity.Guest;

public interface GuestService {
	
	List<Guest> getAllGuests();
    Guest getGuestById(Long id) throws GuestException;
    Guest createGuest(Guest guest) throws GuestException;
    Guest updateGuest(Long id, Guest guest) throws GuestException;
    void deleteGuest(Long id) throws GuestException;

}
