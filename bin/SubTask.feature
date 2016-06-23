Feature: Create SubTask
  As a ToDo App user
  I should be able to create a subtask
  So I can break down my tasks in smaller pieces

  Scenario: The user should see a button labeled as ‘Manage Subtasks’
    Given user navigates to "http://qa-test.avenuecode.com/"
    When user logs in using Username as "avenuetester111@gmail.com"
    When password as "master$123"
    Then login should be "successful"
    Then user should see "My Tasks"
    When Userclick the link "My Tasks"
    When enter the new task "uma" by hitting "enter"
    Then user should see "Manage Subtasks"
    
  Scenario: This button should have the number of subtasks created for that tasks
    Given user navigates to "http://qa-test.avenuecode.com/"
    When user logs in using Username as "avenuetester111@gmail.com"
    When password as "master$123"
    Then login should be "successful"
    Then user should see "My Tasks"
    When Userclick the link "My Tasks"
    When enter the new task "uma" by hitting "enter"
    Then user should see "Manage Subtasks"
		Then verify No of subtasks created for that tasks
		
	Scenario: Clicking this button opens up a modal dialog
    Given user navigates to "http://qa-test.avenuecode.com/"
    When user logs in using Username as "avenuetester111@gmail.com"
    When password as "master$123"
    Then login should be "successful"
    Then user should see "My Tasks"
    When Userclick the link "My Tasks"
    When enter the new task "uma" by hitting "enter"
    Then user should see "Manage Subtasks"
		When Userclick the link "Manage Subtasks"
		Then verify the task ID and the task description should be disabled
		Then User able to add subtask with two fifty chars and MMDDyyyy format data and see the subtask is appended
		But The Task Description and Due Date are required fields