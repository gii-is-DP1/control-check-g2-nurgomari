package org.springframework.samples.petclinic.feeding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FeedingType extends BaseEntity{
    
	@NotNull
	@Length(min = 5, max = 50)
	@Column(name = "name", unique = true)
	private String name;
	
	@NotNull
    private String description;
    
	@NotNull
	@JoinColumn(name = "pet_type_id")
	@ManyToOne
    PetType petType;
}
