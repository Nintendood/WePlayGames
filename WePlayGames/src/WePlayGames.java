    import java.io.*;
    import java.net.*;
    import java.util.*;

    /**
     * WePlayGames Class enables the user to set up a server on this machine
     */
    public final class WePlayGames
    {

        /**
         * Main: enables the user to run the server
         * @param argv
         * @throws Exception
         */
        public static void main(String argv[]) throws Exception
        {
            int port = 1337;

            // Establish the listen socket.
            ServerSocket welcomeSocket = new ServerSocket(port);
            
            ArrayList<Thread> clients = new ArrayList<>();

            // Process HTTP service requests in an infinite loop
            while (true)
            {
                // Listen for a TCP connection request.
                Socket connectionSocket = welcomeSocket.accept();
                DataOutputStream os = new DataOutputStream(connectionSocket.getOutputStream());
                
                os.writeBytes("Connected!");

                // Construct an object to process the HTTP request message.
                HttpRequest request = new HttpRequest(connectionSocket);

                // Create a new thread to process the request.
                Thread thread = new Thread(request);

                // Start the thread.
                thread.start();
                
                clients.add(thread);
                
                for (int i = 0; i < clients.size(); i++)
                {
                    // TODO: add reading and writing lgoic here
                }
                
                
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
