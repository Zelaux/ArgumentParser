package argumentparser;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@AllArgsConstructor
public class Param {
    public final String name;
    public final boolean optional;
    public final boolean variadic;

    @Nullable
    public final String handlerName;

    @Override
    public String toString() {
        return "Param{" +
            "name='" + name + '\'' +
            ", optional=" + optional +
            ", variadic=" + variadic +
            ", handlerName='" + handlerName + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Param that = (Param) o;
        return optional == that.optional && variadic == that.variadic && Objects.equals(name, that.name) && Objects.equals(handlerName, that.handlerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, optional, variadic, handlerName);
    }
}
