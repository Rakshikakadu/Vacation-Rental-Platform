package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Entity.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

}
