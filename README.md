# ChatRoom

## 1. Features

1. Multi-Client Chat
2. Login and Registration System
3. GUI
4. Web Application

## 2. How do I Implement them

1. For multi-client chat, I use websocket provided by Java and Vue. I write two classes Websocket Config and Controller to implement this.
2. For login and registration system, first, I use JPA provided by Spring Boot to access MySQL. Second, I use POST to send user's data from front-end to back end. Third, in the front-end, I use axios provided by Vue to post data.
3. for GUI, I use some components of Element UI (https://element.eleme.cn/#/zh-CN).
4. for Web, I use Spring Boot as back-end and Vue as front-end.

## 3. How to run my code

1. create a database named chat

2. in this database, create a table named user, it has 5 columns, uid, uname, password, email, address.

   ```sql
   CREATE TABLE user
   (
       uid int(10) primary key NOT NULL AUTO_INCREMENT,
       uname varchar(30) NOT NULL,
       password varchar(255) NOT NULL,
       email varchar(255) NOT NULL,
       address varchar(255) NOT NULL,
       UNIQUE (uname)
   );
   ```

3. resources/applications.properties , in this file, you can change the server port and the user and password. My server port is 8004, if you can't use this one, you can change. If you change the server port, open front/src/views/home/ChatPage.vue , go to line 46 to change the port.
4. install Node.js. open cmd, and input `npm install -g @vue/cli` to install vue, if this doesn't work, you can use `cnpm install -g @vue/cli`, you can check by input `vue -V`, my version is @vue/cli 5.0.8. Then open the front folder in cmd, input `npm install`  or `cnpm install`to install dependencies.
5. To run my project, first run ChatApplication, then open front folder, input `npm run serve` in cmd.
