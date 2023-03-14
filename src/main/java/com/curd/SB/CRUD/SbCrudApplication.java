package com.curd.SB.CRUD;

import com.curd.SB.CRUD.models.Tutorial;
import com.curd.SB.CRUD.services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RequestMapping(path = "/")
@RestController
public class SbCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbCrudApplication.class, args);
	}

	@GetMapping("/")
	public String helloWorld(){
		return "Hello World";
	}

	@Autowired
	TutorialService tutorialService;

	@PostMapping("/tutorials")
	public Tutorial createTutorial(@RequestBody Tutorial tutorial){
		return tutorialService.createTutorial(tutorial);
	}

	@GetMapping("/tutorials")
	public List<Tutorial> getTutorials(){
		return tutorialService.getTutorials();
	}
}
