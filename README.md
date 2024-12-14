# FitHub 
#### Note: The UI is buggy for Windows because the app was designed in macOS. I am working on fixing it.

## Introduction

Author: Kushagra Sharma

FitHub is a fitness partner built for individuals looking to establish a structured fitness routine that aligns with their specific goals. Be it gaining muscle, losing fat, or simply maintaining the current physical state, FitHub offers workout and diet plans tailored to user-specific properties like height, weight and weekly workout intensity. The main goal of this project is to promote a healthy and balanced lifestyle and **help** people achieve it.

## User Stories

*Setting up the app as a first-time user:*
- As a user, I want to be able to set my fitness goal (bulk/cut/maintain) and change it any time I want.
- As a user, I want to be able to set my name, height, weight, age and weekly workout intensity.

*The default services the app provides to the user upon setup:*
- As a user, I want to receive a weekly diet plan including daily calorie consumption and ingredients required for individual meals.
- As a user, I want my diet plan to be tailored to my height, weight, age, workout intensity and fitness goal.
- As a user, I want to receive a workout split for the gym.

*Customizable feature that the app offers to the user:*
###### Workout Planner
- As a user, I want to be able to customize my workout split in any way I might need to.
- As a user, I want to be able to add/remove/edit an Exercise (class X) in my Workout routine (class Y).
- As a user, I want to be able to add/remove/edit a Set (class X) completed for an Exercise (class Y).
- As a user, I want to be able to edit weight lifted and reps completed for any Set (class X) completed.
- As a user, I want to receive statistics about (a) my workout split and (b) the individual workouts present in the split.

###### Diet Planner
- As a user, I want to be able to create a specific Meal (class X) to replace with another meal in my Diet Plan (class Y).
- As a user, I want to be able to view the calorific info, the macronutrient info and the list of ingredients for any Meal in my Diet Plan
- As a user, I want to receive statistics about (a) my entire diet plan and (b) any specific day of the diet plan
- As a user, I want to be able to change the name/quantity content of any Meal in my Diet Plan

###### User Profile
- As a user, I want to be able to view/edit my user profile containing my name, height, weight, age, workout intensity, fitness goal
- As a user, I want my user profile to display my BMR and daily recommended calorie intake (calculated for me upon setting up the app)
- As a user, I want my Diet Plan to update accordingly if I change my height, weight, age, weekly workout intensity or fitness goal.

*Data Persistence: *
- As a user, when I select the quit option from the application menu, I want to be reminded to **save**: 
  - any changes I may have made to my user profile
  - any changes I may have made to my workout split
  - any changes I may have made to my diet plan
- As a user, when I start the application, I want to be given the option to **load**: 
  - my user profile (including any changes I may or may not have made to it in the past)
  - my workout split (including any changes I may or may not have made to it in the past)
  - my diet plan (including any changes I may or may not have made to it in the past)

**User Stories -- Part of updates ONLY AFTER January 2025!!!**
- As a user, I want to be able to connect the app to a wearable fitness device so that my health metrics are automatically recorded
- As a user, I want to be able to join a community within the app where I can share my progress

## Instructions for End User
### User Setup
- Upon launching the app, you can either load from a previously saved profile or create your new profile
- Once you're done with the aforementioned step, you will be directed to the main menu. This is your FitHub dashboard
- The Main Menu will have the following options: Workout Tracker, Diet Planner, User Profile, Save, Load, Exit

## Navigating the app
### Workout Tracker: 
Here, you can keep track of your workouts and exercises completed.
- Actions: You can add exercises to all of your workouts, and you can view/edit/delete exercises that are already in your workouts
- Visual Component: You can view your workout statistics including muscle groups worked, sets completed, reps completed and total weight lifted
### Diet Planner: 
Here, you can keep track of your weekly meals consumed.
- Notes: You can NOT delete an existing meal OR add a new meal to your Diet Plan. The Diet Plan strictly adheres to 3 meals per day
- Actions: You can edit every aspect of meals in your diet plan, ranging from Name, Ingredients, Calories, Quantity and Macronutrients
- Visual Component: You can view your meal statistics including graphs and progress bars representing macronutrient information, calorie and quantity consumption.
### User Profile: 
- You can view your user profile, and edit any attribute of your user profile, ranging from Name, Height, Weight, Age, Weekly Workout Intensity and Fitness Goal.
- You can also view other non-editable information like your Body-Mass Index (BMI) and daily recommended calorie consumption based on your attributes.
- Actions: Any changes you make to your user attributes, will be reflected in your meal plan, your meal plan will automatically adjust the quantities of each meal to match your calorie goal. Should you want to change this, you can always go to the diet planner, but by default, the app carries out adjustments for you.
### Misc:
- You can save any changes you made in the Workout Tracker / Diet Planner / User Profile, by coming back to the main menu.
- You can load any changes you may have made in the past from the main menu. This is particularly useful when you make changes that you didn't intend to

