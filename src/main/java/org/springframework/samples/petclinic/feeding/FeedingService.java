package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;

@Service
public class FeedingService {
	@Autowired
	FeedingRepository feedingRepository;
	
    public List<Feeding> getAll(){
    	List<Feeding> res = feedingRepository.findAll();
        return res;
    }

    public List<FeedingType> getAllFeedingTypes(){
        return feedingRepository.findAllFeedingTypes();
    }

    public FeedingType getFeedingType(String typeName) {
        return feedingRepository.findFeedingType(typeName);
    }
    
    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
    	if (p.getPet().getType()!=p.getFeedingType().getPetType()) {
			throw new UnfeasibleFeedingException();
		}
    	else {
    		return feedingRepository.save(p);
    	}
               
    }

    
}
