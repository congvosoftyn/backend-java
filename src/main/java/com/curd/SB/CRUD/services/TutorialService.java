package com.curd.SB.CRUD.services;

import com.curd.SB.CRUD.models.Tutorial;
import com.curd.SB.CRUD.repositories.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialService {
    @Autowired
    TutorialRepository tutorialRepository;

    public Tutorial createTutorial(Tutorial tutorial){
        Tutorial tutorial1 = Tutorial.builder()
                .title(tutorial.getTitle())
                .description(tutorial.getDescription())
                .build();

        return tutorialRepository.save(tutorial1);
    }

    public List<Tutorial> getTutorials(){
        return tutorialRepository.findAll();
    }
}
