# WaterPipe
[_**Dmitriy Liakhov**_](https://www.linkedin.com/in/dmitiy-liakhov-82388a183/)<br>
[dimaliahov@gmail.com](mailto:dimaliahov@gmail.com)


#### Hi!
#### Application for checking the connection between points, finding the shortest path and determining the length.

#### Content
    - Photo
    - How to run
    - How to use
## Photo
 
![GitHub Logo](/photo/home.jpg)

![GitHub Logo](/photo/home_clear_db.jpg)

![GitHub Logo](/photo/home_quick-check.jpg)

![GitHub Logo](/photo/homw_wrong_uploud.jpg)

## How to run
   - Create DataBase H2;
       - Perform:
        CREATE TABLE IF NOT EXISTS ROUTING<br>
                (<br>
                ID     BIGINT PRIMARY KEY AUTO_INCREMENT,<br>
                pointA INT NOT NULL,<br>
                pointB INT NOT NULL,<br>
                length INT NOT NULL<br>
                );<br>
        - Enter where the base is located:
        In [this](/out/production/dimaliahov/com/gmail/dimaliahov/db/DBwork.class) file.<br>
        Change data for communication with the database.
        
        
        

## How to use

#### The source file must by like:

1;2;10<br>
2;3;20<br>
3;4;30<br>
3;5;15<br>
6;7;20<br>
7;10;20<br>

the file must have an extension: .csv<br>

#### The checking file must by like:

1;3<br>
6;10<br>
5;8<br>

the file must have an extension: .csv<br>
