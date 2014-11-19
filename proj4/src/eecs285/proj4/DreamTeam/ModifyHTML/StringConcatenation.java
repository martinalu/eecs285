package eecs285.proj4.DreamTeam.ModifyHTML;


/**
 * This class is going to be where the java methods that modify the HTML will go.
 * 
 *    Refer to our README.md in the project directory- specifically the 
 *    GUI Description section. Reading that will reveal what types of methods
 *    need to be created. In general, they will follow a theme. The function 
 *    will take a string argument, modify it, and return that string. That's
 *    my intuition. If it becomes clear that taking some sort of an HTML object
 *    is better, than do that. I'm merely suggesting the first thing that came 
 *    to mind.
 *    
 */

public class StringConcatenation
{
  //Demo: Makes a string italicized when rendered HTML.
  public String makeItalic(String stringArg)
  {
    stringArg = "<i>" + stringArg + "</i>";
    return stringArg;
  }

}
