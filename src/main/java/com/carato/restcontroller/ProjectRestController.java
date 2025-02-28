package com.carato.restcontroller;

import com.carato.entity.ProjectEntity;
import com.carato.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/p")
public class ProjectRestController {

    @Autowired
    private ProjectRepo repo;

    @GetMapping("/a")
    public ResponseEntity<String> name() {
        return new ResponseEntity<String>("carato-backend", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ProjectEntity> saveProject(@RequestBody ProjectEntity p){
        ProjectEntity project = repo.save(p);
        return new ResponseEntity<>(project,HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<ProjectEntity>> getAllProjects(){
        List<ProjectEntity> list = repo.findAll();
        return new ResponseEntity<List<ProjectEntity>>(list,HttpStatus.OK);
    }


}
