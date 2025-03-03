package es.iespuertodelacruz.jcr.tiktaktoe.shared.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    private final Path uploads = Paths.get("uploads");

    public String save(MultipartFile file) {
        try {
            Files.createDirectories(uploads);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload");
        }
        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new RuntimeException("Filename is null");
        }
        Path filePath = this.uploads.resolve(filename);
        filePath = getFreePath(filename, filePath);
        try {
            Files.copy(file.getInputStream(), filePath);
            return filePath.getFileName().toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    private Path getFreePath(String filename, Path filePath) {
        int counter = 1;
        while (Files.exists(filePath)) {
            String newFilename = filename.replaceFirst("(\\.[^.]+$|$)", "_" + counter + "$1");
            filePath = this.uploads.resolve(newFilename);
            counter++;
        }
        return filePath;
    }

    public UrlResource findByName(String filename) {
        try {
            Path pathForFilename = uploads.resolve(filename);
            UrlResource resource = new UrlResource(pathForFilename.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("no se puede acceder a " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
