package com.note.base;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Expression01 {
    @Test
    public void test04() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
        // String expression = "10 * 2 + 6 / (3 - 1)";
        // String expression = "2>1";
        String expression = "1==2";
        try {
            // 通过调用 JavaScript 来计算字符串表达式
            Object eval = scriptEngine.eval(expression);
            String result = String.valueOf(eval);
            System.out.println(result);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
