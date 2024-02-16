package Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import EXception.PropertyException;
import Entity.Property;

public interface PropertyService {
	
	Page<Property> getAllProperties(Pageable pageable);
    Property getPropertyById(Long id) throws PropertyException;
    Property createProperty(Property property) throws PropertyException;
    Property updateProperty(Long id, Property property) throws PropertyException;
    void deleteProperty(Long id) throws PropertyException;

}
