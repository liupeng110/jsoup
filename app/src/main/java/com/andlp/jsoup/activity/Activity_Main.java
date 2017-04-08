package com.andlp.jsoup.activity;

import android.os.Environment;
import android.os.Bundle;
import android.widget.TextView;

import com.andlib.lp.util.L;
import com.andlp.jsoup.R;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class Activity_Main extends  Activity_Base {
   @ViewInject(R.id.txt) TextView txt;
    String tag ="Activity_Main";
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        test();


//        Blog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        article();

    }

    private void test(){
        x.task().run(new Runnable() {
            @Override
            public void run() {
                try {
                    File input = new File( Environment.getExternalStorageDirectory().getPath()+"/andlp/a.html");
                    if (!input.exists()){ input.createNewFile();}
////                    String url = "https://www.baidu.com/s?wd= 在线 无需播放器";
//                    Connection connect = HttpConnection.connect(url);
//                    connect.timeout(3000);
//                    connect.header("Accept-Encoding", "gzip,deflate,sdch");
//                    connect.header("Connection", "close");
//                    connect.validateTLSCertificates(false);
//                    connect.execute();


                    //Document parse = connect.post();
//                    Document doc2 =connect.get();
//                    L.i(tag+doc2.title());
//                    final String content = connect.get().html();

                     Document doc2 = Jsoup.connect("https://m.baidu.com/?from=1013843a&pu=sz%401321_480&wpo=btmfast")
                            .validateTLSCertificates(false)
                            .get();
                    final String content = doc2.toString();
                    L.i(tag+content.length());
                    L.i(tag+content);
                    L.i(tag+content.length());
//                    String content = doc2.toString();
                    x.task().autoPost(new Runnable() {
                        @Override
                        public void run() {
                            txt.setText(content+content.length());
//                            text.setText(content.substring(0,1200));
                        }
                    });

                }catch (Throwable t){t.printStackTrace();}
            }
        });

    }
    /**
     * 获取博客上的文章标题和链接
     */
    public   void article() {
        x.task().run(new Runnable() {
            @Override
            public void run() {
                Document doc;
                try {
                    doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/").get();
                    Elements ListDiv = doc.getElementsByAttributeValue("class", "postTitle");
                    for (Element element : ListDiv) {
                        Elements links = element.getElementsByTag("a");
                        for (Element link : links) {
                              String linkHref = link.attr("href");
                              String linkText = link.text().trim();
                            System.out.println(linkHref);
                            System.out.println(linkText);
                            list.add(linkHref+"\n");
                            list.add(linkText+"\n");
                            x.task().autoPost(new Runnable() {
                                @Override
                                public void run() {
                                    String  sb="";
                                     int size =list.size();
                                        for (int a=0;a<size;a++){
                                            sb+=list.get(a);
                                        }
                                  txt.setText(sb);
                                }
                            });
                        }
                    }
                } catch( IOException e)
                { e.printStackTrace(); }

            }   });
    }
    /**
     * 获取指定博客文章的内容
     */
    public static void Blog() {
        x.task().run(new Runnable() {
            @Override
            public void run() {
                Document doc;
        try {
            doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","postBody");
            for (Element element :ListDiv) {
                System.out.println(element.html());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            }   });
    }


    public void search(String keyword, int page) {
            int pageSize = 10;
            //百度搜索结果每页大小为10，pn参数代表的不是页数，而是返回结果的开始数
            //如获取第一页则pn=0，第二页则pn=10，第三页则pn=20，以此类推，抽象出模式：(page-1)*pageSize
            String url = "http://www.baidu.com/s?pn=" + (page - 1) * pageSize + "&wd=" + keyword;

            try {
                Document document = Jsoup.connect(url).get();

                //获取搜索结果数目
                int total = 0;
                int len = 10;
                if (total < 1) {     } //null
                //如果搜索到的结果不足一页
                if (total < 10) { len = total;  }


            }catch (Throwable t){t.printStackTrace();}
        }
}




