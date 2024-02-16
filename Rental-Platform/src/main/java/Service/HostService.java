package Service;

import java.util.List;

import EXception.HostException;
import Entity.Host;

public interface HostService {
	  
	List<Host> getAllHosts();
    Host getHostById(Long id) throws HostException;
    Host createHost(Host host)  throws HostException;
    Host updateHost(Long id, Host host)  throws HostException;
    void deleteHost(Long id)  throws HostException;
    
}
