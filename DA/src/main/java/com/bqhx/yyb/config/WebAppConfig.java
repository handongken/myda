package com.bqhx.yyb.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 修改配置
 * @author Administrator
 *
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
	@Value("${uploadfiles.path}")
	private String uploadfilespath;
	@Value("${uploadfiles.url}") 
	private String uploadfilesurl;
	
	/**
	 * 将${uploadfiles.path}修改虚拟路径为${uploadfiles.url}
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
    	if(uploadfilespath.equals("") || uploadfilespath.equals("${uploadfiles.path}")){
    		String imagesPath = WebAppConfig.class.getClassLoader().getResource("").getPath();
    		if(imagesPath.indexOf(".jar")>0){
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            }else if(imagesPath.indexOf("classes")>0){
                imagesPath = "file:"+imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
    		imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + uploadfilesurl;
    		uploadfilespath = imagesPath;
    	}
    	LoggerFactory.getLogger(WebAppConfig.class).info("imagesPath="+uploadfilespath);
    	String imageUrl = uploadfilesurl + "**";
    	LoggerFactory.getLogger(WebAppConfig.class).info("imageUrl="+imageUrl);
    	registry.addResourceHandler(imageUrl).addResourceLocations(uploadfilespath);
        super.addResourceHandlers(registry);
    }
}
