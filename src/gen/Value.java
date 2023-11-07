import java.util.List;
import java.util.Map;

public class Value {

    public static Value VOID = new Value(new Object());

    final Object value;

    public Value(Object value) {
        this.value = value;
    }

    public Integer asEntero() {
        return (Integer) value;
    }

    public Float asReal() {
        return (Float) value;
    }

    public Character asCaracter() {
        return (Character) value;
    }

    public Boolean asBooleano() {
        return (Boolean) value;
    }

    public String asCadena() {
        return String.valueOf(value);
    }

    public List<Value> asArreglo() {
        return (List<Value>) value;
    }

    public Map<String, Value> asRegistro() {
        return (Map<String, Value>) value;
    }

    public boolean isEntero() {
        return value instanceof Integer;
    }

    public boolean isReal() {
        return value instanceof Float;
    }

    public boolean isCaracter() {
        return value instanceof Character;
    }

    public boolean isBooleano() {
        return value instanceof Boolean;
    }

    public boolean isCadena() {
        return value instanceof String;
    }

    public boolean isArreglo() {
        return value instanceof List<?>;
    }

    public boolean isRegistro() {
        return value instanceof Map<?, ?>;
    }

    @Override
    public int hashCode() {
        if (value == null) {
            return 0;
        }
        return this.value.hashCode();
    }
    public Boolean menorQue(Value other) {
        System.out.println(other.value.getClass());
        if (this.value.getClass() != other.value.getClass()) {
            throw new UnsupportedOperationException("No se pueden comparar valores de tipos diferentes.");
        }

        if (isEntero()) {
            return asEntero() < other.asEntero();
        } else if (isReal()) {
            return asReal() < other.asReal();
        } else if (isCaracter()) {
            return asCaracter() < other.asCaracter();
        } else if (isCadena()) {
            return asCadena().compareTo(other.asCadena()) < 0;
        } else {
            throw new UnsupportedOperationException("El operador < no está definido para este tipo de valor.");
        }
    }

    public Boolean mayorQue(Value other) {
        if (this.value.getClass() != other.value.getClass()) {
            throw new UnsupportedOperationException("No se pueden comparar valores de tipos diferentes.");
        }

        if (isEntero()) {
            return asEntero() > other.asEntero();
        } else if (isReal()) {
            return asReal() > other.asReal();
        } else if (isCaracter()) {
            return asCaracter() > other.asCaracter();
        } else if (isCadena()) {
            return asCadena().compareTo(other.asCadena()) > 0;
        } else {
            throw new UnsupportedOperationException("El operador > no está definido para este tipo de valor.");
        }
    }

    public Boolean menorIgualQue(Value other) {
        if (this.getClass() != other.getClass()) {
            throw new UnsupportedOperationException("No se pueden comparar valores de tipos diferentes.");
        }

        if (isEntero()) {
            return asEntero() <= other.asEntero();
        } else if (isReal()) {
            return asReal() <= other.asReal();
        } else if (isCaracter()) {
            return asCaracter() <= other.asCaracter();
        } else if (isCadena()) {
            return asCadena().compareTo(other.asCadena()) <= 0;
        } else {
            throw new UnsupportedOperationException("El operador <= no está definido para este tipo de valor.");
        }
    }

    public Boolean mayorIgualQue(Value other) {
        if (this.value.getClass() != other.value.getClass()) {
            throw new UnsupportedOperationException("No se pueden comparar valores de tipos diferentes.");
        }

        if (isEntero()) {
            return asEntero() >= other.asEntero();
        } else if (isReal()) {
            return asReal() >= other.asReal();
        } else if (isCaracter()) {
            return asCaracter() >= other.asCaracter();
        } else if (isCadena()) {
            return asCadena().compareTo(other.asCadena()) >= 0;
        } else {
            throw new UnsupportedOperationException("El operador >= no está definido para este tipo de valor.");
        }
    }
    @Override
    public boolean equals(Object o) {
        if (value == o) {
            return true;
        }
        if (value == null || o == null || o.getClass() != value.getClass()) {
            return false;
        }
        Value that = (Value) o;
        return this.value.equals(that.value);
    }

    @Override
    public String toString() {
        if (isBooleano()){
            if (asBooleano()){
                return "VERDADERO";
            }else{
                return "FALSO";
            }
        }
        return String.valueOf(value);
    }
}


