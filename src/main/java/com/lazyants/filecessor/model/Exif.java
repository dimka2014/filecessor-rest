package com.lazyants.filecessor.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public class Exif {

    private Date datetimeOriginal;

    private String exposureTime;

    private String aperture;

    private String focalLength;

    private String colorSpace;

    private double xResolution;

    private double yResolution;

    private int width;

    private int height;

    private String iso;

    private double latitude;

    private double longitude;

    private String camera;

    private String lens;

}
