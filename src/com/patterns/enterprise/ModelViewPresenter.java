package com.patterns.enterprise;

/**
 * All the Model-View-* patterns have goals to increase:
 * 1. Modularity
 * 2. Flexibility
 * 3. Testability
 * 4. Maintainability
 *
 * Model - represents set of classes that describes the busines logic and data. Defines business rules for how
 *  data can be manipulated and changed
 *
 * View - used for making interactions with users like XML, Activity, etc. No logic
 *
 * Presenter - gets input from View, processes the data with the help of the Model and passes the results back
 *  the View after processing is done.
 *
 * Points:
 * 1. Input is directed to the View
 * 2. One to one relationship between the View and the Presenter
 * 3. View holds the reference to its Presenter and the Presenter is aware of the View
 * 4. View is not aware of the Model. Presenter updates the Model.
 *
 * Notes:
 * Of the 3 MV* patterns, MVP has the highest reliability and lowest hindrance when it comes to rendering frames
 *
 * MVP has better compatibility than MVC
 *
 * Changes are less with MVVM and MVP than with MVC in terms of modifiability
 */
public class ModelViewPresenter {
}
