package com.tekinarslan.material.sample.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BitmapUtil {

	public static Bitmap compressBitmap(Bitmap bitmap, int litmitSize) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 100;// 个人喜欢从80开始,
		bitmap.compress(Bitmap.CompressFormat.PNG, options, baos);
//		LogUtils.d(TAG, "bitmapSize==" + baos.toByteArray().length / 1024 + "k");
		while (baos.toByteArray().length / 1024 > litmitSize) {
			baos.reset();
			options -= 10;
			bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap compressBitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
//		LogUtils.d(TAG, "compressbitmapSize==" + baos.toByteArray().length/ 1024 + "k");
		return compressBitmap;
	}

}
