# Project 2 - *Articale Search*

**Name of your app** is an android app that allows a user to search for articles on web using simple filters. The app utilizes [New York Times Search API](http://developer.nytimes.com/docs/read/article_search_api_v2).

Time spent: **36** hours spent in total

## User Stories

The following **required** functionality is completed:

* [ok] User can **search for news article** by specifying a query and launching a search. Search displays a grid of image results from the New York Times Search API.
* [ok] User can click on "settings" which allows selection of **advanced search options** to filter results
* [ok] User can configure advanced search filters such as:
  * [ok] Begin Date (using a date picker)
  * [ok] News desk values (Arts, Fashion & Style, Sports)
  * [ok] Sort order (oldest or newest)
* [ok] Subsequent searches have any filters applied to the search results
* [ok] User can tap on any article in results to view the contents in an embedded browser.
* [ok] User can **scroll down to see more articles**. The maximum number of articles is limited by the API search.

The following **optional** features are implemented:

* [ok] Implements robust error handling, [check if internet is available](http://guides.codepath.com/android/Sending-and-Managing-Network-Requests#checking-for-network-connectivity), handle error cases, network failures
* [ok] Used the **ActionBar SearchView** or custom layout as the query box instead of an EditText
* [ok] User can **share an article link** to their friends or email it to themselves
* [ok] Replaced Filter Settings Activity with a lightweight modal overlay
* [ok] Improved the user interface and experiment with image assets and/or styling and coloring

The following **bonus** features are implemented:

* [ok] Use the [RecyclerView](http://guides.codepath.com/android/Using-the-RecyclerView) with the `StaggeredGridLayoutManager` to display improve the grid of image results
* [ok] For different news articles that only have text or only have images, use [Heterogenous Layouts](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) with RecyclerView
* [ ] Use Parcelable instead of Serializable using the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).
* [ok] Leverages the [data binding support module](http://guides.codepath.com/android/Applying-Data-Binding-for-Views) to bind data into layout templates.
* [ok] Replace all icon drawables and other static image assets with [vector drawables](http://guides.codepath.com/android/Drawables#vector-drawables) where appropriate.
* [ok] Replace Picasso with [Glide](http://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en) for more efficient image rendering.
* [ok] Uses [retrolambda expressions](http://guides.codepath.com/android/Lambda-Expressions) to cleanup event handling blocks.
* [ok] Leverages the popular [GSON library](http://guides.codepath.com/android/Using-Android-Async-Http-Client#decoding-with-gson-library) to streamline the parsing of JSON data.
* [ok] Leverages the [Retrofit networking library](http://guides.codepath.com/android/Consuming-APIs-with-Retrofit) to access the New York Times API.
* [ok] Replace the embedded `WebView` with [Chrome Custom Tabs](http://guides.codepath.com/android/Chrome-Custom-Tabs) using a custom action button for sharing. (_**2 points**_)

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/2YkMxnW.mp4' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.


## Open-source libraries used
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [ButterKnife] (https://github.com/JakeWharton/butterknife) - Field and method binding for Android views which uses annotation processing to generate boilerplate code for you
- [Rxjava] (https://github.com/ReactiveX/RxJava) -  Reactive Extensions for the JVM
- [Dagger] (https://github.com/square/dagger) - A fast dependency injector for Android and Java.
- [retrofit] (https://github.com/square/retrofit) - Type-safe HTTP client for Android and Java by Square, Inc.
- [Okhttp] (https://github.com/square/okhttp) - An HTTP+HTTP/2 client for Android and Java applications.



## License

    Copyright 2016 BeyonderVN

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
