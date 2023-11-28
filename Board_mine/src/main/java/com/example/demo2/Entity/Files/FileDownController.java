package com.example.demo2.Entity.Files;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileDownController {

    @GetMapping("/download/{uuid}/{fileName}") // --> 파일을 조회하고 불러오는 기능수행

    public ResponseEntity<Resource> downloadFile(@PathVariable String uuid,
                                                 @PathVariable String fileName){
        Path filepath = Paths.get("C:/Users/G/Desktop/DB_Files/"+uuid+fileName);

        try{
            Resource ressource = new UrlResource(filepath.toUri());
            if(ressource.exists()|| ressource.isReadable()){
                return ResponseEntity.ok().header(
                        "Content-Disposition",
                        "attachment; fileName=\""+ ressource.getFilename()+ "\"")
                        .body(ressource);
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
