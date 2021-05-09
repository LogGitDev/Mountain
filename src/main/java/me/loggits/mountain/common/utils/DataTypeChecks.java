package me.loggits.mountain.common.utils;

import com.sun.istack.internal.Nullable;

public class DataTypeChecks {
    public static boolean isPrimitiveWrapper(@Nullable Object input) {
        return input instanceof Integer
               || input instanceof Boolean
               || input instanceof Character
               || input instanceof Byte
               || input instanceof Short
               || input instanceof Double
               || input instanceof Long
               || input instanceof Float;
    }
}
