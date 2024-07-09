package com.ums.controller;

import com.ums.entity.Property;
import com.ums.exceptions.ResourceNotFound;
import com.ums.repository.PropertyRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
    @GetMapping("/{locationName}")
    public ResponseEntity<List<Property>> getPropertyListings(
            @PathVariable String locationName ){
        List<Property> propertiesListing = propertyRepository.listPropertyByLocationOrCountryName(locationName);
        return new ResponseEntity<>(propertiesListing, HttpStatus.OK);
    }
    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getPropertyBYId(
            @PathVariable long propertyId
    ){
        Property property = propertyRepository.findById(propertyId).orElseThrow(
                ()->new ResourceNotFound("Property not found with id" + propertyId)
        );

        return new ResponseEntity<>(property, HttpStatus.OK);
    }


    //http://localhost:8080/api/v1/property?pageSize=3&pageNo=0&sortBy=propertyName&sortDir=asc
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperty(
        @RequestParam(name="pageSize", defaultValue="5",required = false)int pageSize,
        @RequestParam(name="pageNo", defaultValue="0",required = false)int pageNo,
        @RequestParam(name="propertyName", defaultValue="id",required = false) String propertyName,
        @RequestParam(name="sortDir", defaultValue="asc",required = false) String sortDir
    ){
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(propertyName).ascending() : Sort.by(propertyName).descending();
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Property> all= propertyRepository.findAll(pageable);
        List<Property> properties=all.getContent();

        //page first and last

        int pageNumber = pageable.getPageNumber();
        int pageCapacity = pageable.getPageSize();
        int totalPages = all.getTotalPages();
        long totalElements = all.getTotalElements();
        boolean hasNext = all.hasNext();
        boolean hasPrevious = all.hasPrevious();
        boolean last = all.isLast();
        boolean first = all.isFirst();

        System.out.println(pageNumber);
        System.out.println(pageCapacity);
        System.out.println(totalPages);
        System.out.println(totalElements);
        System.out.println(hasNext);
        System.out.println(hasPrevious);
        System.out.println(last);
        System.out.println(first);



        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}
