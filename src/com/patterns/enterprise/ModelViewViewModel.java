package com.patterns.enterprise;

/**
 * All the Model-View-* patterns have goals to increase:
 * 1. Modularity
 * 2. Flexibility
 * 3. Testability
 * 4. Maintainability
 *
 * Model - similar to the Model in MVC
 *
 * View - graphical interface between the user and the design pattern. Similar to the one in MVC.
 *
 * View-Model - an abstraction of the View on one side, and a wrapper of the Model data to be linked.
 *  Comprises a Model that is converted to a View, and it also contains commands that the View can
 *  use to influence the Model.
 *
 * Points:
 * 1. Input is directed to the Controller
 * 2. One to one relationship between the ViewModel and the View
 * 3. ViewModel does not have any knowledge of its View
 * 4. View is not aware of the Model. ViewModel updates the View
 *
 *
 * Notes:
 * Data binding in MVVM creates additional overload that could drastically affect performance when performing
 * complex tasks.
 *
 * MVVM has the best compatibility because of its data binding
 *
 * Changes are less with MVVM and MVP than with MVC in terms of modifiability
 */
public class ModelViewViewModel {
}
