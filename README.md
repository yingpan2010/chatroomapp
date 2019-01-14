<<<<<<< HEAD
# chat room demo

#### Description
Build a tiny chatting room web application

#### Software Architecture
Maven, Springboot hibernate mysql

#### Installation

#### Instructions
The project is already build by maven, go to the target fold and run the jar file.
localhost:8080/chatRoomTest.html

## function：
* allowed to create multiple chat rooms(user who enter the roomId at first time is the host)
* info for entering/leaving the room
* get the username by using sessionId in a ConcurrentHashMap
* generate a random username for each user
* know the active user

## TODO List
* Read more springboot document and improve the project(first time use springboot)
* write SchemaExport in test, generate database by SchemaExport
* connect to database
* finish the MVC structure, make the restful API
* add cache, search chat content
* convert spring style logger to springboot
* Offline message
* @Someone
* write edge cases

## change list:
* fixed logging: use log4j2 with spring boot
* make the UI fancy
* spring security to set authorization for different room
* Javadoc