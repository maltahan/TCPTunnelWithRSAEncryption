In order to run this demo on Linux you should do the following:

1-	copy the two folders to your machine(RSATunnel,apache-tomcat-7.0.34). 
the RSATunnel contains the RSA algorithm implementation and how client and server contact with each other.
The other folder contains the endpoint to which the server will send the final decrypted data.
 
2-	Navigate to the place where the apache server is (/apache-tomcat-7.0.34/bin) and run the following command (sh startup.sh) 
in order to run the Apache server and see the result later.

3-	The java files are already compiled, but if you want to compile them, navigate to the src folder in the RSATunnel folder and run javac *.java.

4-	Open a terminal and navigate to the src folder in RSATunnel (RSATunnel/src) and run the command (java TCPServer) to run the server.

5-	Open another command line and navigate also to the src in RSATunnel and run the command (java TCPClient) 
to make the client listen to the message coming from the browser.

6-	Now open another terminal and type (telnet localhost 8050) and then type your message.
Note(the port 8050 is where the client listen to the coming message from the browser).

7-	And then we will see that the message is received  by the client, encrypted and sent to the server, 
the server will received it and decrypted it, and finally sent it the end point, the end point which is a webservice 
will replay that it has received the message and give you a url where you can see the results, 
or you can make curl request to it(like curl -s http://localhost:8040/myEndPointApp/rest/recive).
Note(you should install the last version of jdk). Like (1.8.0_111).
