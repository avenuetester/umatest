Feature: Create Task
  
  As a ToDo App user
  I should be able to create a task
  So I can manage my tasks

  Scenario: The user should always see the "My Tasks" link on the NavBar
    Given user navigates to "http://qa-test.avenuecode.com/"
    When user logs in using Username as "avenuetester111@gmail.com"
    When password as "master$123"
    Then login should be "successful"
    Then user should see "My Tasks"
    When Userclick the link "My Tasks"
    Then user should see the created tasks "" so far
    
   Scenario: The user should see a message on the top part saying that list belongs to the logged user
    Given user navigates to "http://qa-test.avenuecode.com/"
    When user logs in using Username as "avenuetester111@gmail.com"
    When password as "master$123"
    Then login should be "successful"
    Then user should see "My Tasks"
    When Userclick the link "My Tasks"
    Then user should see the message "UmaRao"'s ToDo List 
    
    Scenario: The user should be able to enter a new task by hitting enter or clicking on the add task button
    Given user navigates to "http://qa-test.avenuecode.com/"
    When user logs in using Username as "avenuetester111@gmail.com"
    When password as "master$123"
    Then login should be "successful"
    Then user should see "My Tasks"
    When Userclick the link "My Tasks"
    When enter the new task "task100" by hitting "enter"
    When enter the new task "regression" by hitting "add task button"
    Then user should see the created tasks "regression" so far
  
   Scenario: The task should require at least three characters so the user can enter it
    Given user navigates to "http://qa-test.avenuecode.com/"
    When user logs in using Username as "avenuetester111@gmail.com"
    When password as "master$123"
    Then login should be "successful"
    Then user should see "My Tasks"
    When Userclick the link "My Tasks"
    When enter the new task "ta" by hitting "enter"
    Then verify the task lenght in the tasks list
    
    Scenario: The task can’t have more than 250 characters
    Given user navigates to "http://qa-test.avenuecode.com/"
    When user logs in using Username as "avenuetester111@gmail.com"
    When password as "master$123"
    Then login should be "successful"
    Then user should see "My Tasks"
    When Userclick the link "My Tasks"
    When enter the new task "pack my box with five dozen liquor jugs pack my box with five dozen liquor jugs pack my box with five dozen liquor jugs pack my box with five dozen liquor jugs pack my box with five dozen liquor jugs pack my box with five dozen liquor jugs pack my box with five dozen liquor jugs pack my box with five dozen liquor jugs pack my box with five dozen liquor jugs pack my box with five dozen liquor jugs " by hitting "enter"
    Then verify the task lenght in the tasks list
    
    Scenario: added task should be appended on the list of created tasks
    Given user navigates to "http://qa-test.avenuecode.com/"
    When user logs in using Username as "avenuetester111@gmail.com"
    When password as "master$123"
    Then login should be "successful"
    Then user should see "My Tasks"
    When Userclick the link "My Tasks"
    And User counts the list of created taks "before"
    When enter the new task "smoke " by hitting "enter"
     And User counts the list of created taks "after"
    Then verify appended task


    