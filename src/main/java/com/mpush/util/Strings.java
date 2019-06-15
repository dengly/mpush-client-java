/*
 * (C) Copyright 2015-2016 the original author or authors.
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
 *
 * Contributors:
 *     ohun@live.cn (夜色)
 */

package com.mpush.util;


/**
 * Created by ohun on 2015/12/23.
 *
 * 字符串工具
 *
 * @author ohun@live.cn (夜色)
 */
public final class Strings {
    public static final String EMPTY = "";

    /**
     * 用于验证用户id、别名、标签的正则表达式
     */
    private static final String REGEX_USERID_ALIAS_TAGS = "^([A-Za-z0-9_\\-]){1,127}$";
    public static boolean verifyString(String str, String regex){
        return str.matches(regex);
    }
    public static boolean verifyUserId(String userId){
        if(userId==null){
            return false;
        }
        return verifyString(userId, REGEX_USERID_ALIAS_TAGS);
    }
    public static boolean verifyAlias(String alias){
        if(alias==null){
            return false;
        }
        return verifyString(alias, REGEX_USERID_ALIAS_TAGS);
    }
    public static boolean verifyTags(String tags){
        if(tags==null){
            return false;
        }
        if(tags.indexOf(",")>0){
            String[] tagsArr = tags.split(",");
            boolean rev = true;
            for(String item : tagsArr){
                rev = rev && verifyString(item, REGEX_USERID_ALIAS_TAGS);
                if(!rev){
                    return rev;
                }
            }
            return rev;
        }else{
            return verifyString(tags, REGEX_USERID_ALIAS_TAGS);
        }
    }

    public static boolean isBlank(CharSequence text) {
        if (text == null || text.length() == 0) return true;
        for (int i = 0, L = text.length(); i < L; i++) {
            if (!Character.isWhitespace(text.charAt(i))) return false;
        }
        return true;
    }

    public static long toLong(String text, long defaultVal) {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
        }
        return defaultVal;
    }

    public static int toInt(String text, int defaultVal) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
        }
        return defaultVal;
    }
}
