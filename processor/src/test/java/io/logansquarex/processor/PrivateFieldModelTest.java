package io.logansquarex.processor;

import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;

import io.logansquarex.processor.processor.JsonAnnotationProcessor;

import static com.google.common.truth.Truth.ASSERT;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class PrivateFieldModelTest {

    @Test
    public void generatedSource() {
        ASSERT.about(javaSource())
                .that(JavaFileObjects.forResource("model/good/PrivateFieldModel.java"))
                .processedWith(new JsonAnnotationProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(JavaFileObjects.forResource("generated/PrivateFieldModel$$JsonObjectMapper.java"));
    }
}
