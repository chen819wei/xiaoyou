package com.xiaoyou.servlet;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet(name = "SendEmail")
public class SendEmail extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        // �ռ��˵ĵ����ʼ� ID
        String to = "chen819wei@qq.com";

        // �����˵ĵ����ʼ� ID
        String from = "chen819wei@gmail.com";

        // �������Ǵӱ����������͵����ʼ�
        String host = "localhost";

        // ��ȡϵͳ������
        Properties properties = System.getProperties();

        // �����ʼ�������
        properties.setProperty("mail.smtp.host", host);

        // ��ȡĬ�ϵ� Session ����
        Session session = Session.getDefaultInstance(properties);

        // ������Ӧ��������
        response.setContentType("text/html;charset=UTF-8");

        try {
            // ����һ��Ĭ�ϵ� MimeMessage ����
            MimeMessage message = new MimeMessage(session);
            // ���� From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // ���� To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            // ���� Subject: header field
            message.setSubject("This is the Subject Line!");
            // ��������ʵ����Ϣ
            message.setText("This is actual message");
            // ������Ϣ
            Transport.send(message);
            String title = "���͵����ʼ�";
            String res = "�ɹ�������Ϣ...";
            String docType = "<!DOCTYPE html> \n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<p align=\"center\">" + res + "</p>\n" +
                    "</body></html>");
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
}