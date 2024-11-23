# FitHub 

## Introduction

Author: Kushagra Sharma

This personal project was created as a part of CPSC 210: Software Construction at The University of British Columbia. The project is developed in Java, employing various software design principles and concepts taught in class. The application aims to provide a practical and optimal solution for anyone ranging from fitness enthusiasts to absolute beginners.

**Why is this project of interest to me?**

The most important reason I chose this project is that I relate to what it feels like trying to maintain a healthy lifestyle as a university student. Having to eat right and hit the gym while also focusing on academics and maintaining grades is not easy. (not to mention the forever-lasting job search)
Secondly, I have been losing weight over the summer of 2024. My two closest friends also hit the gym: one of them is trying to gain muscle and the other one maintains his physique. The app offers three "paths": bulk, cut, and maintain. I have a deep understanding of all 3 of those paths due to my friends, so I chose this project to implement this knowledge and try to help others.

## What is FitHub and what will it do?

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

*Data Persistence: (Phase 2)*
- As a user, when I select the quit option from the application menu, I want to be reminded to **save**: 
  - any changes I may have made to my user profile
  - any changes I may have made to my workout split
  - any changes I may have made to my diet plan
- As a user, when I start the application, I want to be given the option to **load**: 
  - my user profile (including any changes I may or may not have made to it in the past)
  - my workout split (including any changes I may or may not have made to it in the past)
  - my diet plan (including any changes I may or may not have made to it in the past)

*Features that are planned for FUTURE PHASES:*
- As a user, I want to be able to add/remove diet plans I followed to my weekly food consumption log.
- As a user, I want to be able to add/remove workouts I complete, to my weekly workout log.
- As a user, I want to be able to view my weekly logs to track my progress whenever I need/want to.
- As a user, I want to be able to view my workout splits and diet plans.
- As a user, I want to be able to save my progress data and all customized plans generated for me.
- As a user, I want to be able to load any data I may have saved in the past.

**User Stories -- Part of updates ONLY AFTER January 2025!!!**
- As a user, I want to be able to connect the app to a wearable fitness device so that my health metrics are automatically recorded
- As a user, I want to be able to join a community within the app where I can share my progress

## Instructions for End User
### User Setup
- Upon launching the app, you can either load from a previously saved profile or create your own new profile
- Once you're done with the aforementioned, you will be directed to the main menu. This is your FitHub dashboard
- The Main Menu will have the following options: Workout Tracker, Diet Planner, User Profile, Save, Load, Exit

## Navigating the app
### Workout Tracker: 
Here, you can keep track of your workouts and exercise completed.
- Actions: You can add exercises to all of your workouts, and you can view/edit/delete exercises that are already in your workouts
- Visual Component: You can view your workout statistics including muscle groups worked, sets completed, reps completed and total weight lifted
### Diet Planner: 
Here, you can keep track of your weekly meals consumed.
- Notes: You can NOT delete an existing meal OR add a new meal to your Diet Plan. The Diet Plan strictly adheres to 3 meals per day
- Actions: You can edit every aspect about meals in your diet plan, ranging from Name, Ingredients, Calories, Quantity and Macronutrients
- Visual Component: You can view your meal statistics including graphs and progress bars representing macronutrient information, calorie and quantity consumption.
### User Profile: 
- You can view your user profile, and edit any attribute of your user profile, ranging from Name, Height, Weight, Age, Weekly Workout Intensity and Fitness Goal.
- You can also view other non-editable information like your Body-Mass Index (BMI) and daily recommended calorie consumption based on your attributes.
- Actions: Any changes you make to your user attributes, will be reflected in your meal plan, your meal plan will automatically adjust the quantities of each meal to match your calorie goal. Should you want to change this, you can always go the diet planner, but by default, the app carries out adjustments for you.
### Misc:
- You can save any changes you made in the Workout Tracker / Diet Planner / User Profile, by coming back to the main menu.
- You can load any changes you may have made in the past from the main menu. This is particularly useful when you make changes that you didn't intend to

## References and citations
The following resources have been referenced and used to create FitHub:
- Oracle's open source Java Swing Documentation: https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/javax/swing/package-summary.html
- Software Construction principles taught in CPSC 210.
- Prior knowledge of Java gained during High School: ISC (Indian School Certificate) - Computer Science Curriculum