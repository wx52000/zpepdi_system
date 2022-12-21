package zpepdi.system.tools;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectUtils {

    public static Map<String,Object> objectToMap(Object object){
        Map<String,Object> map = new HashMap<>();
        Field []fields = object.getClass().getDeclaredFields();
        try {
        for (Field field : fields){
                field.setAccessible(true);
                map.put(field.getName(),field.get(object));
            }
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
}
