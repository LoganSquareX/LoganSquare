package io.logansquarex.processor.type.field;

import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

import static io.logansquarex.processor.processor.ObjectMapperInjector.JSON_PARSER_VARIABLE_NAME;

public class FloatFieldType extends NumberFieldType {

    public FloatFieldType(boolean isPrimitive) {
        super(isPrimitive);
    }

    @Override
    public TypeName getTypeName() {
        return (isPrimitive ? TypeName.FLOAT : ClassName.get(Float.class));
    }

    @Override
    public TypeName getNonPrimitiveTypeName() {
        return ClassName.get(Float.class);
    }

    @Override
    public void parse(Builder builder, int depth, String setter, Object... setterFormatArgs) {
        if (isPrimitive) {
            setter = replaceLastLiteral(setter, "(float)$L.getValueAsDouble()");
            builder.addStatement(setter, expandStringArgs(setterFormatArgs, JSON_PARSER_VARIABLE_NAME));
        } else {
            setter = replaceLastLiteral(setter, "$L.getCurrentToken() == $T.VALUE_NULL ? null : new Float($L.getValueAsDouble())");
            builder.addStatement(setter, expandStringArgs(setterFormatArgs, JSON_PARSER_VARIABLE_NAME, JsonToken.class, JSON_PARSER_VARIABLE_NAME));
        }
    }
}
