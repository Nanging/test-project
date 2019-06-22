package com.sd.demo.service.Impl;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.sd.demo.dao.PlaceImageDao;
import com.sd.demo.entity.PlaceImage;
import com.sd.demo.service.PlaceImageService;
@Service
public class PlaceImageServiceImpl implements PlaceImageService{
	
	
	@Autowired
	private PlaceImageDao placeImageDao;
	
	@Value("${file.host}")
	private String host;
	@Value("${file.port}")
	private String port;
	
	@Value("${file.imagePath}")
	private String imagePath;
	
	private String http = "http://";
	
    public String getUploadFilePath(MultipartFile file, String fileName ) {
        if (file.isEmpty()) {
            throw new NullPointerException("文件为空");
        }
        String suffix = file.getOriginalFilename();
        String prefix = suffix.substring(suffix.lastIndexOf(".")+1);
        fileName+=('.'+prefix);
        try {
			File path = new File(ResourceUtils.getURL("classpath:").getPath());
			if (!path.exists()) {
				path = new File("");
			}
			System.out.println("path:"+path.getAbsolutePath());
			File upload = new File(path.getAbsolutePath(),"static/images/upload/");
			if (!upload.exists()) {
				upload.mkdirs();
			}
			System.out.println("upload url:"+upload.getAbsolutePath());
	        File dest = new File(upload.getAbsolutePath() +'/'+ fileName);
	        if (!dest.getParentFile().exists()) {
	            dest.getParentFile().mkdirs();
	        }
	        file.transferTo(dest);
			String filePathNew = dest.getPath();
			System.out.println("save path:"+filePathNew);
			System.out.println(saveUploadFile(fileName));
			return saveUploadFile(fileName);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error : "+e.getMessage());
			return null;
		}
    }
 
    private String saveUploadFile(String filePathNew) {
        String localhost = null;
        try {
        	localhost = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        	System.out.println("get server host Exception e:"+e.getMessage());
        }
        System.out.println(localhost+": insert record="+1+",file="+filePathNew); 
        return http+host+':'+port+imagePath+filePathNew;
    }
    
    @Override
	public Set<Long> uploadImages(MultipartFile[] files) throws HttpServerErrorException{

		Set<Long> imageids = new HashSet<Long>();
		for (MultipartFile file : files) {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = "{"+UUID.randomUUID().toString()+"}"+df.format(new Date());
			System.out.println(fileName);
			String newUrl = getUploadFilePath(file, fileName);
			if (null==newUrl) {
				throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
			}		
			imageids.add(createPlaceImage(newUrl).getId());
		}
		return imageids;
	}
    
    @Override
	public PlaceImage createPlaceImage(String url){
    	PlaceImage placeImage = new PlaceImage();
    	placeImage.setUrl(url);
    	placeImageDao.saveAndFlush(placeImage);
		return placeImageDao.getOne(placeImage.getId());
		
	}

}
