package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class MainActivity extends AppCompatActivity {
    TextView bus_text;
    String bus_data="new";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bus_text = findViewById(R.id.bus);
        bus_text.setText("failed");
        String stateID = "200000078";


        bus_text.setText(bus_data);



    }

    public String test() throws ParserConfigurationException, IOException, SAXException {
        String serviceKey = "h6nv4lK9Cv3Anb96btTQBHJLS3mk28kTbm3s%2BjyuoWIXpZ9s2H0jHqOaOA1OTb2c979KZfVibugPdBXRvUY92w%3D%3D";
        String stationId = "200000078"; //예제 정류장
        String queryUrl = "http://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalList?serviceKey="+serviceKey+"&stationId="+stationId;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder doc = factory.newDocumentBuilder();
        Document parser = doc.parse("C:\\Users\\ghee9\\AndroidStudioProjects\\Notification\\app\\src\\main\\res\\layout\\db_list.xml");
        NodeList list = parser.getElementsByTagName("LinearLayout");
        return list.toString();

    }

//
//    public String test() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
//        String serviceKey = "h6nv4lK9Cv3Anb96btTQBHJLS3mk28kTbm3s%2BjyuoWIXpZ9s2H0jHqOaOA1OTb2c979KZfVibugPdBXRvUY92w%3D%3D";
//        String stationId = "200000078"; //예제 정류장
//        String queryUrl = "http://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalList?serviceKey="+serviceKey+"&stationId="+stationId;
//
//        //https://makerj.tistory.com/149
//        Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(queryUrl.getBytes()));
//        xml.getDocumentElement().normalize();
//
//        XPath xpath = XPathFactory.newInstance().newXPath();
//
//        NodeList node = (NodeList) xpath.evaluate("//", xml, XPathConstants.NODESET);
//        return node.toString();
//
//
//    }






//
//    public void test() throws ParserConfigurationException, IOException, SAXException {
//
//        String serviceKey = "h6nv4lK9Cv3Anb96btTQBHJLS3mk28kTbm3s%2BjyuoWIXpZ9s2H0jHqOaOA1OTb2c979KZfVibugPdBXRvUY92w%3D%3D";
//        String stationId = "200000078"; //예제 정류장
//        String queryUrl = "http://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalList?serviceKey="+serviceKey+"&stationId="+stationId;
//
//        Document info = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(queryUrl);
//        info.getDocumentElement().normalize();
//        NodeList nList = info.getElementsByTagName("busArrivalList");
//        bus_data = nList.getTextContent
//
////        for(int temp = 0; temp < nList.getLength(); temp++){
////            Node nNode = nList.item(temp);
////            if(nNode.getNodeType()==Node.ELEMENT_NODE){
////                Element e = (Element) nNode;
////
////
////            }
//
//    }////https://grand-unified-engine.tistory.com/37
/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub

                // 아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기
                // API
                bus_data = getBusXmlData();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        bus_text.setText(bus_data+"\n\n"); //TextView에 문자열  data 출력
                    }
                });
            }
        }).start();
   }


    String getBusXmlData(){
        StringBuffer buffer=new StringBuffer("after");

        String serviceKey = "h6nv4lK9Cv3Anb96btTQBHJLS3mk28kTbm3s%2BjyuoWIXpZ9s2H0jHqOaOA1OTb2c979KZfVibugPdBXRvUY92w%3D%3D";
        String stationId = "200000078"; //예제 정류장


        String queryUrl = "http://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalList?serviceKey="+serviceKey+"&stationId="+stationId;

        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;
            int i =0;
            xpp.next();
            int eventType= xpp.getEventType();
            buffer.append(xpp.getText());


            while(eventType != XmlPullParser.END_DOCUMENT){
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//태그 이름 얻어오기
                        System.out.println(tag);

                        if(tag.equals("response")) ; // 첫번째 검색결과
                        else if(tag.equals("fcstValue")) {
                            i++;
                            if (i == 1) {
                                buffer.append("강수확률 : ");
                                xpp.next();
                                buffer.append(xpp.getText()+"%");
                                buffer.append("\n");
                            }
                            if (i == 2) {
                                buffer.append("강수형태 : ");
                                xpp.next();
                                if(xpp.getText().equals("0")){
                                    buffer.append("없음");
                                    buffer.append("\n");
                                }
                                else if(xpp.getText().equals("1")){
                                    buffer.append("비");
                                    buffer.append("\n");
                                }
                                else if(xpp.getText().equals("2")){
                                    buffer.append("비+눈(진눈개비)");
                                    buffer.append("\n");
                                }
                                else if(xpp.getText().equals("3")){
                                    buffer.append("눈");
                                    buffer.append("\n");
                                }
                                else if(xpp.getText().equals("4")){
                                    buffer.append("소나기");
                                    buffer.append("\n");
                                }
                                else if(xpp.getText().equals("5")){
                                    buffer.append("빗방울");
                                    buffer.append("\n");
                                }
                                else if(xpp.getText().equals("6")){
                                    buffer.append("빗방울+눈날림");
                                    buffer.append("\n");
                                }
                                else if(xpp.getText().equals("7")){
                                    buffer.append("눈날림");
                                    buffer.append("\n");
                                }
                            }
                            if(i==3){
                                buffer.append("6시간 강수량 : ");
                                xpp.next();
                                buffer.append(xpp.getText()+"mm");
                                buffer.append("\n");
                            }
                            if(i==4){
                                buffer.append("습도 : ");
                                xpp.next();
                                buffer.append(xpp.getText()+"%");
                                buffer.append("\n");
                            }
                            if(i==5){
                                buffer.append("6시간 신적설 : ");
                                xpp.next();
                                buffer.append(xpp.getText()+"cm");
                                //   buffer.append("\n");
                            }
                            if(i==7){
                                buffer.append("3시간 기온 : ");
                                xpp.next();
                                buffer.append(xpp.getText()+"℃");
                                buffer.append("\n");
                            }
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //태그 이름 얻어오기
                        if(tag.equals("item")) buffer.append("\n");
                        break;
                }
                eventType= xpp.next();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
        }
        return buffer.toString();//StringBuffer 문자열 객체 반환
    }
*/













    //알림
    public void createNotification(View view) {
        show();
    }

    private void show() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("알림 제목");
        builder.setContentText("알림 세부 텍스트");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //이미 실행되어 있으면 내용을 업데이트 해줌

        builder.setContentIntent(pendingIntent);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(largeIcon);

        builder.setColor(Color.RED);

        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(ringtoneUri);

        long[] vibrate = {0,100,200,300};   //진동 패턴
        builder.setVibrate(vibrate);
        builder.setAutoCancel(true);    //노티를 클릭했을 때도 노티ㅇㅇ 아니면 false

        //오레오에서 채널 등록 필요
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); //%%과 같은코드
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("default", "기본 채널",
                    NotificationManager.IMPORTANCE_DEFAULT));
        }
        manager.notify(1, builder.build());
    }

    public void removeNotification(View view) {
        hide();
    }

    private void hide() {
        NotificationManagerCompat.from(this).cancel(1); //%%과 같은코드, 1번 노티 제거
    }

}