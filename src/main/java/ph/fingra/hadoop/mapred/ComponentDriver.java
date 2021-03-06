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

package ph.fingra.hadoop.mapred;

import org.apache.hadoop.util.ProgramDriver;

import ph.fingra.hadoop.common.logger.ErrorLogger;
import ph.fingra.hadoop.mapred.parts.component.ComponentAppversionStatistic;
import ph.fingra.hadoop.mapred.parts.component.ComponentCountryStatistic;
import ph.fingra.hadoop.mapred.parts.component.ComponentDeviceStatistic;
import ph.fingra.hadoop.mapred.parts.component.ComponentFrequencyStatistic;
import ph.fingra.hadoop.mapred.parts.component.ComponentHourSessionStatistic;
import ph.fingra.hadoop.mapred.parts.component.ComponentLanguageStatistic;
import ph.fingra.hadoop.mapred.parts.component.ComponentNewuserStatistic;
import ph.fingra.hadoop.mapred.parts.component.ComponentOsversionStatistic;
import ph.fingra.hadoop.mapred.parts.component.ComponentResolutionStatistic;
import ph.fingra.hadoop.mapred.parts.component.ComponentUserSessionStatistic;

public class ComponentDriver {

    public static void main(String argv[]) {
        
        int exitcode = -1;
        
        ProgramDriver pgd = new ProgramDriver();
        try {
            
            pgd.addClass("componentnewuser", ComponentNewuserStatistic.class,
                    "Fingraph OSS map/reduce program for component/componentnewuser");
            pgd.addClass("componentusersession", ComponentUserSessionStatistic.class,
                    "Fingraph OSS map/reduce program for component/componentusersession");
            pgd.addClass("componentfrequency", ComponentFrequencyStatistic.class,
                    "Fingraph OSS map/reduce program for component/componentfrequency");
            pgd.addClass("componenthoursession", ComponentHourSessionStatistic.class,
                    "Fingraph OSS map/reduce program for component/componenthoursession");
            pgd.addClass("componentdevice", ComponentDeviceStatistic.class,
                    "Fingraph OSS map/reduce program for component/componentdevice");
            pgd.addClass("componentcountry", ComponentCountryStatistic.class,
                    "Fingraph OSS map/reduce program for component/componentcountry");
            pgd.addClass("componentlanguage", ComponentLanguageStatistic.class,
                    "Fingraph OSS map/reduce program for component/componentlanguage");
            pgd.addClass("componentappversion", ComponentAppversionStatistic.class,
                    "Fingraph OSS map/reduce program for component/componentappversion");
            pgd.addClass("componentosversion", ComponentOsversionStatistic.class,
                    "Fingraph OSS map/reduce program for component/componentosversion");
            pgd.addClass("componentresolution", ComponentResolutionStatistic.class,
                    "Fingraph OSS map/reduce program for component/componentresolution");
            
            pgd.driver(argv);
            
            // seccess
            exitcode = 0;
        }
        catch(Throwable e) {
            ErrorLogger.log(e.toString());
        }
        
        System.exit(exitcode);
    }
}
