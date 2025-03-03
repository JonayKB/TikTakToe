package es.iespuertodelacruz.jcr.tiktaktoe.shared.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.iespuertodelacruz.jcr.tiktaktoe.shared.services.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/images")
@CrossOrigin
@Tag(name = "Images", description = "Images management")
public class ImageController {
    @Autowired
    ImageService storageService;

    Logger logger = Logger.getLogger(ImageController.class.getName());

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            String filename = storageService.save(file);

            logger.info("Image uploaded: " + filename);
            return ResponseEntity.ok(filename);
        } catch (RuntimeException e) {
            logger.warning("Error uploading image: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> getImage(@PathVariable String filename) {
        try {
            UrlResource uri = storageService.findByName(filename);
            logger.info("getImage api/images/ OK: " + filename);
            return ResponseEntity.ok(uri);
        } catch (RuntimeException e) {
            logger.warning("getImage api/images/ ERROR: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

}
