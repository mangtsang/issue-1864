Test project for [eclipse/jetty.project#1864](https://github.com/eclipse/jetty.project/issues/1864)
===

To build
---

```
$ mvn clean install
```

To run server:
---

```
$ java -jar target/demo.jar
2017-10-16 17:02:15.779:INFO::main: Logging initialized @644ms to org.eclipse.jetty.util.log.StdErrLog
2017-10-16 17:02:15.880:INFO:oejs.Server:main: jetty-9.4.7.v20170914
2017-10-16 17:02:15.931:INFO:oejs.session:main: DefaultSessionIdManager workerName=node0
2017-10-16 17:02:15.931:INFO:oejs.session:main: No SessionScavenger set, using defaults
2017-10-16 17:02:15.934:INFO:oejs.session:main: Scavenging every 660000ms
2017-10-16 17:02:15.951:INFO:oejsh.ContextHandler:main: Started o.e.j.s.ServletContextHandler@4c1d9d4b{/,file:///mnt/c/code/jetty/stackoverflow/issue-1864/webapps,AVAILABLE}
2017-10-16 17:02:15.979:INFO:oejs.AbstractConnector:main: Started ServerConnector@76908cc0{HTTP/1.1,[http/1.1]}{0.0.0.0:8090}
2017-10-16 17:02:15.979:INFO:oejs.Server:main: Started @850ms
```

To test large upload:
---------------------

**Command Line technique:**

In a new console window, upload a large file, the more MB's the better.

```
$ curl --verbose --data-binary "@20mb.dat" http://localhost:8090/upload
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 8090 (#0)
> POST /upload HTTP/1.1
> Host: localhost:8090
> User-Agent: curl/7.47.0
> Accept: */*
> Content-Length: 20772792
> Content-Type: application/x-www-form-urlencoded
> Expect: 100-continue
>
< HTTP/1.1 413 File: 19 MB. Refused the file.
< Date: Mon, 16 Oct 2017 17:07:07 GMT
< Cache-Control: must-revalidate,no-cache,no-store
< Content-Type: text/html;charset=iso-8859-1
< Content-Length: 364
< Connection: close
< Server: Jetty(9.4.7.v20170914)
<
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<title>Error 413 File: 19 MB. Refused the file.</title>
</head>
<body><h2>HTTP ERROR 413</h2>
<p>Problem accessing /upload. Reason:
<pre>    File: 19 MB. Refused the file.</pre></p><hr><a href="http://eclipse.org/jetty">Powered by Jetty:// 9.4.7.v20170914</a><hr/>

</body>
</html>
* Closing connection 0
```

**Browser technique:**

In a new browser window, open [http://localhost:8090/](http://localhost:8090/), 
select a file to upload (make it big), and hit "Upload" button.
