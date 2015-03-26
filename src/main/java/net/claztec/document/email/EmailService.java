package net.claztec.document.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * Created by claztec on 15. 3. 25.
 */
@Service
public class EmailService {

    @Autowired
    private MailSender mailSender;

    public void send(String from, String to, String subject, String message) {
        sendEmail(from, to, subject, message);
    }

    @Async
    public Future sendAsync(String from, String to, String subject, String message) {
        return sendEmail(from, to, subject, message);
    }

    private Future sendEmail(String from, String to, String subject, String message) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> SEND MAIL START  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> SEND MAIL END  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return new AsyncResult("Send Mail OK");
    }


    public void infinityLoop(String name) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> SYNC LOOP START + " + name + "  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        int count = 0;
        boolean isTrue = true;
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while(isTrue) {
            System.out.println("loop count:" + count);
            if (count == 999999) {
                isTrue = false;
            }
            count++;
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> SYNC LOOP END + " + name + "  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }

    @Async
    public Future asyncInfinityLoop(String name) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> ASYNC LOOP START + " + name + " + >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("/media/claztec/Storage1/Log/log.txt"));


            System.out.println("File created successfully");
            out.write(">>>>>>>>>>> created >>>>>>>>>>>>>>>\n");
            int count = 0;
            boolean isTrue = true;
    //        try {
    //            Thread.sleep(10000);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
            while(isTrue) {
//                System.out.println("loop count:" + count);
                out.write("loop count:" + count + "\n");
                if (count == 999999) {
                    isTrue = false;
                }
                count++;
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> ASYNC LOOP END + " + name + " + >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        return new AsyncResult("LOOP END OK");

    }



    @Async
    public void asyncInfinityLoopNohup(String name) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> ASYNC LOOP START + " + name + " + >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        BufferedWriter out = null;
        try {

            Date date =  new Date();
            String timeStamp = new Timestamp(date.getTime()).toString();
            out = new BufferedWriter(new FileWriter("/media/claztec/Storage1/Log/log.txt." + timeStamp));


            System.out.println("File created successfully");
            out.write(">>>>>>>>>>> created >>>>>>>>>>>>>>>\n");
            int count = 0;
            boolean isTrue = true;
            //        try {
            //            Thread.sleep(10000);
            //        } catch (InterruptedException e) {
            //            e.printStackTrace();
            //        }
            while(isTrue) {
//                System.out.println("loop count:" + count);
                out.write("loop count:" + count + "\n");
                if (count == 999999) {
                    isTrue = false;
                }
                count++;
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> ASYNC LOOP END + " + name + " + >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }
}
