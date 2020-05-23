# Custom Java Platformer

Create your custom levels and try to reach the end.

Made in pure Java using Graphics2D and JFrame.


# Creating your level

Simply edit the `"level.png"` file in the res directory

> 1 pixel corresponds to 1 object of that type

Keep in mind the following color codes when building the level:

- Blue: the player's spawn position. Use only 1 pixel.
- White: a platform block. The player can stand on this.
- Red: a 'death' block. Touching this reverts the player back to spawn
- Black: empty space. Nothing here.

