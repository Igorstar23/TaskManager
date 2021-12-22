package ua.edu.sumdu.j2se.igor.tasks.model;

public class ListTypes {
       public enum types { ARRAY, LINKED}

       /**
        * @param taskObj must be not null
        * @throws IllegalArgumentException if taskObj is null
        * @return  type of taskObj*/
       public static ListTypes.types getTypeList(Iterable<Task> taskObj) {

              if (taskObj == null) throw new IllegalArgumentException("Param taskObj is null!");

              return (taskObj instanceof ArrayTaskList)? types.ARRAY : types.LINKED;
       }
}
