/**
 * 
 */
package com.masai.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.masai.exceptions.FileTypeNotValidException;
import com.masai.payloads.ImageResponse;

/**
 * @author tejas
 *
 */
public interface FileService {

	ImageResponse updatePostImage(String path, MultipartFile multipartFile) throws IOException, FileTypeNotValidException;

	InputStream servePostImage(String path, String imageName) throws FileNotFoundException;

}
