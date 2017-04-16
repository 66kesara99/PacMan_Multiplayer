# PacMan_Multiplayer

CO324 Course Project

You will develop a multiplayer game similar to PacMan. Play begins once four players have joined in. They score points by collecting coloured dots on a 10x10 grid. Red, green and blue dots count for one, two and, four points respectively. When two players collide they lose three points each. The game ends when all dots on the board have been collected.
Players move their characters on a two dimensional grid using arrow keys. You will be provided most of the client JavaScript code for game play. Your will design and code the backend logic to maintain the current state of the game. Game state consists of the coordinates of remaining coloured dots and each player’s location and score
Application Design

As each player logs in, the server initiates an SSE response to their browser. Once all four player have joined they are initially placed at the four corners of the grid. The server also generates a random distribution of red, green and blue dots on the grid.
On each keystroke the client sends an HTTP POST with the keystrokes represented by  the strings “UP”, “DOWN”, “LEFT” and “RIGHT.” On receiving a keystroke, the server broadcasts a new event to all clients giving the updated state of the game. Event data consists of a JSON object containing integer grid coordinates denoting the position of the remaining dots and, players’ scores and coordinates. Player and dot coordinates may appear in any order, but the game front-end may assume that they are valid (i.e. not negative or out of bounds)

{ 
 “DOTS”: [
   [“B”, x1, y1] , [“G”, x2, y2] ,  [“R”, x3, y3] , … 
 ] , 
 “PLAYERS”: [ 
   [“P1”, s1, xp1, yp1] , [“P2”, s2, xp2, yp2] , 
   [“P3”, s3, xp3, yp3] , [“P4”, s4, xp4, yp4]
 ] 
}

On receiving a new event from the server, the client code must redraw the grid and update players’ scores. The server indicates the end of the game by sending an empty dots array and a player array containing the final scores.
Implementation notes

Your servlet must implement the game update protocol exactly as specified. Game logic must be kept in separate from the servlet. You may use one or more classes for this purpose.
Game logic must validate player moves to ensure that invalid states such as an out of grid player position is not possible. When players collide they are reset to their initial positions.
Limit use of third-party libraries to a minimum. You may use the javax.json package to construct JSON strings if necessary. Instructors must be able to import your final project submission on a lab Netbeans installation without any further configuration.
Make only the additions required to receive responses and update the game in the JavaScript front-end code that is provided. You just need two loops to update the positions of dots and players.
