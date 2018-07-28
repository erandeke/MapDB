import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WriterToDisc {


    public static void main(String args[])
    {
        try {
            WriterToDisc.writeToDisc();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static  void  writeToDisc() throws IOException {

        File file=new File("E:\\MapStorage\\Store.txt");
        DB db=DBMaker.newFileDB(file).closeOnJvmShutdown().make();
        final String chromaPrintStore;
        ConcurrentMap<String,String> map=db.createHashMap("chroma")
                 .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.STRING)
                .keepCounter(true)
                .makeOrGet();
        for(int i=0;i<90000;i++)
        {
          //map.put("Key"+i,"Value"+i);
           System.out.println( map.get("Key"+i));
        }

       db.commit();
        db.close();





    }
}
