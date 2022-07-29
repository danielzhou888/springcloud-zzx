package com.zzx.tools.refreshIpPv;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class FileUtil {
 
    //追加写入数据
    public  void write(String selection,String data,boolean isAppend){
        //输出流
        File file = new File(System.getProperty("user.dir")+"/conf");
        if(!file.exists()){
            file.mkdir();
        }
        try{
            if (selection.toLowerCase().equals("ip")){
                 file = new File(System.getProperty("user.dir")+"/conf/ip.txt");
            }
            if(selection.toLowerCase().equals("url")){
                file = new File(System.getProperty("user.dir")+"/conf/url.txt");
            }
            FileOutputStream fos = new FileOutputStream(file,isAppend);
            fos.write(data.getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
        }catch (Exception e){
            System.out.println("写入文件失败。");
        }
    }
    //读取文件，并将文件内容写入一个list里，返回该list
    public List<String> readFile(String fileName){
        List<String> listStr = new ArrayList<>();
        //输入流
        String path = FileUtil.class.getResource("").getPath();
        File file = new File(System.getProperty("user.dir")+"/conf/"+fileName);
        try{
            FileInputStream is = new FileInputStream(file);
            Scanner scanner = new Scanner(is);
            while (scanner.hasNextLine()) {
                listStr.add(scanner.nextLine());
            }
            scanner.close();
            is.close();
        } catch (Exception e) {
            System.out.println("读取文件失败");
        }
        return listStr;
    }
}