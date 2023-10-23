package co.com.ajac.messaging.util;

import io.vavr.control.Option;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class StgMonad {

    public static Option<String> getOption(String ts) {
        return StringUtils.isBlank(ts) ? Option.none() : Option.of(ts);
    }

    public static <T> Option<T> getOption(T object) {
        return Option.of(object);
    }
}
