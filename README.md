# KBC_game_project
KBC 2.0 - A Quiz Game in Java
Overview
KBC 2.0 is a Java-based quiz game inspired by the popular TV show "Kaun Banega Crorepati". This application features multiple game modes, lifelines, score tracking, and database integration to store player information and scores.

Key Data Structures Used
1. Binary Search Tree (BST)
Purpose: Efficient player data storage and retrieval

Implementation:

java
class Bnode {
  String Player;
  int age;
  int Easy_score;
  int Hard_score;
  Bnode left;
  Bnode right;
  // Constructor and methods
}

class BST {
  Bnode root;
  void insert(String Player, int age, int Easy_score, int Hard_score) {
    // Insertion logic
  }
  Bnode search(Bnode Treenode, String player) {
    // Search logic
  }
}
Advantages:

O(log n) average case for search and insert operations

Efficient for maintaining sorted player data

Memory efficient compared to hash tables

2. Linked List
Purpose: Tracking question-by-question performance

Implementation:

java
class node {
  int qno;
  int score;
  boolean answer;
  node next;
  // Constructor
}

class valueSet {
  node head;
  void addValue(int q, int s, boolean v) {
    // Linked list insertion
  }
  // Other methods
}
Advantages:

Dynamic size (no fixed capacity)

Efficient insertion at end (O(1) with tail pointer)

Sequential access perfect for score tracking

3. Arrays
Purpose: Managing threads for timed questions

Implementation:

java
Thread t[] = new Thread[11]; // For managing question timers
Usage:

Fixed-size collection for managing parallel timers

Random access to individual threads

4. Strings
Usage:

Player names and tags

Question text and options

Database queries and results

5. Database Structures (MySQL)
Tables:

Player table with player information

Multiple question tables (easy_questions, Hard_Set1-3)

Answer reference table (getAns)

Algorithmic Approaches
Recursive Tree Traversal:

Used in BST operations (insertion and search)

Enables efficient player lookup

Random Number Generation:

For selecting random questions in hard mode

For lifeline probability calculations

Time Management:

Thread-based timer for question deadlines

Interrupt handling for timeout scenarios

Input Validation:

Character-by-character validation for answer inputs

Menu selection validation

Complexity Analysis
Operation	Data Structure	Time Complexity	Space Complexity
Player Insertion	BST	O(log n) avg	O(n)
Player Search	BST	O(log n) avg	O(1)
Score Recording	Linked List	O(1) append	O(n)
Question Retrieval	MySQL	O(1) indexed	O(1)
Lifeline Processing	Various	O(1)	O(1)
Memory Management
Object-Oriented Design: Encapsulation helps manage memory effectively

Garbage Collection: Automatic collection of unused objects (question threads, temporary objects)

File I/O: Score logs written to files to reduce memory load

The combination of these data structures provides an efficient balance between memory usage and performance for this quiz game application.
