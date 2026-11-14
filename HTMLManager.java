import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;

  public HTMLManager(Queue<HTMLTag> tags){
    this.tags = tags;
  }

  public Queue<HTMLTag> getTags(){
    return tags;
  }

  public String toString(){
    Queue<HTMLTag> temp = new LinkedList<>();
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
