package com.carato.restcontroller;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.carato.config.S3Configuration;
import com.carato.entity.ProjectEntity;
import com.carato.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/p")
public class ProjectRestController {

    @Autowired
    private AmazonS3 client;

    @Autowired
    private S3Configuration config;

    @Autowired
    private ProjectRepo repo;


    @GetMapping("/a")
    public ResponseEntity<String> name() {
        return new ResponseEntity<String>("carato-backend", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ProjectEntity> saveProject(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "duration") String duration,
            @RequestParam("image") MultipartFile image){

        ProjectEntity project = null;

        try {
            String bucketName = config.getBucketName();
            client.putObject(new PutObjectRequest(bucketName,image.getOriginalFilename(),
                    image.getInputStream(), new ObjectMetadata())
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            String imgSrc = "http://"+bucketName+".s3.amazonaws.com/"+image.getOriginalFilename();

            project = repo.save(new ProjectEntity(name, Long.parseLong(duration), imgSrc));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(project,HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<ProjectEntity>> getAllProjects(){
        List<ProjectEntity> list = repo.findAll();
        return new ResponseEntity<List<ProjectEntity>>(list,HttpStatus.OK);
    }


}
