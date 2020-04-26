package com.ankit.assignment;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitInterface {

    @GET("kekiz_api/actions.php?action=get_cakes&&category=27")
    Observable<CakesListResponse> getCakesLists();

}
