package org.eclipse.jetty.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Issue1864
{
    public static void main(String[] args) throws Exception {

        Server server = new Server(8090);  // http://127.0.0.1:8090/
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setResourceBase("webapp");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloWorld()), "/upload");
        context.addServlet(new ServletHolder(new DefaultServlet()), "/");
        server.start();
        server.join();
    }

    public static class HelloWorld extends HttpServlet
    {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            doPost(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException
        {
            resp.sendError(413, "File: " + req.getContentLength() / 1024 / 1024 + " MB. Refused the file.");
        }
    }
}
