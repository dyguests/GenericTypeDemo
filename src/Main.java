import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Main {

    private static Gson gson;

    public static void main(String[] args) {
        gson = new Gson();
        Wrap<Foo> wrap = new Wrap<>(new Foo("233"));
        String json = gson.toJson(wrap);
        Type type = new TypeToken<Wrap<String>>() {
        }.getType();
        Object obj1 = gson.fromJson(json, type);
        System.out.println(obj1);
        genericErase(wrap);
    }

    private static <T> void genericErase(Wrap<T> wrap) {
        String json = gson.toJson(wrap);
        Type type = new TypeToken<Wrap<T>>() {
        }.getType();
        Object obj2 = gson.fromJson(json, type);
        System.out.println(obj2);
    }
}

class Wrap<T> {
    T t;

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