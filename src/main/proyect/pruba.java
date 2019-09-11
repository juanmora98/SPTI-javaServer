
import java.lang.*; 
import java.util.*; 
import java.io.*; 
import java.net.*;

static class StreamConnector extends Thread
{
        InputStream is;
        OutputStream os;

        StreamConnector(InputStream is, OutputStream os)
        {
                this.is = is;
                this.os = os;
        }

        public void run()
        {
                BufferedReader isr = null;
                BufferedWriter osw = null;
                try {
                        isr = new BufferedReader(new InputStreamReader(is));
                        osw = new BufferedWriter(new OutputStreamWriter(os));

                        char buffer[] = new char[8192];
                        int lenRead;

                        while( (lenRead = isr.read(buffer, 0, buffer.length)) > 0)
                        {
                                osw.write(buffer, 0, lenRead);
                                osw.flush();
                        }
                }
                catch (Exception ioe){}

                try {
                        if(isr != null) isr.close();
                        if(osw != null) osw.close();
                }
                catch (Exception ioe){}
        }
}