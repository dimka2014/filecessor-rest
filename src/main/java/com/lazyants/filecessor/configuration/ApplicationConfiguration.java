package com.lazyants.filecessor.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="application")
@Data
public class ApplicationConfiguration {
    public static final String ORIGINAL_SUBDIRECTORY = "original";
    public static final String REGULAR_SUBDIRECTORY = "regular";

    private String mediaDirectoryPath;

    public String getOriginalDirectory() {
        return mediaDirectoryPath + "/" + ORIGINAL_SUBDIRECTORY + "/";
    }

    public String getRegularDirectory() {
        return mediaDirectoryPath + "/" + REGULAR_SUBDIRECTORY + "/";
    }

    private String jwtSecret;
    private String usersFile;
}
