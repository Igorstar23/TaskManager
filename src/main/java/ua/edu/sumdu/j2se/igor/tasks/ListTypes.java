package ua.edu.sumdu.j2se.igor.tasks;

public class ListTypes {
       public enum types {
              ARRAY(ArrayTaskList.class.getName()),
              LINKED(LinkedTaskList.class.getName());

              private final String name;

              types(String s) {
                    this.name = s;
              }
              public String getName() { return this.name; }
       }

       /**
        * @param taskObj must be not null
        * @throws IllegalArgumentException if taskObj is null
        * @return  type of taskObj*/
       public static ListTypes.types getTypeList(AbstractTaskList taskObj) {

              if (taskObj == null) throw new IllegalArgumentException("Param taskObj is null!");

              return (taskObj instanceof ArrayTaskList)? types.ARRAY : types.LINKED;
       }
}
