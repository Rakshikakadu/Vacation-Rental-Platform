package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
