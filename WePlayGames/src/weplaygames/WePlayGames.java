/*************************************************************************
 * Program:
 *    Lab Webserver, Computer Communication and Networking
 *    Brother Jones, CS 460
 * Author:
 *    John Decker
 * Summary:
 *    Enables the user to access the server through wget and telnet as well
 *   a browser to request items.
 ************************************************************************** */
/*** NOTE: The header needs to be changed for second submission (take 2) ***/
/*** NOTE: The header needs to be changed for second submission (take 2) ***/


import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Webservb Class enables the user to set up a server on this machine
 */
public final class WebServerb
{

    /**
     * Main: enables the user to run the server
     * @param argv
     * @throws Exception
     */
    public static void main(String argv[]) throws Exception
    {
        // sets the port number to be used (default: 6789; optional: argv[0])
        int port = argv.length > 0 ? Integer.parseInt(argv[0]) : 6789;

        // Establish the listen socket.
        ServerSocket welcomeSocket = new ServerSocket(port);

        // Process HTTP service requests in an infinite loop
        while (true)
        {
            // Listen for a TCP connection request.
            Socket connectionSocket = welcomeSocket.accept();

            // Construct an object to process the HTTP request message.
            HttpRequest request = new HttpRequest(connectionSocket);

            // Create a new thread to process the request.
            Thread thread = new Thread(request);

            // Start the thread.
            thread.start();
        }
    }
}

/**
 * HTTPRequest: enables the user to request information and data from this
 * server.
 */
final class HttpRequest implements Runnable
{
    final static String CRLF = "\r\n";
    Socket socket;

    // Constructor
    public HttpRequest(Socket socket) throws Exception
    {
        this.socket = socket;
    }

    // Implement the run() method of the Runnable interface.
    public void run()
    {
        try
        {
           //process request is now gone
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    /*************************************************************************
     * Program:
     *    Lab Webserver, Computer Communication and Networking
     *    Brother Jones, CS 460
     * Author:
     *    John Decker
     * Summary:
     *    Enables the user to access the server through wget and telnet as well
     *   a browser to request items.
     ************************************************************************** */
/*** NOTE: The header needs to be changed for second submission (take 2) ***/
/*** NOTE: The header needs to be changed for second submission (take 2) ***/


    import java.io.*;
    import java.net.*;
    import java.util.*;

    /**
     * Webservb Class enables the user to set up a server on this machine
     */
    public final class WebServerb
    {

        /**
         * Main: enables the user to run the server
         * @param argv
         * @throws Exception
         */
        public static void main(String argv[]) throws Exception
        {
            // sets the port number to be used (default: 6789; optional: argv[0])
            int port = argv.length > 0 ? Integer.parseInt(argv[0]) : 6789;

            // Establish the listen socket.
            ServerSocket welcomeSocket = new ServerSocket(port);

            // Process HTTP service requests in an infinite loop
            while (true)
            {
                // Listen for a TCP connection request.
                Socket connectionSocket = welcomeSocket.accept();

                // Construct an object to process the HTTP request message.
                HttpRequest request = new HttpRequest(connectionSocket);

                // Create a new thread to process the request.
                Thread thread = new Thread(request);

                // Start the thread.
                thread.start();
            }
        }
    }

    /**
     * HTTPRequest: enables the user to request information and data from this
     * server.
     */
    final class HttpRequest implements Runnable
    {
        final static String CRLF = "\r\n";
        Socket socket;

        // Constructor
        public HttpRequest(Socket socket) throws Exception
        {
            this.socket = socket;
        }

        // Implement the run() method of the Runnable interface.
        public void run()
        {
            try
            {
                processRequest();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }

        /**
         * processRequest():
         * this will process the request that is given to the server
         * @throws Exception
         */
        private void processRequest() throws Exception
        {
            // Get a reference to the socket's input and output streams.
            InputStream is = socket.getInputStream();
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());

            // Set up input stream filters.
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // Get the request line of the HTTP request message.
            String requestLine = br.readLine();


            // Send the status line.
            os.writeBytes(statusLine);

            // Send the content type line.
            os.writeBytes(contentTypeLine);

            // Send a blank line to indicate the end of the header lines.
            os.writeBytes(CRLF);

            // Send the entity body.
            if (fileExists)
            {
                sendBytes(fis, os);
                fis.close();
            }
            else
            {
                os.writeBytes(entityBody);
            }

            // Close streams and socket.
            os.close();
            br.close();
            socket.close();
        }

        /**
         * sendBytes():
         * will input a stream and output information given
         * @param fis
         * @param os
         * @throws Exception
         */
        private static void sendBytes(FileInputStream fis, OutputStream os)
                throws Exception
        {
            // Construct a 1K buffer to hold bytes on their way to the socket.
            byte[] buffer = new byte[1024];
            int bytes = 0;

            // Copy requested file into the socket's output stream.
            while((bytes = fis.read(buffer)) != -1)
            {
                os.write(buffer, 0, bytes);
            }
        }
        
    }

}
