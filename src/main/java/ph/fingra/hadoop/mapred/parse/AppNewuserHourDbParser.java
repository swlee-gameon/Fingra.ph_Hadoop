/**
 * Copyright 2014 tgrape Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ph.fingra.hadoop.mapred.parse;

import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;

import ph.fingra.hadoop.common.ConstantVars;
import ph.fingra.hadoop.common.ConstantVars.LogValidation;
import ph.fingra.hadoop.common.ConstantVars.ParseError;
import ph.fingra.hadoop.common.util.FormatUtil;

public class AppNewuserHourDbParser {
    
    private static final int COL_APPKEY = 0;
    private static final int COL_TOKEN = 1;
    private static final int COL_YEAR = 2;
    private static final int COL_MONTH = 3;
    private static final int COL_DAY = 4;
    private static final int COL_HOUR = 5;
    
    private static final int COL_COUNT = 6;
    
    // temporary hourly app_newuser merge format
    private static final String APPNEWUSERHOURDB_PATTERN_REGEX
        = "^[a-zA-Z0-9]*\\t"            // appkey
                + "[a-zA-Z0-9_-]*\\t"   // token
                + "[0-9]{4}\\t[0-9]{2}\\t[0-9]{2}\\t[0-9]{2}$"; // year, month, day, hour
    
    private static Pattern APPNEWUSERHOURDB_PATTERN;
    static {
        APPNEWUSERHOURDB_PATTERN = Pattern.compile(APPNEWUSERHOURDB_PATTERN_REGEX);
    }
    
    private String appkey;
    private String token;
    private String year;
    private String month;
    private String day;
    private String hour;
    
    private boolean raised_error;
    private int parse_error;
    private LogValidation error_level;
    
    public AppNewuserHourDbParser() {
        this.raised_error = false;
        this.parse_error = ParseError.NONE;
        this.error_level = LogValidation.CLEAN;
    }
    
    public void parse(Text record) {
        if (record == null)
            parse("");
        else
            parse(record.toString());
    }
    
    private void parse(String record) {
        
        // initialize
        this.appkey = "";
        this.token = "";
        this.year = "";
        this.month = "";
        this.day = "";
        this.hour = "";
        
        this.raised_error = false;
        this.parse_error = ParseError.NONE;
        this.error_level = LogValidation.CLEAN;
        
        if (record == null || record.trim().isEmpty()) {
            this.raised_error = true;
            this.parse_error = ParseError.EMPTYLINE;
            this.error_level = LogValidation.WASTE;
            return;
        }
        
        if (APPNEWUSERHOURDB_PATTERN.matcher(record).matches() == true) {
            
            String[] fields = record.split(ConstantVars.DB_FIELD_SEPERATER_REGX, COL_COUNT);
            
            if (fields == null || fields.length < COL_COUNT) {
                this.raised_error = true;
                this.parse_error = ParseError.ERRORFIELDCOUNT;
                this.error_level = LogValidation.WASTE;
                return;
            }
            
            if (fields.length == COL_COUNT) {
                
                this.appkey = fields[COL_APPKEY].trim();
                this.token = fields[COL_TOKEN].trim();
                this.year = fields[COL_YEAR].trim();
                this.month = fields[COL_MONTH].trim();
                this.day = fields[COL_DAY].trim();
                this.hour = fields[COL_HOUR].trim();
                
                if ( !isValidNumber(this.year) || !isValidNumber(this.month)
                        || !isValidNumber(this.day) || !isValidNumber(this.hour) ) {
                    this.raised_error = true;
                    this.parse_error = ParseError.ERRORTIME;
                    this.error_level = LogValidation.MALFORMED;
                    return;
                }
                
                this.raised_error = false;
                this.error_level = LogValidation.CLEAN;
            }
            else {
                this.raised_error = true;
                this.parse_error = ParseError.ERRORFIELDCOUNT;
                this.error_level = LogValidation.WASTE;
                return;
            }
        }
        else {
            this.raised_error = true;
            this.parse_error = ParseError.MISSMATCH;
            this.error_level = LogValidation.WASTE;
            return;
        }
        
        return;
    }
    
    public boolean hasError() {
        return this.raised_error;
    }
    public int getParseError() {
        return this.parse_error;
    }
    public LogValidation getErrorLevel() {
        return this.error_level;
    }
    
    public double getDouble(String src) {
        if (src == null || src.isEmpty())
            return 0;
        return Double.parseDouble(src);
    }
    public int getInteger(String src) {
        if (src == null || src.isEmpty())
            return 0;
        return Integer.parseInt(src);
    }
    
    public boolean isValidNumber(String src) {
        return FormatUtil.isValidNumber(src);
    }
    
    public String getAppkey() {
        return this.appkey;
    }
    public String getToken() {
        return this.token;
    }
    public String getYear() {
        return this.year;
    }
    public String getMonth() {
        return this.month;
    }
    public String getDay() {
        return this.day;
    }
    public String getHour() {
        return this.hour;
    }
    
    public void printDebug() {
        System.out.println("[haserror] " + this.raised_error);
        System.out.println("[appkey] " + this.appkey);
        System.out.println("[token] " + this.token);
        System.out.println("[year] " + this.year);
        System.out.println("[month] " + this.month);
        System.out.println("[day] " + this.day);
        System.out.println("[hour] " + this.hour);
    }
    
    public static void main(String[] args) throws Exception {
        
        AppNewuserHourDbParser parser = new AppNewuserHourDbParser();
        
        String test = "";
        
        System.out.println("full");
        test = "fin01263	00000000-101c-4612-ffff-ffff9a3181c8	2014	04	17	16";
        parser.parse(test);
        parser.printDebug();
        
        System.out.println("miss");
        test = "fin01263	00000000-101c-4612-ffff-ffff9a3181c8	2014	04	17";
        parser.parse(test);
        parser.printDebug();
    }
}
