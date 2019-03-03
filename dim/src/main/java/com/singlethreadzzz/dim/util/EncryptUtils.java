package com.singlethreadzzz.dim.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public abstract class EncryptUtils {
	
	/**
	 * 自定义保存密码的加密方式
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String shiroSHA256(String userName, String password) {
		
		return new SimpleHash("SHA-256", password, ByteSource.Util.bytes(userName + "LoveLive"), 2).toString();
		
	}

}
