# RapiDapi

Android App using The Rapid API


## Description

a simple app that contains some basic functionality according to Code assignments. It connects to the Rapid DB API and fetch search&filter services.


## Tech Stack
- Dagger 2 - Used to provide dependency injection
- Retrofit 2 - OkHttp3 - request/response API
- Glide - for image loading.
- RxJava 2 - reactive programming paradigm
- LiveData - use LiveData to see UI update with data changes.
- Data Binding - bind UI components in layouts to data sources
- Navigation Component - handling navigation action 

## Overview of app arch.
- follow the rules from Architecture guidelines recommended by Google.
- keep Activity only responsible for manage Bottom Navigation 
- keep Fragment only responsible for UI related code 
- ViewModel provides data required by the UI class
- Repository layer provides data to ViewModel classes. (single source of truth)
- Navigate each fragment with Navigation Component
