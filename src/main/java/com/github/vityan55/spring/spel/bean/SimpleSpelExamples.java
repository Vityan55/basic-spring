package com.github.vityan55.spring.spel.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SimpleSpelExamples {

    //Простое SpEl выражение. Можем использовать все математические операции как в языке java)
    @Value("#{4 * 2}")
    private int numEval;

    //работа со строками
    @Value("#{'I am a '.concat('String')}")
    private String strEval;

    //работа с булевыми значениями
    @Value("#{2 > 3 and 1 < 10}")
    private boolean boolEval;

    //работа с тернарными операторами
    @Value("#{1 != 1 ? 0 : 1}")
    private int ternaryEval;

    //задаем значение по умолчанию если параметр не указан
    @Value("#{'${my.prop}' ?: 'defaultValue'}")
    private String defaultValue;

    //используем SpEl в файле .properties
    @Value("${app.os.name}")
    private String osName;

    public void simpleEval(){
        System.out.println("Number " + numEval);
        System.out.println("String " + strEval);
        System.out.println("Boolean " + boolEval);
        System.out.println("Ternary " + ternaryEval);
        System.out.println("Default value " + defaultValue);
        System.out.println("OS name " + osName);
    }

    public void simpleEvaluateContextExample(){
        simpleReadOnlyContext();
        System.out.println("------------------");
        simpleReadWriteContext();
        System.out.println("------------------");
    }

    //простой пример который умеет только считывать но не изменять
    private void simpleReadOnlyContext(){
        //создаем парсер выражение SpEl
        SpelExpressionParser parser = new SpelExpressionParser();

        //создаем объект из которого будем получать данные
        ExpressionParserObject object = new ExpressionParserObject();

        //создаем контекст и устанавливаем для него правило только для чтения
        EvaluationContext readOnlyContext = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        //чтение из нашего object
        Expression firstStrValueExpression = parser.parseExpression("strValues[0]");

        //получаем значение
        String firstFromList = firstStrValueExpression.getValue(readOnlyContext, object, String.class);

        System.out.println("First from list: " + firstFromList);

        //получаем второй элемент из Map
        Expression secondFromMap = parser.parseExpression("keyValues[2]");

        System.out.println("Second element from Map " + secondFromMap.getValue(readOnlyContext, object, String.class));
    }

    private void simpleReadWriteContext(){
        SpelExpressionParser parser = new SpelExpressionParser();

        ExpressionParserObject object = new ExpressionParserObject();

        //то же самое, что и выше, но только для чтения и записи
        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();

        Expression getListExp = parser.parseExpression("strValues");
        List<String> values = getListExp.getValue(context, object, List.class);

        //добавление элемента
        values.add("four");

        System.out.println("Object list: " + object.getStrValues());

        Expression changeFirstValueExp = parser.parseExpression("keyValues[1]");
        //именение значения элемента
        changeFirstValueExp.setValue(context, object, "new one");

        System.out.println("Object map: " + object.getKeyValues());
    }

    private static class ExpressionParserObject{
        private List<String> strValues = new ArrayList<>(List.of("one", "two", "three"));
        private Map<Integer, String> keyValues = new HashMap<>(Map.of(1, "one", 2, "two", 3, "three"));

        public List<String> getStrValues() {
            return strValues;
        }

        public Map<Integer, String> getKeyValues() {
            return keyValues;
        }
    }
}
