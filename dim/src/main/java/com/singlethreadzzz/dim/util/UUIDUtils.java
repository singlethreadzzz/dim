package com.singlethreadzzz.dim.util;

import java.util.UUID;

public abstract class UUIDUtils {
	
    /**
     * UUID生成方法
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
   }

}
