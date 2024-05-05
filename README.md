# Word Ladder Solver

<br/>

## Introduction

Welcome to the Word Ladder Solver! This web application is designed to solve word ladder puzzles efficiently using a range of search algorithms. Whether you're a word puzzle enthusiast looking for a challenge or a student exploring algorithmic problem-solving, this solver provides an interactive platform to discover solutions.

## Table of Contents

- [Introduction](#introduction)
- [Concept](#concept)
  - [Uniform Cost Search (UCS)](#uniform-cost-search-ucs)
  - [Greedy Best First Search (GBFS)](#greedy-best-first-search-gbfs)
  - [A* Algorithm](#a-algorithm)
- [How It Works](#how-it-works)
- [Development](#development)
  - [Prerequisites](#prerequisites)
- [Backup](#backup)
- [Project Status](#project-status)

## Concept

The Word Ladder Solver offers three distinct algorithms to find solutions to word ladder puzzles. Each algorithm has its unique approach to traversing the word ladder graph and finding the optimal path from the start word to the end word.

### Uniform Cost Search (UCS)

Uniform Cost Search is a classic algorithm used for solving problems in graphs where each edge has a cost. In the context of word ladders, UCS explores the graph by considering the cost of each step between words. It prioritizes paths with lower cumulative costs, leading to an optimal solution in terms of the shortest path between words.

### Greedy Best First Search (GBFS)

Greedy Best First Search is a heuristic-based algorithm that makes decisions based on the estimated cost to reach the goal. In the context of word ladders, GBFS prioritizes expanding paths that are closer to the target word, making it a faster but potentially less optimal solution compared to UCS.

### A\* Algorithm

The A* algorithm combines the advantages of UCS and GBFS by using both the actual cost from the start node (UCS) and the estimated cost to the goal node (GBFS). This hybrid approach allows A* to find the shortest path efficiently while also considering heuristic information to guide the search towards the goal.

## How It Works

1. **Input Words:** Start by entering the start and end words of the word ladder puzzle.
2. **Select Algorithm:** Choose one of the available search algorithms.
3. **Solve:** Click the "Solve" button to initiate the solving process.
4. **View Solution:** Once the solution is found, you'll see details such as runtime, visited nodes count, and the path length.
5. **Save Solution:** Optionally, save the solution as a text file for future reference.

## Development

To run the application locally:

### Prerequisites

Before starting the development process, ensure that your machine meets the following prerequisites:

- [Node.js](https://nodejs.org/) (LTS version recommended): Node.js is a JavaScript runtime required for running the frontend of the application and managing JavaScript dependencies using npm.
- [npm](https://www.npmjs.com/): npm is the Node.js package manager used for installing and managing JavaScript dependencies.
- [Java](https://www.java.com/en/) (JDK LTS version): Java Development Kit is required for compiling and running the backend server.
- [Maven](https://maven.apache.org/) (LTS version): Maven is a build automation tool used for managing Java projects and their dependencies.

### 1. Clone the Repository

```bash
git clone https://github.com/fairuzald/Tucil3_13522057
cd Tucil3_13522057
```

### 2. Navigate to the Source (src/packages) Directory

```bash
cd src/packages

```

### 3. Install All Dependencies

```bash
npm run install-all
```

### 4. Runs Development Server

```bash
npm run dev
```

### 5: Open your browser and navigate to

Client-side is running on [localhost:3000](http://localhost:3000), and the server is on [localhost:8080](http://localhost:8080).

## Backup

For backup only CLI:

### 1. Clone the Repository

```bash
git clone https://github.com/fairuzald/Tucil3_13522057
cd Tucil3_13522057
```

### 2. Run the runner

```bash
# In windows
 ./run.bat
# in Linux make Makefile
make
```

## Project Status

Project is complete
