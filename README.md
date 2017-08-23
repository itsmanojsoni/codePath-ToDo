# codePath-ToDo

# Pre-work - SimpleToDo 

SimpleToDo is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Manoj Kumar Soni

Time spent: 30 hours spent in total

## User Stories

The following  functionality is completed:

* [X] User can successfully add and remove items from the todo list
* [X] User can tap a todo item in the list and bring up an edit screen for the todo item and then have any changes to the text reflected in the todo list.
* [X] User can persist todo items and retrieve them properly on app restart

The following features are implemented:

* [X] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [X] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [X] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [X] Add support for selecting the priority of each todo item (and display in listview item)
* [X] Tweak the style improving the UI / UX, play with colors, images or backgrounds


## Video Walkthrough

I have uploaded video in the same github repo https://github.com/itsmanojsoni/codePath-ToDo/device-2017-08-22-203816.mp4. This video describes how the app works. 

The app has very simple screens to edit and save the data. It works almost like how it was described in the Pre-Work page. 

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."
Answer : It has been an awesome experience so far working on the Android app development. 
The platform is continuously evolving and with new set of tools, 
it has become much easier and much more efficient to develop an app on Android. 

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

Answer: I did not use Array Adapter as such. I have used a custom adapter with a recycler View. 
Adapter is a great tool which is used to provide data to the view. We can isolate data from the view this way 
and make the process of displaying different data items much more efficient. 

## Notes

Describe any challenges encountered while building the app.
Answer. One of the main challenges was the time constraint that I had. 
I had to trave to India to visit my family and I could not get much time to spend to improve the UX.
I wanted to improve the UI of the action bar especially, but did not have enough time for that. 
Also, I did not give enough thought about the design of the app initially because I wanted to finish it faster. 
I wanted to write a very efficient app but using so many fragments made it a little bit more verbose 
than it should have been. I think given more time, I would like to design much better UX for the app. 
## License

    Copyright [2017] [Manoj Kumar Soni]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
