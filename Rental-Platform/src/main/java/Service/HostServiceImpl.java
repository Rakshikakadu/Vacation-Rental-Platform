package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EXception.HostException;
import Entity.Host;
import Repository.HostRepository;

@Service
public class HostServiceImpl implements HostService{
	
	@Autowired
	private HostRepository hostRepository;

	@Override
	public List<Host> getAllHosts() {
		
		return hostRepository.findAll();
	}

	@Override
	public Host getHostById(Long id) throws HostException {
		
		return hostRepository.findById(id).orElseThrow(() -> new HostException("Host not found with given id: " + id));
	}

	@Override
	public Host createHost(Host host) throws HostException {
		if(host == null) {
			throw new HostException("Provide host details");
		}
		return null;
	}

	@Override
	public Host updateHost(Long id, Host host) throws HostException {
		
		Host existingHost = hostRepository.findById(id)
                .orElseThrow(() -> new HostException("Host not found with id: " + id));
        existingHost.setName(host.getName());
        existingHost.setHost_status(host.getHost_status());
        existingHost.setLocation(host.getLocation());
        existingHost.setPropertyType(host.getPropertyType());
        existingHost.setAbout(host.getAbout());
        existingHost.setHostingSince(host.getHostingSince());
        return hostRepository.save(existingHost);
	}

	@Override
	public void deleteHost(Long id) throws HostException {
		if (!hostRepository.existsById(id)) {
            throw new HostException("Host not found with id: " + id);
        }
        hostRepository.deleteById(id);
	}

}
