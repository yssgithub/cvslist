package com.you;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.DumbAwareRunnable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import com.intellij.ui.components.JBScrollPane;
import com.you.cvs.CvsUtil;
import com.you.cvs.ProcessUtil;
import com.you.synth.SynthFrame;
import com.you.utils.DateChooser;
import com.you.utils.MyPanel;


import javax.swing.*;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class PrintCvsListAction extends AnAction implements DumbAwareRunnable {

    private static boolean norecord = false;
    private static String noconnected = null;
    private static String result = null;

    private static String inputDate = null;
    private static String message = "";
    private static String title = "CVS History";
    private static final String userHome = System.getProperty("user.dir") + "/.idea/cvs_conf.properties";

    private static JFrame jFrame = null;
    private static MyPanel jPanel = null;
    private static JLabel cvsroot = null;
    private static JTextField cvsrootField = null;
    private static JLabel projectPath = null;
    private static JTextField projectPathField = null;
    private static JLabel username = null;
    private static JTextField usernameField = null;
    private static JLabel password = null;
    private static JTextField passwordField = null;
    private static JButton login = null;
    private static JLabel date = null;
    private static JTextField date1Field = null;
    private static JTextField date2Field = null;
    private static JButton ok = null;
    private static JButton cancel = null;
    private static JScrollPane jScrollPane = null;
    private static JPopupTextArear jPopupTextArearField = null;

    private static final int FRAME_WIDTH = 550;
    private static final int FRAME_HEIGHT = 650;

    @Override
    public void actionPerformed(AnActionEvent e) {
        PrintCvsListAction runnable = new PrintCvsListAction();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void run() {

    }public static void main(String[] args) {
        try {
            SynthLookAndFeel synth = new SynthLookAndFeel();
            synth.load(SynthFrame.class.getResourceAsStream("demo.xml"), SynthFrame.class);
            UIManager.setLookAndFeel(synth);

            jFrame = new JFrame("cvs history list");
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            jFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            int x = (int)(toolkit.getScreenSize().getWidth()-jFrame.getWidth())/2;
            int y = (int)(toolkit.getScreenSize().getHeight()-jFrame.getHeight())/2;
            jFrame.setLocation(x, y);
            jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jFrame.setResizable(false);

            jPanel = new MyPanel(null);
            jPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

            cvsroot = new JLabel("cvsroot");
            cvsroot.setBounds(80, 30, 100, 30);
            cvsrootField = new JTextField("cvsroot");
            cvsrootField.setBounds(80+100, 30, 300, 30);

            projectPath = new JLabel("projectPath");
            projectPath.setBounds(80, 30+50, 100, 30);
            projectPathField = new JTextField("projectPath");
            projectPathField.setBounds(80+100, 30+50, 300, 30);

            username = new JLabel("用户名");
            username.setBounds(80, 30+100, 100, 30);
            usernameField = new JTextField("username");
            usernameField.setBounds(80+100, 30+100, 300, 30);
            
            password = new JLabel("密  码");
            password.setBounds(80, 30+150, 100, 30);
            passwordField = new JTextField("password");
            passwordField.setBounds(80+100, 30+150, 160, 30);
            login = new JButton("登  录");
            login.setBounds(80+300, 30+150, 100, 30);
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cvsroot = cvsrootField.getText();
                    if(cvsroot==null || "".equals(cvsroot.trim())){
                        JOptionPane.showMessageDialog(jFrame, "cvsroot信息未填写");
                        return;
                    }
                    String username = usernameField.getText();
                    if(username==null || "".equals(username.trim())){
                        JOptionPane.showMessageDialog(jFrame, "用户名未填写");
                        return;
                    }
                    String password = passwordField.getText();
                    if(password==null || "".equals(password.trim())){
                        JOptionPane.showMessageDialog(jFrame, "密码未填写");
                        return;
                    }
                    try {
                        String s = CvsUtil.loginCvsServer(cvsroot, username, password);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });

            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            date = new JLabel("提交日期");
            date.setBounds(80, 30+150+50, 100, 30);
            date1Field = new JTextField(today);
            date1Field.setFocusable(false);
            date1Field.setBounds(80+100, 30+150+50, 100, 30);
            date2Field = new JTextField(today);
            date2Field.setFocusable(false);
            DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
            dateChooser1.register(date1Field, true, date2Field);
            DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
            dateChooser2.register(date2Field, false, date1Field);
            date2Field.setBounds(80+100+200, 30+150+50, 100, 30);

            ok = new JButton("获取历史");
            ok.setBounds(50+50, 30+230+50, 100, 30);
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cvsroot = cvsrootField.getText();
                    if(cvsroot==null || "".equals(cvsroot.trim())){
                        JOptionPane.showMessageDialog(jFrame, "cvsroot信息未填写");
                        return;
                    }
                    String projectPath = projectPathField.getText();
                    if(projectPath==null || "".equals(projectPath.trim())){
                        JOptionPane.showMessageDialog(jFrame, "projectPath信息未填写");
                        return;
                    }
                    String username = usernameField.getText();
                    if(username==null || "".equals(username.trim())){
                        JOptionPane.showMessageDialog(jFrame, "用户信息未填写");
                        return;
                    }
                    username = username.replaceAll("，", ",");
                    String date1 = date1Field.getText();
                    String date2 = date2Field.getText();

                    //call the method
                    getCvsHistory(cvsroot, projectPath, username, date1, date2);
                }
            });
            cancel = new JButton("暂不获取");
            cancel.setBounds(50+100+200, 30+230+50, 100, 30);
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jFrame.dispose();
                }
            });

            jPopupTextArearField = new JPopupTextArear("");
            jPopupTextArearField.setSelectedTextColor(Color.YELLOW);
            jPopupTextArearField.setSelectionColor(Color.GRAY);
            jPopupTextArearField.setSize(FRAME_WIDTH-60, 230);
            jScrollPane = new JBScrollPane(jPopupTextArearField);
            jScrollPane.setVerticalScrollBarPolicy(JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            jScrollPane.setBounds(30, 320+50, FRAME_WIDTH-60, 230);

            jPanel.add(cvsroot);
            jPanel.add(cvsrootField);

            jPanel.add(projectPath);
            jPanel.add(projectPathField);

            jPanel.add(username);
            jPanel.add(usernameField);

            jPanel.add(password);
            jPanel.add(passwordField);
            jPanel.add(login);

            jPanel.add(date);
            jPanel.add(date1Field);
            jPanel.add(date2Field);

            jPanel.add(ok);
            jPanel.add(cancel);

            jPanel.add(jScrollPane);

            jFrame.add(jPanel);

            jFrame.setVisible(true);

            //回填信息
            String readLine = null;
            File file = new File(userHome);
            if(file.exists()) {
                Properties p = new Properties();
                InputStream is = new FileInputStream(file);
                p.load(is);
                Iterator it = p.keySet().iterator();

                cvsrootField.setText(p.getOrDefault("cvsroot", "cvsroot")+"");
                projectPathField.setText(p.getOrDefault("projectPath", "projectPath")+"");
                usernameField.setText(p.getOrDefault("username", "username")+"");

                is.close();
            }
        }catch (Exception e){

        }
    }

    private static void getCvsHistory(String cvsroot, String projectPath, String username, String date1, String date2){
        Set<String> set = new HashSet<>();
        String[] arr = null;
        String[] strings = null;
        String[] userList = null;
        List<String> dates = new ArrayList<>();
        try {
            userList = username.split(",");

            dates = getDates(date1, date2);

            for(int i=0; i<userList.length; i++) {
                for (int j = 0; j < dates.size(); j++) {
                    arr = CvsUtil.commitHistoryFilePath(cvsroot, projectPath, userList[i], dates.get(j)).split("\n");
                    for (Iterator<String> iterator = new TreeSet<>(Arrays.asList(arr)).iterator(); iterator.hasNext(); ) {
                        set.add(iterator.next());
                    }
                }
            }
            norecord = false;
            noconnected = null;
            if(!set.isEmpty()){
                set.forEach(name->{
                    if("No records selected.".equals(name)){
                        norecord = true;
                    }
                    if("".equals(name) && set.size()==1){
                        noconnected = "请检查是否连接了CVS服务器";
                    }
                });
            }
            if(noconnected!=null){
                result = noconnected;
                jPopupTextArearField.setText(result);
                return;
            }else if(set.size()==0 || norecord){
                result = date1 + "到" + date2 + "未找到用户["+username+"]的提交记录";
                if(date1.equals(date2)){
                    result = date1 + "未找到用户["+username+"]的提交记录";
                }
                jPopupTextArearField.setText(result);
                return;
            }else{
                strings = new String[set.size()];
                strings = set.toArray(strings);
                result = array2String(strings);
                Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable text = new StringSelection(result);
                sysClip.setContents(text, null);
                jPopupTextArearField.setText(result);

                //成功后保存信息
                File propFile = new File(userHome);
                boolean createFile = true;
                if(propFile.exists()){
                    propFile.delete();
                }
                if(!propFile.exists()){
                    createFile = propFile.createNewFile();
                }
                if(!createFile){
                    JOptionPane.showMessageDialog(jFrame, "获取配置数据失败");
                    return;
                }
                //put key-val into file
                FileOutputStream out = new FileOutputStream(userHome);
                OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
                BufferedWriter bufWrite = new BufferedWriter(outWriter);
                bufWrite.write("cvsroot=" + cvsroot + "\r\n");
                bufWrite.write("projectPath=" + projectPath + "\r\n");
                bufWrite.write("username=" + username + "\r\n");
                bufWrite.close();
                outWriter.close();
                out.close();
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(jFrame, e.getMessage());
        }
    }

    /**
     * 获取两个日期之间的所有日期
     * @param date1
     * @param date2
     * @return
     */
    private static List<String> getDates(String date1, String date2) throws Exception{
        ArrayList<String> dates = new ArrayList<String>();
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date1);//定义起始日期
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);//定义结束日期
        if(endDate.before(startDate)){
            Date tmp = startDate;
            startDate = endDate;
            endDate = tmp;
        }
        Calendar dd = Calendar.getInstance();//定义日期实例
        dd.setTime(startDate);//设置日期起始时间
        while (dd.getTime().getTime() <= endDate.getTime()) {//判断是否到结束日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sdf.format(dd.getTime());
            dates.add(str);
            dd.add(Calendar.DAY_OF_MONTH, 1);//进行当前日期月份加1
        }
        return dates;
    }

    public static String array2String(String [] stringArray) {
        if (stringArray == null) {
            return null;
        }
        return String.join("\n", stringArray);
    }

    @Override
    public boolean isDumbAware() {
        return true;
    }
    
}
