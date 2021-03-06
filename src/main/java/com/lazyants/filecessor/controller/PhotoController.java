package com.lazyants.filecessor.controller;

import com.lazyants.filecessor.model.*;
import com.lazyants.filecessor.service.PhotoSaver;
import com.lazyants.filecessor.utils.MetadataDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api", produces = {"application/json"})
public class PhotoController {

    private final PhotoRepository photoRepository;

    private final PhotoSaver photoSaver;

    private PhotoResourceAssembler photoResourceAssembler = new PhotoResourceAssembler(PhotoController.class, PhotoResource.class);

    @Autowired
    public PhotoController(PhotoRepository photoRepository, PhotoSaver photoSaver) {
        this.photoRepository = photoRepository;
        this.photoSaver = photoSaver;
    }

    @RequestMapping(value = "/photos", method = RequestMethod.GET)
    public PagedResources<PhotoResource> list(Pageable pageable, PagedResourcesAssembler<Photo> pagedAssembler) {
        return pagedAssembler.toResource(photoRepository.findAll(pageable), photoResourceAssembler);
    }

    @RequestMapping(value = "/photos/{id}", method = RequestMethod.GET)
    public PhotoResource get(@PathVariable("id") String id) {
        return new PhotoResource(photoRepository.findOne(id));
    }

    @RequestMapping(value = "/photos/upload", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public PhotoResource upload(@Valid @ModelAttribute PhotoFile file) {
        return new PhotoResource(photoSaver.saveFile(file));
    }

    @RequestMapping(value = "/photos/url", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public PhotoResource downloadImageByUrl(@RequestBody @Valid final LinkBody linkBody) {
        return new PhotoResource(photoSaver.downloadImage(linkBody.getUrl()));
    }

    @RequestMapping(value = "/photos/video-preview", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public PhotoResource downloadVideoPreview(@RequestBody @Valid final LinkBody linkBody) {
        return new PhotoResource(photoSaver.downloadImage(MetadataDownloader.getThumbnailUrl(linkBody.getUrl())));
    }
}
