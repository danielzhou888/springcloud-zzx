package org.zzx.nk.tool;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JsonObject代码生成工具
 * @author 周志祥
 */
public class JsonCodeGenerator {

    private static final Logger logger = LoggerFactory.getLogger(JsonCodeGenerator.class);

    /**
     * 将json字符串转成对应的JsonObject代码
     * @param json
     * @return
     */
    public static String convertToJsonString(String json) {
        JsonElement jsonElement = JsonParser.parseString(json);
        StringBuilder sb = new StringBuilder();
        sb.append("JsonObject jsonObject = new JsonObject();\n\n");
        processJsonElement("jsonObject", jsonElement, sb, 0);
        logger.info("生成的代码：\n{}", sb.toString());
        return sb.toString();
    }

    private static void processJsonElement(String variableName, JsonElement element, StringBuilder sb, int indentLevel) {
        if (element.isJsonObject()) {
            JsonObject jsonObject = element.getAsJsonObject();
            for (String key : jsonObject.keySet()) {
                JsonElement value = jsonObject.get(key);
                if (value.isJsonObject()) {
                    String newVariableName = variableName + "_" + key;
                    appendIndent(sb, indentLevel);
                    sb.append("JsonObject ").append(newVariableName).append(" = new JsonObject();\n");
                    appendIndent(sb, indentLevel);
                    sb.append(variableName).append(".add(\"").append(key).append("\", ").append(newVariableName).append(");\n\n");
                    processJsonElement(newVariableName, value, sb, indentLevel + 1);
                } else if (value.isJsonArray()) {
                    String arrayVariableName = variableName + "_" + key;
                    appendIndent(sb, indentLevel);
                    sb.append("JsonArray ").append(arrayVariableName).append(" = new JsonArray();\n");
                    appendIndent(sb, indentLevel);
                    sb.append(variableName).append(".add(\"").append(key).append("\", ").append(arrayVariableName).append(");\n\n");
                    processJsonArray(arrayVariableName, value.getAsJsonArray(), sb, indentLevel + 1);
                } else {
                    appendIndent(sb, indentLevel);
                    sb.append(variableName).append(".addProperty(\"").append(key).append("\", ");
                    if (value.getAsJsonPrimitive().isString()) {
                        sb.append("\"").append(value.getAsString()).append("\"");
                    } else if (value.getAsJsonPrimitive().isNumber()) {
                        sb.append(value.getAsNumber());
                    } else if (value.getAsJsonPrimitive().isBoolean()) {
                        sb.append(value.getAsBoolean());
                    }
                    sb.append(");\n");
                }
            }
        }
    }

    private static void processJsonArray(String variableName, JsonArray jsonArray, StringBuilder sb, int indentLevel) {
        for (JsonElement arrayElement : jsonArray) {
            if (arrayElement.isJsonObject()) {
                String arrayElementVariableName = variableName + "_element";
                appendIndent(sb, indentLevel);
                sb.append("JsonObject ").append(arrayElementVariableName).append(" = new JsonObject();\n");
                appendIndent(sb, indentLevel);
                sb.append(variableName).append(".add(").append(arrayElementVariableName).append(");\n\n");
                processJsonElement(arrayElementVariableName, arrayElement.getAsJsonObject(), sb, indentLevel + 1);
            } else {
                appendIndent(sb, indentLevel);
                sb.append(variableName).append(".add(");
                if (arrayElement.getAsJsonPrimitive().isString()) {
                    sb.append("\"").append(arrayElement.getAsString()).append("\"");
                } else if (arrayElement.getAsJsonPrimitive().isNumber()) {
                    sb.append(arrayElement.getAsNumber());
                } else if (arrayElement.getAsJsonPrimitive().isBoolean()) {
                    sb.append(arrayElement.getAsBoolean());
                }
                sb.append(");\n");
            }
        }
    }

    private static void appendIndent(StringBuilder sb, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            sb.append("    ");
        }
    }

    public static void main(String[] args) {
        String json = "[\"饮料\",\"调料佐料酱料\",\"食品\",\"贵金属原料\",\"矿产资源石灰石石头煤炭石头矿产资源煤炭6吨\",\"食品饮料\",\"酒类\",\"矿石原料\",\"大豆\",\"大米\"]";
        convertToJsonString(json);
    }
}
