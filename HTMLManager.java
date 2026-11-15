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
    for(int i = 0; i < size; i++){

      HTMLTag test = tags.remove();

      if(test.isOpening()){
        temp.push(test);
        tags.add(test);
      } else if(test.isSelfClosing()){
        tags.add(test);
      } else if(test.isClosing()){
        if(test.isClosing()){
          if(temp.size() != 0){
            HTMLTag other = temp.pop();
            if(test.matches(other)){
              tags.add(test);
            } else {
              tags.add(other.getMatching());
            }
          }
        }
        
      }
      
      
    }

    while(temp.size() != 0){
      HTMLTag test = temp.pop();
      tags.add(test.getMatching());
    }

    
  }

  public String toString(){

    String output = "";
    int length = tags.size();
    for(int i = 0; i < length; i++){
      HTMLTag element = tags.remove();
      output += element.toString();
      tags.add(element);
    }

    return output;
  }
  
}
