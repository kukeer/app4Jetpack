package com.ws.hugs.app.picture.paging;

import androidx.annotation.NonNull;

import com.ws.hugs.data.remote.MM131Article;
import com.ws.hugs.data.remote.response.MPageResponse;
import com.xcheng.retrofit.Call;
import com.xcheng.retrofit.Callback;
import com.xcheng.retrofit.HttpError;

public abstract class MM131ArticleCallback implements Callback<MPageResponse<MM131Article>> {

    @Override
    public void onStart(Call call) {

    }

    @NonNull
    @Override
    public HttpError parseThrowable(Call call, Throwable t) {
        HttpError httpError = new HttpError(t.getMessage(),t);
        return httpError;
    }

    @NonNull
    @Override
    public MPageResponse<MM131Article> transform(Call<MPageResponse<MM131Article>> call, MPageResponse<MM131Article> article) {
        return article;
    }


}
