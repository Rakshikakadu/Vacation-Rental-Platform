package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EXception.GuestException;
import Entity.Guest;
import Service.GuestService;

@RestController
@RequestMapping("/guests")
public class GuestController {
	
	@Autowired
    private GuestService guestService;

    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuests() {
        List<Guest> guests = guestService.getAllGuests();
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @GetMapping("/{guestId}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long guestId) throws GuestException {
        Guest guest = guestService.getGuestById(guestId);
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) throws GuestException {
        Guest createdGuest = guestService.createGuest(guest);
        return new ResponseEntity<>(createdGuest, HttpStatus.CREATED);
    }

    @PutMapping("/{guestId}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long guestId, @RequestBody Guest guest) throws GuestException {
        Guest updatedGuest = guestService.updateGuest(guestId, guest);
        return new ResponseEntity<>(updatedGuest, HttpStatus.OK);
    }

    @DeleteMapping("/{guestId}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long guestId) throws GuestException {
        guestService.deleteGuest(guestId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
