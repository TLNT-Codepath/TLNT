# TLNT

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)


## Overview
### Description
TLNT is a mobile application that allows employers to match with local talent. Whether employers are looking for artists, actors, or musicians, they'll be able to find them by using TLNT. 

### App Evaluation
- **Category:** Job Searching Application
- **Mobile:** This app will be developed first as a mobile application but could primarily function as a desktop web application, similar to Indeed. 
- **Story:** Allows local talent to apply to project listings from employers. Also allows employers to create project listings and search for talent.
- **Market:** Anyone looking for freelance work in art, acting, or music could use this application.
- **Habit:** This could be an application that freelancers keep coming back to depending on whether employers in their area are frequently seeking talent.
- **Scope:** We will start first with creating the functionality to allow users that are looking for work to view all the listings in their area. They will be able to apply to any of them. We will also allow users that sign-up as employers to create and edit project posts.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

[x]User can sign up for an account and login
[x]User can create a profile as either an employer or talent. If they are talent, they will be able to add relevant information such as a link to their portfolio.
[x]User (both employer and talent) can edit their profile.
[x]An employer can make a post for work that contains a description and the type of talent they need.
[x]An employer can modify/delete their post.
[x]If the user is categorized as talent, they will see a list of all employer posts.
* If the user is categorized as talent, they can apply to the employer post.

**Optional Nice-to-have Stories**

* Users categorized as talent can also create posts. This could be an introduction post or a post that they're job-hunting.
* Users categorized as talent can upload images, videos, or audio clips to their profile. This will serve as their portfolio that employers can view.

### 2. Screen Archetypes

* Login/Signup Screen
   * User can sign up for an account and login
* Profile Creation Screen
   * User can create a profile as either an employer or talent. If they are talent, they will be able to add relevant information such as a link to their portfolio.
* Employer Post Creation Screen
   * An employer can make a post for work that contains a description and the type of talent they need.
   * An employer can modify/delete their post.
* Talent Feed Screen
  * If the user is categorized as talent, they will see a list of all employer posts.
  * If the user is categorized as talent, they can apply to the employer post.
* Profile Screen
  * User (both employer and talent) can edit their profile.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Talent Feed Screen
* Project Post Creation Screen (only visible to employers)
* Profile Screen

**Flow Navigation** (Screen to Screen)

* Talent Feed Screen
   * Project Detail View
   * User can apply to the posting
* Project Post Creation Screen
   * User (employer only) can create a new project post with a description, image, and a list of talent they're looking for.
* Profile Screen
   * User can view and edit their profile (both employer and talent)

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="https://github.com/TLNT-Codepath/TLNT/blob/main/lofi.png" width=600>

### [BONUS] Digital Wireframes & Mockups
<img src="https://github.com/TLNT-Codepath/TLNT/blob/main/hifi.PNG" width=600>

### [BONUS] Interactive Prototype
<img src="https://github.com/TLNT-Codepath/TLNT/blob/main/prototype.gif" width=600>

## Schema 
[This section will be completed in Unit 9]
### Models
User
|Property|Type|Description|
|--------|----|-----------|
|isTalent|Bool|Whether or not user is talent|
|bio|String|User bio|
|profilePic|image|User image|

Post
|Property|Type|Description|
|--------|----|-----------|
|objectId|String|unique id for the user post (default field)|
|author|Pointer to user|Post Author|
|Title|String|Title of the post|
|Description|String|Body of the post|
|contactInfo|String|Contact info for the post|
|createdAt|DateTime|date when post is created (default field)|
|updatedAt|DateTime|date when post is last updated (default field)|


### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
