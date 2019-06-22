package com.sd.demo.service;

import java.util.Set;

import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.sd.demo.entity.PlaceImage;

public interface PlaceImageService {

	Set<Long> uploadImages(MultipartFile[] files) throws HttpServerErrorException;

	PlaceImage createPlaceImage(String url);

}
