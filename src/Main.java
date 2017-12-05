import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Main {

    private static Gson gson;

    public static void main(String[] args) {
        gson = new Gson();
        Wrap<Foo> wrap = new Wrap<>(new Foo("233"));
        String json = gson.toJson(wrap);
        Type type = new TypeToken<Wrap<Foo>>() {
        }.getType();
        Object obj1 = gson.fromJson(json, type);
        System.out.println(obj1);
        Type tType = new TypeToken<Foo>() {
        }.getType();
        genericErase(wrap, tType);
    }

    private static <T> void genericErase(Wrap<T> wrap, Type tType) {
        String json = gson.toJson(wrap);
        Type type = new TypeToken<Wrap<T>>() {
        }.getType();
        Object obj2 = gson.fromJson(json, type);
        System.out.println(obj2);
        Object obj3 = gson.fromJson(json, GsonUtil.type(Wrap.class, tType));
        System.out.println(obj3);
    }
}

class Wrap<T> {
    private final T t;

    Wrap(T t) {
        this.t = t;
    }
}

class Foo {
    String s;

    Foo(String s) {
        this.s = s;
    }
}