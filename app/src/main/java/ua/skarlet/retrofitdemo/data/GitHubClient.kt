/**
 * Copyright © 2020 by Lavreniuk Vladyslav
 */

package ua.skarlet.retrofitdemo.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubClient {

    @GET("users/{user}/repos")
    fun reposForUser(@Path("user") user: String): Call<List<GitHubRepo>>
}