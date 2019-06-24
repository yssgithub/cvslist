package com.you.cvs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

import com.you.cvs.ProcessUtil.StringProcessor;

import javax.swing.*;

public class CvsUtil {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static String whereCvs() throws IOException {
        if(OS.indexOf("windows")>=0){
            String result = ProcessUtil.exec("where cvs");
            if("".equals(result)){
                JOptionPane.showMessageDialog(null, "未找到本机安装的CVS客户端");
                return null;
            }
            return ProcessUtil.exec("where cvs");
        }
        if(OS.indexOf("mac")>=0&&OS.indexOf("os")>0){
            String result = ProcessUtil.exec("mdfind cvs.app");
            if("".equals(result)){
                JOptionPane.showMessageDialog(null, "未找到本机安装的CVS客户端");
                return null;
            }
            return ProcessUtil.exec("mdfind cvs.app");
        }
        return null;
    }

    /**
     * 获取指定用户及日期的提交历史记录
     * @param userName cvs账号
     * @param date 格式：2019-05-31
     * @return
     * @throws IOException
     */
    public static String commitHistoryFilePath(String cvsroot, String projectPath, String userName, String date) throws IOException {
        String cvsRoot = ":pserver:"+userName+"@"+cvsroot;
        if(whereCvs()==null){
            return "";
        }
        return ProcessUtil.exec(whereCvs() + " -d " + cvsRoot + " history -c -u " + userName + " -D " + date, new StringProcessor() {
            @Override
            public String process(String str) {
                return str.replaceAll(
                        "[^\\s]+\\s+[^\\s]+\\s+[^\\s]+\\s+[^\\s]+\\s+[^\\s]+\\s+[^\\s]+\\s+([^\\s]+)\\s+"+projectPath+"([^\\s]+)\\s+== <remote>",
                        "$2/$1");
            }
        });
    }

}