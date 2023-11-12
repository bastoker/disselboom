package st.bas.func;

import java.util.function.Function;

public interface Functor<T> {
    /**
     * Maps the underlying value to a different component type.
     *
     * @param mapper A mapper
     * @param <U>    The new functor type
     * @return The new Functor
     */
    <U> Functor<U> map(Function<? super T, ? extends U> mapper);
}
