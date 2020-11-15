package org.cloud.core.net.download;

import org.cloud.core.rx.RxBus;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

/**
 * FileName: ProgressResponseBody
 * Author: Admin
 * Date: 2020/11/14 10:45
 * Description: ProgressResponseBody
 */
public class ProgressResponseBody extends ResponseBody {

    private final ResponseBody responseBody;

    private BufferedSource bufferedSource;
    private String tag;

    public ProgressResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public ProgressResponseBody(ResponseBody responseBody, String tag) {
        this.responseBody = responseBody;
        this.tag = tag;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @NotNull
    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(new ForwardingSource(responseBody.source()) {
                long bytesReaded = 0;
                @Override
                public long read(@NotNull Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    bytesReaded += bytesRead == -1 ? 0 : bytesRead;
                    //使用RxBus的方式，实时发送当前已读取(上传/下载)的字节数据
                    RxBus.getInstance().send(new DownLoadStateBean(contentLength(), bytesReaded, tag));
                    return bytesRead;
                }
            });
        }
        return bufferedSource;
    }
}
