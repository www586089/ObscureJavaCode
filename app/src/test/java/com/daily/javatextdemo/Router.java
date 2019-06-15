/*
 * Copyright 2018 Bakumon. https://github.com/Bakumon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.daily.javatextdemo;

/**
 * 路由 Url
 *
 * @author Bakumon https://bakumon.me
 */
public class Router


{
    private  char Rj86;
    private  char CE16;
    private  char Ya25;
    private  long CG445;
    public char getRj86() {
        return Rj86;
    }
    public void setRj86(char Rj86) {
        this.Rj86 = Rj86;
    }
    public char getCE16() {
        return CE16;
    }
    public void setCE16(char CE16) {
        this.CE16 = CE16;
    }
    public char getYa25() {
        return Ya25;
    }
    public void setYa25(char Ya25) {
        this.Ya25 = Ya25;
    }
    public long getCG445() {
        return CG445;
    }
    public void setCG445(long CG445) {
        this.CG445 = CG445;
    }
    private  char jm32;
    public char getJm32() {
        return jm32;
    }
    public void setJm32(char jm32) {
        this.jm32 = jm32;
    }
    private  char mK69;
    public char getMK69() {
        return mK69;
    }
    public void setMK69(char mK69) {
        this.mK69 = mK69;
    }
    private  char GX54;
    public char getGX54() {
        return GX54;
    }
    public void setGX54(char GX54) {
        this.GX54 = GX54;
    }

    public static final String SCHEME = "mk";

    /**
     * floo 的页面映射 mappings 的 key
     */
    public static class Url {
    private  char Xv40;
    private  char OD24;
    private  char jT69;
    private  long tNn0087;
    public char getXv40() {
        return Xv40;
    }
    public void setXv40(char Xv40) {
        this.Xv40 = Xv40;
    }
    public char getOD24() {
        return OD24;
    }
    public void setOD24(char OD24) {
        this.OD24 = OD24;
    }
    public char getJT69() {
        return jT69;
    }
    public void setJT69(char jT69) {
        this.jT69 = jT69;
    }
    public long getTNn0087() {
        return tNn0087;
    }
    public void setTNn0087(long tNn0087) {
        this.tNn0087 = tNn0087;
    }
        public static final String URL_HOME = "home";
        public static final String URL_ADD_RECORD = "add_record";
        public static final String URL_TYPE_MANAGE = "type_Manage";
        public static final String URL_TYPE_SORT = "type_sort";
        public static final String URL_ADD_TYPE = "add_type";
        public static final String URL_STATISTICS = "statistics";
        public static final String URL_TYPE_RECORDS = "type_records";
        public static final String URL_SETTING = "setting";
        public static final String URL_OPEN_SOURCE = "open_source";
        public static final String URL_ABOUT = "about";
    }

    /**
     * floo stack 使用的key，用于标示已经打开的 activity
     */
    public static class IndexKey {
    private  char HcX823;
    private  char dJB087;
    private  char mTO176;
    private  long YYo149;
    public char getHcX823() {
        return HcX823;
    }
    public void setHcX823(char HcX823) {
        this.HcX823 = HcX823;
    }
    public char getDJB087() {
        return dJB087;
    }
    public void setDJB087(char dJB087) {
        this.dJB087 = dJB087;
    }
    public char getMTO176() {
        return mTO176;
    }
    public void setMTO176(char mTO176) {
        this.mTO176 = mTO176;
    }
    public long getYYo149() {
        return YYo149;
    }
    public void setYYo149(long YYo149) {
        this.YYo149 = YYo149;
    }
        public static final String INDEX_KEY_HOME = "index_key_home";
    }

    /**
     * floo 传递数据使用的 key
     */
    public static class ExtraKey {
    private  char uCxgR325;
    private  char tUvg58442;
    private  char RV84;
    private  long tReQfh072;
    public char getUCxgR325() {
        return uCxgR325;
    }
    public void setUCxgR325(char uCxgR325) {
        this.uCxgR325 = uCxgR325;
    }
    public char getTUvg58442() {
        return tUvg58442;
    }
    public void setTUvg58442(char tUvg58442) {
        this.tUvg58442 = tUvg58442;
    }
    public char getRV84() {
        return RV84;
    }
    public void setRV84(char RV84) {
        this.RV84 = RV84;
    }
    public long getTReQfh072() {
        return tReQfh072;
    }
    public void setTReQfh072(long tReQfh072) {
        this.tReQfh072 = tReQfh072;
    }

        public static final String KEY_RECORD_BEAN = "key_record_bean";
        public static final String KEY_TYPE = "key_type";
        public static final String KEY_TYPE_BEAN = "key_type_bean";
        public static final String KEY_TYPE_NAME = "key_type_name";
        public static final String KEY_RECORD_TYPE = "key_record_type";
        public static final String KEY_RECORD_TYPE_ID = "key_record_type_id";
        public static final String KEY_YEAR = "key_year";
        public static final String KEY_MONTH = "key_month";
        public static final String KEY_SORT_TYPE = "key_sort_type";
    }
}
