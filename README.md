# TaipeiZooDemo
#### An android project to show some Taipei zoo information.
- Write in Kotlin language.
- Using MVVM (live data and data binding) to observe data change and update UI.
- Using Jetpack navigation to navigate between fragments.
- Using Room to cache online data.
- Using Retrofit to handle Restful api request and response.
- Using RxAndroid for multi-thread programming.
- Using Glide to handle image and error handling.

#### UI:
- MainActivity.kt: The only one activity, contains all fragments. 
- ZooAreaListFragment.kt: Show whole zoo areas in Taipei zoo.
- ZooAreaDetailFragment.kt: Show the zoo area detail information and plants you can find in this area.
- PlantDetailFragment.kt: Show plant detail information.


#### Data source:
 - Zoo area: https://data.taipei/api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire
 - Plant data: https://data.taipei/api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire
