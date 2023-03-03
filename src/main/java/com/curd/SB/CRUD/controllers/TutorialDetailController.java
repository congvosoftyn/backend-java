package com.curd.SB.CRUD.controllers;

import com.curd.SB.CRUD.exceptions.ResourceNotFoundException;
import com.curd.SB.CRUD.models.Tutorial;
import com.curd.SB.CRUD.models.TutorialDetails;
import com.curd.SB.CRUD.repositories.TutorialDetailRepository;
import com.curd.SB.CRUD.repositories.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class TutorialDetailController {
    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private TutorialDetailRepository tutorialDetailRepository;

    @GetMapping({"/details/{id}", "/tutorials/{id}/details"})
    public ResponseEntity<TutorialDetails> getDetailsById(@PathVariable(value = "id") Long id){
        TutorialDetails details = tutorialDetailRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not Found Tutorial with id = "+id));

        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PostMapping("/tutorial/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> createDetails(@PathVariable(value = "tutorialId") Long tutorialId, @RequestBody TutorialDetails tutorialDetails){
        Tutorial tutorial = tutorialRepository.findById(tutorialId)
                .orElseThrow(()->new ResourceNotFoundException("Not Found Tutorial with id = "+tutorialId));

        tutorialDetails.setCreateOn(new Date());
        tutorialDetails.setTutorial(tutorial);

        TutorialDetails details = tutorialDetailRepository.save(tutorialDetails);

        return  new ResponseEntity<>(details, HttpStatus.CREATED);
    }

    @PutMapping("/details/{id}")
    public ResponseEntity<TutorialDetails> updateDetails(@PathVariable(value = "id") Long id, @RequestBody TutorialDetails tutorialDetails){
        TutorialDetails details = tutorialDetailRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id = "+id+" not found!"));

        details.setCreatedBy(tutorialDetails.getCreatedBy());

        return  new ResponseEntity<>(tutorialDetailRepository.save(details),HttpStatus.OK);
    }

    @DeleteMapping("/details/{id}")
    public ResponseEntity<HttpStatus> deleteDetails(@PathVariable("id") long id){
        tutorialDetailRepository.deleteById(id);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> deleteDetailOfTutorial(@PathVariable(value = "tutorialId") long tutorialId){
        if(!tutorialRepository.existsById(tutorialId)){
            throw  new ResourceNotFoundException("Not found Tutorial with id = "+tutorialId);
        }

        tutorialDetailRepository.deleteByTutorialId(tutorialId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
