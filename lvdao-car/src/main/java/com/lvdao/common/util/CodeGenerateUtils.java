package com.lvdao.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.lvdao.common.CommonConst;

public class CodeGenerateUtils {
	
	public static void main(String[] args) {

    	try {
    		List<String> resultList = new ArrayList<String>();
    		
    		boolean overwrite = true;
    		
    		File configFile = new File(CodeGenerateUtils.class.getResource(CommonConst.PATH_CODE_CONFIG_FILE).getFile());
    		
    		ConfigurationParser cp = new ConfigurationParser(resultList);
    		
    		Configuration config = cp.parseConfiguration(configFile);
    		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    		
    		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, resultList);
    		myBatisGenerator.generate(null);
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
