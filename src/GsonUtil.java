import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GsonUtil {

//Json转字符串，输出T的类型
/*public static <T> T toString(String response,Class<T> classZ){
    Gson gson = new Gson();
    Type objectType = type(BaseBean.class,classZ);
    return gson.fromJson(response,objectType);
}*/

/*public static <T> T toString(String response,Class<T> classZ) {
    return new Gson().fromJson(response,classZ);
}*/

    //会报错，因为Gson进行序列化、反序列化的时候会将泛型擦除，所有的类都变成Object，故得不到所需要的泛型。
    public static <T> T toString(String response, Class<T> classZ) {
        try {
            return new Gson().fromJson(response, new TypeToken<T>() {
            }.getType());
        } catch (Throwable e) {
            return null;
        }
    }

    /**
     * ParameterizedType对象，对于Object、接口和原始类型返回null，对于数组class则是返回Object.class。
     * ParameterizedType是表示带有泛型参数的类型的Java类型，
     * JDK1.5引入了泛型之后，Java中所有的Class都实现了Type接口，ParameterizedType则是继承了Type接口，
     * 所有包含泛型的Class类都会实现这个接口。
     *
     * @param raw
     * @param args
     * @return
     */
    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }
}