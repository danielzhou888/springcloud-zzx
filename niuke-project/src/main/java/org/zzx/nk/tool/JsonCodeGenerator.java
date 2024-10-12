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
        String json = "{\n" +
                "    \"orgId\": \"8229097\",\n" +
                "    \"orgName\": \"百川-测试项目1\",\n" +
                "    \"belongActSys\": \"28\",\n" +
                "    \"belongActSysName\": \"天津路歌网货\",\n" +
                "    \"taxNo\": \"91330101352463148H\",\n" +
                "    \"businessType\": \"1\",\n" +
                "    \"operateName\": \"周志祥\",\n" +
                "    \"taxType\": \"0\",\n" +
                "    \"extraRate\": \"0.0500\",\n" +
                "    \"buoyancyLines\": \"0.0100\",\n" +
                "    \"invoiceBasis\": \"0\",\n" +
                "    \"invoiceMode\": \"0\",\n" +
                "    \"chargedMode\": \"1\",\n" +
                "    \"taxServerPer\": \"0.0500\",\n" +
                "    \"orgAttr\": \"3\",\n" +
                "    \"buId\": \"908\",\n" +
                "    \"buName\": \"大客户二部\",\n" +
                "    \"groupName\": \"跨越集团\",\n" +
                "    \"businessManager\": \"Zhou Daniel\",\n" +
                "    \"bmTelNo\": \"17338026080\",\n" +
                "    \"bmMobileNo\": \"17338026080\",\n" +
                "    \"financeName\": \"雷琦\",\n" +
                "    \"paymentSendMobile\": \"18225902018\",\n" +
                "    \"saleName\": \"周志祥\",\n" +
                "    \"saleMobileNo\": \"15605513713\",\n" +
                "    \"saleNo\": \"100\",\n" +
                "    \"isLoginTax\": \"0\",\n" +
                "    \"region\": \"4\",\n" +
                "    \"businessOperate\": \"无\",\n" +
                "    \"businessPromotion\": \"无\",\n" +
                "    \"implementOperate\": \"周志祥\",\n" +
                "    \"transportOperate\": \"无\",\n" +
                "    \"guaranteeOperate\": \"无\",\n" +
                "    \"intelligentOperate\": \"无\",\n" +
                "    \"roleType\": \"1\",\n" +
                "    \"auditFlag\": \"0\",\n" +
                "    \"receiptFlag\": \"0\",\n" +
                "    \"lbsRule\": \"0\",\n" +
                "    \"endRule\": \"1\",\n" +
                "    \"transferFee\": \"0.00\",\n" +
                "    \"tipsFlag\": \"0\",\n" +
                "    \"mocheckType\": \"0\",\n" +
                "    \"maxAllFreight\": \"100000\",\n" +
                "    \"wxStateFlag\": \"1\",\n" +
                "    \"mbauRight\": \"0\",\n" +
                "    \"certWarningPeroid\": \"0\",\n" +
                "    \"payWay\": \"0\",\n" +
                "    \"creditState\": \"0\",\n" +
                "    \"financeOptName\": \"无\",\n" +
                "    \"blType\": \"0\",\n" +
                "    \"creditAmount\": \"0.00\",\n" +
                "    \"intType\": \"0\",\n" +
                "    \"freeDays\": \"0\",\n" +
                "    \"insName\": \"无\",\n" +
                "    \"companyGroupId\": \"1\",\n" +
                "    \"companyGroupName\": \"无归属集团\",\n" +
                "    \"companyId\": \"120247201\",\n" +
                "    \"companyName\": \"杭州达迩网络科技股份有限公司\",\n" +
                "    \"defaultRiskCtrlType\": \"1\",\n" +
                "    \"orgAccountType\": \"1\",\n" +
                "    \"taxGroupId\": [\n" +
                "        \"9433\"\n" +
                "    ],\n" +
                "    \"payGroupId\": [\n" +
                "        \"9443\"\n" +
                "    ],\n" +
                "    \"sysCfgList\": [\n" +
                "        {\n" +
                "            \"cfgItem\": \"1\",\n" +
                "            \"cfgValue\": \"1\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"oilCardRabateInfoList\": [\n" +
                "        {\n" +
                "            \"oilCardId\": \"14378\",\n" +
                "            \"rebateRate\": \"0.0000\",\n" +
                "            \"presentRate\": \"0.0000\",\n" +
                "            \"cardType\": \"2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"oilCardId\": \"14379\",\n" +
                "            \"rebateRate\": \"0.0000\",\n" +
                "            \"presentRate\": \"0.0000\",\n" +
                "            \"cardType\": \"999\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"orgIntfeeList\": [\n" +
                "\n" +
                "    ],\n" +
                "    \"taxDraweePartyList\": [\n" +
                "        {\n" +
                "            \"taxDraweePartyId\": \"645567\",\n" +
                "            \"drawee\": \"杭州达迩网络科技股份有限公司\",\n" +
                "            \"draweeTaxNo\": \"91330101352463148H\",\n" +
                "            \"draweeState\": \"1\",\n" +
                "            \"mobileNo\": \"12344566\",\n" +
                "            \"address\": \"公司地址\",\n" +
                "            \"accountBank\": \"开户行\",\n" +
                "            \"accountNo\": \"1232141341231241\",\n" +
                "            \"itemAmount\": \"0.00\",\n" +
                "            \"itemFundsPercent\": \"0.00\",\n" +
                "            \"payOilAndEtc\": \"9\",\n" +
                "            \"blUsable\": \"0\",\n" +
                "            \"draweeProvince\": \"安徽\",\n" +
                "            \"draweeCity\": \"安庆\",\n" +
                "            \"draweeCounty\": \"大观区\",\n" +
                "            \"invoiceStyle\": \"0\",\n" +
                "            \"draweeCustomerLevel\": \"C\",\n" +
                "            \"contractList\": [\n" +
                "\n" +
                "            ],\n" +
                "            \"madrList\": [\n" +
                "\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"carrierCfgList\": [\n" +
                "\n" +
                "    ],\n" +
                "    \"orgMaintainUserList\": [\n" +
                "        {\n" +
                "            \"maintainRoleId\": \"1\",\n" +
                "            \"maintainUserId\": \"196862\",\n" +
                "            \"maintainUserName\": \"周志祥\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"maintainRoleId\": \"9\",\n" +
                "            \"maintainUserId\": \"196862\",\n" +
                "            \"maintainUserName\": \"周志祥\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"maintainRoleId\": \"8\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"maintainRoleId\": \"2\",\n" +
                "            \"maintainUserId\": \"196862\",\n" +
                "            \"maintainUserName\": \"周志祥\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        convertToJsonString(json);
    }
}
