# WalkMyDog

![GitHub all releases](https://img.shields.io/github/downloads/kev512/WalkMyDog/total?logo=GitHub&style=flat)
[![Website](https://img.shields.io/website?color=orange&label=Kotlin&up_message=1.4.32&url=https%3A%2F%2Fkotlinlang.org)](https://kotlinlang.org)
![GitHub last commit](https://img.shields.io/github/last-commit/kev512/WalkMyDog?logo=GitHub)
![GitHub repo size](https://img.shields.io/github/repo-size/kev512/WalkMyDog?logo=GitHub)

![project_logo](https://user-images.githubusercontent.com/55996233/144767562-354a58d3-7815-488f-a93b-7f5582aa9a60.png)

Project of the mobile app to walk dogs out of your neighborhood.

---

### Table of Contents

- [Description](#description)
- [Technologies](#technologies)
- [Setup](#setup)
- [For Collaborators - implementation of new features](#for-collaborators---implementation-of-new-features)
- [Authors Info](#authors-info)

---

## Description

The main assumption of this project is to create a mobile app which will implement the business process. The application will be a platform connecting pet owners (dogs) with people who, for a small charge, want to help them in their care and take them for walks.

---

## Technologies

The project is developed using the following technologies:

- Kotlin 1.4.32
- Android Studio 4.1.2

#### API Reference

- minSdkVersion: **Initial release for Android 7.0 (API level 24)** - Android Nougat
- targetSdkVersion: Android 12 (API level 31) - Android Snow Cone

---

## Setup

To run this project on your local device, install it by following these steps:

#### Installation

> - **Step 1:** Android Studio: go to **File > New > Project from Version Control**.
> - **Step 2:** After clicking on the **Project from Version Control** a pop-up screen will arise. In the **Version** control choose **Git** from the drop-down menu.
> - **Step 3:** **Paste the link in the URL** and choose the **Directory** on your local device. Click on the **Clone** button and you are done.
> - **URL:**
             ```
                 https://github.com/kev512/WalkMyDog.git
             ```
> - **Step 4:** Click on the **Clone** button and you are done.

---

## For Collaborators - implementation of new features

#### Attention!

On the **master** branch, should be added only fully tested and working components. Each new feature should be developed on a separate, specially dedicated branch (the name of the branch thematically refers to the added functionality).

#### Adding a new feature

After cloning a project from the repository, by default you will be switched to the master branch (locally on your computer). To start creating new functionality, create a new branch:

> - Android Studio: on the lower right corner, click on **"master" > New Banch**
> - Create a name that will refer to the functionalities you create > **Create**

Now a new branch has been created on your local computer that you can code on.

> - To add changes to an existing branch, you must commit them: **VCS > :heavy_check_mark: Commit...**
> - **Commit Message**: Add a comment that will clearly describe what functionalities have been added by you **> Commit**

To add changes from your local computer to the repository server you must push them:

> - Android Studio: **VCS > Git > Push... > 'branch_name' --> origin : 'branch_name' > Push**

To merge the new branch with the **"master"** branch, perform the **"Compare & pull request"** operation. For security reasons, auto-merge is disabled and requires additional approval. Once your changes are approved, the branch will be merged with the master branch and then deleted.

#### Important!

Whenever you return to work on code, you should update your local project. By default, on your computer, you will be switched to the branch you last worked on.

To update a project:

> - Android Studio: on the lower right corner, click on **"your_branch_name" > switch to master > Checkout**
> - After **master** is checked out, go to: **Git > :arrow_lower_left: Update project... > OK**

After this operation, you will have the latest version of the project. To start working on a new feature, create a new branch.

#### :bangbang::bangbang::bangbang: ERROR FIX: This Version of The Android Support Plugin for IntelliJ IDEA /AS Cannot Open This Project :bangbang::bangbang::bangbang:

If android studio cannot open the project properly and the following message is displayed:

```
This version of the Android Support plugin for IntelliJ IDEA (or Android Studio) cannot open this project, please retry with version XYZ or newer.
```
Go to file **build.gradle (Project: WalkMyDog)** > find section **dependencies** and comment line like is shown below
```
// classpath 'com.android.tools.build:gradle:7.0.4'
```

Then you need to check your current Android studio version: At the top menu go to **Help > About**. After that in **Project Structure** Setting, chose from the dropdown appropriate Android Gradle Pugin Version.
```
classpath 'com.android.tools.build:gradle:4.1.2'
```

:fire: Support YT video: https://www.youtube.com/watch?v=LvCF2ar8xDU

---

## Authors Info

- Wojtech99 :point_right: https://github.com/Wojtech99
- lukaszbrylka :point_right: https://github.com/lukaszbrylka
- kev512 :point_right: https://github.com/kev512
