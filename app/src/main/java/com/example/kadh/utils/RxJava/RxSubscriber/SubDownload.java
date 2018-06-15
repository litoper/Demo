package com.example.kadh.utils.RxJava.RxSubscriber;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.subscribers.DefaultSubscriber;
import okhttp3.ResponseBody;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/15
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public abstract class SubDownload<T> extends DefaultSubscriber<T> {

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        onFail(t);
        this.cancel();
    }

    @Override
    public void onComplete() {
        this.cancel();
    }

    //下载成功的回调
    public abstract void onSuccess(T t);

    //下载失败回调
    public abstract void onFail(Throwable throwable);

    //下载进度监听
    public abstract void onProgress(int progress, long total);


    /**
     * 将文件写入本地
     *
     * @param responseBody 请求结果全体
     * @param savePath     目标文件夹
     * @param fileName     目标文件名
     * @return 写入完成的文件
     * @throws IOException IO异常
     */
    public File saveFile(ResponseBody responseBody, String savePath, String fileName) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = responseBody.byteStream();
            final long total = responseBody.contentLength();
            long sum = 0;

            File dir = new File(savePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, fileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                //这里就是对进度的监听回调
                onProgress((int) (finalSum * 100 / total), total);
            }
            fos.flush();
            return file;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
