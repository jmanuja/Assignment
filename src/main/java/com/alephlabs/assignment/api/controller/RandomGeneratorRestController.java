package com.alephlabs.assignment.api.controller;

import com.alephlabs.assignment.exception.CommonException;
import com.alephlabs.assignment.service.GeneratorServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RandomGeneratorRestController {
    Logger logger = LoggerFactory.getLogger(RandomGeneratorRestController.class);

    @Autowired
    private GeneratorServiceInterface generatorService;

    @GetMapping("/generate")
    public ResponseEntity<String> process(@RequestParam Integer count){
        logger.info("count : " +count);
        try {
            generatorService.generateData(count);
            return ResponseEntity.ok().build();
        }catch (UnexpectedRollbackException | CommonException e){
            logger.error(e.getMessage(),e);
            return ResponseEntity.badRequest().body("Duplicated Data Found. Please try again");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return ResponseEntity.internalServerError().body("Error Occurred. Please Rerun the application and try");
        }
    }
}
