package com.app.idnbin.util;


import com.app.idnbin.Profile.Support.Model.BotReply;
import com.app.idnbin.Profile.Support.Model.SupportRequestBody;
import com.app.idnbin.MarketAnalysis.Newsfeed;
import com.app.idnbin.democall.BidData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiIterface {

    @GET("u/11158660/rss.xml")
    Call<Newsfeed>getnewsdata();

    @GET("device/rss/rss.html")
    Call<Newsfeed> getearningdata();

    @GET("forex-economic-calendar-events")
    Call<Newsfeed> getforexdata();

    @GET("quotes?pairs=eurusd&api_key=B70qKwu64eVlzwfIwe900xhT2Pn6KvZS")
    Call<ArrayList<BidData>> getBidData();

    @POST("bots/idnbot/converse/1")
    Call<BotReply> getSupportData(@Body SupportRequestBody supportRequestBody);
}
