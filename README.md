# PlanenSAS App Android


This is an app for test knowledge about android using the Archiquecture MVVM(model-View-ViewModel), cryptography, and loggin session.

# New Features!

  - for check

### Plugins

la app is built based on the next nice libraries.

| Plugin | README |
| ------ | ------ |
| ButterKnife (Binding) |             [README.md][PlDb] |
| ViewModel and LiveData |  [README.md][PlGh] |
| Room DB |                 [README.md][PlGd] |
| retrofit |                [README.md][PlOd] |
| drawable library |        [README.md][PlMe] |
| iconify (icons)|                 [README.md][PlGa] |
| retrolambda |             [README.md][PlRL] |
| aspectj  (AOP Paradigm)|                 [README.md][PlAJ] |
| Fresco By FACEBOOK (Images)|      [README.md][PlFresco] |


### Development issues
-  aspectj https://github.com/Archinamon/android-gradle-aspectj/issues/80#issuecomment-441564585 (can't uses <AGP 3.0.1)
-  ButterKnife is used instead of Databind because it was easier, databind is easy in a way but tricky to implement in two ways binding (the database uses only androidx conflicts)
-  drawable library is more flexible for make over the standar implementation

### TODOs/pendientes

 - update the app using NetworkBoundResource class https://proandroiddev.com/android-architecture-components-network-awareness-using-livedata-1a8d3749734d, for check the data saved by the user and sync the room DB
 - develop Testing code
 - migrate all dependecies to AndroidX
 - generate version based on kotlin
 - improve App reciclerview using Diffutil https://medium.com/@iammert/using-diffutil-in-android-recyclerview-bdca8e4fbb00
 - foreinkeys from table states and clientTable->estado


License
----

WTF


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [PlDb]: <https://jakewharton.github.io/butterknife/>
   [PlGh]: <https://developer.android.com/jetpack/docs/guide>
   [PlGd]: <https://developer.android.com/topic/libraries/architecture/room>
   [PlOd]: <https://square.github.io/retrofit/>
   [PlMe]: <https://github.com/mikepenz/MaterialDrawer>
   [PlGa]: <https://github.com/mikepenz/Android-Iconics>
   [PlRL]: <https://github.com/evant/gradle-retrolambda>
   [PlAJ]: <https://github.com/Archinamon/android-gradle-aspectj>
   [PlFresco]: <https://frescolib.org/>
