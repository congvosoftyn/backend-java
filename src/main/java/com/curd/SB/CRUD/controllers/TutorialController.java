package com.curd.SB.CRUD.controllers;

import com.curd.SB.CRUD.exceptions.ResourceNotFoundException;
import com.curd.SB.CRUD.models.Tutorial;
import com.curd.SB.CRUD.repositories.TutorialDetailRepository;
import com.curd.SB.CRUD.repositories.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {
    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    TutorialDetailRepository tutorialDetailRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null) tutorialRepository.findAll().forEach(tutorials::add);
        else tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id){
        Tutorial tutorial = tutorialRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found tutorial with id = "+id));

        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial){
        Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(),true));
        return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial){
        Tutorial _tutorial = tutorialRepository.findById(id)
                .orElseThrow(()->new ResourceAccessException("Not found tutorial with id = "+id));

        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());

        return  new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tutorial> deleteTutorial(@PathVariable("id")long id){
        if(tutorialDetailRepository.existsById(id)){
            tutorialDetailRepository.deleteById(id);
        }
        tutorialRepository.deleteById(id);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<HttpStatus> deleteAllTutorials(){
        tutorialRepository.deleteAll();

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/published")
    public ResponseEntity<List<Tutorial>> findByPublished(){
        List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

        if(tutorials.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }
}
