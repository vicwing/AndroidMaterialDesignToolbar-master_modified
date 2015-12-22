
 package com.tekinarslan.material.sample.common.utils;

 import com.google.gson.Gson;

 import org.json.JSONObject;

 /**
  * @ClassName:[UtilsJsonParser]
  * @Description:json解析工具类
  * @author cdj
  * @CreateDate:[2015-2-2 下午2:38:14]
  * @UpdateUser:  UpdateUser
  * @UpdateDate:  [2015-2-2 下午2:38:14]
  * @UpdateRemark: [说明本次修改内容]
  * @version [V1.0]
  */
 public class UtilsJsonParser {
      /**
       * @Description:[]
       * @param response
       * @param clazz
       * @return
       */
      public static  <T>T parseJson(JSONObject response, Class<T> clazz){
             if (response==null) {
                 return null;
             }
              T fromJson = new Gson().fromJson(response.toString(), clazz);
              if (fromJson!=null) {
                 return fromJson;
             }
              return null;

      }
 //	 public static <T> T getImpl(Class<T> clazz) {
 //		  String name = clazz.getSimpleName();
 //		  String className = properties.getProperty(name);// name:简单名称
 //		  try {
 //		   return (T) Class.forName(className).newInstance();
 //		  } catch (Exception e) {
 //		   e.printStackTrace();
 //		  }
 //		  return null;
 //		 }
 //		}
 }
