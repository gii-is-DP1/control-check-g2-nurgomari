package org.springframework.samples.petclinic.feeding;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {
	
	@Autowired
	private FeedingService fs;
	private PetService ps;
	
	private static final String VIEWS_FEEDING_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateFeedingForm";
    
	@GetMapping(value = "/create")
	public String initCreationForm(ModelMap modelMap) {
		String view = VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
		Feeding f = new Feeding();
		modelMap.put("feeding", f);
		modelMap.put("feedingType", fs.getAllFeedingTypes());
		modelMap.put("pets", ps.getAllPets());
		return view;
	}
	
	@PostMapping(value = "/create")
	public String processCreationForm(@Valid Feeding feeding, BindingResult result, ModelMap modelMap) {
		String view = VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
		if(result.hasErrors()) {
			modelMap.addAttribute("feeding", feeding);
			modelMap.put("productType", fs.getAllFeedingTypes());
			return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
		}
		else {
			try {
				fs.save(feeding);
			} catch (UnfeasibleFeedingException e) {
				e.printStackTrace();
				return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
			}
			modelMap.addAttribute("message", "Feeding succesfully saved!");
			
		}
		return view;
	}
}
