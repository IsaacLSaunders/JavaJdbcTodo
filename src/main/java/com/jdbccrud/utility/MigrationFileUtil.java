package com.jdbccrud.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.ClassPathResource;

public class MigrationFileUtil {

    public static int countMigrationFiles() {
        try {
            Path migrationFolderPath = Paths.get(new ClassPathResource("db/migration").getURI());
            return (int) Files.list(migrationFolderPath).count();
        } catch (IOException e) {
            // Handle exceptions, possibly log them, and consider a default behavior
            return 0; // Default to 0 if there's an error
        }
    }
}

