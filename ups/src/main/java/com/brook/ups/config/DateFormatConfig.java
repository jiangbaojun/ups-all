package com.brook.ups.config;

import com.brook.ups.common.constant.ConfigConstant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局日期格式化
 */
@JsonComponent
public class DateFormatConfig {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat(ConfigConstant.DATE_FORMAT);

    /**
     * 日期格式化
     */
    public static class DateJsonSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if(date instanceof Timestamp){
                dateFormat = new SimpleDateFormat(ConfigConstant.DATETIME_FORMAT);
            }else{
                dateFormat = new SimpleDateFormat(ConfigConstant.DATE_FORMAT);
            }
            //时区
//            dateFormat.setTimeZone(ConfigConstant.TIMEZONE_CHINA);
            //格式化日期
            jsonGenerator.writeString(dateFormat.format(date));
        }
    }

    /**
     * 解析日期字符串
     */
    public static class DateJsonDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            try {
                //时区
//                dateFormat.setTimeZone(ConfigConstant.TIMEZONE_CHINA);
                //解析日期
                return DateUtils.parseDate(String.valueOf(jsonParser.getText()), ConfigConstant.DATE_TIME_FORMAT);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }
    }
}