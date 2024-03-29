package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Entity.Host;

@Repository
public interface HostRepository extends JpaRepository<Host, Long>{

}
