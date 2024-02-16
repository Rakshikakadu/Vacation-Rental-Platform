package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EXception.GuestException;
import Entity.Guest;
import Repository.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService{
	
	@Autowired
	private GuestRepository guestRepository;

	@Override
	public List<Guest> getAllGuests() {
		return guestRepository.findAll();
	}

	@Override
	public Guest getGuestById(Long id) throws GuestException {
		return guestRepository.findById(id)
                .orElseThrow(() -> new GuestException("Guest not found with id: " + id));
	}

	@Override
	public Guest createGuest(Guest guest) throws GuestException {
		
		if(guest == null) {
			throw new GuestException("Guest details not provided");
		}
		
		return guestRepository.save(guest);
	}

	@Override
	public Guest updateGuest(Long id, Guest guest) throws GuestException {
		Guest existingGuest = guestRepository.findById(id)
                .orElseThrow(() -> new GuestException("Guest not found with id: " + id));
        existingGuest.setName(guest.getName());
        existingGuest.setGender(guest.getGender());
        existingGuest.setDateOfBirth(guest.getDateOfBirth());
        existingGuest.setBio(guest.getBio());
        
        return guestRepository.save(existingGuest);
	}

	@Override
	public void deleteGuest(Long id) throws GuestException {
		if (!guestRepository.existsById(id)) {
            throw new GuestException("Guest not found with id: " + id);
        }
        guestRepository.deleteById(id);
		
	}
	
	

}
