package com.patterns.enterprise;

/**
 * All the Model-View-* patterns have goals to increase:
 * 1. Modularity
 * 2. Flexibility
 * 3. Testability
 * 4. Maintainability
 *
 * Model - responsible for the business logic of the application. Manages state of the application.
 *  Read and writes data, persist state, network, validate
 *
 * View - presenting data to the user, and handling user interaction
 *
 * Controller - view layer and model layer are glued together by one or more of these controllers
 *
 * Points:
 * 1. Input is directed to the Controller
 * 2. Many to many relationship between the View and the Controller
 * 3. View does not have any knowledge of the Controller
 * 4. View is aware of the Model it is expecting to pass on to it
 *
 * Notes:
 *
 * Better performance than MVVM
 *
 * Worse compatibility between MVVM and MVP
 *
 * MVC increase the number of changes when it comes to modifiability.
 */
public class ModelViewController {
}
