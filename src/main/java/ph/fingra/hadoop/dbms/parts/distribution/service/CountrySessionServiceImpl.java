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

package ph.fingra.hadoop.dbms.parts.distribution.service;

import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import ph.fingra.hadoop.dbms.common.ConnectionFactory;
import ph.fingra.hadoop.dbms.parts.distribution.dao.CountrySessionDao;
import ph.fingra.hadoop.dbms.parts.distribution.domain.CountrySessionAll;

public class CountrySessionServiceImpl implements CountrySessionService {
    
    private static CountrySessionService instance = null;
    
    static {
        instance = new CountrySessionServiceImpl();
    }
    
    public static CountrySessionService getInstance() {
        return instance;
    }
    
    // ------------------------------------------------------------------------
    //cd_country_session_day
    // ------------------------------------------------------------------------
    
    public int insertBatchCountrySessionDay(List<CountrySessionAll> in_volist)
            throws Exception {
        
        if (in_volist == null) {
            return 0;
        }
        
        SqlSession session = ConnectionFactory.getSession().openSession(ExecutorType.BATCH, false);
        CountrySessionDao dao = session.getMapper(CountrySessionDao.class);
        
        boolean has_error = false;
        
        try {
            
            if (in_volist != null) {
                
                Iterator<CountrySessionAll> it = in_volist.iterator();
                
                while (it.hasNext()) {
                    CountrySessionAll insert = it.next();
                    dao.insertCountrySessionDay(insert);
                }
            }
            
            List<BatchResult> results = session.flushStatements();
            results.clear();
        }
        catch (Exception e) {
            has_error = true;
            session.rollback();
            session.close();
            throw e;
        }
        finally {
            if (has_error == false)
                session.commit();
            session.close();
        }
        
        return (has_error == false) ? 1 : 0;
    }
    
    public int deleteCountrySessionDayByDate(String year, String month,
            String day) throws Exception {
        
        SqlSession session = ConnectionFactory.getSession().openSession(ExecutorType.BATCH, false);
        CountrySessionDao dao = session.getMapper(CountrySessionDao.class);
        
        boolean has_error = false;
        
        try {
            dao.deleteCountrySessionDayByKey(year, month, day, "", "");
            List<BatchResult> results = session.flushStatements();
            results.clear();
        }
        catch (Exception e) {
            has_error = true;
            session.rollback();
            session.close();
            throw e;
        }
        finally {
            if (has_error == false)
                session.commit();
            session.close();
        }
        
        return (has_error == false) ? 1 : 0;
    }
    
    public int selectCountrySessionDayCountByKey(String year, String month,
            String day, String appkey, String country) throws Exception {
        
        SqlSession session = ConnectionFactory.getSession().openSession();
        CountrySessionDao dao = session.getMapper(CountrySessionDao.class);
        
        int cnt = 0;
        
        try {
            cnt = dao.selectCountrySessionDayCountByKey(year, month, day, appkey, country);
        }
        finally {
            session.close();
        }
        
        return cnt;
    }
    
    // ------------------------------------------------------------------------
    //cd_country_session_week
    // ------------------------------------------------------------------------
    
    public int insertBatchCountrySessionWeek(List<CountrySessionAll> in_volist)
            throws Exception {
        
        if (in_volist == null) {
            return 0;
        }
        
        SqlSession session = ConnectionFactory.getSession().openSession(ExecutorType.BATCH, false);
        CountrySessionDao dao = session.getMapper(CountrySessionDao.class);
        
        boolean has_error = false;
        
        try {
            
            if (in_volist != null) {
                
                Iterator<CountrySessionAll> it = in_volist.iterator();
                
                while (it.hasNext()) {
                    CountrySessionAll insert = it.next();
                    dao.insertCountrySessionWeek(insert);
                }
            }
            
            List<BatchResult> results = session.flushStatements();
            results.clear();
        }
        catch (Exception e) {
            has_error = true;
            session.rollback();
            session.close();
            throw e;
        }
        finally {
            if (has_error == false)
                session.commit();
            session.close();
        }
        
        return (has_error == false) ? 1 : 0;
    }
    
    public int deleteCountrySessionWeekByDate(String year, String week)
            throws Exception {
        
        SqlSession session = ConnectionFactory.getSession().openSession(ExecutorType.BATCH, false);
        CountrySessionDao dao = session.getMapper(CountrySessionDao.class);
        
        boolean has_error = false;
        
        try {
            dao.deleteCountrySessionWeekByKey(year, week, "", "");
            List<BatchResult> results = session.flushStatements();
            results.clear();
        }
        catch (Exception e) {
            has_error = true;
            session.rollback();
            session.close();
            throw e;
        }
        finally {
            if (has_error == false)
                session.commit();
            session.close();
        }
        
        return (has_error == false) ? 1 : 0;
    }
    
    public int selectCountrySessionWeekCountByKey(String year, String week,
            String appkey, String country) throws Exception {
        
        SqlSession session = ConnectionFactory.getSession().openSession();
        CountrySessionDao dao = session.getMapper(CountrySessionDao.class);
        
        int cnt = 0;
        
        try {
            cnt = dao.selectCountrySessionWeekCountByKey(year, week, appkey, country);
        }
        finally {
            session.close();
        }
        
        return cnt;
    }
    
    // ------------------------------------------------------------------------
    //cd_country_session_month
    // ------------------------------------------------------------------------
    
    public int insertBatchCountrySessionMonth(List<CountrySessionAll> in_volist)
            throws Exception {
        
        if (in_volist == null) {
            return 0;
        }
        
        SqlSession session = ConnectionFactory.getSession().openSession(ExecutorType.BATCH, false);
        CountrySessionDao dao = session.getMapper(CountrySessionDao.class);
        
        boolean has_error = false;
        
        try {
            
            if (in_volist != null) {
                
                Iterator<CountrySessionAll> it = in_volist.iterator();
                
                while (it.hasNext()) {
                    CountrySessionAll insert = it.next();
                    dao.insertCountrySessionMonth(insert);
                }
            }
            
            List<BatchResult> results = session.flushStatements();
            results.clear();
        }
        catch (Exception e) {
            has_error = true;
            session.rollback();
            session.close();
            throw e;
        }
        finally {
            if (has_error == false)
                session.commit();
            session.close();
        }
        
        return (has_error == false) ? 1 : 0;
    }
    
    public int deleteCountrySessionMonthByDate(String year, String month)
            throws Exception {
        
        SqlSession session = ConnectionFactory.getSession().openSession(ExecutorType.BATCH, false);
        CountrySessionDao dao = session.getMapper(CountrySessionDao.class);
        
        boolean has_error = false;
        
        try {
            dao.deleteCountrySessionMonthByKey(year, month, "", "");
            List<BatchResult> results = session.flushStatements();
            results.clear();
        }
        catch (Exception e) {
            has_error = true;
            session.rollback();
            session.close();
            throw e;
        }
        finally {
            if (has_error == false)
                session.commit();
            session.close();
        }
        
        return (has_error == false) ? 1 : 0;
    }
    
    public int selectCountrySessionMonthCountByKey(String year, String month,
            String appkey, String country) throws Exception {
        
        SqlSession session = ConnectionFactory.getSession().openSession();
        CountrySessionDao dao = session.getMapper(CountrySessionDao.class);
        
        int cnt = 0;
        
        try {
            cnt = dao.selectCountrySessionMonthCountByKey(year, month, appkey, country);
        }
        finally {
            session.close();
        }
        
        return cnt;
    }
    
}
