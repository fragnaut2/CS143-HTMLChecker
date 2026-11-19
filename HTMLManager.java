import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;

  public HTMLManager(Queue<HTMLTag> tags){
    if(tags == null){
      throw new IllegalArgumentException();
    } else {
      this.tags = tags;
    }
  }

  public Queue<HTMLTag> getTags(){
    return tags;
  }

  public void fixHTML(){
    Stack<HTMLTag> temp = new Stack<>();

    int size = tags.size();

    // Loop through every element in tags queue.
    for(int i = 0; i < size; i++){

      // Remove a tag from tags and save it as the tag being actively processed.
      HTMLTag test = tags.remove();

      // If the tag is an opening tag, put that tag on the top of the temp stack
      // and replace that tag in the original tags queue. 
      if(test.isOpening()){
        temp.push(test);
        tags.add(test);
      } else if(test.isSelfClosing()){
        // If the tag is self closing, then immediately add that tag back to the
        // original tags list.
        tags.add(test);
      } else if(test.isClosing()){
        // If the tag is a closing tag, compare it to the opening tag on top of 
        // the stack.
        if(temp.size() != 0){
          HTMLTag other = temp.pop();
          if(test.matches(other)){
            // If the opening and closing tags match, add the closing tag back 
            // into the tags queue.
            tags.add(test);
          } else {
            // If the tags don't match, then get the matching closing tag and 
            // add that to the tags queue.
            tags.add(other.getMatching());
          }
        }
      }
    }

    // If there is anything still lft on the temp stack, cycle through each 
    // element and add the correct closing tag to the tags queue.
    while(!temp.isEmpty()){
      HTMLTag test = temp.pop();
      tags.add(test.getMatching());
    }
  }

  public String toString(){

    String output = "\n";
    int size = tags.size();
    for(int i = 0; i < size; i++){
      HTMLTag element = tags.remove();
      output += element.toString().trim();
      tags.add(element);
    }
    return output;
  }
  
}
