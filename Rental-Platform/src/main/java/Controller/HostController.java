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

import EXception.HostException;
import Entity.Host;
import Service.HostService;

@RestController
@RequestMapping("/hosts")
public class HostController {
	
	
	@Autowired
    private HostService hostService;

    @GetMapping
    public ResponseEntity<List<Host>> getAllHosts() {
        List<Host> hosts = hostService.getAllHosts();
        return new ResponseEntity<>(hosts, HttpStatus.OK);
    }

    @GetMapping("/{hostId}")
    public ResponseEntity<Host> getHostById(@PathVariable Long hostId) throws HostException {
        Host host = hostService.getHostById(hostId);
        return new ResponseEntity<>(host, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Host> createHost(@RequestBody Host host) throws HostException {
        Host createdHost = hostService.createHost(host);
        return new ResponseEntity<>(createdHost, HttpStatus.CREATED);
    }

    @PutMapping("/{hostId}")
    public ResponseEntity<Host> updateHost(@PathVariable Long hostId, @RequestBody Host host) throws HostException {
        Host updatedHost = hostService.updateHost(hostId, host);
        return new ResponseEntity<>(updatedHost, HttpStatus.OK);
    }

    @DeleteMapping("/{hostId}")
    public ResponseEntity<Void> deleteHost(@PathVariable Long hostId) throws HostException {
        hostService.deleteHost(hostId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
