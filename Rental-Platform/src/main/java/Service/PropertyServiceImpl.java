package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import EXception.PropertyException;
import Entity.Property;
import Repository.PropertyRepository;

@Service
public class PropertyServiceImpl implements PropertyService{
	
	@Autowired
    private PropertyRepository propertyRepository;

	@Override
	public Page<Property> getAllProperties(Pageable pageable) {
		 return propertyRepository.findAll(pageable);
	}

	@Override
	public Property getPropertyById(Long id) throws PropertyException {
		return propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyException("Property not found with id: " + id));
	}

	@Override
	public Property createProperty(Property property) throws PropertyException {
		
		if(property == null) {
			throw new PropertyException("Provide property details...");
		}
		
		return propertyRepository.save(property);
	}

	@Override
	public Property updateProperty(Long id, Property property) throws PropertyException {
		Property existingProperty = propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyException("Property not found with id: " + id));
        existingProperty.setTitle(property.getTitle());
        existingProperty.setDescription(property.getDescription());
        
        return propertyRepository.save(existingProperty);
	}

	@Override
	public void deleteProperty(Long id) throws PropertyException {
		 if (!propertyRepository.existsById(id)) {
	            throw new PropertyException("Property not found with id: " + id);
	        }
	        propertyRepository.deleteById(id);
		
	}
	
	
	

}