## References and citations
The following resources have been referenced and used to create FitHub:
- Oracle's open-source Java Swing Documentation: https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/javax/swing/package-summary.html
- Software Construction principles taught in CPSC 210.
- Prior knowledge of Java gained during High School: ISC (Indian School Certificate) - Computer Science Curriculum

## Phase 4
### Task 2: Event Logging 
#### Sample Event Data (logged after runtime)
*Wed Nov 27 18:02:22 PST 2024*
Loaded User Data From File

*Wed Nov 27 18:02:32 PST 2024*
Viewed Meal Info: Grilled Steak with Sweet Potatoes

*Wed Nov 27 18:02:34 PST 2024*
Viewed Weekly Diet Plan Statistics

*Wed Nov 27 18:02:43 PST 2024*
Removed Exercise From Leg Day: Bulgarian Split-Squats

*Wed Nov 27 18:02:46 PST 2024*
Removed Exercise From Pull Day: Pull-ups

*Wed Nov 27 18:03:23 PST 2024*
Added Exercise To Pull Day: Face Pulls

*Wed Nov 27 18:03:28 PST 2024*
Viewed Workout Split Statistics

*Wed Nov 27 18:03:31 PST 2024*
Viewed Exercise: Face Pulls

*Wed Nov 27 18:04:05 PST 2024*
Updated Exercise Name For Face Pulls To: Back Extensions

*Wed Nov 27 18:04:05 PST 2024*
Added Lower Back To List Of Muscles Worked in Back Extensions

*Wed Nov 27 18:04:05 PST 2024*
Removed Forearms From List Of Muscles Worked in Back Extensions

*Wed Nov 27 18:04:05 PST 2024*
Removed Rear Delts From List Of Muscles Worked in Back Extensions

### Task 3: Reminiscing
#### Design Improvement
If I had more time to complete the project, there are a lot of design changes I would make. I would also add some more features. Here's a comprehensive overview of some thoughts:
- **Poor Modularity and Duplicated Code:** Due to the 25-line cap on the method length, most of the code in DietPlanner.java and WorkoutTracker.java was first written with huge methods reaching over 500 lines of code per method. I then broke those methods down into smaller methods to meet the 25-line cap. This is a very good example of poor design because the helper methods that were generated as a result of breaking down bigger methods were not helpful *anywhere else* in the code. If I had time, I would completely rebuild the aforementioned classes from scratch, and increase modularity in my code. This would not only reduce method lengths but more importantly, it would get rid of most if not all of the duplication. Currently, the aforementioned classes share a lot of duplicated code: Making interactive buttons, adding hover functionality to buttons, creating the base backgrounds and panels for the frame, etc. I would make an abstract class called Organizer.java to lay out the basic structure of both the Diet Planner and the Workout Tracker. I would then have the two classes extend Organizer.java. According to my calculations, this would bring down the length of DietPlanner.java from 1100 lines to about 600, and WorkoutTracker.java from 1600 to approximately 900 lines of code.
- **Singleton Design Pattern:** UserProfile.java is a perfect fit for the Singleton Design Pattern because there is always one user. If I had the time, I would refactor my UserProfile class to use the Singleton Design Pattern and this would make the job of the UI classes way easier. Let me elaborate: The UI uses a state machine. The UserProfile (which also contains the Diet Plan and Workout Split) is the only variable that's passed between these states. For example, the main menu has a class-level variable mainUser. It then calls on to DietPlanner/WorkoutTracker/ConfigureUser with mainUser as the parameter. Instead of having to pass this UserProfile between states, I could simply follow the Singleton Design Pattern and have each of my UI states call on the getInstance() method in UserProfile.java. 
- **Efficiency:** Instead of using Strings and then manually making sure only a certain set of values is used for certain variables like muscles worked, or fitness goal, I would've used enums for these attributes as I did for the State Machine that the UI utilizes.

- **Final Thoughts:** I realized everything I've mentioned so far while working on Phase 4 Task 2. I realized that in some places, I had relied on the UI to perform some operations that should be performed in the model package. That is why I started refactoring some code in the UI package and realized how much simplicity, modularity and efficiency I could've had in my code if during Phase 1, I was familiar with the concepts taught in the Design Module.
With that said, I still am quite happy with how the app turned out. The code, while having the aforementioned flaws, is still very efficient and incredibly easy to understand from a third-person perspective. I had a lot of fun working on this project, it will always carry a special place in my heart.
