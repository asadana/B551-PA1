# B551-PA1
Programming Assignment #1 for B551 - Elements of AI
------------------------

**Important Variables**

*src.com.main.Main.java* contains the following variables which allows the file to be read in the right way.
>// fileName variable contains the list of cities in the form of "City1 City2 Distance"
>// The default location of this file is assumed in the project folder "B551-PA1/romania-distance.txt"
>
>`fileName = "romania-distance.txt";` 

> // delim stores the delimiter used to parse the txt file in fileName [Default: Space]
>
> `delim = " ";`

*src.com.util.Search.java* contains the following variable which allows the initial search depth of IDS to be set.
>// ID_DEPTH variable stores the depth which is initially provided to the Iterative Deepening Search.
>`ID_DEPTH = 2;`

**How to Run**
The *src.com.Main.java* is the main launcher file for this project.
On execution, it will display all the cities read, with corresponding neighbors.
It will then prompt for your query, which is expected in the form of:

    City1, City2 BFS/DFS/IDS
    Arad, Bucharest BFS

