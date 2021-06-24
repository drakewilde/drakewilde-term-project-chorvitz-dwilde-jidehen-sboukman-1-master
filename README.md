# cs0320 Term Project 2020

**Team Members:** Jordan Idehen, Cole Horvitz, Solomon Boukman, Drake Wilde

**Team Strengths and Weaknesses:** 
- Jordan 
Strengths: SQL, Databases, HTML/CSS, Java
Weakness: Algorithms, Aesthetics

- Cole
Strengths: Front-end development, graphic design, web design
Weaknesses: Servers, Back-end development 

- Drake
Strengths: Back-end development, Algorithms
Weaknesses: Front-end development, HTML/CSS

- Solomon
Strengths: Algorithms, Git, Routing
Weeknesses: graphic design, SQL, code organization

**Project Idea(s):** _Fill this in with three unique ideas! (Due by March 2)_
### Idea 1
_Approved - this is a great idea, but you'll have to flesh out the matching algorithm more for the class_

Our first idea is an application designed to aid the Brown HOPE organization (Housing Opportunities for People Everywhere). Finding public hoising for homeless individuals in Rhode Island can be a difficult task because each housing establishment uses different criteria to determine who they will accept and deny. Using data collected by HOPE, we plan to create a database and website that enables members of HOPE to weigh an individual's housing options based on their profile and tell them where they are most likely to be accepted. 

The critical features we will need to devlop are a database, an alogrithm to determine the best matches between housing units and individuals, and a user interface. We believe those three features are the most critical to making the website useful, however, there will likely be additional features we will add along the process. The most challenging aspects of the features will be organizing the data for the database, creating an accurate and reliable algorithm for matching, and making an intuitive yet effective user interface. 

### Idea 2
_Rejected - no algorithm_

Our second idea is a social media platform for Brown students aimed at fostering creative collaboration. Brown students of many different backgrounds are partaking in research and other creative projects that many other students, outside of their immediate friend groups or classes, do not know about. Our platform would aim to be a space where students could conversate and post about their research, projects, or general interests. This may sound like any other social media platform right now, but ours would differentiate itself through a variety of features to ensure a positive and productive user experience.

The critical features to be implemented for our application would be a campus email verification system, an anonyminity posting feature, a dynamic main "timeline" with sub-"timelines", a database to keep track of user accounts and posts,and a user friendly interface. In terms of email verification we would need to connect to the Brown email verification system, this can be done with the help of Brown Computing Services. Our anonyminity feature would only allow users to post under an anonymous alias to encourage users to post what genuinely interests them without judgement from others. This would be implemented as a alias feature of a user account. The dynamic "timeline" would be a heap sorted by most liked posts displayed on the webserver from top to bottom of the screen. This aspect of the application would only serve as the main page, users will be able to navigate to the sub-"timelines" to see posts of a certain catagory. These would be diplayed just as the page however these features would be implemented with a queue. We also will have to be keeping track of the post and profile data through databases that update with information our webserver. Lastly, we would ensure a user friendly interface for easy navigation and access for students with HTML, CSS, and JavaScript. 

### Idea 3
_Rejected - no algorithm_

Another idea is a web application to match Brown Students and other people who use Brown facilities who may want to play organized team sports at the Nelson or other locations. By entering a queue with specified logisitics such as location, available time, number of players, game length, this web application would allow people with limited time and busy schedules to quikcly play short games at sporadic times during the day by enetering their preferences. 

The critical features would be a priority queue of some sort as well as a recommendation feature; I suspect that there may be low usage within the application and if preferences do not match up, students should still find some way to play. There would also be other complications, mainly if other students who are not affiliated with the application are using the space that people want to. This may be solved by working with the Brown Atheltics department, however it does seem hard to implement.

**Mentor TA:** _Put your mentor TA's name and email here once you're assigned one!_

## Meetings
_On your first meeting with your mentor TA, you should plan dates for at least the following meetings:_

**Specs, Mockup, and Design Meeting:** _(Schedule for on or before March 13)_

**4-Way Checkpoint:** _(Schedule for on or before April 23)_

**Adversary Checkpoint:** _(Schedule for on or before April 29 once you are assigned an adversary TA)_


## Motivation and Project Notes
The coronavirus has affected all of us greatly and has disrupted lives in an unprecedented manner. Given the spread of the disease, we started to notice something: humans are kind of like nodes. The disease spreads at a certain rate depending on social distance. We decided to use this metaphor to convey a commentary on the coronavirus. Specifically, we want to promote social distancing. This project uses graph nodes on a grid, symbolizing people,  whose population has been infected by a virus. Edge weights are the distance between two nodes. The virus spreads to its nearest neighbor and grows exponentially. 

## How to Build and Run
1. On the root folder, start the backend by running `./run` 
2. Navigate to /src/main/resources/static/js/app and run `npm install` to gain any missing dependencies. 
3. The backend should be hosted on localhost:4567. If this is not the case, change the string in the Javascript file `/app/src/components/App.js`
4. Begin the React server by running `npm start` A browser will render the program.

