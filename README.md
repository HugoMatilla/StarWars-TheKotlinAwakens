![badge](https://img.shields.io/badge/version-1.0.0-green.svg)

Android app in Kotlin implementing Clean Architecture with data from The Wookieepedia.
Based on Fernando Cejas' [Post](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/) and	 Antonio Leiva's [Book](https://github.com/antoniolg/Kotlin-for-Android-Developers) 

Suggestions and comments are more than welcome :smiley:

![demo](./art/swkotlin.gif)

# Clean Architecture

Uncle Bob's [Post](http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html)  
Fernando Cejas' [Post](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)  

# Domain Layer
The Domain layer is a java module that is in charge of the business logic of the application.   
Anything that is not retrieving data or showing data to the user should be in this layer.   
Main citizens of this layer are the use cases. They implement the a Command that receives the type of the data that is going to be return and a callback.   
```kotlin

	interface IDoStuffUseCase : Command<ReturnType?, IDoStuffUseCase.Callback> {

	    interface Callback {
	        fun onDoStuffSuccess(result: ReturnType?)
	        fun onError(error: ErrorBundle)
	    }

	    override fun execute(callback: Callback)
	}

```

A simple implementation should be like   

```kotlin

	class DoStuffUseCase() : IDoStuffUseCase {

	    override fun execute(callback: IDoStuffUseCase.Callback) {
	        try {
	            val result = doStuff()
	            callback.onDoStuffSuccess(result)
	        } catch(e: Exception) {
	            callback.onError(DefaultError(e))
	        }
	    }
	}
```
# Data Layer
The data layer is in charge of retrieving  and save data either locally or in the cloud.   
It implements the repository pattern (well, my idea of it). There are a repository class per use case. The interface is part of the Domain layer and the interface is a contract between the use case and the repository that define the functions(queries) that the use case can call.   
 
The repository is in charge of implementing and deciding what to do to perform the query. i.e: get data from the database, if there is no data, get it from the cloud and save it in the database.  

(No Retrofit and no ORMs) The Database and Cloud functionalities are done without using external libraries just Anko and Gson.  

#Presentation Layer (App) 
Implements the MVP architecture. Well there is no Model, it uses the Domain models. For now the Presenter layer uses the same data types.

The Presenters are initialized in the Views, and the views are passed to the presenter.  
The Presenters are in charge of the application [threading](#threading).   
All Presenters must implement the `Presenter` abstract class and include the View which they interact. In order to implement it  they also have to create an interface that inherits  from `Presenter.View` that is the contract between the View and the Presenter.  

```kotlin
	
	class StuffListPresenter(view: StuffListPresenter.View) : 
		Presenter<StuffListPresenter.View>(view), IStuffListPresenter {
		...

	    interface View : Presenter.View {
	        fun showStuff(stuff: List<Stuff>)
	    }
    }
```
```kotlin

    class StuffListView : StuffListPresenter.View {
    	...
    }

```

# Threading
The presenter is the one in charge of changing code execution the UI main thread to a different one.   
It uses anko's `async` and `uiThread`  to change from different threads   
Is the developers responsibility to add this in the presenters.  
```kotlin

	 fun doStuff(someParameter: Parameter) {
        async() {
            DoStuffUseCase(someParameter).execute(
            	object : IDoStuffUseCase.Callback {
                       
                        override fun onStuffDone(result: Result) {
                            uiThread {
                                view.showResult(result)
                            }
                        }

                        override fun onError(error: Error) {
                            uiThread {
                                view.showError(error)
                            }
                        }

                    }
                )
        }
    }

```

# Error Handling
Each layer behave with the errors as they wish, recovering, or passing it.  
The Data layer throw exceptions that the Usecase should catch.  
The Use Case must convert exceptions into errors that the presenter can understand.  
Use cases callbacks have an onError function.  
The presenter must decide weather to show or not a message in the view, if it receive an error from the use case.  

